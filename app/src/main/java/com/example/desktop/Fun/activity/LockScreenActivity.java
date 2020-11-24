package com.example.desktop.Fun.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.desktop.Bean.wordbookbean.Cet4ReviewBean;
import com.example.desktop.R;
import com.example.desktop.Util.LoadDataAsyncTask;
import com.example.desktop.Util.URLContent;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class LockScreenActivity extends AppCompatActivity implements LoadDataAsyncTask.OnGetNetDataListener {

    private Button submitBtn;
    private EditText inputWord;
    private LinearLayout bodyLL;
    private String translation = "v.提交";
    private String word = "submit";
    private String inputword;
    private ImageView imageIv;
    private TextView sentenceTv,translationTv;
    private TextView contentTv;
    private int inputCount = 0;
    private int planCount = 10;
    private String wordmean,wordtran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        setContentView(R.layout.activity_lock_screen);

        contentTv = findViewById(R.id.lockscreen_tv_translate);
        bodyLL = findViewById(R.id.lockscreen_ll_body);
        submitBtn = findViewById(R.id.lockscreen_btn_submit);
        inputWord = findViewById(R.id.lockscreen_et_input);
        imageIv = findViewById(R.id.lockscreen_image);
        sentenceTv = findViewById(R.id.lockscreen_tv_sentence);
        translationTv = findViewById(R.id.lockscreen_tv_sentranslate);

//        加载复习数据 默认四级词包
        SharedPreferences sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String name = sp.getString("USER_NAME","");
        String url = URLContent.getCet4Review(name,planCount);
        LoadDataAsyncTask task = new LoadDataAsyncTask(this, this, true);
        task.execute(url);

        if (TextUtils.isEmpty(wordtran)){
            contentTv.setText(translation);
        }

//        展示图片
        showImage();

        inputWord.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){

                }else{
                    inputword = inputWord.getText().toString();
                }
            }
        });
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(wordmean)){
                    if(wordmean.equals(inputword)){
                        Toast.makeText(getApplicationContext(),"解锁成功！",Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        inputCount++;
                        if(inputCount > 3){
                            Toast.makeText(getApplicationContext(),wordmean,Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(),"输入错误！",Toast.LENGTH_SHORT).show();
                        }
                    }
                }else {
                    if(word.equals(inputword)){
                        Toast.makeText(getApplicationContext(),"解锁成功！",Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        inputCount++;
                        if(inputCount > 3){
                            Toast.makeText(getApplicationContext(),word,Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(),"输入错误！",Toast.LENGTH_SHORT).show();
                        }
                    }
                }


            }
        });
    }

    private void showImage() {
        SharedPreferences everySentence = getSharedPreferences("everysentence",MODE_PRIVATE);
        String picURL = everySentence.getString("picurl","");
        String sentence = everySentence.getString("sentence","美好的一天！");
        String translation = everySentence.getString("translation","What a good day!");
        sentenceTv.setText(sentence);
        translationTv.setText(translation);
        if(!TextUtils.isEmpty(picURL)){
            Picasso.with(this).load(picURL).into(imageIv);
        }else {
            imageIv.setBackgroundResource(R.mipmap.fun);
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
                InputMethodManager imm = (InputMethodManager) LockScreenActivity.this
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
    public void onBackPressed() {
        // do nothing
    }

    @Override
    public void onSuccess(String json) {

        if (!TextUtils.isEmpty(json)) {
            Cet4ReviewBean bean = new Gson().fromJson(json, Cet4ReviewBean.class);
            List<Cet4ReviewBean._$4Bean> wordlist = bean.get_$4();
            Collections.shuffle(wordlist);
            wordtran = wordlist.get(0).getExplaination();
            wordmean = wordlist.get(0).getContent();
            contentTv.setText(wordtran);
        }
    }
}
