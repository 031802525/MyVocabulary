package com.example.desktop.Register;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.desktop.MainActivity;
import com.example.desktop.R;
import com.example.desktop.sql.DBOpenHelper;
import com.example.desktop.sql.user;

import java.util.ArrayList;
import java.util.Random;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText username,userpwd;
    private TextView logintoRegisterTv,forgetTv;
    private ImageView backIv;
    private Button loginBtn;
    private CheckBox rememberCb,autoCb;
    private int BackgroudImags[] = {R.drawable.backgroud_1,R.drawable.backgroud_2,R.drawable.backgroud_3,
                                    R.drawable.backgroud_4,R.drawable.backgroud_5,R.drawable.backgroud_6,
                                    R.drawable.backgroud_7};
    private DBOpenHelper mDBOpenHelper;// 用于创建数据表，然后进行增删改查操作
    private SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//保存实例状态
        setContentView(R.layout.activity_login);//设置视图内容的配置文件
        init();
        mDBOpenHelper = new DBOpenHelper(this);//创建数据库实例化对象
        sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        setSp();
//        设置背景图片随机选择
        setLoginBackgroud();
    }

    private void setSp() {
        if(sp.getBoolean("ISCHECK",false)){
//            设置默认是记录密码状态
            rememberCb.setChecked(true);
            username.setText(sp.getString("USER_NAME",""));
            userpwd.setText(sp.getString("PASSWORD",""));
            if(sp.getBoolean("AUTO_ISCHECK",false)){
                autoCb.setChecked(true);
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }

    }



    private void init() {
//        初始化控件
        username = findViewById(R.id.login_username);
        userpwd = findViewById(R.id.login_pwd);
        backIv = findViewById(R.id.login_back);
        loginBtn = findViewById(R.id.login_btn_login);
        logintoRegisterTv = findViewById(R.id.login_tv_register);
        forgetTv = findViewById(R.id.login_tv_losspwd);
        rememberCb = findViewById(R.id.login_remembercheckbox);
        autoCb = findViewById(R.id.login_autocheckbox);
//        设置点击事件
        logintoRegisterTv.setOnClickListener(this);
        backIv.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
        forgetTv.setOnClickListener(this);

        rememberCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(rememberCb.isChecked()){
                    sp.edit().putBoolean("ISCHECK",true).commit();
                }else {
                    sp.edit().putBoolean("ISCHECK",false).commit();
                }
            }
        });

        autoCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(autoCb.isChecked()){
                    sp.edit().putBoolean("AUTO_ISCHECK",true).commit();
                }else {
                    sp.edit().putBoolean("AUTO_ISCHECK",false).commit();
                }
            }
        });
    }


    private void setLoginBackgroud() {
//        设置背景虚化程度
        View v = findViewById(R.id.login_backgroud);
        v.getBackground().setAlpha(200);
//        设置背景图片
        Random r = new Random();
        int random = r.nextInt(7);  // [0,7)
        v.setBackgroundResource(BackgroudImags[random]);
    }


    @Override
    public void onClick(View view) {
//        Intent intent = null;
        switch (view.getId()) {
            case R.id.login_tv_register:
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.login_btn_login://登录验证
                String name = username.getText().toString().trim();
                String password = userpwd.getText().toString().trim();
                ArrayList<user> data = mDBOpenHelper.getAllData();
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password)) {
                    boolean match = false;
                    for (int i = 0; i < data.size(); i++) {
                        user user = data.get(i);
                        if (name.equals(user.getName()) && password.equals(user.getPassword())) {
                            match = true;
                            break;
                        } else {
                            match = false;
                        }
                    }
                    if(match){
                            if (rememberCb.isChecked()) {
                                SharedPreferences.Editor editor = sp.edit();
                                editor.putString("USER_NAME", name);
                                editor.putString("PASSWORD", password);
                                editor.commit();
                            }
                        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent1);
                            finish();
                    }else {
                        Toast.makeText(this, "密码错误", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this,"请输入您的用户名或密码",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.login_tv_losspwd:
                Intent intent2 = new Intent(LoginActivity.this,ForgetPwdActivity.class);
                startActivity(intent2);
                break;
            case R.id.login_back:
                finish();
                break;
        }
    }
}