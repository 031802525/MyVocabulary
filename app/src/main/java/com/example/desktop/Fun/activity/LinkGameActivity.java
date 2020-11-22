package com.example.desktop.Fun.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.desktop.Fun.dialog.CommomDialog;
import com.example.desktop.Fun.util.CountDownTimerUtil;
import com.example.desktop.MainActivity;
import com.example.desktop.R;
import com.example.desktop.frag.QuweiFragment;

import java.net.Inet4Address;

public class LinkGameActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView timehoderTv,wordbagTv,backTv;
    private ListView leftwordspellLv,rightwordtranslateLv;
    private long settime = 10000;
    private CountDownTimerUtil timer;

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

        timer = new CountDownTimerUtil(settime,1000) {
            @Override
            public void onTick(long l) {
                long day = l / (1000*60*60*24);
                long hour = (l - day * (1000*60*60*24)) / (1000*60*60);
                long minute = (l - day * (1000*60*60*24) - hour * (1000*60*60)) / (1000*60);
                long second = (l - day * (1000*60*60*24) - hour * (1000*60*60) - minute * (1000*60)) / 1000;
                if(minute>=10 && second>=10){
                    timehoderTv.setText(minute+":"+second);
                }else if(minute<10 && second>=10){
                    timehoderTv.setText("0"+minute+":"+second);
                }else if(minute<10 && second<10){
                    timehoderTv.setText("0"+minute+":"+"0"+second);
                }else if(minute>=10 && second<10){
                    timehoderTv.setText(minute+":"+"0"+second);
                }
            }

            @Override
            public void onFinish() {
//                new CommomDialog(LinkGameActivity.this, R.style.dialog, "很遗憾！游戏结束！", new CommomDialog.OnCloseListener() {
//                    @Override
//                    public void onClick(Dialog dialog, boolean confirm) {
//                        if (confirm) {
//                            finish();
//                            dialog.dismiss();
//                        } else {
//                            dialog.dismiss();
//                        }
//                    }
//                }).setNegativeButton("再来一局").setPositiveButton("算了").setTitleText("提示").show();
                Intent intent = new Intent(LinkGameActivity.this,LinkGameOverActivity.class);
                startActivity(intent);
                finish();
            }
        };
        timer.start();
    }
//              timer.cancel();
//          settime = (long) (settime*0.5);
//                    timer.setMillisInFuture(settime);

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.linkgame_tv_wordbag:
                Intent intent1 = new Intent(LinkGameActivity.this,WordBagActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.linkgame_tv_back:
//                Intent intent = new Intent(LinkGameActivity.this, MainActivity.class);
//                startActivity(intent);
                timer.cancel();
                finish();
                break;

        }
    }

    @Override
    public void onBackPressed() {
        // do nothing
    }

}
