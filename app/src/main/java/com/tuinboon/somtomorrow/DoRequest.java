package com.tuinboon.somtomorrow;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Url;

public class DoRequest {
    final String BASE_URL = "https://b328-204-168-129-182.eu.ngrok.io/";
    public String user;
    public String pass;
    public ArrayList myList;
    public void DoNormalRequest(String endpoint, TextView textView) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestUser requestUser = retrofit.create(RequestUser.class);
        Call<MyObject> call = requestUser.getUser(endpoint);
        call.enqueue(new Callback<MyObject>() {
            @Override
            public void onResponse(Call<MyObject> call, Response<MyObject> response) {
                if (response.isSuccessful()) {
                    MyObject myObject = response.body();
                    if (myObject != null && myObject.getData() != null) {
                        textView.setText(myObject.getData().getNews());
                    } else {
                        textView.setText("Response is empty");
                    }
                } else {
                    textView.setText("Request failed with code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<MyObject> call, Throwable t) {
                textView.setText("Error getting data: " + t.getMessage());
            }
        });
    }

    public interface RequestUser {
        @GET("{endpoint}")
        Call<MyObject> getUser(@Path("endpoint") String endpoint);
    }



    public void getHomework(String endpoint, TextView textView) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyService myService = retrofit.create(MyService.class);
        Call<List<MyObject>> call = myService.getList(BASE_URL + endpoint);
        call.enqueue(new Callback<List<MyObject>>() {
            @Override
            public void onResponse(Call<List<MyObject>> call, Response<List<MyObject>> response) {
                if (response.isSuccessful()) {
                    List<MyObject> objects = response.body();
                    StringBuilder sb = new StringBuilder();
                    for (MyObject object : objects) {
                        sb.append(object.subject).append(": ").append(object.dothis).append("\n");
                    }
                    textView.setText(sb.toString());
                } else {
                    textView.setText("Error: " + response.code() + " " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<MyObject>> call, Throwable t) {
                textView.setText("Error getting data: " + t.getMessage());
            }
        });
    }

    public interface MyService {
        @GET
        Call<List<MyObject>> getList(@Url String url);
    }


    public void getMark(String endpoint, TextView textView) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyService2 myService = retrofit.create(MyService2.class);
        Call<List<MyObject>> call = myService.getList(BASE_URL + endpoint);
        call.enqueue(new Callback<List<MyObject>>() {
            @Override
            public void onResponse(Call<List<MyObject>> call, Response<List<MyObject>> response) {
                if (response.isSuccessful()) {
                    List<MyObject> objects = response.body();
                    StringBuilder sb = new StringBuilder();
                    ArrayList<String> myList = new ArrayList<>();
                    for (MyObject object : objects) {
                        myList.add(object.subject);
                        sb.append(object.subject).append(": ").append(object.mark).append("\n");
                    }
                    System.out.println(myList.get(0));
                    textView.setText(sb.toString());
                } else {
                    textView.setText("Error: " + response.code() + " " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<MyObject>> call, Throwable t) {
                textView.setText("Error getting data: " + t.getMessage());
            }
        });
    }

    public interface MyService2 {
        @GET
        Call<List<MyObject>> getList(@Url String url);
    }



    public void test(String endpoint, TextView textView, String username, String password, Context context) {
        user = username;
        pass = password;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        test requestUser = retrofit.create(test.class);
        Call<MyObject> call = requestUser.getList(username, password, BASE_URL + endpoint);
        call.enqueue(new Callback<MyObject>() {
            @Override
            public void onResponse(Call<MyObject> call, Response<MyObject> response) {
                if (response.isSuccessful()) {
                    MyObject myObject = response.body();
                    if (myObject != null && myObject.token != null) {
                        Log.d("test", myObject.token);
                        if ("No valid credentials".equals(myObject.token)) {
                            textView.setText(myObject.token);
                        } else {
                            SharedPreferences sharedPreferences = context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("token", myObject.token);
                            editor.apply();
                            Intent newIntent = new Intent(context, MainActivity.class);
                            newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(newIntent);
                        }
                    } else {
                        textView.setText("Response is empty");
                    }
                } else {
                    textView.setText("Request failed with code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<MyObject> call, Throwable t) {
                textView.setText("Error getting data: " + t.getMessage());
            }
        });
    }

    public interface test {
        @GET()
        Call<MyObject> getList(@Header("username") String user, @Header("password") String pass, @Url String url);
    }
}
