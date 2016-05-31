package vee.apps.publicholidays.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import vee.apps.publicholidays.api.response.Holidays;

/**
 * Created by binaryvi on 05/05/2016.
 */
public interface ApiClient {

    @GET("/v1/holidays")
    Call<Holidays> getHolidays(@Query("country")String country,
                               @Query("year")String Year,
                               @Query("month")String Month);

}
