package com.example.desktop.Fun.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.desktop.Bean.wordbookbean.Cet4ReviewBean;
import com.example.desktop.Fun.adapter.LinkGameAdapter;
import com.example.desktop.Fun.adapter.LinkGameExplainAdapter;
import com.example.desktop.Fun.util.CountDownTimerUtil;
import com.example.desktop.R;
import com.example.desktop.Util.LoadDataAsyncTask;
import com.example.desktop.Util.URLContent;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LinkGameActivity extends AppCompatActivity implements View.OnClickListener,LoadDataAsyncTask.OnGetNetDataListener{

    private TextView timehoderTv,wordbagTv,backTv;
    private ListView leftwordspellLv,rightwordtranslateLv;
    private long settime = 60000;
    private CountDownTimerUtil timer;
    List<Cet4ReviewBean._$4Bean> wordmDatas;
    List<Cet4ReviewBean._$4Bean> explainmDatas;
    private LinkGameAdapter wordadapter;
    private int planCount = 10;
    private LinkGameExplainAdapter explainAdapter;
    private String word,translation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_game);

        init();

        wordmDatas = new ArrayList<>();
        explainmDatas = new ArrayList<>();
        wordadapter = new LinkGameAdapter(this, wordmDatas);
        explainAdapter = new LinkGameExplainAdapter(this, explainmDatas);
        leftwordspellLv.setAdapter(wordadapter);
        rightwordtranslateLv.setAdapter(explainAdapter);

//        加载复习数据 默认四级词包
        initDatas();


        leftwordspellLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                wordadapter.setSelectItem(i);
                wordadapter.notifyDataSetChanged();
                word = wordadapter.getItemtranslation(i);
                int p = 0;
                if(word.equals(translation)){
                    wordmDatas.remove(i);
                    for (int j = 0; j < explainmDatas.size(); j++) {
                        if (explainmDatas.get(j).getExplaination() == word) {
                            p = j;
                            break;
                        }
                    }
                    explainmDatas.remove(p);
                    wordadapter.notifyDataSetChanged();
                    explainAdapter.notifyDataSetChanged();
                }
                if(wordmDatas.size() == 0){
                    Toast.makeText(LinkGameActivity.this,"恭喜！",Toast.LENGTH_SHORT).show();
                    settime = (long) (settime*0.85);
                    timer.setMillisInFuture(settime);
                    timer.start();
                    initDatas();
                }
            }
        });

        rightwordtranslateLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                explainAdapter.setSelectItem(i);
                explainAdapter.notifyDataSetChanged();
                translation = explainAdapter.getItemtranslation(i);
                int p = 0;
                if(translation.equals(word)){
                    explainmDatas.remove(i);
                    for (int j = 0; j < wordmDatas.size(); j++) {
                        if (wordmDatas.get(j).getExplaination() == translation) {
                            p = j;
                            break;
                        }
                    }
                    wordmDatas.remove(p);
                    wordadapter.notifyDataSetChanged();
                    explainAdapter.notifyDataSetChanged();
                }
                if(explainmDatas.size() == 0){
                    Toast.makeText(LinkGameActivity.this,"恭喜！",Toast.LENGTH_SHORT).show();
                    settime = (long) (settime*0.85);
                    timer.setMillisInFuture(settime);
                    timer.start();
                    initDatas();
                }
            }
        });
    }

    private void initDatas() {
        SharedPreferences sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String name = sp.getString("USER_NAME","");
        String url = URLContent.getCet4Review(name,planCount);
        LoadDataAsyncTask task = new LoadDataAsyncTask(this, this, true);
        task.execute(url);
    }

    @Override
    public void onSuccess(String json) {
        if(!TextUtils.isEmpty(json)){
            wordmDatas.clear();
            Cet4ReviewBean bean = new Gson().fromJson(json, Cet4ReviewBean.class);
            List<Cet4ReviewBean._$4Bean> list = bean.get_$4();
            for (int i = 0; i < list.size(); i++) {
                wordmDatas.add(list.get(i));
                explainmDatas.add(list.get(i));
            }
//            打乱list
            Collections.shuffle(wordmDatas);
            Collections.shuffle(explainmDatas);
//            通知适配器更新
            wordadapter.notifyDataSetChanged();
            explainAdapter.notifyDataSetChanged();
        }

    }

    private void init() {
        timehoderTv = findViewById(R.id.linkgame_tv_timepicker);
        wordbagTv = findViewById(R.id.linkgame_tv_wordbag);
        backTv = findViewById(R.id.linkgame_tv_back);
        leftwordspellLv = findViewById(R.id.linkgame_lv_leftwordspell);
        rightwordtranslateLv = findViewById(R.id.linkgame_lv_rightwordtranslate);
        backTv.setOnClickListener(this);
        wordbagTv.setOnClickListener(this);

        timer = new CountDownTimerUtil(settime,1000) {
            @Override
            public void onTick(long l) {
                long day = l / (1000*60*60*24);
                long hour = (l - day * (1000*60*60*24)) / (1000*60*60);
                long minute = (l - day * (1000*60*60*24) - hour * (1000*60*60)) / (1000*60);
                long second = (l - day * (1000*60*60*24) - hour * (1000*60*60) - minute * (1000*60)) / 1000;
                if(minute>=10 && second>=10){
                    timehoderTv.setText(minute+":"+second);
                }else if(minute<10 && second>=10){
                    timehoderTv.setText("0"+minute+":"+second);
                }else if(minute<10 && second<10){
                    timehoderTv.setText("0"+minute+":"+"0"+second);
                }else if(minute>=10 && second<10){
                    timehoderTv.setText(minute+":"+"0"+second);
                }
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(LinkGameActivity.this,LinkGameOverActivity.class);
                startActivity(intent);
                finish();
            }
        };
        timer.start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.linkgame_tv_wordbag:
                timer.cancel();
                Intent intent1 = new Intent(LinkGameActivity.this,WordBagActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.linkgame_tv_back:
                timer.cancel();
                finish();
                break;

        }
    }

    @Override
    public void onBackPressed() {
        // do nothing
    }


}
