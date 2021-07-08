package com.example.aromaproject;

import android.app.Application;
import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SelectIP extends Application {
    OkHttpClient client = new OkHttpClient();

    public void selectIP() {
        RequestBody body = new FormBody.Builder()
                .add("method","SELECT")
                .add("table","device")

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
            String ck = responseData;

            try {
                JSONObject jsonObject = new JSONObject(ck);
                String table = jsonObject.getString("table");
                JSONObject jsonObject1 = new JSONObject(table);
                JSONArray jsonArray = jsonObject1.getJSONArray("device");

                for (int i = 0; i < jsonArray.length(); i++) {
                    String test = jsonArray.get(i).toString();
                    JSONObject re = new JSONObject(test);
                    String ip = re.getString("ip");
                    String se = re.getString("serialNum");
                    try {
                        if (MyGlobals2.getInstance().getData().equals(se)) {
                            MyGlobals3.getInstance().setData(ip);
                            break;
                        } else {
                            Log.d("NONONONONO", "okok" + "안돼");
                        }
                    }catch (NullPointerException ignored){

                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };
}
