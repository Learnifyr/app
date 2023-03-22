package com.tuinboon.somtomorrow;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class testClass extends AppCompatActivity {
    public MyObject data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        TextView text = findViewById(R.id.textView6);

        DoRequest request = new DoRequest();
        request.hi(new DoRequest.MyObjectCallback() {
            @Override
            public void onSuccess(MyObject result) {
                data = result;
                Log.d("LMAO", String.valueOf(data.response));
            }

            @Override
            public void onError(Throwable error) {
                // handle the error here
            }
        });
    }
}
