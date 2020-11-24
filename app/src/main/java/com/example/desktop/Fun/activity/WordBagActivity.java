package com.example.desktop.Fun.activity;

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
import com.example.desktop.Fun.adapter.WordBagAdapter;
import com.example.desktop.R;
import com.example.desktop.Util.LoadDataAsyncTask;
import com.example.desktop.Util.URLContent;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class WordBagActivity extends AppCompatActivity implements View.OnClickListener, LoadDataAsyncTask.OnGetNetDataListener {

    ListView wordlistLv;
    ImageView backIv;
    List<Cet4ReviewBean._$4Bean> mDatas;
    private int planCount = 10;
    private WordBagAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_bag);
        init();

        initDatas();
        mDatas = new ArrayList<>();
        adapter = new WordBagAdapter(this, mDatas);
        wordlistLv.setAdapter(adapter);
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
                Intent intent = new Intent(this,LinkGameActivity.class);
                startActivity(intent);
                finish();
                break;
        }
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
        if (!TextUtils.isEmpty(json)) {
            mDatas.clear();
            Cet4ReviewBean bean = new Gson().fromJson(json, Cet4ReviewBean.class);
            List<Cet4ReviewBean._$4Bean> list = bean.get_$4();
            for (int i = 0; i < list.size(); i++) {
                mDatas.add(list.get(i));
            }
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onBackPressed() {
//        do nothing
    }
}
