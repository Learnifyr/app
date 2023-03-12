package com.tuinboon.somtomorrow;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Url;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://cca6-204-168-129-182.eu.ngrok.io/";
    private TextView textView;
    private String endpoint = "api/VWO%201/huiswerk";

    public interface MyService {
        @GET
        Call<List<MyObject>> getList(@Url String url);
    }

    private final MyService mService;

    public MainActivity() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mService = retrofit.create(MyService.class);
    }

    public void getList(String endpoint, Callback<List<MyObject>> callback) {
        Call<List<MyObject>> call = mService.getList(BASE_URL + endpoint);
        call.enqueue(callback);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        getList(endpoint, new Callback<List<MyObject>>() {
            @Override
            public void onResponse(Call<List<MyObject>> call, Response<List<MyObject>> response) {
                if (response.isSuccessful()) {
                    List<MyObject> objects = response.body();
                    StringBuilder sb = new StringBuilder();
                    for (MyObject object : objects) {
                        sb.append(object.getSubject() + ": " + object.getDo()).append("\n");
                    }
                    textView.setText(sb);
                } else {
                    textView.setText("Error: " + response.code() + " " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<MyObject>> call, Throwable t) {
                textView.setText("Error: " + t.getMessage());
            }
        });
    }
}
