package com.example.desktop.Shouye.activity;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.desktop.Bean.wordbookbean.Cet4ReviewBean;
import com.example.desktop.Bean.wordbookbean.Cet4learnBean;
import com.example.desktop.Bean.wordbookbean.Cet6ReviewBean;
import com.example.desktop.Bean.wordbookbean.Cet6learnBean;
import com.example.desktop.Bean.wordbookbean.HeightWordBean;
import com.example.desktop.Bean.wordbookbean.HeightwordReviewBean;
import com.example.desktop.Fun.adapter.WordBag4Adapter;
import com.example.desktop.Fun.adapter.WordBag6Adapter;
import com.example.desktop.Fun.adapter.WordBag8Adapter;
import com.example.desktop.My.activity.FinishActivity;
import com.example.desktop.R;
import com.example.desktop.Util.LoadDataAsyncTask;
import com.example.desktop.Util.URLContent;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LearnWordActivity extends AppCompatActivity implements View.OnClickListener ,LoadDataAsyncTask.OnGetNetDataListener{

    TextView showTv,yinbiaoTv,wordmeanTv;
    RadioButton explainBtn,nextwordBtn,fanhuiBtn,bofangBtn;
    List<Cet4learnBean._$4Bean> list4;
    List<Cet6learnBean._$6Bean> list6;
    List<HeightWordBean._$8Bean> list8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_word);

        init();
        list4 = new ArrayList<>();
        list6 = new ArrayList<>();
        list8 = new ArrayList<>();
        wordmeanTv.setVisibility(View.GONE);
        loadData();
//        initData();
    }

    private void loadData() {
        SharedPreferences sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String name = sp.getString("USER_NAME","");
        int type = sp.getInt("wordtype",4);
//        加载网络数据
        String url = "";
        if(type == 4){
            url = URLContent.getCet4Word(name);
        }else if(type == 6){
            url = URLContent.getCet6Word(name);
        }else if(type == 8){
            url = URLContent.getHighWord(name);
        }
        LoadDataAsyncTask task = new LoadDataAsyncTask(this, this, true);
        task.execute(url);

    }


    private void initData() {
        wordmeanTv.setVisibility(View.GONE);
        SharedPreferences sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        int type = sp.getInt("wordtype",4);
        if(list4.size()!=0 || list6.size()!=0 || list8.size()!=0){
            if(type == 4){
//                Collections.shuffle(list4);
                showTv.setText(list4.get(0).getContent());
                yinbiaoTv.setText(list4.get(0).getPhonetic());
                wordmeanTv.setText(list4.get(0).getExplaination());
                list4.remove(0);
            }else if(type == 6){
//                Collections.shuffle(list6);
                showTv.setText(list6.get(0).getContent());
                yinbiaoTv.setText(list6.get(0).getPhonetic());
                wordmeanTv.setText(list6.get(0).getExplaination());
                list6.remove(0);

            }else if(type == 8){
//                Collections.shuffle(list8);
                showTv.setText(list8.get(0).getContent());
                yinbiaoTv.setText(list8.get(0).getPhonetic());
                wordmeanTv.setText(list8.get(0).getExplaination());
                list8.remove(0);
            }
        }else {
            Toast.makeText(this,"恭喜完成今日计划！",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LearnWordActivity.this, FinishActivity.class);
            startActivity(intent);
            finish();
        }

    }


    @Override
    public void onSuccess(String json) {
        if (!TextUtils.isEmpty(json)) {
            SharedPreferences sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
            int type = sp.getInt("wordtype",4);
            if(type == 4){
                Cet4learnBean bean = new Gson().fromJson(json, Cet4learnBean.class);
                List<Cet4learnBean._$4Bean> list = bean.get_$4();
                for (int i = 0; i < list.size(); i++) {
                    list4.add(list.get(i));
                }
            }else if(type == 6){
                Cet6learnBean bean = new Gson().fromJson(json, Cet6learnBean.class);
                List<Cet6learnBean._$6Bean> list = bean.get_$6();
                for (int i = 0; i < list.size(); i++) {
                    list6.add(list.get(i));
                }

            }else if(type == 8){
                HeightWordBean bean = new Gson().fromJson(json, HeightWordBean.class);
                List<HeightWordBean._$8Bean> list = bean.get_$8();
                for (int i = 0; i < list.size(); i++) {
                    list8.add(list.get(i));
                }
            }
        }

    }

    private void init() {
        explainBtn = findViewById(R.id.bdc_xiangjie);
        nextwordBtn = findViewById(R.id.bdc_xiayici);
        fanhuiBtn = findViewById(R.id.bdc_fanhuijian);
        bofangBtn = findViewById(R.id.bdc_bofang);
        showTv = findViewById(R.id.bdc_xianshidanci);
        yinbiaoTv = findViewById(R.id.bdc_yinbiao);
        wordmeanTv = findViewById(R.id.bdc_danciyisi);

        explainBtn.setOnClickListener(this);
        nextwordBtn.setOnClickListener(this);
        fanhuiBtn.setOnClickListener(this);
        bofangBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bdc_xiangjie:
                wordmeanTv.setVisibility(View.VISIBLE);
                break;
            case R.id.bdc_xiayici:
                initData();
                break;
            case R.id.bdc_fanhuijian:
                finish();
                break;
            case R.id.bdc_bofang:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        // do nothing
    }



}