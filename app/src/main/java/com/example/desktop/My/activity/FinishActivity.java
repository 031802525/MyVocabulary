package com.example.desktop.My.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.desktop.My.util.DateUtil;
import com.example.desktop.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class FinishActivity extends AppCompatActivity implements View.OnClickListener {

    private Button finishBtn;
    private List<Integer> isCheck = new ArrayList<>();
    private int count = 0;
    private boolean isDaka = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        finishBtn = findViewById(R.id.finish_btn);
        finishBtn.setOnClickListener(this);

        int day = DateUtil.getCurrentday();
        SharedPreferences sp = this.getSharedPreferences("data", Context.MODE_PRIVATE);
        int lastDay = sp.getInt("day",0);
        if(lastDay == day) {
            finishBtn.setText("今日已打卡");
            finishBtn.setEnabled(false);
        }else {
            finishBtn.setText("立即打卡");
            finishBtn.setEnabled(true);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.finish_btn:
                    int day = DateUtil.getCurrentday();
                    isCheck.add(day);
                    finishBtn.setText("今日已打卡");
                    finishBtn.setEnabled(false);
                    SharedPreferences sp = this.getSharedPreferences("data", Context.MODE_PRIVATE);
                    int count = sp.getInt("count",0);
                    int lastDay = sp.getInt("day",day);
                    SharedPreferences.Editor edit = sp.edit();
                    edit.putInt("size", isCheck.size());
                    for (int i = 0; i < isCheck.size(); i++) {
                        edit.putInt("type" + i, isCheck.get(i));
                    }
                    edit.putInt("count",++count);
                    if(lastDay != day){
                        edit.putInt("day",day);
                    }else {
                        edit.putInt("day",lastDay);
                    }
                    edit.commit();
//                Toast.makeText(getApplication(),day,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this,DakaCalendarActivity.class);
                    startActivity(intent);
                    finish();
                break;
        }
    }
}