package com.example.desktop.My.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.desktop.MainActivity;
import com.example.desktop.My.OnSignedSuccess;
import com.example.desktop.My.adapter.AdapterDate;
import com.example.desktop.My.util.DateUtil;
import com.example.desktop.My.view.SignDate;
import com.example.desktop.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DakaCalendarActivity extends AppCompatActivity implements View.OnClickListener {

    private SignDate signDate;
    private ImageView backIv;
    private ImageView imageIv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daka_calendar);
        signDate = findViewById(R.id.signDate);
        backIv = findViewById(R.id.daka_iv_back);
        imageIv = findViewById(R.id.pic);
        backIv.setOnClickListener(this);

        signDate.setOnSignedSuccess(new OnSignedSuccess() {
            @Override
            public void OnSignedSuccess() {
                Log.e("lj","Success");
            }
        });
        SharedPreferences sp = this.getSharedPreferences("data", Context.MODE_PRIVATE);
//        Toast.makeText(this,"打卡天数："+sp.getInt("count",0),Toast.LENGTH_SHORT).show();
        SharedPreferences everySentence = getSharedPreferences("everysentence",MODE_PRIVATE);
        String picURL = everySentence.getString("picurl","");
        if(!TextUtils.isEmpty(picURL)){
            Picasso.with(this).load(picURL).into(imageIv);
        }else {
            imageIv.setBackgroundResource(R.mipmap.fun);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.daka_iv_back:
                Intent intent = new Intent(DakaCalendarActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
//        do nothing
    }
}