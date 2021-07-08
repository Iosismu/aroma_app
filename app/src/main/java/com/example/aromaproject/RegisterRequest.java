package com.example.aromaproject;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import java.io.IOException;
import java.lang.reflect.Array;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;




public class RegisterRequest extends Application {

    OkHttpClient client = new OkHttpClient();

    public void sendData(String id, String serialNum, String location) {

        RequestBody body = new FormBody.Builder()
                .add("method", "INSERT")
                .add("table", "user")
                .add("id", id.toString())
                .add("serialNum", serialNum.toString())
                .add("location", location.toString())
                .add("recipe", "1")
                .add("functionP", "1")
                .add("functionL", "1")
                .add("functionM", "2")
                .add("password","Null")
                .build();

        Request request = new Request.Builder()
                .url("https://teamaroma.pythonanywhere.com/con/data")
                .post(body)
                .build();
        client.newCall(request).enqueue(updateUserInfoCallback);
    }

    public void getData(String id, String serialNum, String location) {

    }

    private Callback updateUserInfoCallback = new Callback() {
        @Override
        public void onFailure(@NotNull Call call, @NotNull IOException e) {
            Log.d("TEST", "ERROR Message: " + e.getMessage());
        }

        @Override
        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
            final String responseData = response.body().string();
            Log.d("TEST", "responseData: " + responseData);
        }
    };
}
