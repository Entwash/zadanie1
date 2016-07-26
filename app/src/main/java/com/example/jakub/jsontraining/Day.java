package com.example.jakub.jsontraining;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jakub on 25.07.2016.
 */
public class Day implements Parcelable{
    String app;
    String name;
    String plan;
    String topic;

    public Day(String app, String name, String plan, String topic) {
        this.app = app;
        this.name = name;
        this.plan = plan;
        this.topic = topic;
    }

    protected Day(Parcel in) {
        app = in.readString();
        name = in.readString();
        plan = in.readString();
        topic = in.readString();
    }

    public static final Creator<Day> CREATOR = new Creator<Day>() {
        @Override
        public Day createFromParcel(Parcel in) {
            return new Day(in);
        }

        @Override
        public Day[] newArray(int size) {
            return new Day[size];
        }
    };

    public String getApp() {
        return app;
    }

    public String getName() {
        return name;
    }

    public String getPlan() {
        return plan;
    }

    public String getTopic() {
        return topic;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(app);
        parcel.writeString(name);
        parcel.writeString(plan);
        parcel.writeString(topic);
    }
}
