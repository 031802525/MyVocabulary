package com.example.desktop.Shouye.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.desktop.Bean.translatebean.EnglishToChineseBean;
import com.example.desktop.R;
import com.example.desktop.Util.LoadDataAsyncTask;
import com.example.desktop.Util.URLContent;
import com.google.gson.Gson;

import java.util.List;

public class EnglishToChineseActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView backIv,searchIv,fanzhuanIv;
    private EditText inputwordEt;
    private TextView englishTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_english_to_chinese);

        init();

    }

    private void init() {
        backIv = findViewById(R.id.back2);
        searchIv = findViewById(R.id.sousuo2);
        fanzhuanIv = findViewById(R.id.fanzhuan2);
        inputwordEt = findViewById(R.id.editText2);
        englishTv = findViewById(R.id.yiwen2);

        backIv.setOnClickListener(this);
        fanzhuanIv.setOnClickListener(this);
        searchIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back2:
                finish();
                break;
            case R.id.fanzhuan2:
                Intent intent = new Intent(EnglishToChineseActivity.this,ChineseToEnglishActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.sousuo2:
                final String english = inputwordEt.getText().toString().trim();
                String url = URLContent.getEnglishToChinese(english);
                LoadDataAsyncTask task = new LoadDataAsyncTask(this, new LoadDataAsyncTask.OnGetNetDataListener() {
                    @Override
                    public void onSuccess(String json) {
                        if (!TextUtils.isEmpty(json)) {
                            EnglishToChineseBean bean = new Gson().fromJson(json, EnglishToChineseBean.class);
                            List<EnglishToChineseBean.TransResultBean> result = bean.getTrans_result();
                            englishTv.setText(result.get(0).getDst());
                        }
                    }
                }, false);
                task.execute(url);
                break;
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
                InputMethodManager imm = (InputMethodManager) EnglishToChineseActivity.this
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

}