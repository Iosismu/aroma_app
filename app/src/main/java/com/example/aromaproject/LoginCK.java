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

public class LoginCK extends Application {
    public String ck;
    public String ck1;
    OkHttpClient client = new OkHttpClient();
    Login login = new Login();

    public JSONArray dbid = null;
    SharedPreferences sf = null;
    public void checkData(){

        RequestBody body = new FormBody.Builder()
                .add("method","SELECT")
                .add("table","user")
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
            //Log.d("TEST", "toString :" + response.body().toString());
            //Log.d("TEST", "body :" + response.body().string());
            Log.d("TEST", responseData);
            ck = responseData;

            Log.d("TEST", "ck :" + ck);

           /* try {
                //ck = "\"" + responseData + "\"";
                JSONObject json = new JSONObject(ck);
                Log.d("JSON", "JSON :" + json.getString("method"));
            } catch (JSONException e) {
                e.printStackTrace();
            }*/



            try {
                JSONObject jsonObject = new JSONObject(ck);
                String method = jsonObject.getString("method");
                String table = jsonObject.getString("table");
                JSONObject jsonObject1 = new JSONObject(table);
                String user = jsonObject1.getString("user");

                JSONArray jsonArray = jsonObject1.getJSONArray("user");


                for(int i=0; i<jsonArray.length(); i++) {
                    String test = jsonArray.get(i).toString();
                    JSONObject re = new JSONObject(test);
                    String test2 = re.getString("location");
                    String test3 = re.getString("functionP_1");
                    String test33 = re.getString("functionP_2");
                    String test333 = re.getString("functionP_3");
                    String test4 = re.getString("functionL");
                    String test5 = re.getString("functionM");
                    String test6 = re.getString("id");
                    String test7 = re.getString("recipe");
                    String test8 = re.getString("timeStamp");
                    String test9 = re.getString("serialNum");
                    Log.d("JSON3", "ID: " + re.getString("id"));
            //        Log.d("JSON3", "PRE: " + login.getPreferences());
//                    if(login.getPreferences().equals(test6)) {
//                        Log.d("JSON3", "okok" + "마즘마즘마즘");
//                        break;
//                    }
//                    else {
//                        Log.d("JSON3", "okok" + "안돼");
//                    }
                }

                List<String> list = Arrays.asList(user);
                Log.d("JSON", "method :" + method);
                Log.d("JSON2", "table :" + table);
                Log.d("JSON3", "user :" + user);


//                JSONArray jsonArray = new JSONArray();
//                for (int i=0; i < jsonArray.length(); i++) {
//                    JSONObject subJsonObject = jsonArray.getJSONObject(i);
//                    String description = subJsonObject.getString("method");
//                    String id = subJsonObject.getString("");
//
//                    String structured_formatting = subJsonObject.getString("structured_formatting");
//                    JSONObject subJsonObject2 = new JSONObject(structured_formatting);
//                    String main_text = subJsonObject2.getString("main_text");
//
//                    System.out.println("description: " + description + "\n" +
//                            "id: " + id + "\n" +
//                            "main_text: " + main_text);
//                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

}


