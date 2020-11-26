package com.example.desktop.Shouye.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.desktop.Bean.translatebean.EnglishToChineseWordBean;
import com.example.desktop.R;
import com.example.desktop.Shouye.adapter.SearchWordAdapter;
import com.example.desktop.Util.LoadDataAsyncTask;
import com.example.desktop.Util.URLContent;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchWordActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView backIv,searchIv,vocalIv;
    private EditText inputwordEt;
    private TextView wordTv,meanTv;
    private ListView sentenceLv;
    private String word = "";
    List<EnglishToChineseWordBean.WebBean> mDatas;
    private SearchWordAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_word);

        init();
        mDatas = new ArrayList<>();
        adapter = new SearchWordAdapter(this, mDatas);
        sentenceLv.setAdapter(adapter);

    }

    private void init() {
        backIv = findViewById(R.id.search_iv_back);
        searchIv = findViewById(R.id.search_Iv_search);
        vocalIv = findViewById(R.id.search_Iv_vocal);
        inputwordEt = findViewById(R.id.search_et_inputword);
        wordTv = findViewById(R.id.search_tv_word);
        meanTv = findViewById(R.id.search_tv_mean);
        sentenceLv = findViewById(R.id.search_lv_sentence);

        backIv.setOnClickListener(this);
        searchIv.setOnClickListener(this);
        vocalIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.search_iv_back:
                finish();
                break;
            case R.id.search_Iv_search:
                word = inputwordEt.getText().toString().trim();
                String url = URLContent.getWordTranslateURL(word);
                LoadDataAsyncTask task = new LoadDataAsyncTask(this, new LoadDataAsyncTask.OnGetNetDataListener() {
                    @Override
                    public void onSuccess(String json) {
                        if (!TextUtils.isEmpty(json)) {
                            EnglishToChineseWordBean bean = new Gson().fromJson(json, EnglishToChineseWordBean.class);
                            wordTv.setText(bean.getQuery());
                            List<String> translation = bean.getTranslation();
                            String s = "";
                            for (int i = 0; i < translation.size(); i++) {
                                s = s + translation.get(i) + "\n";
                            }
                            meanTv.setText(s);
                            List<EnglishToChineseWordBean.WebBean> list = bean.getWeb();
                            for (int i = 0; i < list.size(); i++) {
                                mDatas.add(list.get(i));
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                }, false);
                task.execute(url);
                break;
            case R.id.search_Iv_vocal:
//                音频资源无效
//                String mp3URL = URLContent.getEngPronuns(word);
//                SharedPreferences everySentence = getSharedPreferences("everysentence",MODE_PRIVATE);
//                String mp3URL = everySentence.getString("mp3url","");
//                if(!TextUtils.isEmpty(mp3URL)) {
//                    MediaPlayer mediaPlayer = new MediaPlayer();
//                    try {
//                        mediaPlayer.setDataSource(mp3URL);
//                        mediaPlayer.prepare();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    mediaPlayer.start();
//                }else {
//                    Toast.makeText(this,"无音频资源！",Toast.LENGTH_SHORT).show();
//                }

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
                InputMethodManager imm = (InputMethodManager) SearchWordActivity.this
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
//        do nothing
    }
}