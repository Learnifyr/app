package com.tuinboon.somtomorrow;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText username = findViewById(R.id.usernametext);
        EditText password = findViewById(R.id.passwordtext);

        Button confirmbtn = findViewById(R.id.confirmbtn);

        TextView testView = findViewById(R.id.textView7);

        DoRequest request = new DoRequest();

        confirmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = String.valueOf(username.getText());
                String pass = String.valueOf(password.getText());
                if (user != null && pass != null) {
                    request.test("test", testView, user, pass, getBaseContext());
                }
            }
        });
    }
}
