package com.example.mp_3.dutchpaydisignguideproject.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.example.mp_3.dutchpaydisignguideproject.ui.notice.NoticeActivity;
import com.example.mp_3.dutchpaydisignguideproject.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler(){
            public void handleMessage(Message msg){
                super.handleMessage(msg);
                //액티비티 전환
                startActivity(new Intent(SplashActivity.this , NoticeActivity.class));
                finish();
            }
        };
        handler.sendEmptyMessageDelayed(0, 1500);
    }
}