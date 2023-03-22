package com.tuinboon.somtomorrow;
import com.tuinboon.somtomorrow.Huiswerk;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {

    public boolean trigger = true;

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DoRequest request = new DoRequest();

        Button button1 = findViewById(R.id.button1);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);

        CardView myCardView = findViewById(R.id.menubar);
        Button menubutton = findViewById(R.id.menubutton);

        textView = findViewById(R.id.textView3);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String token = ((SharedPreferences) sharedPreferences).getString("token", "a");
        Log.d("test", token);
        request.PushRequest("token", token, getBaseContext());

        request.DoNormalRequest("api/VWO1/news", textView);


        ConstraintLayout backgroundLayout = findViewById(R.id.mainview);
        String myString = ((SharedPreferences) sharedPreferences).getString("hexCode", "#000000");
        String myString2 = ((SharedPreferences) sharedPreferences).getString("hexCode2", "#000000");
        if (myString != null && myString2 != null) {
            int color = Color.parseColor(myString);
            int color2 = Color.parseColor(myString2);
            myCardView.setBackgroundTintList(ColorStateList.valueOf(color2));
            menubutton.setBackgroundColor(color2);
            backgroundLayout.setBackgroundColor(color);
        }


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Dev.class);
                startActivity(intent);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Ouders.class);
                startActivity(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Huiswerk.class);
                startActivity(intent);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Cijfers.class);
                startActivity(intent);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Docent.class);
                startActivity(intent);
            }
        });


        menubutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (trigger) {
                    Log.d("test", "bericht");
                    myCardView.setVisibility(View.VISIBLE);
                    Log.d("test", "bericht2");
                    trigger = false;
                } else {
                    myCardView.setVisibility(View.GONE);
                    trigger = true;
                }
            }
        });

    }
}