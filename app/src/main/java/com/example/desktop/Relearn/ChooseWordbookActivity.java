package com.example.desktop.Relearn;

import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.desktop.Bean.planbean.PlanCountBean;
import com.example.desktop.MainActivity;
import com.example.desktop.R;
import com.example.desktop.Register.LoginActivity;
import com.example.desktop.Util.LoadDataAsyncTask;
import com.example.desktop.Util.URLContent;
import com.google.gson.Gson;

public class ChooseWordbookActivity extends AppCompatActivity implements View.OnClickListener,LoadDataAsyncTask.OnGetNetDataListener {

    private Button study4Btn,study6Btn,study8Btn,confirmBtn;
    private EditText inputPlanEt;
    private TextView plancount,planbook;
    private RelativeLayout book1Rl,book2Rl,book3Rl;
    private boolean is4Press = false;
    private boolean is6Press = false;
    private boolean is8Press = false;
    private boolean isbook1 = false;
    private boolean isbook2 = false;
    private boolean isbook3 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_wordbook);

        init();
        SharedPreferences sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String name = sp.getString("USER_NAME","");
        String url = URLContent.getPlanCount(name);
        LoadDataAsyncTask task = new LoadDataAsyncTask(this, new LoadDataAsyncTask.OnGetNetDataListener() {
            @Override
            public void onSuccess(String json) {
                if (!TextUtils.isEmpty(json)) {
                    PlanCountBean bean = new Gson().fromJson(json, PlanCountBean.class);
                    plancount.setText(bean.getPlan()+"");
                    SharedPreferences sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                    int type = sp.getInt("wordtype",0);
                    if(type == 4){
                        planbook.setText("大学四级");
                    }else if(type == 6){
                        planbook.setText("大学六级");
                    }else if(type == 8){
                        planbook.setText("考研词汇");
                    }else if (type == 0){
                        planbook.setText("当前未选择词书");
                    }
                }
            }
        }, false);
        task.execute(url);
    }

    private void init() {
        study4Btn = findViewById(R.id.uf);
        study6Btn = findViewById(R.id.us);
        study8Btn = findViewById(R.id.uw);
        confirmBtn = findViewById(R.id.comfirm);
        inputPlanEt = findViewById(R.id.choose_et);
        plancount = findViewById(R.id.plan_count);
        planbook = findViewById(R.id.plan_book);
        book1Rl = findViewById(R.id.book1_rl);
        book2Rl = findViewById(R.id.book2_rl);
        book3Rl = findViewById(R.id.book3_rl);

        confirmBtn.setOnClickListener(this);
        book1Rl.setOnClickListener(this);
        book2Rl.setOnClickListener(this);
        book3Rl.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.book1_rl:
                if(!isbook1){
                    book1Rl.setBackgroundResource(R.drawable.item);
                    book2Rl.setEnabled(false);
                    book3Rl.setEnabled(false);
                    isbook1 = true;
                }else {
                    book1Rl.setBackgroundResource(R.drawable.login_edittext);
                    book2Rl.setEnabled(true);
                    book3Rl.setEnabled(true);
                    isbook1 = false;
                }
                break;
            case R.id.book2_rl:
                if(!isbook2){
                    book2Rl.setBackgroundResource(R.drawable.item);
                    book1Rl.setEnabled(false);
                    book3Rl.setEnabled(false);
                    isbook2 = true;
                }else {
                    book2Rl.setBackgroundResource(R.drawable.login_edittext);
                    book1Rl.setEnabled(true);
                    book3Rl.setEnabled(true);
                    isbook2 = false;
                }
                break;
            case R.id.book3_rl:
                if(!isbook3){
                    book3Rl.setBackgroundResource(R.drawable.item);
                    book1Rl.setEnabled(false);
                    book2Rl.setEnabled(false);
                    isbook3 = true;
                }else {
                    book3Rl.setBackgroundResource(R.drawable.login_edittext);
                    book1Rl.setEnabled(true);
                    book2Rl.setEnabled(true);
                    isbook3 = false;
                }
                break;
            case R.id.comfirm:
                String planNum = inputPlanEt.getText().toString().trim();
                int plan = 0;
                if(!TextUtils.isEmpty(planNum)){
                    try {
                        plan = Integer.parseInt(planNum);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }else {
                    plan = 0;
                }
                if(plan < 10 || plan > 1000){
                    Toast.makeText(this,"请输入10~1000以内的数字",Toast.LENGTH_SHORT).show();
                }else{
                    SharedPreferences sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    String name = sp.getString("USER_NAME","");
                    String url = URLContent.getUpdatePlanCount(name,plan);
                    if(isbook1){
                        editor.putInt("wordtype",4);
                    }else if(isbook2){
                        editor.putInt("wordtype",6);
                    }else if(isbook3){
                        editor.putInt("wordtype",8);
                    }else {
                        editor.putInt("wordtype",0);
                        Toast.makeText(this,"请选择一本词书",Toast.LENGTH_SHORT).show();
                    }
                    editor.putInt("plan",plan);
                    editor.commit();
                    if(plan == 0) {
                        Toast.makeText(this,"请输入修改的值",Toast.LENGTH_SHORT).show();
                    }
                    if(sp.getInt("wordtype",0)!=0 && plan!=0){
                        LoadDataAsyncTask task = new LoadDataAsyncTask(this, this,true);
                        task.execute(url);
                        Toast.makeText(this,"修改成功！",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ChooseWordbookActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    break;
                }
                }

    }

    //    EditText的处理

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(ev.getAction()==MotionEvent.ACTION_DOWN){
            View v=getCurrentFocus();
            boolean  hideInputResult =isShouldHideInput(v,ev);
            if(hideInputResult){
                v.clearFocus();
                InputMethodManager imm = (InputMethodManager) ChooseWordbookActivity.this
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
        if (!TextUtils.isEmpty(json)) {
//            confirmBtn.setText("修改成功");
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}