package com.example.desktop.Register;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.desktop.R;
import com.example.desktop.sql.DBOpenHelper;
import com.example.desktop.sql.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ForgetPwdActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView fbackIv;
    private EditText fusername,fpassword;
    private Button forgetBtn;
    private TextView getpwd;
    private DBOpenHelper fDBOpenHelper;
    private int BackgroudImags[] = {R.drawable.backgroud_1,R.drawable.backgroud_2,R.drawable.backgroud_3,
            R.drawable.backgroud_4,R.drawable.backgroud_5,R.drawable.backgroud_6,
            R.drawable.backgroud_7};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pwd);

        init();
        fDBOpenHelper = new DBOpenHelper(this);
        setRegisterBackgroud();
    }

    private void init() {
        fbackIv = findViewById(R.id.forget_back);
        fusername = findViewById(R.id.forget_username);
        fpassword = findViewById(R.id.forget_pwd);
        forgetBtn = findViewById(R.id.forget_btn);
        getpwd = findViewById(R.id.forget_tv_getpwd);

        forgetBtn.setOnClickListener(this);
        getpwd.setOnClickListener(this);
        fbackIv.setOnClickListener(this);
    }

    private void setRegisterBackgroud() {
//        设置背景虚化程度
        View v = findViewById(R.id.forget_backgroud);
        v.getBackground().setAlpha(200);
//        设置背景图片
        Random r = new Random();
        int random = r.nextInt(7);  // [0,7)
        v.setBackgroundResource(BackgroudImags[random]);
    }

    @Override
    public void onClick(View view) {
        String username = fusername.getText().toString().trim();
        String password = fpassword.getText().toString().trim();
        ArrayList<user> data = fDBOpenHelper.getAllData();
        switch (view.getId()){
            case R.id.forget_btn:
                for (int i = 0; i < data.size(); i++) {
                    user user = data.get(i);
                    if(username.equals(user.getName())){
                        fDBOpenHelper.updatae(password);
                        Toast.makeText(this,"修改成功",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(this,"该用户不存在",Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.forget_tv_getpwd:
                for (int i = 0; i < data.size(); i++) {
                    user user = data.get(i);
                    if(username.equals(user.getName())) {
                        Toast.makeText(this, user.getPassword(), Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(this,"该用户不存在",Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.forget_back:
                finish();
                break;
        }
    }
}
