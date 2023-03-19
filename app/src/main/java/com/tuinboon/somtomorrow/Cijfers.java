package com.tuinboon.somtomorrow;

import com.tuinboon.somtomorrow.Dev;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Url;

public class Cijfers extends AppCompatActivity {



    private static final String BASE_URL = "https://409f-204-168-129-182.eu.ngrok.io/";
    private TextView textView;
    private String endpoint = "api/VWO%201/Tuinboon/cijfers";

    public interface MyService {
        @GET
        Call<List<MyObject>> getList(@Url String url);
    }

    private final MyService mService;

    public Cijfers() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mService = retrofit.create(MyService.class);
    }

    public void getList(String endpoint, Callback<List<MyObject>> callback) {
        Call<List<MyObject>> call = mService.getList(BASE_URL + endpoint);
        call.enqueue(callback);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cijfers);

        textView = findViewById(R.id.textView);

        ConstraintLayout backgroundLayout = findViewById(R.id.cijferview);
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String myString = ((SharedPreferences) sharedPreferences).getString("hexCode", "");
        if (myString != null) {
            int color = Color.parseColor(myString);
            backgroundLayout.setBackgroundColor(color);
        }

        getList(endpoint, new Callback<List<MyObject>>() {
            @Override
            public void onResponse(Call<List<MyObject>> call, Response<List<MyObject>> response) {
                if (response.isSuccessful()) {
                    List<MyObject> objects = response.body();
                    StringBuilder sb = new StringBuilder();
                    for (MyObject object : objects) {
                        Log.d("epic", object.toString());
                        sb.append(object.subject + ": " + object.mark).append("\n");
                    }
                    textView.setText(sb);
                } else {
                    textView.setText("Error: " + response.code() + " " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<MyObject>> call, Throwable t) {
                textView.setText("Error getting data please check for any errors");
            }
        });

        Button returnbtn = findViewById(R.id.back2);

        returnbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cijfers.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
