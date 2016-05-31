package vee.apps.myweatherapp.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by binaryvi on 30/04/2016.
 */
public class ApiService {

    public static String API_KEY = "7ba562d6d351de151a134d660652ff41";
    public static String BASE_URL = "http://api.openweathermap.org";
    public static String BASE_PATH = "/data/2.5";

    public static <S> S createService(Class<S> serviceClass){
        final OkHttpClient okhttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();
        /*okhttpClient.networkInterceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder()
                        .addHeader("Accept","application/json").build();
                return chain.proceed(request);
            }
        });*/

        Gson gson = new GsonBuilder().create();

        Retrofit builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okhttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return builder.create(serviceClass);
    }

}
