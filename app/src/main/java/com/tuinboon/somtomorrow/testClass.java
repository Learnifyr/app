package com.tuinboon.somtomorrow;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class testClass extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        TextView text = findViewById(R.id.textView6);

        DoRequest request = new DoRequest();
        //request.test("test", text);
    }
}
