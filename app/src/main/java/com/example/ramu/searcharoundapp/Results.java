package com.example.ramu.searcharoundapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ramu on 1/21/2017.
 */

public class Results implements Parcelable {
    String title;
    String address;
    String city;
    String state;
    String phone;
    double latitude;
    double longitude;
    String diatance;
    String businessURL;

    Results(String title, String address, String city, String state,
            String phone, double latitude, double longitude, String diatance, String businessURL){
        this.title = title;
        this.address = address;
        this.city = city;
        this.state = state;
        this.phone = phone;
        this.latitude = latitude;
        this.longitude = longitude;
        this.diatance = diatance;
        this.businessURL = businessURL;
    }

    protected Results(Parcel in) {
        title = in.readString();
        address = in.readString();
        city = in.readString();
        state = in.readString();
        phone = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
        diatance = in.readString();
        businessURL = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(address);
        dest.writeString(city);
        dest.writeString(state);
        dest.writeString(phone);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeString(diatance);
        dest.writeString(businessURL);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Results> CREATOR = new Parcelable.Creator<Results>() {
        @Override
        public Results createFromParcel(Parcel in) {
            return new Results(in);
        }

        @Override
        public Results[] newArray(int size) {
            return new Results[size];
        }
    };
}