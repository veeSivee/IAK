package vee.apps.myweatherapp.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import vee.apps.myweatherapp.api.response.Weather;

/**
 * Created by binaryvi on 30/04/2016.
 */
public interface ApiClient {
    @GET("/data/2.5/weather")
    Call<Weather> getWeather(@Query("q")String q,
                             @Query("appid") String appId);

}
