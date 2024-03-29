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

    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cijfers);

        textView = findViewById(R.id.textView);
        Button returnbtn = findViewById(R.id.back2);

        ConstraintLayout backgroundLayout = findViewById(R.id.cijferview);
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String myString = ((SharedPreferences) sharedPreferences).getString("hexCode", "");
        String myString2 = ((SharedPreferences) sharedPreferences).getString("hexCode2", "");
        if (myString != null) {
            int color = Color.parseColor(myString);
            int color2 = Color.parseColor(myString2);
            returnbtn.setBackgroundColor(color2);
            backgroundLayout.setBackgroundColor(color);
        }

        DoRequest request = new DoRequest();
        request.getMark("api/VWO1/Tuinboon/cijfers", textView);



        returnbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cijfers.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
