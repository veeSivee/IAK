package vee.apps.publicholidays.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by binaryvi on 05/05/2016.
 */
public class ApiService {


    public static String API_KEY = "";
    public static String BASE_URL = "http://holidayapi.com";
    public static String BASE_PATH = "apiecommerce/public/index.php/list/";

    public static <S> S createService(Class<S> serviceClass){
        final OkHttpClient okhttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();

        Gson gson = new GsonBuilder().create();

        Retrofit builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okhttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return builder.create(serviceClass);
    }

}
