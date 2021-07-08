package com.example.aromaproject;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UserUpdate extends Application {
    public String ck;

    OkHttpClient client = new OkHttpClient();

    public void updateuser(String de, String se, String one, String two, String thr, String L, String M){

        RequestBody body = new FormBody.Builder()
                .add("method","UPDATE")
                .add("table","user")
                .add("id",de)
                .add("serialNum",se)
                .add("recipe","0")
                .add("functionP_1",one)
                .add("functionP_2",two)
                .add("functionP_3",thr)
                .add("functionL",L)
                .add("functionM",M)
                .add("id",de)
                .add("password","")
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


