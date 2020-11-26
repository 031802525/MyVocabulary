package com.example.desktop.Shouye.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.desktop.Bean.wordbookbean.Cet4ReviewBean;
import com.example.desktop.Bean.wordbookbean.Cet6ReviewBean;
import com.example.desktop.Bean.wordbookbean.HeightwordReviewBean;
import com.example.desktop.Fun.adapter.WordBag4Adapter;
import com.example.desktop.Fun.adapter.WordBag6Adapter;
import com.example.desktop.Fun.adapter.WordBag8Adapter;
import com.example.desktop.MainActivity;
import com.example.desktop.R;
import com.example.desktop.Util.LoadDataAsyncTask;
import com.example.desktop.Util.URLContent;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class WordListActivity extends AppCompatActivity implements View.OnClickListener,LoadDataAsyncTask.OnGetNetDataListener {
    ListView wordlistLv;
    ImageView backIv;
    List<Cet4ReviewBean._$4Bean> m4Datas;
    List<Cet6ReviewBean._$6Bean> m6Datas;
    List<HeightwordReviewBean._$8Bean> m8Datas;
    private int planCount = 10;
    private WordBag4Adapter adapter4;
    private WordBag6Adapter adapter6;
    private WordBag8Adapter adapter8;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_bag);
        init();

        initDatas();
        SharedPreferences sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        int type = sp.getInt("wordtype",4);
        if(type == 4){
            m4Datas = new ArrayList<>();
            adapter4 = new WordBag4Adapter(this, m4Datas);
            wordlistLv.setAdapter(adapter4);
        }else if(type == 6){
            m6Datas = new ArrayList<>();
            adapter6 = new WordBag6Adapter(this, m6Datas);
            wordlistLv.setAdapter(adapter6);
        }else if(type == 8){
            m8Datas = new ArrayList<>();
            adapter8 = new WordBag8Adapter(this, m8Datas);
            wordlistLv.setAdapter(adapter8);
        }
    }

    private void init() {
        wordlistLv = findViewById(R.id.wordbag_lv_wordlist);
        backIv = findViewById(R.id.wordbag_iv_back);
        backIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.wordbag_iv_back:
                Intent intent = new Intent(WordListActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    private void initDatas() {
        SharedPreferences sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String name = sp.getString("USER_NAME","");
        int type = sp.getInt("wordtype",4);
        int plan = sp.getInt("plan",10);
        int num = sp.getInt("nowwordnum",plan);
        String url = "";
        if(type == 4){
            url = URLContent.getCet4Review(name,num);
        }else if(type == 6){
            url = URLContent.getCet6Review(name,num);
        }else if(type == 8){
            url = URLContent.getCet8Review(name,num);
        }
        LoadDataAsyncTask task = new LoadDataAsyncTask(this, this, false);
        task.execute(url);
    }

    @Override
    public void onSuccess(String json) {
        if (!TextUtils.isEmpty(json)) {
            SharedPreferences sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor= sp.edit();
            int type = sp.getInt("wordtype",4);
            if(type == 4){
                Cet4ReviewBean bean = new Gson().fromJson(json, Cet4ReviewBean.class);
                List<Cet4ReviewBean._$4Bean> list = bean.get_$4();
                editor.putInt("nowwordnum",bean.getNum());
                editor.commit();
                for (int i = 0; i < list.size(); i++) {
                    m4Datas.add(list.get(i));
                }
                adapter4.notifyDataSetChanged();
            }else if(type == 6){
                Cet6ReviewBean bean = new Gson().fromJson(json, Cet6ReviewBean.class);
                List<Cet6ReviewBean._$6Bean> list = bean.get_$6();
                editor.putInt("nowwordnum",bean.getNum());
                editor.commit();
                for (int i = 0; i < list.size(); i++) {
                    m6Datas.add(list.get(i));
                }
                adapter6.notifyDataSetChanged();
            }else if(type == 8){
                HeightwordReviewBean bean = new Gson().fromJson(json, HeightwordReviewBean.class);
                List<HeightwordReviewBean._$8Bean> list = bean.get_$8();
                editor.putInt("nowwordnum",bean.getNum());
                editor.commit();
                for (int i = 0; i < list.size(); i++) {
                    m8Datas.add(list.get(i));
                }
                adapter8.notifyDataSetChanged();
            }

        }
    }

    @Override
    public void onBackPressed() {
//        do nothing
    }
}