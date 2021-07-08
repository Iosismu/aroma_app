package com.example.aromaproject;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.ToggleButton;

import java.io.DataOutputStream;
import java.net.Socket;

public class Mainview extends AppCompatActivity {

    ImageButton play , stop;
    private Button btn_1 , BTN_S ;
    private ToggleButton TB_1,TB_2,TB_3,tb_yellow;
    private Switch all, co, mu;
    PowerUpdate powerU = new PowerUpdate();
    UserUpdate userU = new UserUpdate();
    TextView text5;
    TextView text13;
    Spinner spinner;
    Spinner spinner2;
    String color = null;
    String music = null;
    int cP = 0;
    int mP = 0;
    Login login = new Login();
    SharedPreferences LastSelet;
    SharedPreferences.Editor editor;

    SharedPreferences LastCheck;
    SharedPreferences.Editor Seditor;

    SharedPreferences LastClick;
    SharedPreferences.Editor Ceditor;

    SharedPreferences data;
    SharedPreferences.Editor Deditor;

    MediaPlayer mediaPlayer;
    SelectIP ip = new SelectIP();
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainview);
        ip.selectIP();
        System.out.println(login.aaaa);
        play = findViewById(R.id.play);
        stop = findViewById(R.id.stop);
        text5 = findViewById(R.id.textView5);
        text13 = findViewById(R.id.textView13);
        all = findViewById(R.id.switch2);
        co = findViewById(R.id.switch3);
        mu = findViewById(R.id.switch4);

        Intent intent = getIntent();
        String per1 = intent.getStringExtra("향1");
        String per2 = intent.getStringExtra("향2");
        String per3 = intent.getStringExtra("향3");
        int song = intent.getIntExtra("노래",0);
        int lighting = intent.getIntExtra("무드등",0);


        LastSelet=getSharedPreferences("LastSetting",Context.MODE_PRIVATE);
        editor=LastSelet.edit();

        data=getSharedPreferences("dataCK",Context.MODE_PRIVATE);
        Deditor=data.edit();

        final int LaskClick = LastSelet.getInt("LastClick",0);
        final int LaskClick2= LastSelet.getInt("LastClick2",0);
        
        Toast.makeText(getApplicationContext(), "로그인에 성공", Toast.LENGTH_SHORT).show();
        spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter monthAdapter = ArrayAdapter.createFromResource(this, R.array.color, android.R.layout.simple_spinner_dropdown_item);
        //R.array.test는 저희가 정의해놓은 1월~12월 / android.R.layout.simple_spinner_dropdown_item은 기본으로 제공해주는 형식입니다.
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(monthAdapter); //어댑터에 연결해줍니다.

        if(song != 0 && lighting != 0){
            ClientThread thread = new ClientThread();
            thread.MyThread(data.getString("device",""), per1, per2, per3, String.valueOf(lighting), String.valueOf(song),data.getString("light", ""), data.getString("musicO",""));
            thread.start();
        }


        if(lighting != 0){
            lighting = lighting - 1;
            spinner.setSelection(lighting);
        }else {
            spinner.setSelection(LaskClick);
        }
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                color = (String) parent.getItemAtPosition(position);
                text5.setText(color);
                if(color.equals("빨간색")){
                    try {
                        ClientThread thread = new ClientThread();
                        userU.updateuser(MyGlobals.getInstance().getData(),MyGlobals2.getInstance().getData(),data.getString("one","")
                                ,data.getString("two",""),data.getString("thr",""),"1",data.getString("music",""));
                        thread.MyThread(data.getString("device",""), data.getString("one","")
                                ,data.getString("two",""),data.getString("thr",""),"1",data.getString("music",""),data.getString("light", ""), data.getString("musicO",""));
                        thread.start();
                    }catch (NullPointerException ignored){

                    }
                    Deditor.putString("color","1").commit();
                }
                else if(color.equals("파랑색")){
                    try {
                        ClientThread thread = new ClientThread();
                        userU.updateuser(MyGlobals.getInstance().getData(),MyGlobals2.getInstance().getData(),data.getString("one","")
                                ,data.getString("two",""),data.getString("thr",""),"2",data.getString("music",""));
                        thread.MyThread(data.getString("device",""), data.getString("one","")
                                ,data.getString("two",""),data.getString("thr",""),"2",data.getString("music",""),data.getString("light", ""), data.getString("musicO",""));
                        thread.start();
                    }catch (NullPointerException ignored){

                    }
                    Deditor.putString("color","2").commit();
                }
                else if(color.equals("초록색")){
                    try {
                        ClientThread thread = new ClientThread();
                        userU.updateuser(MyGlobals.getInstance().getData(), MyGlobals2.getInstance().getData(), data.getString("one", "")
                                , data.getString("two", ""), data.getString("thr", ""), "3", data.getString("music", ""));
                        thread.MyThread(data.getString("device",""), data.getString("one", "")
                                , data.getString("two", ""), data.getString("thr", ""), "3", data.getString("music", ""),data.getString("light", ""), data.getString("musicO",""));
                        thread.start();
                    }catch (NullPointerException ignored){

                    }
                    Deditor.putString("color","3").commit();
                }
                else if(color.equals("하얀색")){
                    try {
                        ClientThread thread = new ClientThread();
                        userU.updateuser(MyGlobals.getInstance().getData(),MyGlobals2.getInstance().getData(),data.getString("one","")
                                ,data.getString("two",""),data.getString("thr",""),"4",data.getString("music",""));
                        thread.MyThread(data.getString("device",""), data.getString("one","")
                                ,data.getString("two",""),data.getString("thr",""),"4",data.getString("music",""),data.getString("light", ""), data.getString("musicO",""));
                        thread.start();
                    }catch (NullPointerException ignored){

                    }
                    Deditor.putString("color","4").commit();
                }
                else if(color.equals("노랑색")){
                    try {
                        ClientThread thread = new ClientThread();
                        userU.updateuser(MyGlobals.getInstance().getData(),MyGlobals2.getInstance().getData(),data.getString("one","")
                                ,data.getString("two",""),data.getString("thr",""),"5",data.getString("music",""));
                        thread.MyThread(data.getString("device",""), data.getString("one","")
                                ,data.getString("two",""),data.getString("thr",""),"5",data.getString("music",""),data.getString("light", ""), data.getString("musicO",""));
                        thread.start();
                    }catch (NullPointerException ignored){

                    }
                    Deditor.putString("color","5").commit();
                }
                editor.putInt("LastClick",position).commit();
            } //이 오버라이드 메소드에서 position은 몇번째 값이 클릭됬는지 알 수 있습니다.
            //getItemAtPosition(position)를 통해서 해당 값을 받아올수있습니다.

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        spinner2 = (Spinner) findViewById(R.id.spinner2);

        ArrayAdapter monthAdapter2 = ArrayAdapter.createFromResource(this, R.array.music, android.R.layout.simple_spinner_dropdown_item);
        //R.array.test는 저희가 정의해놓은 1월~12월 / android.R.layout.simple_spinner_dropdown_item은 기본으로 제공해주는 형식입니다.
        monthAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(monthAdapter2); //어댑터에 연결해줍니다.
        if(song != 0){
            song = song - 1;
            spinner2.setSelection(song);
        }else {
            spinner2.setSelection(LaskClick2);
        }
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                music = (String) parent.getItemAtPosition(position);
                text13.setText(music);
                if(music.equals("BTS - DYNAMITE")){
                    try {
                        ClientThread thread = new ClientThread();
                        userU.updateuser(MyGlobals.getInstance().getData(),MyGlobals2.getInstance().getData(),data.getString("one","")
                                ,data.getString("two",""),data.getString("thr",""),data.getString("color",""),"1");
                        thread.MyThread(data.getString("device",""), data.getString("one","")
                                ,data.getString("two",""),data.getString("thr",""),data.getString("color",""),"1",data.getString("light", ""), data.getString("musicO",""));
                        thread.start();
                    }catch (NullPointerException ignored){

                    }
                    Deditor.putString("music","1").commit();
                }
                else if(music.equals("헤이즈 - 비도 오고 그래서")){
                    try {
                        ClientThread thread = new ClientThread();
                        userU.updateuser(MyGlobals.getInstance().getData(),MyGlobals2.getInstance().getData(),data.getString("one","")
                                ,data.getString("two",""),data.getString("thr",""),data.getString("color",""),"2");
                        thread.MyThread(data.getString("device",""), data.getString("one","")
                                ,data.getString("two",""),data.getString("thr",""),data.getString("color",""),"2",data.getString("light", ""), data.getString("musicO",""));
                        thread.start();
                    }catch (NullPointerException ignored){

                    }
                    Deditor.putString("music","2").commit();
                }
                else if(music.equals("버스커 버스커 - 벛꽃엔딩")){
                    try {
                        ClientThread thread = new ClientThread();
                        userU.updateuser(MyGlobals.getInstance().getData(),MyGlobals2.getInstance().getData(),data.getString("one","")
                                ,data.getString("two",""),data.getString("thr",""),data.getString("color",""),"3");
                        thread.MyThread(data.getString("device",""), data.getString("one","")
                                ,data.getString("two",""),data.getString("thr",""),data.getString("color",""),"3",data.getString("light", ""), data.getString("musicO",""));
                        thread.start();
                    }catch (NullPointerException ignored){

                    }
                    Deditor.putString("music","3").commit();
                }
                else if(music.equals("바이브 - 가을타나봐")){
                    try {
                        ClientThread thread = new ClientThread();
                        userU.updateuser(MyGlobals.getInstance().getData(),MyGlobals2.getInstance().getData(),data.getString("one","")
                                ,data.getString("two",""),data.getString("thr",""),data.getString("color",""),"4");
                        thread.MyThread(data.getString("device",""),data.getString("one","")
                                ,data.getString("two",""),data.getString("thr",""),data.getString("color",""),"4",data.getString("light", ""), data.getString("musicO",""));
                        thread.start();
                    }catch (NullPointerException ignored){

                    }
                    Deditor.putString("music","4").commit();
                }
                else if(music.equals("애국가")){
                    try {
                        ClientThread thread = new ClientThread();
                        userU.updateuser(MyGlobals.getInstance().getData(),MyGlobals2.getInstance().getData(),data.getString("one","")
                                ,data.getString("two",""),data.getString("thr",""),data.getString("color",""),"5");
                        thread.MyThread(data.getString("device",""),data.getString("one","")
                                ,data.getString("two",""),data.getString("thr",""),data.getString("color",""),"5",data.getString("light", ""), data.getString("musicO",""));
                        thread.start();
                    }catch (NullPointerException ignored){

                    }
                    Deditor.putString("music","5").commit();
                }
                editor.putInt("LastClick2",position).commit();
            } //이 오버라이드 메소드에서 position은 몇번째 값이 클릭됬는지 알 수 있습니다.

            //getItemAtPosition(position)를 통해서 해당 값을 받아올수있습니다.

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
        //정지 버튼 눌렀을때
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                }
            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer = MediaPlayer.create(Mainview.this,R.raw.dynamite);
                mediaPlayer.start();
            }
        });

        btn_1 = findViewById(R.id.btn_1);
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mainview.this , newrecipe.class);
                startActivity(intent);
            }
        });
        LastClick=getSharedPreferences("aaa",Context.MODE_PRIVATE);
        Ceditor=LastClick.edit();


        TB_1 = findViewById(R.id.TB_1);
        boolean tgpref2 = LastClick.getBoolean("tgpref2",true);
        if(per1 == "1"){
            TB_1.setChecked(false);
        }
        else if(tgpref2 == true) //if (tgpref) may be enough, not sure
        {
            TB_1.setChecked(true);
        }
        else
        {
            TB_1.setChecked(false);
        }

        TB_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((TB_1.isChecked())){
                        try {
                            ClientThread thread = new ClientThread();
                            Ceditor.putBoolean("tgpref2", true); // value to store
                            Ceditor.commit();
                            powerU.updatepower(MyGlobals.getInstance().getData(), data.getString("device", ""), "0", data.getString("two", ""), data.getString("thr", ""),
                                    data.getString("light", ""), data.getString("music", ""));
                            userU.updateuser(MyGlobals.getInstance().getData(), MyGlobals2.getInstance().getData(), "0", data.getString("two", ""), data.getString("thr", ""), data.getString("color", ""), data.getString("music", ""));
                            Deditor.putString("one", "0").commit();
                            thread.MyThread(data.getString("device", ""), "0", data.getString("two", ""), data.getString("thr", ""),
                                data.getString("color", ""), data.getString("music", ""),data.getString("light", ""), data.getString("musicO",""));
                            thread.start();
                    }catch (NullPointerException ignored){

                    }
                }
                else{
                    try {
                        ClientThread thread = new ClientThread();
                        Ceditor.putBoolean("tgpref2", false); // value to store
                        Ceditor.commit();
                        powerU.updatepower(MyGlobals.getInstance().getData(),data.getString("device",""),"1",data.getString("two",""),data.getString("thr",""),
                                data.getString("light",""),data.getString("music",""));
                        userU.updateuser(MyGlobals.getInstance().getData(),MyGlobals2.getInstance().getData(),"1",data.getString("two",""),data.getString("thr",""),data.getString("color",""),data.getString("music",""));
                        thread.MyThread(data.getString("device",""),"1",data.getString("two",""),data.getString("thr",""),
                                data.getString("color",""),data.getString("music",""),data.getString("light", ""), data.getString("musicO",""));
                        thread.start();
                    }catch (NullPointerException ignored){

                    }
                    Deditor.putString("one","1").commit();
                }
            }
        });

        TB_2 = findViewById(R.id.TB_2);
        boolean tgpref = LastClick.getBoolean("tgpref",true);
        if(per2 == "1"){
            TB_2.setChecked(false);
        }
        else if(tgpref == true) //if (tgpref) may be enough, not sure
        {
            TB_2.setChecked(true);
        }
        else
        {
            TB_2.setChecked(false);
        }

        TB_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((TB_2.isChecked())){
                    try {
                        ClientThread thread = new ClientThread();
                        Ceditor.putBoolean("tgpref", true); // value to store
                        Ceditor.commit();
                        powerU.updatepower(MyGlobals.getInstance().getData(), data.getString("device", ""), data.getString("one", ""), "0", data.getString("thr", ""),
                                data.getString("light", ""), data.getString("music", ""));
                        userU.updateuser(MyGlobals.getInstance().getData(), MyGlobals2.getInstance().getData(), data.getString("one", ""), "0", data.getString("thr", ""), data.getString("color", ""), data.getString("music", ""));
                        Deditor.putString("two", "0").commit();
                        thread.MyThread(data.getString("device", ""), data.getString("one", ""), "0", data.getString("thr", ""),
                                data.getString("color", ""), data.getString("music", ""),data.getString("light", ""), data.getString("musicO",""));
                        try {
                            thread.start();
                        }catch (NullPointerException ignored){

                        }
                    }catch (NullPointerException ignored){

                    }
                }
                else{
                    try {
                        ClientThread thread = new ClientThread();
                        Ceditor.putBoolean("tgpref", false); // value to store
                        Ceditor.commit();
                        powerU.updatepower(MyGlobals.getInstance().getData(), data.getString("device", ""), data.getString("one", ""), "1", data.getString("thr", ""),
                                data.getString("light", ""), data.getString("music", ""));
                        userU.updateuser(MyGlobals.getInstance().getData(), MyGlobals2.getInstance().getData(), data.getString("one", ""), "1", data.getString("thr", ""), data.getString("color", ""), data.getString("music", ""));
                        Deditor.putString("two", "1").commit();
                        thread.MyThread(data.getString("device", ""), data.getString("one", ""), "1", data.getString("thr", ""),
                                data.getString("color", ""), data.getString("music", ""),data.getString("light", ""), data.getString("musicO",""));
                        thread.start();
                    }catch (NullPointerException ignored){

                    }
                }
            }
        });

        TB_3= findViewById(R.id.TB_3);
        boolean tgpref3 = LastClick.getBoolean("tgpref3",true);
        if(per3 == "1"){
            TB_3.setChecked(false);
        }
        else if(tgpref3 == true) //if (tgpref) may be enough, not sure
        {
            TB_3.setChecked(true);
        }
        else
        {
            TB_3.setChecked(false);
        }
        TB_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((TB_3.isChecked())){
                    try {
                        ClientThread thread = new ClientThread();
                        Ceditor.putBoolean("tgpref3", true); // value to store
                        Ceditor.commit();
                        powerU.updatepower(MyGlobals.getInstance().getData(), data.getString("device", ""), data.getString("one", ""), data.getString("two", ""), "0",
                                data.getString("light", ""), data.getString("music", ""));
                        userU.updateuser(MyGlobals.getInstance().getData(), MyGlobals2.getInstance().getData(), data.getString("one", ""), data.getString("two", ""), "0", data.getString("color", ""), data.getString("music", ""));
                        Deditor.putString("thr", "0").commit();
                        thread.MyThread(data.getString("device", ""), data.getString("one", ""), data.getString("two", ""), "0",
                                data.getString("color", ""), data.getString("music", ""),data.getString("light", ""), data.getString("musicO",""));
                        thread.start();
                    }catch (NullPointerException ignored){

                    }
                }
                else{
                    try {
                        ClientThread thread = new ClientThread();
                        Ceditor.putBoolean("tgpref3", false); // value to store
                        Ceditor.commit();
                        powerU.updatepower(MyGlobals.getInstance().getData(), data.getString("device", ""), data.getString("one", ""), data.getString("two", ""), "1",
                                data.getString("light", ""), data.getString("music", ""));
                        userU.updateuser(MyGlobals.getInstance().getData(), MyGlobals2.getInstance().getData(), data.getString("one", ""), data.getString("two", ""), "1", data.getString("color", ""), data.getString("music", ""));
                        Deditor.putString("thr", "1").commit();
                        thread.MyThread(data.getString("device", ""), data.getString("one", ""), data.getString("two", ""), "1",
                                data.getString("color", ""), data.getString("music", ""),data.getString("light", ""), data.getString("musicO",""));
                        thread.start();
                    }catch (NullPointerException ignored){

                    }
                }
            }
        });

        final ToggleButton tb3 = (ToggleButton) this.findViewById(R.id.tb_yellow);
        tb_yellow = findViewById(R.id.tb_yellow);
        tb_yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tb3.isChecked()) {
                    tb_yellow.setBackgroundDrawable(getResources().getDrawable(R.drawable.yellow));
                    toastMessage("무드등 사용이 활성화 되었습니다.");
                }else {
                    tb_yellow.setBackgroundDrawable(getResources().getDrawable(R.drawable.yellowoff));
                    toastMessage("무드등 사용이 비활성화 되었습니다.");
                }

            }
        });

        LastCheck=getSharedPreferences("LastCheck",Context.MODE_PRIVATE);
        Seditor=LastCheck.edit();

        final boolean allB = LastCheck.getBoolean("allall",false);
        final boolean coB= LastCheck.getBoolean("coco",false);
        final boolean muB= LastCheck.getBoolean("mumu",false);

        all.setChecked(allB);
        all.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    try {
                        ClientThread thread = new ClientThread();
                        Seditor.putBoolean("allall", true).commit();
                        powerU.updatepower(MyGlobals.getInstance().getData(), "1", data.getString("one", ""), data.getString("two", ""), data.getString("thr", "")
                                , data.getString("light", ""), data.getString("music", ""));
                        Deditor.putString("device", "1").commit();

                        thread.MyThread("1", data.getString("one", ""), data.getString("two", ""), data.getString("thr", "")
                                ,data.getString("color",""),data.getString("music","") , data.getString("light", ""), data.getString("musicO", ""));
                        thread.start();
                    }catch (NullPointerException ignored){

                    }
                }else{
                    try {
                        ClientThread thread = new ClientThread();
                        powerU.updatepower(MyGlobals.getInstance().getData(), "0", data.getString("one", ""), data.getString("two", ""), data.getString("thr", "")
                                , data.getString("light", ""), data.getString("music", ""));
                        Seditor.putBoolean("allall", false).commit();
                        Deditor.putString("device", "0").commit();

                        thread.MyThread("0", data.getString("one", ""), data.getString("two", ""), data.getString("thr", "")
                                ,data.getString("color",""),data.getString("music",""), data.getString("light", ""), data.getString("musicO", ""));
                        thread.start();
                    }catch (NullPointerException ignored){

                    }
                }
            }
        });
        co.setChecked(coB);
        co.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    try {
                        ClientThread thread = new ClientThread();
                        powerU.updatepower(MyGlobals.getInstance().getData(), data.getString("device", ""), data.getString("one", ""), data.getString("two", ""), data.getString("thr", "")
                                , "1", data.getString("music", ""));
                        Deditor.putString("light", "1").commit();
                        Seditor.putBoolean("coco", true).commit();

                        thread.MyThread(data.getString("device", ""), data.getString("one", ""), data.getString("two", ""), data.getString("thr", "")
                                ,data.getString("color",""),data.getString("music","") , "1", data.getString("musicO", ""));
                        thread.start();
                    }catch (NullPointerException ignored){

                    }
                }else{
                    try {
                        ClientThread thread = new ClientThread();
                        powerU.updatepower(MyGlobals.getInstance().getData(), data.getString("device", ""), data.getString("one", ""), data.getString("two", ""), data.getString("thr", "")
                                , "0", data.getString("music", ""));
                        Deditor.putString("light", "0").commit();
                        Seditor.putBoolean("coco", false).commit();
                        thread.MyThread(data.getString("device", ""), data.getString("one", ""), data.getString("two", ""), data.getString("thr", "")
                                ,data.getString("color",""),data.getString("music","") , "0", data.getString("musicO", ""));
                        thread.start();
                    }catch (NullPointerException ignored){

                    }
                }
            }
        });
        mu.setChecked(muB);
        mu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    try {
                        ClientThread thread = new ClientThread();
                        powerU.updatepower(MyGlobals.getInstance().getData(), data.getString("device", ""), data.getString("one", ""), data.getString("two", "")
                                , data.getString("thr", ""), data.getString("light", ""), "1");
                        Deditor.putString("musicO", "1").commit();
                        Seditor.putBoolean("mumu", true).commit();
                        thread.MyThread(data.getString("device", ""), data.getString("one", ""), data.getString("two", "")
                                , data.getString("thr", ""),data.getString("color",""),data.getString("music",""), data.getString("light", ""), "1");
                        thread.start();
                        Log.d("hoststst", "music: " + data.getString("music",""));
                    }catch (NullPointerException ignored){

                    }
                }else{
                    try {
                        ClientThread thread = new ClientThread();
                        powerU.updatepower(MyGlobals.getInstance().getData(), data.getString("device", ""), data.getString("one", ""), data.getString("two", "")
                                , data.getString("thr", ""), data.getString("light", ""), "0");
                        Deditor.putString("musicO", "0").commit();
                        Seditor.putBoolean("mumu", false).commit();
                        thread.MyThread(data.getString("device", ""), data.getString("one", ""), data.getString("two", "")
                                , data.getString("thr", ""),data.getString("color",""),data.getString("music","") ,data.getString("light", ""), "0");
                        Log.d("hoststst", "music: " + data.getString("music",""));
                        thread.start();
                    }catch (NullPointerException ignored){

                    }
                }
            }
        });
  }
    public void logout(View view){
        toastMessage("로그아웃됨");
        Intent intent = new Intent(Mainview.this, Login.class);
        startActivity(intent);
    }
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

    class ClientThread extends Thread {

        private String de, per1, per2, per3, L, M, Lo, Mo;

        public void MyThread(String de, String per1, String per2, String per3, String L, String M, String Lo, String Mo) {
            this.de = de;
            this.per1 = per1;
            this.per2 = per2;
            this.per3 = per3;
            this.L = L;
            this.M = M;
            this.Lo = Lo;
            this.Mo = Mo;
        }

        @Override
        public void run() {
            String host = MyGlobals3.getInstance().getData();
            Log.d("hoststst", "Host is: " + host);

            int port = 20021;
            Log.d("HOSTHOSTHOSTHOST", MyGlobals3.getInstance().getData());

            String aa = "@"+ Integer.valueOf(de) + ","+Integer.valueOf(per1)+","+ Integer.valueOf(per2) + ","+Integer.valueOf(per3)+","+ Integer.valueOf(L) + "," + Integer.valueOf(M) + "," + Integer.valueOf(Lo) + "," + Integer.valueOf(Mo);
            try {
                Socket socket = new Socket(host, port);
                DataOutputStream outstream = new DataOutputStream(socket.getOutputStream());
                outstream.writeUTF(aa);
                outstream.flush();
                Log.d("ClientStream", "Sent to server.");
            } catch (Exception e) {
                e.printStackTrace();
            }
//        }
//        public void hit(String de, String functionP_1, String functionP_2, String functionP_3, String functionL, String functionM) {
//            String host = "218.233.193.4";
//            int port = 80;
//            String aa = '$'+ de + ", "+functionP_1+", " + ", "+ functionP_2 + ", "+functionP_3+", "+ functionL + ", " + functionM;
//
//            try {
//                Socket socket = new Socket(host, port);
//
//                DataOutputStream outstream = new DataOutputStream(socket.getOutputStream());
//                outstream.writeUTF(aa);
//                outstream.flush();
//                Log.d("ClientStream", "Sent to server.");
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        }
    }
}