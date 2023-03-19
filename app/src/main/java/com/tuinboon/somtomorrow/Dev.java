package com.tuinboon.somtomorrow;

import android.content.Intent;
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

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hexCode = color1.getText().toString();
            }
        });
    }
}
