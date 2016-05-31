package vee.apps.myweatherapp;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import vee.apps.myweatherapp.api.request.WeatherRequest;
import vee.apps.myweatherapp.api.response.Weather;

public class MainActivity extends AppCompatActivity implements WeatherRequest.OnWeatherRequestListener{

    @BindView(R.id.tv_city)
    TextView tvCity;

    @BindView(R.id.tv_description)
    TextView tvDescription;

    @BindView(R.id.tv_humidity)
    TextView tvHumidity;

    @BindView(R.id.tv_main)
    TextView tvMain;

    @BindView(R.id.tv_temp)
    TextView tvTemp;

    private ProgressDialog progressDialog;
    private WeatherRequest mWeatherRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mWeatherRequest = new WeatherRequest(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Harap Tunggu...");
        progressDialog.show();

        String city = "London,uk";
        mWeatherRequest.callApi(city);

        /*try{
        }catch (Exception e){
            tvCity.setText(e.getMessage());
            Toast.makeText(MainActivity.this,e.toString(),Toast.LENGTH_SHORT).show();
        }*/

    }

    @Override
    public void onRequestWeatherSuccess(Weather weatherResponse) {
        progressDialog.dismiss();
        tvCity.setText(weatherResponse.getCity());
        tvDescription.setText(weatherResponse.getListweather().get(0).getDescription());
        double degrees = weatherResponse.getWeathermain().getTemp() - 273;
        tvTemp.setText(degrees + "  C");
        tvHumidity.setText(weatherResponse.getWeathermain().getHumidity() + "");
        tvMain.setText(weatherResponse.getListweather().get(0).getMain());
    }

    @Override
    public void onRequestWeatherFailure(String message) {

    }
}
