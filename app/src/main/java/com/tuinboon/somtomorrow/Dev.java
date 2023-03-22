package com.tuinboon.somtomorrow;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dev extends AppCompatActivity {
    List<Integer> keys = new ArrayList<Integer>();
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
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d("test", String.valueOf(keyCode));
        keys.add(keyCode);
        List<Integer> code = Arrays.asList(24, 24, 25, 25, 4);
        Log.d(keys.toString(), code.toString());
        if (keys.equals(code)) {
            Log.d("MATCH", "MATCH");
            Intent intent = new Intent(Dev.this, testClass.class);
            startActivity(intent);

        }
        return true;
    }
}
