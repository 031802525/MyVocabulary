package com.example.desktop.Fun.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.desktop.Fun.db.MyDatabaseHelper;
import com.example.desktop.Fun.util.CalTime;
import com.example.desktop.Fun.view.PickerView;
import com.example.desktop.MainActivity;
import com.example.desktop.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddAlarmActivity extends AppCompatActivity {

    private PickerView minute_pv;
    private PickerView second_pv;
    private String timeDitance;
    private Calendar mCalendar;
    private TextView quxiaoTv,saveTv,distanceTv,zhengdongcontentTv,chongfucontentTv;
    private ImageView zhengdongIv,chongfuIv;
    private MyDatabaseHelper myDatabaseHelper;
    private SQLiteDatabase db;
    private int minute,hour;
    private boolean flag = false;
    SharedPreferences preferences,timesp;
    SharedPreferences.Editor editor,timeeditor;
    Calendar calendar = Calendar.getInstance();
    private long oneDaytimeMM = 2682060;
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH)+1;
    int day = calendar.get(Calendar.DAY_OF_MONTH);
    int hour1 = calendar.get(Calendar.HOUR_OF_DAY);
    int minute1 = calendar.get(Calendar.MINUTE);
    int second = calendar.get(Calendar.SECOND);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getSupportActionBar().hide();//隐藏ActionBar
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//透明化通知栏
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        setContentView(R.layout.activity_add_alarm);

        preferences = getSharedPreferences("alarmcount",MODE_PRIVATE);
        timesp = getSharedPreferences("alarmtime",MODE_PRIVATE);

        int alarmcount = preferences.getInt("alarmcount",1);
        long alarmtime = timesp.getLong("alarmtime",0);
        long instanceTime = getInstanceTime();
        long finaltime = instanceTime-alarmtime;
        if(finaltime>oneDaytimeMM){
            alarmcount = 1;
        }
//        Toast.makeText(AddAlarmActivity.this,"应用被打开了："+alarmcount+"次",Toast.LENGTH_LONG).show();

        if(alarmcount == 1){
            Intent intent = getIntent();
            String activityname = intent.getStringExtra("name");
//            Toast.makeText(AddAlarmActivity.this,"谁？"+activityname,Toast.LENGTH_SHORT).show();
            minute = intent.getIntExtra("minute",0);
            hour = intent.getIntExtra("hour",0);

//            Toast.makeText(AddAlarmActivity.this,"分钟:"+minute,Toast.LENGTH_SHORT).show();
//            Toast.makeText(AddAlarmActivity.this,"小时:"+hour,Toast.LENGTH_SHORT).show();
            defaultFunction(hour,minute);
        }
        editor = preferences.edit();
        timeeditor = timesp.edit();

        editor.putInt("alarmcount",++alarmcount);
        timeeditor.putLong("alarmtime",instanceTime);

        editor.commit();
        timeeditor.commit();



        initView();
        init();
    }

//    获取当前时间
    public long getInstanceTime(){
        long mm = second+minute*60+hour*60*60+day*24*60*60+month*30*24*60*60;
        return mm;
    }

    private void initView() {
        minute_pv = findViewById(R.id.minute_pv);
        second_pv = findViewById(R.id.second_pv);
        quxiaoTv = findViewById(R.id.addalarm_tv_quxiao);
        saveTv = findViewById(R.id.addalarm_tv_save);
        distanceTv = findViewById(R.id.addalarm_tv_distance);
        zhengdongcontentTv = findViewById(R.id.zhengdong_tv_content);
        chongfucontentTv = findViewById(R.id.chongfu_tv_content);
        zhengdongIv = findViewById(R.id.addalarm_iv_zhengdong);
        chongfuIv = findViewById(R.id.addalarm_iv_chongfu);

        //      打开数据库
        myDatabaseHelper=new MyDatabaseHelper(getApplicationContext(),"Items.db",null,1);
        db=myDatabaseHelper.getWritableDatabase();

    }


    private void init() {
        List<String> data = new ArrayList<String>();
        List<String> seconds = new ArrayList<String>();
//        加0操作
        for (int i = 0; i < 24; i++)
        {
            if (i<10) {
                data.add("0" + i);
            }else {
                data.add(i+"");
            }
        }
        for (int i = 0; i < 60; i++)
        {
            seconds.add(i < 10 ? "0" + i : "" + i);
        }
        minute=30;
        hour=12;
        timeDitance=new CalTime().cal(minute,hour);
        distanceTv.setText(timeDitance);
        minute_pv.setData(data);
        second_pv.setData(seconds);

//        设置还剩多少时间开启闹钟
        minute_pv.setOnSelectListener(new PickerView.onSelectListener() {
            @Override
            public void onSelect(String text) {

                hour=Integer.parseInt(text);
                timeDitance=new CalTime().cal(hour,minute);
                distanceTv.setText(timeDitance);
            }
        });
        second_pv.setOnSelectListener(new PickerView.onSelectListener() {
            @Override
            public void onSelect(String text) {
                minute=Integer.parseInt(text);
                timeDitance=new CalTime().cal(hour,minute);
                distanceTv.setText(timeDitance);
            }
        });

//        设置是否震动
        zhengdongIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] items=new String[]{"开","关"};
                AlertDialog.Builder builder=new AlertDialog.Builder(AddAlarmActivity.this);
                builder.setTitle("是否开启振动");

                //添加列表项
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        zhengdongcontentTv.setText(items[i]);
                    }
                });
                //创建并显示
                builder.create().show();
            }
        });

//        设置重复按键的对话框
        chongfuIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] items=new String[]{"仅一次","周一到周五","每天"};
                AlertDialog.Builder builder=new AlertDialog.Builder(AddAlarmActivity.this);
                builder.setTitle("选择重复类型");

                //添加列表项
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        chongfucontentTv.setText(items[i]);
                    }
                });
                //创建并显示
                builder.create().show();
            }
        });

        saveTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues cValue = new ContentValues();
                if (minute<10){
                    cValue.put("time",hour+":0"+minute);
                }else {
                    cValue.put("time",hour+":"+minute);
                }
                cValue.put("create_time",System.currentTimeMillis()+"");
                cValue.put("repeat",chongfucontentTv.getText().toString());
                cValue.put("isSwitchOn",1);
                cValue.put("ifVibrate",zhengdongcontentTv.getText().toString());
                db.insert("clocks",null,cValue);
                Toast.makeText(getApplicationContext(),"保存成功",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(AddAlarmActivity.this,AlarmActivity.class);
                intent.putExtra("addType","yes");
                startActivity(intent);
                finish();
            }
        });
        quxiaoTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddAlarmActivity.this,AlarmActivity.class);
                startActivity(intent);
                intent.putExtra("addType","cancel");
                finish();
            }
        });
    }

    public void defaultFunction(int hour,int minute){
        initView();
        ContentValues cValue = new ContentValues();
        if (minute<10){
            cValue.put("time",hour+":0"+minute);
        }else {
            cValue.put("time",hour+":"+minute);
        }
        cValue.put("create_time",System.currentTimeMillis()+"");
        cValue.put("repeat",chongfucontentTv.getText().toString());
        cValue.put("isSwitchOn",1);
        cValue.put("ifVibrate",zhengdongcontentTv.getText().toString());
        db.insert("clocks",null,cValue);
        Toast.makeText(getApplicationContext(),"保存成功",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(AddAlarmActivity.this,AlarmActivity.class);
        intent.putExtra("addType","yes");
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        Intent intent=new Intent(AddAlarmActivity.this,AlarmActivity.class);
//        startActivity(intent);
        finish();
    }
}