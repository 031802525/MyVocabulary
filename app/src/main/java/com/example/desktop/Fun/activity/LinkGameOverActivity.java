package com.example.desktop.Fun.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.desktop.MainActivity;
import com.example.desktop.R;
import com.example.desktop.frag.QuweiFragment;

public class LinkGameOverActivity extends AppCompatActivity implements View.OnClickListener {

    TextView confirmTv,cancelTv;
    Button restartBtn;
    ImageView backIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_game_over);

        init();

    }

    private void init() {
        confirmTv = findViewById(R.id.gameover_tv_confirm);
        cancelTv = findViewById(R.id.gameover_tv_cancel);
        restartBtn = findViewById(R.id.gameover_btn_restart);
        backIv = findViewById(R.id.gameover_iv_back);
        confirmTv.setOnClickListener(this);
        cancelTv.setOnClickListener(this);
        restartBtn.setOnClickListener(this);
        backIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.gameover_btn_restart:
                Intent intent = new Intent(LinkGameOverActivity.this,LinkGameActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.gameover_tv_cancel:
                Intent intent1 = new Intent(LinkGameOverActivity.this, MainActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.gameover_tv_confirm:
                Intent intent3 = new Intent(LinkGameOverActivity.this,LinkGameActivity.class);
                startActivity(intent3);
                finish();
                break;
            case R.id.gameover_iv_back:
                Intent intent2 = new Intent(LinkGameOverActivity.this,MainActivity.class);
                startActivity(intent2);
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        // do nothing
    }

}