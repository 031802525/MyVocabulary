package com.example.desktop.Relearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.desktop.Bean.wordbookbean.Cet4ReviewBean;
import com.example.desktop.Bean.wordbookbean.Cet6ReviewBean;
import com.example.desktop.Bean.wordbookbean.HeightwordReviewBean;
import com.example.desktop.Fun.adapter.LinkGame4Adapter;
import com.example.desktop.Fun.adapter.LinkGame6Adapter;
import com.example.desktop.Fun.adapter.LinkGame8Adapter;
import com.example.desktop.Fun.adapter.LinkGameExplain4Adapter;
import com.example.desktop.Fun.adapter.LinkGameExplain6Adapter;
import com.example.desktop.Fun.adapter.LinkGameExplain8Adapter;
import com.example.desktop.MainActivity;
import com.example.desktop.My.activity.FinishActivity;
import com.example.desktop.R;
import com.example.desktop.Shouye.activity.LearnWordActivity;
import com.example.desktop.Util.LoadDataAsyncTask;
import com.example.desktop.Util.URLContent;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DueToEnglishActivity extends AppCompatActivity implements View.OnClickListener,LoadDataAsyncTask.OnGetNetDataListener{

    private RadioButton backRb,vocalRb;
    private TextView wordTv,yinbiaoTv,hintTv;
    private Button word1Btn,word2Btn,word3Btn,word4Btn;

    List<Cet4ReviewBean._$4Bean> wordmDatas4;
    List<Cet6ReviewBean._$6Bean> wordmDatas6;
    List<HeightwordReviewBean._$8Bean> wordmDatas8;

    private String[] explainword = {"vi.吹，吹动；吹响","vt.压迫，压制；压抑","vt.概括出vi.形成概念","n.心情，情绪；语气","n.愤怒，愤慨，义愤",
            "n.激光","n.雾；烟雾，尘雾","vt.围住，圈起；附上","a.可溶的；可以解决的"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_word);

        init();
        wordmDatas4 = new ArrayList<>();
        wordmDatas6 = new ArrayList<>();
        wordmDatas8 = new ArrayList<>();

        loadData();

//        initData();
    }


    private void loadData() {
        SharedPreferences sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String name = sp.getString("USER_NAME","");
        int type = sp.getInt("wordtype",4);
        int plan = sp.getInt("plan",10);
        String url = "";
        if(type == 4){
            url = URLContent.getCet4Review(name,plan);
        }else if(type == 6){
            url = URLContent.getCet6Review(name,plan);
        }else if(type == 8){
            url = URLContent.getCet8Review(name,plan);
        }
        LoadDataAsyncTask task = new LoadDataAsyncTask(this, this, true);
        task.execute(url);
    }

    private void initData() {
        word1Btn.setTextColor(Color.BLACK);
        word2Btn.setTextColor(Color.BLACK);
        word3Btn.setTextColor(Color.BLACK);
        word4Btn.setTextColor(Color.BLACK);
        SharedPreferences sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        int type = sp.getInt("wordtype",4);
        if(wordmDatas4.size()!=0 || wordmDatas6.size()!=0 || wordmDatas8.size()!=0){
            if(type == 4){
                wordTv.setText(wordmDatas4.get(0).getContent());
                yinbiaoTv.setText(wordmDatas4.get(0).getPhonetic());
                hintTv.setText(wordmDatas4.get(0).getExplaination());
                if(wordmDatas4.size()>4 && wordmDatas4!=null){
                    word1Btn.setText(wordmDatas4.get(1).getExplaination());
                    word2Btn.setText(wordmDatas4.get(0).getExplaination());
                    word3Btn.setText(wordmDatas4.get(2).getExplaination());
                    word4Btn.setText(wordmDatas4.get(3).getExplaination());
                }else {
                    word1Btn.setText(explainword[1]);
                    word2Btn.setText(hintTv.getText().toString());
                    word3Btn.setText(explainword[2]);
                    word4Btn.setText(explainword[3]);
                }
                wordmDatas4.remove(0);


            }else if(type == 6){
                wordTv.setText(wordmDatas6.get(0).getContent());
                yinbiaoTv.setText(wordmDatas6.get(0).getPhonetic());
                hintTv.setText(wordmDatas6.get(0).getExplaination());
                if(wordmDatas6.size()>4 && wordmDatas6!=null){
                    word1Btn.setText(wordmDatas6.get(2).getExplaination());
                    word2Btn.setText(wordmDatas6.get(3).getExplaination());
                    word3Btn.setText(wordmDatas6.get(0).getExplaination());
                    word4Btn.setText(wordmDatas6.get(1).getExplaination());
                }else {
                    word1Btn.setText(explainword[2]);
                    word2Btn.setText(explainword[3]);
                    word3Btn.setText(hintTv.getText().toString());
                    word4Btn.setText(explainword[1]);
                }
                wordmDatas6.remove(0);

            }else if(type == 8){
                wordTv.setText(wordmDatas8.get(0).getContent());
                yinbiaoTv.setText(wordmDatas8.get(0).getPhonetic());
                hintTv.setText(wordmDatas8.get(0).getExplaination());
                if(wordmDatas8.size()>4 && wordmDatas8!=null){
                    word1Btn.setText(wordmDatas8.get(3).getExplaination());
                    word2Btn.setText(wordmDatas8.get(1).getExplaination());
                    word3Btn.setText(wordmDatas8.get(2).getExplaination());
                    word4Btn.setText(wordmDatas8.get(0).getExplaination());
                }else {
                    word1Btn.setText(explainword[3]);
                    word2Btn.setText(explainword[1]);
                    word3Btn.setText(explainword[2]);
                    word4Btn.setText(hintTv.getText().toString());
                }
                wordmDatas8.remove(0);

            }
        }else {
            Toast.makeText(this,"恭喜完成复习！",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(DueToEnglishActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }


    @Override
    public void onSuccess(String json) {
        if(!TextUtils.isEmpty(json)){
            SharedPreferences sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
            int type = sp.getInt("wordtype",4);
            if(type == 4){
                Cet4ReviewBean bean = new Gson().fromJson(json, Cet4ReviewBean.class);
                List<Cet4ReviewBean._$4Bean> list = bean.get_$4();
                for (int i = 0; i < list.size(); i++) {
                    wordmDatas4.add(list.get(i));
                }
//            打乱list
                Collections.shuffle(wordmDatas4);

            }else if(type == 6){
                Cet6ReviewBean bean = new Gson().fromJson(json, Cet6ReviewBean.class);
                List<Cet6ReviewBean._$6Bean> list = bean.get_$6();
                for (int i = 0; i < list.size(); i++) {
                    wordmDatas6.add(list.get(i));
                }
//            打乱list
                Collections.shuffle(wordmDatas6);

            }else if(type == 8){
                HeightwordReviewBean bean = new Gson().fromJson(json, HeightwordReviewBean.class);
                List<HeightwordReviewBean._$8Bean> list = bean.get_$8();
                for (int i = 0; i < list.size(); i++) {
                    wordmDatas8.add(list.get(i));
                }
//            打乱list
                Collections.shuffle(wordmDatas8);
            }

        }

    }
    private void init() {
        backRb = findViewById(R.id.duetoen_rb_back);
        vocalRb = findViewById(R.id.duetoen_rb_vocal);
        wordTv = findViewById(R.id.duetoen_tv_word);
        yinbiaoTv = findViewById(R.id.duetoen_tv_yinbiao);
        hintTv = findViewById(R.id.duetoen_tv_hint);
        word1Btn = findViewById(R.id.mean1);
        word2Btn = findViewById(R.id.mean2);
        word3Btn = findViewById(R.id.mean3);
        word4Btn = findViewById(R.id.mean4);

        backRb.setOnClickListener(this);
        vocalRb.setOnClickListener(this);
        word1Btn.setOnClickListener(this);
        word2Btn.setOnClickListener(this);
        word3Btn.setOnClickListener(this);
        word4Btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.duetoen_rb_back:
                finish();
                break;
            case R.id.duetoen_rb_vocal:

                break;
            case R.id.mean1:
                String wordmean = hintTv.getText().toString();
                String choosemean = word1Btn.getText().toString();
                if(wordmean.equals(choosemean)){

//                    word1Btn.setBackgroundColor(Color.GREEN);
                    initData();
                }else {
                    word1Btn.setTextColor(Color.RED);
                }
                break;
            case R.id.mean2:
                String wordmean1 = hintTv.getText().toString();
                String choosemean1 = word2Btn.getText().toString();
                if(wordmean1.equals(choosemean1)){

//                    word2Btn.setBackgroundColor(Color.GREEN);
                    initData();
                }else {
                    word2Btn.setTextColor(Color.RED);
                }
                break;
            case R.id.mean3:
                String wordmean2 = hintTv.getText().toString();
                String choosemean2 = word3Btn.getText().toString();
                if(wordmean2.equals(choosemean2)){

//                    word3Btn.setBackgroundColor(Color.GREEN);
                    initData();
                }else {
                    word3Btn.setTextColor(Color.RED);
                }
                break;
            case R.id.mean4:
                String wordmean3 = hintTv.getText().toString();
                String choosemean3 = word4Btn.getText().toString();
                if(wordmean3.equals(choosemean3)){

//                    word4Btn.setBackgroundColor(Color.GREEN);
                    initData();
                }else {
                    word4Btn.setTextColor(Color.RED);
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
//        do nothing
    }
}