package vee.apps.publicholidays;

import android.app.Activity;
import android.app.Application;
import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Locale;

/**
 * Created by binaryvi on 06/05/2016.
 */
public class MyTimePicker {

    Context mContext;
    Calendar myCalendar = Calendar.getInstance();
    Button btnTime;
    public int mYear;
    public int mMonthOfYear;
    public int mDayOfMonth;
    int Year;
    int MonthOfYear;
    int DayOfMonth;

    public MyTimePicker(Context ctx, Button btn){

        this.mContext = ctx;
        btnTime = btn;
    }

    public void getDialog(){
        Calendar now = Calendar.getInstance();
        Year = myCalendar.get(Calendar.YEAR);
        MonthOfYear = myCalendar.get(Calendar.MONTH);
        DayOfMonth = myCalendar.get(Calendar.DAY_OF_MONTH);

        try{
            DatePickerDialog mdiDialog =new DatePickerDialog(mContext,new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    mYear = year;
                    mMonthOfYear = monthOfYear + 1;
                    mDayOfMonth = dayOfMonth;

                    btnTime.setText(getMonth(mMonthOfYear) + String.valueOf(mYear));
                }
            }, Year, MonthOfYear, DayOfMonth);
            mdiDialog.show();

        }catch (Exception e){
        }
    }

    public String getMonth(int month){
        String monthStr = "";
        switch (month){
            case 1:
                monthStr = "Jan   ";
                break;

            case 2:
                monthStr = "Feb   ";
                break;

            case 3:
                monthStr = "Mar   ";
                break;

            case 4:
                monthStr = "Apr   ";
                break;

            case 5:
                monthStr = "May   ";
                break;

            case 6:
                monthStr = "Jun   ";
                break;

            case 7:
                monthStr = "Jul   ";
                break;

            case 8:
                monthStr = "Aug   ";
                break;

            case 9:
                monthStr = "Sep   ";
                break;

            case 10:
                monthStr = "Oct   ";
                break;

            case 11:
                monthStr = "Nov   ";
                break;

            case 12:
                monthStr = "Dec   ";
                break;
        }
        return monthStr;
    }
}
