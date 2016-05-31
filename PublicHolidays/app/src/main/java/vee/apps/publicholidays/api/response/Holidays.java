package vee.apps.publicholidays.api.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by binaryvi on 05/05/2016.
 */
public class Holidays {
    @SerializedName("holidays")
    private ArrayList<HolidaysItem> listHolidays = new ArrayList<>();

    @SerializedName("status")
    private int status;

    public ArrayList<HolidaysItem> getListHolidays() {
        return listHolidays;
    }

    public void setListHolidays(ArrayList<HolidaysItem> listHolidays) {
        this.listHolidays = listHolidays;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
