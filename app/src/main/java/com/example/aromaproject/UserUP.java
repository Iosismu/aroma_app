package com.example.aromaproject;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UserUP extends Application {
    OkHttpClient client = new OkHttpClient();
    Login gs = new Login();
    String s =  MyGlobals.getInstance().getData();
    String sese = MyGlobals2.getInstance().getData();
    public void userUP(String function_1, String function_2, String function_3 , String functionL, String functionM) {
        RequestBody body = new FormBody.Builder()
                .add("method","UPDATE")
                .add("table","user")
                .add("id",s)
                .add("serialNum",sese)
                .add("recipe","1")
                .add("functionP_1",function_1)
                .add("functionP_2",function_2)
                .add("functionP_3",function_3)
                .add("functionL",functionL)
                .add("functionM",functionM)
                .add("id",s)
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


//sql = "UPDATE user SET id = '%s', serialNum = '%s', location = '%s', recipe = %d, functionP = %d, functionL = %d, functionM = %d \
//    WHERE id = '%s'" % (id, serialNum, location, recipe, functionP, functionL, functionM, id)