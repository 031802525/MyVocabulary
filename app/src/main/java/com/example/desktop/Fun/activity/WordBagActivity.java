package com.example.desktop.Fun.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.desktop.R;

public class WordBagActivity extends AppCompatActivity implements View.OnClickListener {

    ListView wordlistLv;
    ImageView addwordIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_bag);
        init();

    }

    private void init() {
        wordlistLv = findViewById(R.id.wordbag_lv_wordlist);
        addwordIv = findViewById(R.id.wordbag_iv_addword);
        addwordIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.wordbag_iv_addword:

                break;
        }
    }
}
