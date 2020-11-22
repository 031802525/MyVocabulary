package com.example.desktop.Fun.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.desktop.Fun.bean.EverydaySentenceBean;
import com.example.desktop.Fun.util.LoadDataAsyncTask;
import com.example.desktop.Fun.util.URLContent;
import com.example.desktop.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class EveryDayActivity extends AppCompatActivity implements LoadDataAsyncTask.OnGetNetDataListener{

    private TextView everydayTv,contentTv;
    private ImageView pictureIv;
    private String chineseUTF8 = null;
    private String chinese = "今天真是开心的一天。";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_every_day);
        everydayTv = findViewById(R.id.everyday_english);
        contentTv = findViewById(R.id.everyday_content);
        pictureIv = findViewById(R.id.everyday_iv_picture);


        try {
            chineseUTF8 = new String(chinese.getBytes("UTF-8"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


//        获取URL
        String url = URLContent.getEnglishDayURL();
//        String url = URLContent.getChineseToEnglish(chineseUTF8);
//        String url = URLContent.getWordTranslateURL("word");
//        加载网络数据
//        创造自定义的异步任务对象
        LoadDataAsyncTask task = new LoadDataAsyncTask(this, this, true);
//        执行异步任务
        task.execute(url);

    }

    @Override
    public void onSuccess(String json) {

//        解析数据
        if(!TextUtils.isEmpty(json)){
//            ChineseToEnglishBean bean = new Gson().fromJson(json, ChineseToEnglishBean.class);
//            everydayTv.setText(bean.getTrans_result().get(0).getDst());
            EverydaySentenceBean bean = new Gson().fromJson(json, EverydaySentenceBean.class);
            everydayTv.setText(bean.getNote());
            contentTv.setText(bean.getContent());
            String picURL = bean.getPicture4();
            Picasso.with(this).load(picURL).into(pictureIv);
            String mp3URL = bean.getTts();
            MediaPlayer mediaPlayer = new MediaPlayer();
            try {
                mediaPlayer.setDataSource(mp3URL);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }

            mediaPlayer.start();
//            WordTranslateBean bean = new Gson().fromJson(json, WordTranslateBean.class);
//            List<String> explains = bean.getExplains();
//            everydayTv.setText(explains.get(0));
        }
    }
}