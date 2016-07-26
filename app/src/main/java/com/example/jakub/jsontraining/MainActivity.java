package com.example.jakub.jsontraining;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class MainActivity extends AppCompatActivity implements DayFragment.OnListFragmentInteractionListener {

    DayService service;

    Button trigger;

    DayFragment fragment;

    ArrayList<Day> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trigger = (Button) findViewById(R.id.button);
        getDays();


//        trigger.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getAgendaData();
//                DayFragment.newInstance(data);
//            }
//        });
    }


    public void getAgendaData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://bootcampagenda.firebaseio.com")
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        service = retrofit.create(DayService.class);

    }

    public void getDays() {

        getAgendaData();

        final Response<List<Day>> response;

        service.getJSON().enqueue(new Callback<List<Day>>() {
            @Override
            public void onResponse(Call<List<Day>> call, Response<List<Day>> response) {
                fragment = DayFragment.newInstance((ArrayList<Day>) response.body());

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, fragment).commit();
            }

            @Override
            public void onFailure(Call<List<Day>> call, Throwable t) {
                System.out.println(""); //przy pobieraniu dostaje obiekt a nie arraya
            }
        });
    }


    public Day parseJSONWithMoshi(final String JSON) {
        final Moshi moshi = new Moshi.Builder().build();
        final JsonAdapter<Day> jsonAdapter = moshi.adapter(Day.class);

        Day day = null;
        try {
            day = jsonAdapter.fromJson(JSON);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return day;
    }

    public String serializeJSONWithMoshi(final Day day) {
        final Moshi moshi = new Moshi.Builder().build();
        final JsonAdapter<Day> jsonAdapter = moshi.adapter(Day.class);

        String dayJSON = jsonAdapter.toJson(day);

        return dayJSON;
    }


    @Override
    public void onListFragmentInteraction(Day item) {

    }
}
