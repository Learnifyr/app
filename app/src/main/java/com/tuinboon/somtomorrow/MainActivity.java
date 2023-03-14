package com.tuinboon.somtomorrow;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class MainActivity extends AppCompatActivity {

    interface RequestUser {
        @GET("api/VWO%201/{uid}")
        Call<MyObject> getUser(@Path("uid") String uid);
    }
    public boolean trigger = true;

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);

        CardView myCardView = findViewById(R.id.menubar);
        Button button1 = findViewById(R.id.menubutton);


        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Cijfers.class);
                startActivity(intent);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Docent.class);
                startActivity(intent);
            }
        });


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (trigger) {
                    Log.d("test", "bericht");
                    myCardView.setVisibility(View.VISIBLE);
                    Log.d("test", "bericht2");
                    trigger = false;
                } else {
                    myCardView.setVisibility(View.GONE);
                    trigger = true;
                }
            }
        });

        textView = findViewById(R.id.textView3);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://409f-204-168-129-182.eu.ngrok.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestUser requestUser = retrofit.create(RequestUser.class);

        requestUser.getUser("news").enqueue(new Callback<MyObject>() {
            @Override
            public void onResponse(Call<MyObject> call, Response<MyObject> response) {
                textView.setText(response.body().data.getNews());
            }

            @Override
            public void onFailure(Call<MyObject> call, Throwable t) {
                textView.setText(t.getMessage());
                //eee
            }
        });
    }
}