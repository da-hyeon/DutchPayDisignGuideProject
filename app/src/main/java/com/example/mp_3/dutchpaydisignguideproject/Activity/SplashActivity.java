package com.example.mp_3.dutchpaydisignguideproject.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.mp_3.dutchpaydisignguideproject.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //액티비티 전환
        startActivity(new Intent(this , NoticeActivity.class));
        finish();
    }
}