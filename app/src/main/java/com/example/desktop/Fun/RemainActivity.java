package com.example.desktop.Fun;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TimePicker;

import com.example.desktop.R;

public class RemainActivity extends AppCompatActivity implements View.OnClickListener {

    private Switch remainAlarm;
    private ImageView backIv;
    private TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remain);

        init();
    }

    private void init() {
        remainAlarm = findViewById(R.id.remain_switch_alarm);
        backIv = findViewById(R.id.remain_iv_back);
        timePicker = findViewById(R.id.remain_timePicker);
        backIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.remain_switch_alarm:

                break;
            case R.id.remain_iv_back:
                finish();
                break;
        }
    }
}
