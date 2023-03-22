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

    public Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    PostThing requestUser = retrofit.create(PostThing.class);
    public void DoNormalRequest(String endpoint, TextView textView) {
        requestUser.getUser(endpoint).enqueue(new Callback<MyObject>() {
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




    public void getHomework(String endpoint, TextView textView) {

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



    public void getMark(String endpoint, TextView textView) {

        MyService myService = retrofit.create(MyService.class);
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



    public void test(String endpoint, String username, String password, Context context) {
        user = username;
        pass = password;

        test requestUser = retrofit.create(test.class);
        Call<MyObject> call = requestUser.getList(username, password, BASE_URL + endpoint);
        call.enqueue(new Callback<MyObject>() {
            @Override
            public void onResponse(Call<MyObject> call, Response<MyObject> response) {
                if (response.isSuccessful()) {
                    MyObject myObject = response.body();
                    if (myObject != null && myObject.token != null) {
                        if ("No valid credentials".equals(myObject.token)) {
                            Log.d("test",myObject.token);
                        } else {
                            SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("token", myObject.token);
                            editor.apply();
                            Intent newIntent = new Intent(context, MainActivity.class);
                            newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(newIntent);
                        }
                    } else {
                        Log.d("test","Response is empty");
                    }
                } else {
                    Log.d("test","Request failed with code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<MyObject> call, Throwable t) {
                Log.d("test","Error getting data: " + t.getMessage());
            }
        });
    }


    public void PushRequest(String endpoint, String input, Context context) {



        PostThing requestUser = retrofit.create(PostThing.class);
        Log.d("test", endpoint+"/"+input);
        requestUser.getUser(endpoint+"/"+input).enqueue(new Callback<MyObject>() {
            @Override

            public void onResponse(Call<MyObject> call, Response<MyObject> response) {
                MyObject myObject = response.body();
                if ("Token valid".equals(myObject.response)) {
                    Log.d("test", String.valueOf(myObject.response));
                } else {
                    Intent newIntent = new Intent(context, Login.class);
                    newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(newIntent);
                }
            }

            @Override
            public void onFailure(Call<MyObject> call, Throwable t) {
                //textView.setText(t.getMessage());
            }
        });
    }



    public void hi(MyObjectCallback callback) {
        Help requestUser = retrofit.create(Help.class);
        Call<MyObject> call = requestUser.getUser("a");
        call.enqueue(new Callback<MyObject>() {
            @Override
            public void onResponse(Call<MyObject> call, Response<MyObject> response) {
                handle_response(call, response);
            }

            @Override
            public void onFailure(Call<MyObject> call, Throwable t) {
                handle_fail(call, t);
            }
        });
    }

    public void handle_response(Call<MyObject> callback, Response<MyObject> response) {
        if (response.isSuccessful()) {
            MyObject myObject = response.body();
        } else {
            Log.d("Ey", "Request failed with code: " + response.code());
        }
    }

    public void handle_fail(Call<MyObject> callback, Throwable t) {
        Log.d("Ey", "Error getting data: " + t.getMessage());
    }

    public interface MyObjectCallback {
        void onSuccess(MyObject result);
        void onError(Throwable error);
    }



    public interface MyService {
        @GET
        Call<List<MyObject>> getList(@Url String url);
    }


    public interface test {
        @GET()
        Call<MyObject> getList(@Header("username") String user, @Header("password") String pass, @Url String url);
    }

    interface PostThing{
        @GET("{endpoint}")
        Call<MyObject> getUser(@Path("endpoint") String endpoint);

    }
    interface Help{
        @GET("token/{endpoint}")
        Call<MyObject> getUser(@Path("endpoint") String endpoint);

    }

}
