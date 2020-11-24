package com.example.desktop.My.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.desktop.My.OnSignedSuccess;
import com.example.desktop.My.adapter.AdapterDate;
import com.example.desktop.My.util.DateUtil;
import com.example.desktop.My.view.SignDate;
import com.example.desktop.R;

import java.util.ArrayList;
import java.util.List;

public class DakaCalendarActivity extends AppCompatActivity implements View.OnClickListener {

    private SignDate signDate;
    private ImageView backIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daka_calendar);
        signDate = findViewById(R.id.signDate);
        backIv = findViewById(R.id.daka_iv_back);
        backIv.setOnClickListener(this);

        signDate.setOnSignedSuccess(new OnSignedSuccess() {
            @Override
            public void OnSignedSuccess() {
                Log.e("lj","Success");
            }
        });
        SharedPreferences sp = this.getSharedPreferences("data", Context.MODE_PRIVATE);
        Toast.makeText(this,"打卡天数："+sp.getInt("count",0),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.daka_iv_back:
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
//        do nothing
    }
}