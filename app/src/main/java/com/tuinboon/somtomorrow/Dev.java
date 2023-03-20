package com.tuinboon.somtomorrow;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Dev extends AppCompatActivity {
    public static String hexCode;
    public String text2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dev);

        EditText color1 = findViewById(R.id.color1);
        EditText color2 = findViewById(R.id.color2);

        Button submit = findViewById(R.id.submit2);
        Button backbtn = findViewById(R.id.backbtn);
        Button defaultbtn = findViewById(R.id.defaultbtn);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("hexCode", color1.getText().toString());
                editor.putString("hexCode2", color2.getText().toString());
                editor.apply();
            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dev.this, MainActivity.class);
                startActivity(intent);
            }
        });

        defaultbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("hexCode", "#3F5267");
                editor.putString("hexCode2", "#576271");
                editor.apply();
            }
        });
    }
}
