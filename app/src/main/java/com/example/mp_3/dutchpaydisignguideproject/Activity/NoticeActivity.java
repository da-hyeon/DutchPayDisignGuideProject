package com.example.mp_3.dutchpaydisignguideproject.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.mp_3.dutchpaydisignguideproject.MainActivity;
import com.example.mp_3.dutchpaydisignguideproject.R;

public class NoticeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        //확인버튼누르면 메인 이동
        findViewById(R.id.btn_ok).setOnClickListener(v-> {

            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

        //취소시 액티비티 종료
        findViewById(R.id.btn_close).setOnClickListener(v-> finish());
    }
}
