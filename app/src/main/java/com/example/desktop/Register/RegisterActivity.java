package com.example.desktop.Register;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.desktop.R;
import com.example.desktop.sql.DBOpenHelper;
import com.example.desktop.sql.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText rusername,ruserpwd,requstpwd;
    private String realCode;
    private Button registerBtn;
    private DBOpenHelper rDBOpenHelper;
    private ImageView rbackIv;

    private int BackgroudImags[] = {R.drawable.backgroud_1,R.drawable.backgroud_2,R.drawable.backgroud_3,
            R.drawable.backgroud_4,R.drawable.backgroud_5,R.drawable.backgroud_6,
            R.drawable.backgroud_7};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();
        rDBOpenHelper = new DBOpenHelper(this);

        //        设置背景图片随机选择
        setRegisterBackgroud();
    }

    private void init() {
//        初始化控件
        rbackIv = findViewById(R.id.register_back);
        rusername = findViewById(R.id.register_username);
        ruserpwd = findViewById(R.id.register_pwd);
        requstpwd = findViewById(R.id.register_comfirpwd);
        registerBtn = findViewById(R.id.register_btn_register);

//        设置点击事件
        rbackIv.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
    }

    private void setRegisterBackgroud() {
//        设置背景虚化程度
        View v = findViewById(R.id.register_backgroud);
        v.getBackground().setAlpha(200);
//        设置背景图片
        Random r = new Random();
        int random = r.nextInt(7);  // [0,7)
        v.setBackgroundResource(BackgroudImags[random]);
    }

    @Override
    public void onClick(View view) {
//        Intent intent = null;
        switch (view.getId()){
            case R.id.register_btn_register:
                String username = rusername.getText().toString().trim();
                String password = ruserpwd.getText().toString().trim();
                String comfirpwd = requstpwd.getText().toString().trim();
                ArrayList<user> data = rDBOpenHelper.getAllData();
                boolean match = false;
                if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(comfirpwd)
                    && (password.equals(comfirpwd))){
                         for (int i = 0; i < data.size(); i++) {
                             user user = data.get(i);
                             if (username.equals(user.getName()) && password.equals(user.getPassword())) {
                                  match = true;
                                 break;
                             } else {
                                   match = false;
                             }
                         }
                        if(!match){
                            // 将用户名和密码加入到数据库中
                            rDBOpenHelper.add(username,password);
                            Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                            startActivity(intent);
                            finish();
                            Toast.makeText(this,"验证通过，注册成功",Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(this, "该用户已存在", Toast.LENGTH_SHORT).show();
                        }
                    }else if (!password.equals(comfirpwd)) {
                        Toast.makeText(this,"两次输入密码不一致，请重新输入",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(this,"未完善信息，注册失败",Toast.LENGTH_SHORT).show();
                    }
                break;
            case R.id.register_back:
                finish();
                break;
        }
    }
}
