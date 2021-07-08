package com.example.aromaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

public class newrecipe extends AppCompatActivity {
    private TextView tv_2;
    private TextView tv_3;
    private TextView tv_4;
    private TextView tv_5;
    private TextView tv_6;
    private Button button17, button15;

    public String test2 = null;
    public String test3 = null;
    public String test33 = null;
    public String test333 = null;
    public String test4 = null;
    public String test5 = null;
    public String test8 = null;

    public String per = null;
    public String per2 = null;
    public String per3 = null;
    public String mu = null;
    public String co = null;
    AutoGetterSetter GS = new AutoGetterSetter();
    UserUP user = new UserUP();
    Login gs = new Login();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newrecipe);

        tv_2 = findViewById(R.id.tv_2);
        tv_3 = findViewById(R.id.tv_3);
        tv_4 = findViewById(R.id.tv_4);
        tv_5 = findViewById(R.id.tv_5);
        tv_6 = findViewById(R.id.tv_6);

        button17 = findViewById(R.id.button17);

        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("method", "SELECT")
                .add("table", "recipe")
                .build();
        Request request = new Request.Builder()
                .url("https://teamaroma.pythonanywhere.com/con/data")
                .post(body)
                .build();

        client.newCall(request).enqueue(updateUserInfoCallback);

        button17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    user.userUP(test3, test33, test333, test4, test5);

                }catch (NullPointerException ignored){

                }

                Intent intent = new Intent(newrecipe.this, Mainview.class);

                intent.putExtra("향1", test3);
                intent.putExtra("향2", test33);
                intent.putExtra("향3", test333);
                intent.putExtra("노래", Integer.parseInt(test5));
                intent.putExtra("무드등", Integer.parseInt(test4));
                toastMessage("적용이 완료되었습니다.");
                startActivity(intent);
            }
        });


    }



    private Callback updateUserInfoCallback = new Callback() {
        @Override
        public void onFailure(@NotNull Call call, @NotNull IOException e) {
            Log.d("TEST", "ERROR Message" + e.getMessage());

        }

        @Override
        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
            final String responseData = response.body().string();
            String ck = responseData;
            //Handler mHandler = new Handler(Looper.getMainLooper());

            newrecipe.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.d("test", "ck :" + ck);
                    try {
                        JSONObject jsonObject = new JSONObject(ck);
                        String method = jsonObject.getString("method");
                        String table = jsonObject.getString("table");
                        JSONObject jsonObject1 = new JSONObject(table);
                        String recipe = jsonObject1.getString("recipe");

                        JSONArray jsonArray = jsonObject1.getJSONArray("recipe");

                        String test = jsonArray.get(0).toString();
                        JSONObject re = new JSONObject(test);
                        test2 = re.getString("author");
                        test3 = re.getString("functionP_1");
                        test33 = re.getString("functionP_2");
                        test333 = re.getString("functionP_3");
                        test4 = re.getString("functionL");
                        test5 = re.getString("functionM");
                        test8 = re.getString("timeStamp");

//                        Log.d("JSON", "method :" + method);
//                        Log.d("JSON2", "table :" + table);
//                        Log.d("JSON3", "user :" + recipe);
//                        Log.d("JSON3", "author :" + test2);
//                        Log.d("JSON3", "functionL :" + test3);
//                        Log.d("JSON3", "functionP :" + test4);
//                        Log.d("JSON3", "functionM :" + test5);
//                        Log.d("JSON3", "timeStamp :" + test8);

                        if(test3.equals("1")){
                            per = "라벤더향";
                        }
                        if(test3.equals("0")){
                            per = "";
                        }
                        if(test33.equals("1")){
                            per2 = "시트러스 향";
                        }
                        if(test33.equals("0")){
                            per2 = "";
                        }
                        if(test333.equals("1")){
                            per3 = "로즈향";
                        }
                        if(test333.equals("0")){
                            per3 = "";
                        }
                        if(test5.equals("1")){
                            mu = "BTS - Dynamite";
                        }
                        if(test5.equals("2")){
                            mu = "헤이즈 - 비도 오고 그래서";
                        }
                        if(test5.equals("3")){
                            mu = "버스커 버스커 - 벛꽃엔딩";
                        }
                        if(test5.equals("4")){
                            mu = "바이브 - 가을타나봐";
                        }
                        if(test5.equals("5")){
                            mu = "애국가";
                        }
                        if(test4.equals("1")){
                            co = "빨간색 무드등";
                        }
                        if(test4.equals("2")){
                            co = "파랑색 무드등";
                        }
                        if(test4.equals("3")){
                            co = "초록색 무드등";
                        }
                        if(test4.equals("4")){
                            co = "하얀색 무드등";
                        }
                        if(test4.equals("5")){
                            co = "노랑색 무드등";
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String s = MyGlobals.getInstance().getData();
                    String se = MyGlobals2.getInstance().getData();
                    tv_2.setText("향:   " + per);
                    tv_3.setText("노래:  " + mu);
                    tv_4.setText("무드등:   " + co);
                    tv_5.setText("아이디:  " + s);
                    tv_6.setText("시리얼 넘버: " + se);
                }
            });
        }
    };


    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

}