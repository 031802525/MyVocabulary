package com.example.desktop.Register;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.desktop.Bean.userbean.CreateUserBean;
import com.example.desktop.Util.LoadDataAsyncTask;
import com.example.desktop.Util.URLContent;
import com.example.desktop.R;
import com.example.desktop.sql.DBOpenHelper;
import com.google.gson.Gson;

import java.util.Random;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, LoadDataAsyncTask.OnGetNetDataListener {

    private EditText rusername,ruserpwd,requstpwd;
    private Button registerBtn;
    private DBOpenHelper rDBOpenHelper;
    private ImageView rbackIv;
    private boolean isInUserTable = false;
    private boolean isPress = false;
    private SharedPreferences sp;
    private TextView submitTv;
    private String username,usrpwd,repwd;



    private int BackgroudImags[] = {R.drawable.bgc1,R.drawable.bgc2,R.drawable.bgc3,
            R.drawable.bgc4,R.drawable.bgc5,R.drawable.bgc6,
            R.drawable.bgc7,R.drawable.bgc8,R.drawable.bgc9,
            R.drawable.bgc10,R.drawable.bgc11,R.drawable.bgc12,R.drawable.bgc13,R.drawable.bgc14,
            R.drawable.bgc15,R.drawable.bgc16,R.drawable.bgc17,R.drawable.bgc18,R.drawable.bgc19,
            R.drawable.bgc20,R.drawable.bgc21,R.drawable.bgc22,R.drawable.bgc23,R.drawable.bgc24,
            R.drawable.bgc25,R.drawable.bgc26};

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
        submitTv = findViewById(R.id.register_submit);

//        设置点击事件
        rbackIv.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
        submitTv.setOnClickListener(this);
    }

    private void setRegisterBackgroud() {
//        设置背景虚化程度
        LinearLayout backgroud = findViewById(R.id.register_backgroud);
//         设置背景图片
        Random r = new Random();
        int random = r.nextInt(26);  // [0,7)
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
                InputMethodManager imm = (InputMethodManager) RegisterActivity.this
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
            CreateUserBean bean = new Gson().fromJson(json, CreateUserBean.class);
            if(!TextUtils.isEmpty(bean.getType())){
                isInUserTable = true;
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.register_btn_register:
                if(isPress){
                    if (isInUserTable) {
                        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(this, "验证通过，注册成功", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(this, "该用户已存在", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(this, "请先验证~", Toast.LENGTH_SHORT).show();

                }
                isPress = false;
                break;
            case R.id.register_back:
                finish();
                break;
            case R.id.register_submit:
                isPress = true;
                username = rusername.getText().toString().trim();
                usrpwd = ruserpwd.getText().toString().trim();
                repwd = requstpwd.getText().toString().trim();
                if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(usrpwd) && !TextUtils.isEmpty(repwd)
                        && (usrpwd.equals(repwd))) {

                    String url = URLContent.getCreateUser(username, usrpwd);
                    LoadDataAsyncTask task = new LoadDataAsyncTask(this, this, true);
                    task.execute(url);
                }else if (!usrpwd.equals(repwd)) {
                    Toast.makeText(this,"两次输入密码不一致，请重新输入",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this,"未完善信息，注册失败",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


}
