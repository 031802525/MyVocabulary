package com.example.desktop.Fun;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.desktop.R;

public class LinkGameActivity extends AppCompatActivity implements View.OnClickListener {

    TextView timehoderTv,wordbagTv,backTv;
    ListView leftwordspellLv,rightwordtranslateLv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_game);

        init();
    }

    private void init() {
        timehoderTv = findViewById(R.id.linkgame_tv_timepicker);
        wordbagTv = findViewById(R.id.linkgame_tv_wordbag);
        backTv = findViewById(R.id.linkgame_tv_back);
        leftwordspellLv = findViewById(R.id.linkgame_lv_leftwordspell);
        rightwordtranslateLv = findViewById(R.id.linkgame_lv_rightwordtranslate);
        backTv.setOnClickListener(this);
        wordbagTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.linkgame_tv_wordbag:

                break;
            case R.id.linkgame_tv_back:
                finish();
                break;

        }
    }
}
