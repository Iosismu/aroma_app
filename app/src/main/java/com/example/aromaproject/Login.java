package com.example.aromaproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Login extends AppCompatActivity {
    OkHttpClient client = new OkHttpClient();

    private boolean saveLoginData;
    private String id;
    private String pwd;

    private EditText idText;
    private EditText serialNum;
    private CheckBox checkBox;

    private SharedPreferences appData;

    private Button button3;
    private CheckBox Auto_LogIn;
    Intent in;

    String aaaa = null;

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String id_ID = "nameKey";
    public static final String se_SE = "phoneKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        idText = (EditText) findViewById(R.id.editTextTextPersonName);
        serialNum = (EditText) findViewById(R.id.editTextTextPersonName2);
        button3 = (Button) findViewById(R.id.button3);
        checkBox = findViewById(R.id.checkBox);

        // 설정값 불러오기
        appData = getSharedPreferences("appData", MODE_PRIVATE);
        load();

      //  Auto_LogIn = (CheckBox) findViewById(R.id.checkBox);

//        SharedPreferences sharedPreference = getSharedPreferences("checkbox", Context.MODE_PRIVATE);
//        String checkbox = sharedPreference.getString("remember", "");
//
//        if (checkbox.equals("true")) {
//            Intent intent = new Intent(Login.this, Mainview.class);
//            startActivity(intent);
//        } else if (checkbox.equals("false")) {
//            toastMessage("Please Sign in");
//        }

        LoginCK LoginSL = new LoginCK();
        LoginSL.checkData();
        RegisteCK registerSL = new RegisteCK();
        registerSL.checkData();

//        Auto_LogIn.setOnCheckedChangeListener((buttonView, isChecked) -> {
//            // TODO Auto-generated method stub
//
//            if (isChecked) {
//                saveCK();
//            } else {
//                saveNCK();
//            }
//        });
        // 이전에 로그인 정보를 저장시킨 기록이 있다면
        if (saveLoginData) {
            idText.setText(id);
            checkBox.setChecked(saveLoginData);
        }

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (registerSL.ck.contains(serialNum.getText().toString()) && LoginSL.ck.contains(id)) {
                        matchingData();
                        Intent intent = new Intent(Login.this, Mainview.class);
                        startActivity(intent);
                        save();
                    } else {
                        toastMessage("아이디 비밀번호를 확인하세요");
                    }                }catch (NullPointerException ignored){

                }
            }
        });
    }

    // 설정값을 저장하는 함수
    private void save() {
        // SharedPreferences 객체만으론 저장 불가능 Editor 사용
        SharedPreferences.Editor editor = appData.edit();

        // 에디터객체.put타입( 저장시킬 이름, 저장시킬 값 )
        // 저장시킬 이름이 이미 존재하면 덮어씌움
        editor.putBoolean("SAVE_LOGIN_DATA", checkBox.isChecked()).commit();
        editor.putString("ID", idText.getText().toString().trim()).commit();
        // apply, commit 을 안하면 변경된 내용이 저장되지 않음
    }

    // 설정값을 불러오는 함수
    private void load() {
        // SharedPreferences 객체.get타입( 저장된 이름, 기본값 )
        // 저장된 이름이 존재하지 않을 시 기본값
        saveLoginData = appData.getBoolean("SAVE_LOGIN_DATA", false);
        id = appData.getString("ID", "");
    }

    //    public void saveCK() {
//        SharedPreferences sharedpreferences = getSharedPreferences("checkbox", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedpreferences.edit();
//        editor.putString("remember", "true");
//        editor.commit();
//    }
//
//    public void saveNCK() {
//        SharedPreferences sharedpreferences = getSharedPreferences("checkbox", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedpreferences.edit();
//        editor.putString("remember", "false");
//        editor.commit();
//    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void matchingData() {
        RequestBody body = new FormBody.Builder()
                .add("method", "SELECT")
                .add("table", "user")
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
            Log.d("TEST", "ERROR Message: " + e.getMessage());
        }

        @Override
        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
            final String responseData = response.body().string();
            //Log.d("TEST", "toString :" + response.body().toString());
            //Log.d("TEST", "body :" + response.body().string());
            // Log.d("TEST", responseData);
            String ck = responseData;

            try {
                JSONObject jsonObject = new JSONObject(ck);
                String table = jsonObject.getString("table");
                JSONObject jsonObject1 = new JSONObject(table);
                JSONArray jsonArray = jsonObject1.getJSONArray("user");

                for (int i = 0; i < jsonArray.length(); i++) {
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
                    if (idText.getText().toString().equals(test6)) {
                        Log.d("OKOKOKOK", "okok" + "마즘마즘마즘");
                        //   GS.setId(re.getString("id"));
                        //   GS.setSerialNum(re.getString("serialNum"));

                        MyGlobals.getInstance().setData(test6);
                        MyGlobals2.getInstance().setData(test9);
                        break;
                    } else {
                        Log.d("NONONONONO", "okok" + "안돼");
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };


}
