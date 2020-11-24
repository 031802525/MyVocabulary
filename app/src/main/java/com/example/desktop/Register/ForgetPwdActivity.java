package com.example.desktop.Register;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.desktop.Bean.userbean.CreateUserBean;
import com.example.desktop.Bean.userbean.UpdatePwdBean;
import com.example.desktop.Util.LoadDataAsyncTask;
import com.example.desktop.Util.URLContent;
import com.example.desktop.R;
import com.example.desktop.sql.DBOpenHelper;
import com.example.desktop.sql.user;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Random;

public class ForgetPwdActivity extends AppCompatActivity implements View.OnClickListener, LoadDataAsyncTask.OnGetNetDataListener {

    private ImageView fbackIv;
    private EditText fusername,fpassword;
    private Button forgetBtn;
    private TextView getpwd;
    private DBOpenHelper fDBOpenHelper;
    private int BackgroudImags[] = {R.drawable.backgroud_1,R.drawable.backgroud_2,R.drawable.backgroud_3,
            R.drawable.backgroud_4,R.drawable.backgroud_5,R.drawable.backgroud_6,
            R.drawable.backgroud_7};

    private boolean isCheck = false;
    private boolean isPress = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pwd);

        init();
        setForgetBackgroud();
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

    private void setForgetBackgroud() {
//        设置背景虚化程度
        LinearLayout backgroud = findViewById(R.id.forget_backgroud);
//         设置背景图片
        Random r = new Random();
        int random = r.nextInt(7);  // [0,7)
        backgroud.setBackgroundResource(BackgroudImags[random]);
        backgroud.getBackground().setAlpha(150);

    }

    @Override
    public void onSuccess(String json) {

        if(!TextUtils.isEmpty(json)){
            UpdatePwdBean bean = new Gson().fromJson(json, UpdatePwdBean.class);
            if(!TextUtils.isEmpty(bean.getType())){
                isCheck = true;
            }
        }
    }


    @Override
    public void onClick(View view) {
        String username = fusername.getText().toString().trim();
        String password = fpassword.getText().toString().trim();
        switch (view.getId()){
            case R.id.forget_btn:
                if(isPress){
                    if(isCheck){
                        Toast.makeText(this,"修改成功",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this,LoginActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(this,"该用户不存在",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(this, "请先验证~", Toast.LENGTH_SHORT).show();
                }
                isPress = false;

                break;
            case R.id.forget_tv_getpwd:
                isPress = true;
                String url = URLContent.getChangePwd(username,password);
                LoadDataAsyncTask task = new LoadDataAsyncTask(this, this, true);
                task.execute(url);
                break;
            case R.id.forget_back:
                finish();
                break;
        }
    }


}
