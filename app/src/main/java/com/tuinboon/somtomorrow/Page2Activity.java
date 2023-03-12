package com.tuinboon.somtomorrow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

public class Page2Activity extends AppCompatActivity {
    interface RequestUser{
        @GET("api/post/cijfers/VWO%201/Tuinbon/{uid}")
        Call<MyObject> getUser(@Path("uid") String uid);

    }

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://409f-204-168-129-182.eu.ngrok.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        RequestUser requestUser = retrofit.create(RequestUser.class);

        requestUser.getUser("3").enqueue(new Callback<MyObject>() {
            @Override

            public void onResponse(Call<MyObject> call, Response<MyObject> response) {
                textView.setText(response.body().data.first_name);
            }

            @Override
            public void onFailure(Call<MyObject> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }
}
