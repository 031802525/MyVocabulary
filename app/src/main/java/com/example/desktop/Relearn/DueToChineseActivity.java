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
import com.example.desktop.Bean.wordbookbean.GetNear4WordBean;
import com.example.desktop.Bean.wordbookbean.HeightwordReviewBean;
import com.example.desktop.MainActivity;
import com.example.desktop.R;
import com.example.desktop.Util.LoadDataAsyncTask;
import com.example.desktop.Util.URLContent;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DueToChineseActivity extends AppCompatActivity implements View.OnClickListener {

    private RadioButton backRb,vocalRb;
    private TextView wordTv,yinbiaoTv,hintTv;
    private Button mean1Btn,mean2Btn,mean3Btn,mean4Btn;

    List<Cet4ReviewBean._$4Bean> wordmDatas4;
    List<Cet6ReviewBean._$6Bean> wordmDatas6;
    List<HeightwordReviewBean._$8Bean> wordmDatas8;

    private String[] word = {"blow","oppress","generalize","mood","indignation","laser","command","fog","enclose","soluble"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_due_to_chinese);

        init();
        wordmDatas4 = new ArrayList<>();
        wordmDatas6 = new ArrayList<>();
        wordmDatas8 = new ArrayList<>();

        loadData();

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
        LoadDataAsyncTask task = new LoadDataAsyncTask(this, new LoadDataAsyncTask.OnGetNetDataListener() {
            @Override
            public void onSuccess(String json) {
                if(!TextUtils.isEmpty(json)){
                    SharedPreferences sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
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
        }, true);
        task.execute(url);

    }


    private void initData() {
        mean1Btn.setTextColor(Color.BLACK);
        mean2Btn.setTextColor(Color.BLACK);
        mean3Btn.setTextColor(Color.BLACK);
        mean4Btn.setTextColor(Color.BLACK);
        SharedPreferences sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        int type = sp.getInt("wordtype",4);
        if(wordmDatas4.size()!=0 || wordmDatas6.size()!=0 || wordmDatas8.size()!=0){
            if(type == 4){
                wordTv.setText(wordmDatas4.get(0).getExplaination());
                yinbiaoTv.setText(wordmDatas4.get(0).getPhonetic());
                hintTv.setText(wordmDatas4.get(0).getContent());
                if(wordmDatas4.size()>4 && wordmDatas4!=null){
                    mean1Btn.setText(wordmDatas4.get(1).getContent());
                    mean2Btn.setText(wordmDatas4.get(0).getContent());
                    mean3Btn.setText(wordmDatas4.get(2).getContent());
                    mean4Btn.setText(wordmDatas4.get(3).getContent());
                }else {
                    mean1Btn.setText(word[1]);
                    mean2Btn.setText(hintTv.getText().toString());
                    mean3Btn.setText(word[2]);
                    mean4Btn.setText(word[3]);
                }
                wordmDatas4.remove(0);
            }else if(type == 6){
                wordTv.setText(wordmDatas6.get(0).getExplaination());
                yinbiaoTv.setText(wordmDatas6.get(0).getPhonetic());
                hintTv.setText(wordmDatas6.get(0).getContent());
                if(wordmDatas6.size()>4 && wordmDatas6!=null){
                    mean1Btn.setText(wordmDatas6.get(2).getContent());
                    mean2Btn.setText(wordmDatas6.get(3).getContent());
                    mean3Btn.setText(wordmDatas6.get(0).getContent());
                    mean4Btn.setText(wordmDatas6.get(1).getContent());
                }else {
                    mean1Btn.setText(word[2]);
                    mean2Btn.setText(word[3]);
                    mean3Btn.setText(hintTv.getText().toString());
                    mean4Btn.setText(word[1]);
                }
                wordmDatas6.remove(0);

            }else if(type == 8){
                wordTv.setText(wordmDatas8.get(0).getExplaination());
                yinbiaoTv.setText(wordmDatas8.get(0).getPhonetic());
                hintTv.setText(wordmDatas8.get(0).getContent());
                if(wordmDatas8.size()>4 && wordmDatas8!=null){
                    mean1Btn.setText(wordmDatas8.get(3).getContent());
                    mean2Btn.setText(wordmDatas8.get(1).getContent());
                    mean3Btn.setText(wordmDatas8.get(2).getContent());
                    mean4Btn.setText(wordmDatas8.get(0).getContent());
                }else {
                    mean1Btn.setText(word[3]);
                    mean2Btn.setText(word[1]);
                    mean3Btn.setText(word[2]);
                    mean4Btn.setText(hintTv.getText().toString());
                }
                wordmDatas8.remove(0);

            }
        }else {
            Toast.makeText(this,"恭喜完成复习！",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(DueToChineseActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }


    private void init() {
        backRb = findViewById(R.id.duetoch_rb_back);
        vocalRb = findViewById(R.id.duetoch_rb_vocal);
        wordTv = findViewById(R.id.duetoch_tv_word);
        yinbiaoTv = findViewById(R.id.duetoch_tv_yinbiao);
        hintTv = findViewById(R.id.duetoch_tv_hint);
        mean1Btn = findViewById(R.id.word1);
        mean2Btn = findViewById(R.id.word2);
        mean3Btn = findViewById(R.id.word3);
        mean4Btn = findViewById(R.id.word4);

        backRb.setOnClickListener(this);
        vocalRb.setOnClickListener(this);
        mean1Btn.setOnClickListener(this);
        mean2Btn.setOnClickListener(this);
        mean3Btn.setOnClickListener(this);
        mean4Btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.duetoch_rb_back:
                finish();
                break;
            case R.id.duetoch_rb_vocal:

                break;
            case R.id.word1:
                String wordmean = hintTv.getText().toString();
                String chooseword = mean1Btn.getText().toString();
                if(wordmean.equals(chooseword)){
                    initData();
                }else {
                    mean1Btn.setTextColor(Color.RED);
                }
                break;
            case R.id.word2:
                String wordmean1 = hintTv.getText().toString();
                String chooseword1 = mean2Btn.getText().toString();
                if(wordmean1.equals(chooseword1)){
                    initData();
                }else {
                    mean2Btn.setTextColor(Color.RED);
                }
                break;
            case R.id.word3:
                String wordmean2 = hintTv.getText().toString();
                String chooseword2 = mean3Btn.getText().toString();
                if(wordmean2.equals(chooseword2)){
                    initData();
                }else {
                    mean3Btn.setTextColor(Color.RED);
                }
                break;
            case R.id.word4:
                String wordmean3 = hintTv.getText().toString();
                String chooseword3 = mean4Btn.getText().toString();
                if(wordmean3.equals(chooseword3)){
                    initData();
                }else {
                    mean4Btn.setTextColor(Color.RED);
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
//        do nothing
    }
}