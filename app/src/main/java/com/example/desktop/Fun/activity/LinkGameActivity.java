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
import com.example.desktop.Bean.wordbookbean.Cet6ReviewBean;
import com.example.desktop.Bean.wordbookbean.HeightwordReviewBean;
import com.example.desktop.Fun.adapter.LinkGame4Adapter;
import com.example.desktop.Fun.adapter.LinkGame6Adapter;
import com.example.desktop.Fun.adapter.LinkGame8Adapter;
import com.example.desktop.Fun.adapter.LinkGameExplain4Adapter;
import com.example.desktop.Fun.adapter.LinkGameExplain6Adapter;
import com.example.desktop.Fun.adapter.LinkGameExplain8Adapter;
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
    List<Cet4ReviewBean._$4Bean> wordmDatas4;
    List<Cet4ReviewBean._$4Bean> explainmDatas4;
    List<Cet6ReviewBean._$6Bean> wordmDatas6;
    List<Cet6ReviewBean._$6Bean> explainmDatas6;
    List<HeightwordReviewBean._$8Bean> wordmDatas8;
    List<HeightwordReviewBean._$8Bean> explainmDatas8;
    private LinkGame4Adapter wordadapter4;
    private LinkGame6Adapter wordadapter6;
    private LinkGame8Adapter wordadapter8;
    private int planCount = 10;
    private LinkGameExplain4Adapter explainAdapter4;
    private LinkGameExplain6Adapter explainAdapter6;
    private LinkGameExplain8Adapter explainAdapter8;
    private String word,translation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_game);


        init();
        SharedPreferences sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        int type = sp.getInt("wordtype",4);
        if(type == 4){
            wordmDatas4 = new ArrayList<>();
            explainmDatas4 = new ArrayList<>();
            wordadapter4 = new LinkGame4Adapter(this, wordmDatas4);
            explainAdapter4 = new LinkGameExplain4Adapter(this, explainmDatas4);
            leftwordspellLv.setAdapter(wordadapter4);
            rightwordtranslateLv.setAdapter(explainAdapter4);
        }else if(type == 6){
            wordmDatas6 = new ArrayList<>();
            explainmDatas6 = new ArrayList<>();
            wordadapter6 = new LinkGame6Adapter(this, wordmDatas6);
            explainAdapter6 = new LinkGameExplain6Adapter(this, explainmDatas6);
            leftwordspellLv.setAdapter(wordadapter6);
            rightwordtranslateLv.setAdapter(explainAdapter6);
        }else if(type == 8){
            wordmDatas8 = new ArrayList<>();
            explainmDatas8 = new ArrayList<>();
            wordadapter8 = new LinkGame8Adapter(this, wordmDatas8);
            explainAdapter8 = new LinkGameExplain8Adapter(this, explainmDatas8);
            leftwordspellLv.setAdapter(wordadapter8);
            rightwordtranslateLv.setAdapter(explainAdapter8);
        }
//        加载复习数据 默认四级词包
        initDatas();


        leftwordspellLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SharedPreferences sp = getApplication().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                int type = sp.getInt("wordtype",4);
                if(type == 4){
                    wordadapter4.setSelectItem(i);
                    wordadapter4.notifyDataSetChanged();
                    word = wordadapter4.getItemtranslation(i);
                    int p = 0;
                    if(word.equals(translation)){
                        wordmDatas4.remove(i);
                        for (int j = 0; j < explainmDatas4.size(); j++) {
                            if (explainmDatas4.get(j).getExplaination() == word) {
                                p = j;
                                break;
                            }
                        }
                        explainmDatas4.remove(p);
                        wordadapter4.notifyDataSetChanged();
                        explainAdapter4.notifyDataSetChanged();
                    }
                    if(wordmDatas4.size() == 0){
                        Toast.makeText(LinkGameActivity.this,"恭喜！",Toast.LENGTH_SHORT).show();
                        settime = (long) (settime*0.85);
                        timer.setMillisInFuture(settime);
                        timer.start();
                        initDatas();
                    }
                }else if(type == 6){
                    wordadapter6.setSelectItem(i);
                    wordadapter6.notifyDataSetChanged();
                    word = wordadapter6.getItemtranslation(i);
                    int p = 0;
                    if(word.equals(translation)){
                        wordmDatas6.remove(i);
                        for (int j = 0; j < explainmDatas6.size(); j++) {
                            if (explainmDatas6.get(j).getExplaination() == word) {
                                p = j;
                                break;
                            }
                        }
                        explainmDatas6.remove(p);
                        wordadapter6.notifyDataSetChanged();
                        explainAdapter6.notifyDataSetChanged();
                    }
                    if(wordmDatas6.size() == 0){
                        Toast.makeText(LinkGameActivity.this,"恭喜！",Toast.LENGTH_SHORT).show();
                        settime = (long) (settime*0.85);
                        timer.setMillisInFuture(settime);
                        timer.start();
                        initDatas();
                    }
                }else if(type == 8){
                    wordadapter8.setSelectItem(i);
                    wordadapter8.notifyDataSetChanged();
                    word = wordadapter8.getItemtranslation(i);
                    int p = 0;
                    if(word.equals(translation)){
                        wordmDatas8.remove(i);
                        for (int j = 0; j < explainmDatas8.size(); j++) {
                            if (explainmDatas8.get(j).getExplaination() == word) {
                                p = j;
                                break;
                            }
                        }
                        explainmDatas8.remove(p);
                        wordadapter8.notifyDataSetChanged();
                        explainAdapter8.notifyDataSetChanged();
                    }
                    if(wordmDatas8.size() == 0){
                        Toast.makeText(LinkGameActivity.this,"恭喜！",Toast.LENGTH_SHORT).show();
                        settime = (long) (settime*0.85);
                        timer.setMillisInFuture(settime);
                        timer.start();
                        initDatas();
                    }
                }

            }
        });

        rightwordtranslateLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SharedPreferences sp = getApplication().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                int type = sp.getInt("wordtype",4);
                if(type == 4){
                    explainAdapter4.setSelectItem(i);
                    explainAdapter4.notifyDataSetChanged();
                    translation = explainAdapter4.getItemtranslation(i);
                    int p = 0;
                    if(translation.equals(word)){
                        explainmDatas4.remove(i);
                        for (int j = 0; j < wordmDatas4.size(); j++) {
                            if (wordmDatas4.get(j).getExplaination() == translation) {
                                p = j;
                                break;
                            }
                        }
                        wordmDatas4.remove(p);
                        wordadapter4.notifyDataSetChanged();
                        explainAdapter4.notifyDataSetChanged();
                    }
                    if(explainmDatas4.size() == 0){
                        Toast.makeText(LinkGameActivity.this,"恭喜！",Toast.LENGTH_SHORT).show();
                        settime = (long) (settime*0.85);
                        timer.setMillisInFuture(settime);
                        timer.start();
                        initDatas();
                    }
                }else if(type == 6){
                    explainAdapter6.setSelectItem(i);
                    explainAdapter6.notifyDataSetChanged();
                    translation = explainAdapter6.getItemtranslation(i);
                    int p = 0;
                    if(translation.equals(word)){
                        explainmDatas6.remove(i);
                        for (int j = 0; j < wordmDatas6.size(); j++) {
                            if (wordmDatas6.get(j).getExplaination() == translation) {
                                p = j;
                                break;
                            }
                        }
                        wordmDatas6.remove(p);
                        wordadapter6.notifyDataSetChanged();
                        explainAdapter6.notifyDataSetChanged();
                    }
                    if(explainmDatas6.size() == 0){
                        Toast.makeText(LinkGameActivity.this,"恭喜！",Toast.LENGTH_SHORT).show();
                        settime = (long) (settime*0.85);
                        timer.setMillisInFuture(settime);
                        timer.start();
                        initDatas();
                    }
                }else if(type == 8){
                    explainAdapter8.setSelectItem(i);
                    explainAdapter8.notifyDataSetChanged();
                    translation = explainAdapter8.getItemtranslation(i);
                    int p = 0;
                    if(translation.equals(word)){
                        explainmDatas8.remove(i);
                        for (int j = 0; j < wordmDatas8.size(); j++) {
                            if (wordmDatas8.get(j).getExplaination() == translation) {
                                p = j;
                                break;
                            }
                        }
                        wordmDatas8.remove(p);
                        wordadapter8.notifyDataSetChanged();
                        explainAdapter8.notifyDataSetChanged();
                    }
                    if(explainmDatas8.size() == 0){
                        Toast.makeText(LinkGameActivity.this,"恭喜通过本关！",Toast.LENGTH_SHORT).show();
                        settime = (long) (settime*0.85);
                        timer.setMillisInFuture(settime);
                        timer.start();
                        initDatas();
                    }
                }

            }
        });
    }

    private void initDatas() {
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
                    explainmDatas4.add(list.get(i));
                }
//            打乱list
                Collections.shuffle(wordmDatas4);
                Collections.shuffle(explainmDatas4);
//            通知适配器更新
                wordadapter4.notifyDataSetChanged();
                explainAdapter4.notifyDataSetChanged();

            }else if(type == 6){
                Cet6ReviewBean bean = new Gson().fromJson(json, Cet6ReviewBean.class);
                List<Cet6ReviewBean._$6Bean> list = bean.get_$6();
                for (int i = 0; i < list.size(); i++) {
                    wordmDatas6.add(list.get(i));
                    explainmDatas6.add(list.get(i));
                }
//            打乱list
                Collections.shuffle(wordmDatas6);
                Collections.shuffle(explainmDatas6);
//            通知适配器更新
                wordadapter6.notifyDataSetChanged();
                explainAdapter6.notifyDataSetChanged();

            }else if(type == 8){
                HeightwordReviewBean bean = new Gson().fromJson(json, HeightwordReviewBean.class);
                List<HeightwordReviewBean._$8Bean> list = bean.get_$8();
                for (int i = 0; i < list.size(); i++) {
                    wordmDatas8.add(list.get(i));
                    explainmDatas8.add(list.get(i));
                }
//            打乱list
                Collections.shuffle(wordmDatas8);
                Collections.shuffle(explainmDatas8);
//            通知适配器更新
                wordadapter8.notifyDataSetChanged();
                explainAdapter8.notifyDataSetChanged();

            }

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
        SharedPreferences sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        int plan = sp.getInt("plan",10);
//        设置倒计时的时间
        if(plan > 10){
            settime = 90000;
        }else if(plan > 20){
            settime = 140000;
        }else if(plan > 30){
            settime = 190000;
        }else if (plan > 40){
            settime = 240000;
        }else if (plan > 50){
            settime = 290000;
        }else if (plan > 60){
            settime = 340000;
        }
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
