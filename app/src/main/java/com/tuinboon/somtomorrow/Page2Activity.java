package com.tuinboon.somtomorrow;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class Page2Activity extends AppCompatActivity {

    public interface MyApi {

        @POST("path/to/api")
        Call<ApiResponse> sendData(@Body RequestBody data);

    }

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://myapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    MyApi myApi = retrofit.create(MyApi.class);

    String jsonData = "{\"name\":\"John\", \"email\":\"john@example.com\"}";
    RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonData);

    Call<ApiResponse> call = myApi.sendData(requestBody);

    call.enqueue(new Callback<ApiResponse>() {
        @Override
        public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
            // Handle the response from the server
        }

        @Override
        public void onFailure(Call<ApiResponse> call, Throwable t) {
            // Handle any errors that occurred during the API call
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docent);
    }
}

