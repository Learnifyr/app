package com.tuinboon.somtomorrow;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class Huiswerk extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huiswerk);

        TextView textView = findViewById(R.id.textView4);

        Button returnbtn = findViewById(R.id.back3);

        DoRequest request = new DoRequest();
        request.getHomework("api/VWO1/huiswerk", textView);

        ConstraintLayout backgroundLayout = findViewById(R.id.huiswerkview);
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String myString = ((SharedPreferences) sharedPreferences).getString("hexCode", "");
        String myString2 = ((SharedPreferences) sharedPreferences).getString("hexCode2", "");
        if (myString != null) {
            int color = Color.parseColor(myString);
            int color2 = Color.parseColor(myString2);
            returnbtn.setBackgroundColor(color2);
            backgroundLayout.setBackgroundColor(color);
        }

        returnbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Huiswerk.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
