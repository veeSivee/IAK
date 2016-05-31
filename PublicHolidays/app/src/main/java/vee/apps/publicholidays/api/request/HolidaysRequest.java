package vee.apps.publicholidays.api.request;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vee.apps.publicholidays.api.ApiClient;
import vee.apps.publicholidays.api.ApiService;
import vee.apps.publicholidays.api.response.Holidays;

/**
 * Created by binaryvi on 05/05/2016.
 */
public class HolidaysRequest {

    private ApiClient apiClient;
    private Call<Holidays> request;
    private OnHolidaysRequestListener listener;

    public HolidaysRequest(OnHolidaysRequestListener listener){
        apiClient = ApiService.createService(ApiClient.class);
        this.listener = listener;
    }

    public void callApi(String country, String Year, String Month){
        request = apiClient.getHolidays(country,Year,Month);
        request.enqueue(new Callback<Holidays>() {
            @Override
            public void onResponse(Call<Holidays> call, Response<Holidays> response) {
                if(response!=null && response.isSuccessful()){
                    Holidays mHolidays = response.body();
                    listener.onRequestHolidaysSuccess(mHolidays);
                }else{
                    listener.onRequestHolidaysFailure("Response Invalid");
                }
            }

            @Override
            public void onFailure(Call<Holidays> call, Throwable t) {
                String errorMessage = t.getMessage() !=null?
                        t.getMessage() : "Beli paket data dulu gih";
                listener.onRequestHolidaysFailure(errorMessage);
            }
        });
    }

    public void cancelApi(){
        if(request!=null){
            request.cancel();
        }
    }

    public interface OnHolidaysRequestListener{
        void onRequestHolidaysSuccess(Holidays holidaysResponse);
        void onRequestHolidaysFailure(String message);
    }

}
