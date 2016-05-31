package vee.apps.publicholidays.api.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by binaryvi on 05/05/2016.
 */
public class HolidaysItem {

    @SerializedName("name")
    private String name;

    @SerializedName("country")
    private String country;

    @SerializedName("date")
    private String date;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
