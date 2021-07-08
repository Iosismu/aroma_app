package com.example.aromaproject;

import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import okhttp3.OkHttpClient;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class Register extends AppCompatActivity {
    OkHttpClient client = new OkHttpClient();

    private EditText id, serialNum, location;
    private Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        id = (EditText) findViewById(R.id.editTextTextPersonName3);
        serialNum = (EditText) findViewById(R.id.editTextTextPersonName4);
        location = (EditText) findViewById(R.id.editTextTextPersonName5);
        button4 = (Button) findViewById(R.id.button4);
        RegisteCK registerSL = new RegisteCK();
        registerSL.checkData();

        button4.setOnClickListener((v) -> {
            if(registerSL.ck.toString().contains(serialNum.getText().toString())) {
                RegisterRequest registerIN = new RegisterRequest();
                Intent intent = new Intent(Register.this , Login.class);
                registerIN.sendData(id.getText().toString(), serialNum.getText().toString(), location.getText().toString());
                toastMessage("회원가입 완료");
                startActivity(intent);
                //[device][i][serialNum]
            } 
            else{
                toastMessage("시리얼 넘버 확인");
            }
        });
    }


            private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}



