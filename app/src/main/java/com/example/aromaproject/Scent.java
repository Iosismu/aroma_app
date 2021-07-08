package com.example.aromaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Scent extends AppCompatActivity {

    private Button button8;
    private ToggleButton TB_2, TB_3, TB_4;
    private ImageView a,b,c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scent);
        button8 = findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Scent.this, Mainview.class);
                toastMessage("설정이 완료되었습니다.");
                startActivity(intent);
            }
        });

        final ToggleButton tb = (ToggleButton) this.findViewById(R.id.TB_2);
        TB_2 = findViewById(R.id.TB_2);

        TB_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tb.isChecked()) {
                    TB_2.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_on)
                    );
                    toastMessage("1번 향기 켜짐");

                } else {
                    TB_2.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_off));
                    toastMessage("1번 향기 꺼짐");
                }
            }
        });

        final ToggleButton tb1 = (ToggleButton) this.findViewById(R.id.TB_3);
        TB_3 = findViewById(R.id.TB_3);
        TB_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tb1.isChecked()) {
                    TB_3.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_on));
                    toastMessage("2번 향기 켜짐");
                }else{
                    TB_3.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_off));
                    toastMessage("2번 향기 꺼짐");
                }

            }
        });

        final ToggleButton tb2 = (ToggleButton) this.findViewById(R.id.TB_4);
        TB_4= findViewById(R.id.TB_4);
        TB_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tb2.isChecked()) {
                    TB_4.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_on));
                    toastMessage("3번 향기 켜짐");

                }else{
                    TB_4.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_off));
                    toastMessage("3번 향기 꺼짐");
                }
            }
        });
    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}