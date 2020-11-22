package com.example.desktop.Fun.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.desktop.R;

public class AddWordActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText addwordspellEd,addwordtranslateEd,getAddwordcontentEd;
    private ImageView addwordIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);

        init();
    }

    private void init() {
        addwordspellEd = findViewById(R.id.addword_ed_addwordspell);
        addwordtranslateEd = findViewById(R.id.addword_ed_addwordtranslate);
        getAddwordcontentEd = findViewById(R.id.addword_ed_addwordcontent);
        addwordIv = findViewById(R.id.addword_iv_addword);
        addwordIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.addword_iv_addword:

                break;
        }
    }
}
