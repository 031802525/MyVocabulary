package com.example.desktop.My.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.desktop.R;
import com.example.desktop.Register.LoginActivity;

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView saveTv;
    private ImageView returnerIv,cameraIv;
    private EditText input2Et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        init();
    }

    private void init() {
        saveTv = findViewById(R.id.save);
        returnerIv = findViewById(R.id.returner);
        cameraIv = findViewById(R.id.Camera);
        input2Et = findViewById(R.id.input2);

        saveTv.setOnClickListener(this);
        returnerIv.setOnClickListener(this);
        cameraIv.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.returner:
                finish();
                break;
            case R.id.save:

                break;
            case R.id.Camera:

                break;
        }
    }
}