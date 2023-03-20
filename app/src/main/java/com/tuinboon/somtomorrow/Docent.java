package com.tuinboon.somtomorrow;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class Docent extends AppCompatActivity {
    interface RequestUser{
        @GET("api/post/cijfers/VWO%201/Tuinbon/{uid}")
        Call<MyObject> getUser(@Path("uid") String uid);

    }

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docent);

        textView = findViewById(R.id.textView2);
        Button myButton = findViewById(R.id.submitbutton);
        Button returnbtn = findViewById(R.id.back);



        ConstraintLayout backgroundLayout = findViewById(R.id.docentview);
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String myString = ((SharedPreferences) sharedPreferences).getString("hexCode", "");
        String myString2 = ((SharedPreferences) sharedPreferences).getString("hexCode2", "");
        if (myString != null) {
            int color = Color.parseColor(myString);
            int color2 = Color.parseColor(myString2);
            returnbtn.setBackgroundColor(color2);
            myButton.setBackgroundColor(color2);
            backgroundLayout.setBackgroundColor(color);
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://409f-204-168-129-182.eu.ngrok.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        RequestUser requestUser = retrofit.create(RequestUser.class);



        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText myEditText = findViewById(R.id.editText);
                String text = myEditText.getText().toString();
                requestUser.getUser(text).enqueue(new Callback<MyObject>() {
                    @Override

                    public void onResponse(Call<MyObject> call, Response<MyObject> response) {
                        textView.setText("Changed mark to "+text);
                    }

                    @Override
                    public void onFailure(Call<MyObject> call, Throwable t) {
                        //textView.setText(t.getMessage());
                    }
                });
            }
        });



        returnbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Docent.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
