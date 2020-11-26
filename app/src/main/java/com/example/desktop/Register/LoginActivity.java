package com.example.desktop.Register;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.desktop.Bean.userbean.LoginUserBean;
import com.example.desktop.Relearn.ChooseWordbookActivity;
import com.example.desktop.Util.LoadDataAsyncTask;
import com.example.desktop.Util.URLContent;
import com.example.desktop.MainActivity;
import com.example.desktop.R;
import com.google.gson.Gson;

import java.util.Random;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoadDataAsyncTask.OnGetNetDataListener {

    private EditText username,userpwd;
    private TextView logintoRegisterTv,forgetTv;
    private ImageView backIv;
    private Button loginBtn;
    private CheckBox rememberCb,autoCb;
    private boolean isPress = false;
    private int BackgroudImags[] = {R.drawable.bgc1,R.drawable.bgc2,R.drawable.bgc3,
            R.drawable.bgc4,R.drawable.bgc5,R.drawable.bgc6,
            R.drawable.bgc7};
    private SharedPreferences sp;
    private String okName,okPwd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//保存实例状态
        setContentView(R.layout.activity_login);//设置视图内容的配置文件
//        初始化控件
        init();
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
        LinearLayout backgroud = findViewById(R.id.login_backgroud);
//         设置背景图片
        Random r = new Random();
        int random = r.nextInt(7);  // [0,7)
        backgroud.setBackgroundResource(BackgroudImags[random]);
        backgroud.getBackground().setAlpha(150);

    }

//    EditText的处理

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(ev.getAction()==MotionEvent.ACTION_DOWN){
            View v=getCurrentFocus();
            boolean  hideInputResult =isShouldHideInput(v,ev);
            if(hideInputResult){
                v.clearFocus();
                InputMethodManager imm = (InputMethodManager) LoginActivity.this
                        .getSystemService(Activity.INPUT_METHOD_SERVICE);
                if(v != null){
                    if(imm.isActive()){
                        imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }
    public  boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = { 0, 0 };
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            //之前一直不成功的原因是,getX获取的是相对父视图的坐标,getRawX获取的才是相对屏幕原点的坐标！！！
            if (event.getRawX() > left && event.getRawX() < right
                    && event.getRawY() > top && event.getRawY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }



    @Override
    public void onSuccess(String json) {
        if(!TextUtils.isEmpty(json)){
            LoginUserBean bean = new Gson().fromJson(json, LoginUserBean.class);
            okName = bean.getId();
            okPwd = bean.getPassword();

        }
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
                if(!isPress){
                    String url = URLContent.getUserLogin(name,password);
                    LoadDataAsyncTask task = new LoadDataAsyncTask(this, this, false);
                    task.execute(url);
                    isPress = true;
                    Toast.makeText(this, "验证成功~ 请再点击一次", Toast.LENGTH_SHORT).show();
                }else{

                    if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password)) {
                        if(name.equals(okName) && password.equals(okPwd)){

                            if (rememberCb.isChecked()) {
                                SharedPreferences.Editor editor = sp.edit();
                                editor.putString("USER_NAME", okName);
                                editor.putString("PASSWORD", okPwd);
                                editor.commit();
                            }
                            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                            Intent intent1 = new Intent(LoginActivity.this, ChooseWordbookActivity.class);
                            startActivity(intent1);
                            finish();
                        }
                        else {
                            Toast.makeText(this, "密码错误或该用户不存在", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(this, "请输入您的用户名或密码", Toast.LENGTH_SHORT).show();
                    }
                    isPress = false;
                }

                break;
            case R.id.login_tv_losspwd:
                Intent intent2 = new Intent(LoginActivity.this,ForgetPwdActivity.class);
                startActivity(intent2);
                break;
            case R.id.login_back:
//                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        // do nothing
    }

}