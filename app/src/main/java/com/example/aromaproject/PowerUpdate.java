package com.example.aromaproject;

import android.app.Application;
import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PowerUpdate extends Application {

    OkHttpClient client = new OkHttpClient();

    public void updatepower(String user, String de ,String one, String two, String thr, String L, String M){
        RequestBody body = new FormBody.Builder()
                .add("method","UPDATE")
                .add("table","powertbl")
                .add("user",user)
                .add("device",de)
                .add("functionP_1",one)
                .add("functionP_2",two)
                .add("functionP_3",thr)
                .add("functionL",L)
                .add("functionM",M)
                .add("user",user)
                .build();

        Request request = new Request.Builder()
                .url("https://teamaroma.pythonanywhere.com/con/data")
                .post(body)
                .build();
        client.newCall(request).enqueue(updateUserInfoCallback);
    }

    private Callback updateUserInfoCallback = new Callback() {
        @Override
        public void onFailure(@NotNull Call call, @NotNull IOException e) {
            Log.d("TEST","ERROR Message: " + e.getMessage());
        }

        @Override
        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
            final String responseData = response.body().string();
        }
    };

}


