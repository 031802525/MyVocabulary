package com.example.desktop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.desktop.Fun.activity.AddAlarmActivity;
import com.example.desktop.Bean.translatebean.EverydaySentenceBean;
import com.example.desktop.Fun.service.AlarmService;
import com.example.desktop.Fun.service.LockScreenService;
import com.example.desktop.Util.LoadDataAsyncTask;
import com.example.desktop.Fun.util.ServiceUtil;
import com.example.desktop.Util.URLContent;
import com.example.desktop.frag.FuxiFragment;
import com.example.desktop.frag.QuweiFragment;
import com.example.desktop.frag.MeFragment;
import com.example.desktop.frag.ShouyeFragment;
import com.google.gson.Gson;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener , LoadDataAsyncTask.OnGetNetDataListener {

    RadioGroup mainRg;
    Fragment ShouyeFrag, QuweiFrag,FuxiFrag,MeFrag;
    private FragmentManager manager;
    public static boolean needRefresh = true;
    private static final String TAG = "MainActivity";
    SharedPreferences preferences,timesp;
    SharedPreferences.Editor editor,timeeditor;
    Calendar calendar = Calendar.getInstance();
    private long oneDaytimeMM = 8640000;
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH)+1;
    int day = calendar.get(Calendar.DAY_OF_MONTH);
    int hour = calendar.get(Calendar.HOUR_OF_DAY);
    int minute = calendar.get(Calendar.MINUTE);
    int second = calendar.get(Calendar.SECOND);

    SharedPreferences everySentence;
    SharedPreferences.Editor everySentenceedit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        打开锁屏和闹铃的服务
        openService();

        setContentView(R.layout.activity_main);

//        根据进入记录设置闹钟
        setAlarm();
//        初始化控件
        init();
//        创建Fragment并加入MainActivity
        createFragment();

        String url = URLContent.getEnglishDayURL();
        LoadDataAsyncTask task = new LoadDataAsyncTask(this, this, false);
        task.execute(url);


    }

    private void createFragment() {
        //创建Fragment对象
        ShouyeFrag = new ShouyeFragment();
        QuweiFrag = new QuweiFragment();
        FuxiFrag = new FuxiFragment();
        MeFrag = new MeFragment();
        addFragmentPage();
    }

    private void init() {
        mainRg = findViewById(R.id.main_rg);
        mainRg.setOnCheckedChangeListener(this);
    }

    private void openService() {
        //默认进来开启锁屏服务
        Intent intent2 = new Intent(MainActivity.this, LockScreenService.class);
//        Toast.makeText(MainActivity.this,"开启成功",Toast.LENGTH_SHORT).show();
        startService(intent2);
//        开启闹钟服务
        if (ServiceUtil.isRunning(getApplication(),".Fun.service.AlarmService")){
        }else {
            startService(new Intent(MainActivity.this, AlarmService.class).putExtra("flag","ClockActivity"));
//            Toast.makeText(getApplicationContext(),"服务开启成功",Toast.LENGTH_SHORT).show();
        }
    }

    private void setAlarm() {
        preferences = getSharedPreferences("count",MODE_PRIVATE);
        timesp = getSharedPreferences("currenttime",MODE_PRIVATE);
        int count = preferences.getInt("count",1);
        long currenttime = timesp.getLong("currenttime",0);
        long instanceTime = getInstanceTime();
        long finaltime = instanceTime-currenttime;
        if(finaltime>oneDaytimeMM){
            count = 1;
        }
//        Toast.makeText(MainActivity.this,"应用被打开了："+count+"次",Toast.LENGTH_LONG).show();
//        Toast.makeText(MainActivity.this,"当前时间："+instanceTime+",  上次打开时间："+currenttime+", 相差时间："+finaltime,Toast.LENGTH_LONG).show();

        if(count == 1){
            Intent intent = new Intent(MainActivity.this, AddAlarmActivity.class);
            intent.putExtra("name","Main");
            intent.putExtra("minute",minute);
            intent.putExtra("hour",hour);
            startActivity(intent);
        }

        editor = preferences.edit();
        timeeditor = timesp.edit();
        //存入数据
        editor.putInt("count",++count);
        timeeditor.putLong("currenttime",instanceTime);
        //提交修改
        editor.commit();
        timeeditor.commit();

//        Toast.makeText(MainActivity.this,"当前时间："+finaltime,Toast.LENGTH_LONG).show();
//        Toast.makeText(MainActivity.this,"当前时间："+getInstanceTime(),Toast.LENGTH_LONG).show();



    }

    //    加入所有的Fragment页面，一个显示，其他三个隐藏
    private void addFragmentPage() {
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.main_layout_center,ShouyeFrag);
        transaction.add(R.id.main_layout_center, QuweiFrag);
        transaction.add(R.id.main_layout_center,FuxiFrag);
        transaction.add(R.id.main_layout_center,MeFrag);
        transaction.hide(QuweiFrag);
        transaction.hide(FuxiFrag);
        transaction.hide(MeFrag);
        transaction.commit();
    }

//    Fragment的切换
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        FragmentTransaction transaction = manager.beginTransaction();
        switch (i){
            case R.id.main_rb_quwei:
                transaction.hide(ShouyeFrag);
                transaction.hide(FuxiFrag);
                transaction.hide(MeFrag);
                transaction.show(QuweiFrag);
                break;
            case R.id.main_rb_fuxi:
                transaction.hide(ShouyeFrag);
                transaction.hide(MeFrag);
                transaction.hide(QuweiFrag);
                transaction.show(FuxiFrag);
                break;
            case R.id.main_rb_me:
                transaction.hide(ShouyeFrag);
                transaction.hide(QuweiFrag);
                transaction.hide(FuxiFrag);
                transaction.show(MeFrag);
                break;
            case R.id.main_rb_shouye:
                transaction.hide(QuweiFrag);
                transaction.hide(FuxiFrag);
                transaction.hide(MeFrag);
                transaction.show(ShouyeFrag);
                break;
        }
        transaction.commit();
    }

//      获取当前时间
    public long getInstanceTime(){
        long mm = second+minute*60+hour*60*60+day*24*60*60+month*30*24*60*60;
        return mm;
    }


//    防止返回键的触发
    @Override
    public void onBackPressed() {
        // do nothing
    }


    @Override
    public void onSuccess(String json) {
        if(!TextUtils.isEmpty(json)){
            EverydaySentenceBean bean = new Gson().fromJson(json, EverydaySentenceBean.class);
            String sentence = bean.getNote();
            String translation = bean.getContent();
            String picURL = bean.getPicture4();
            String mp3URL = bean.getTts();
            everySentence = this.getSharedPreferences("everysentence",MODE_PRIVATE);
            everySentenceedit = everySentence.edit();

            everySentenceedit.putString("sentence",sentence);
            everySentenceedit.putString("translation",translation);
            everySentenceedit.putString("picurl",picURL);
            everySentenceedit.putString("mp3url",mp3URL);

            everySentenceedit.commit();


        }
    }
}
