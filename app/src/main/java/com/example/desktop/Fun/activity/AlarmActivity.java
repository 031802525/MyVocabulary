package com.example.desktop.Fun.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.desktop.Fun.dialog.CommomDialog;
import com.example.desktop.Fun.adapter.AlarmAdapter;
import com.example.desktop.Fun.bean.ClockBean;
import com.example.desktop.Fun.db.MyDatabaseHelper;
import com.example.desktop.Fun.service.AlarmService;
import com.example.desktop.Fun.util.ServiceUtil;
import com.example.desktop.MainActivity;
import com.example.desktop.R;

import java.util.ArrayList;
import java.util.List;

public class AlarmActivity extends AppCompatActivity {

    private ArrayList<Integer> isSwitchOnArray=new ArrayList<>();
    private ArrayList<String> timeArray=new ArrayList<>(),repeatArray=new ArrayList<>();
    private AlarmAdapter alarmAdapter;
    private MyDatabaseHelper myDatabaseHelper;
    private SQLiteDatabase db;
    private List<ClockBean> clockBeanList=new ArrayList<>();
    private ListView alarmList;
    private ImageView addalarmIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getSupportActionBar().hide();//隐藏ActionBar
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//透明化通知栏
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        if (ServiceUtil.isRunning(getApplication(),".Fun.service.AlarmService")){
//        }else {
//            startService(new Intent(AlarmActivity.this, AlarmService.class).putExtra("flag","ClockActivity"));
//            Toast.makeText(getApplicationContext(),"服务开启成功",Toast.LENGTH_SHORT).show();
//        }
        setContentView(R.layout.activity_alarm);
        myDatabaseHelper=new MyDatabaseHelper(getApplicationContext(),"Items.db",null,1);
        db=myDatabaseHelper.getWritableDatabase();

        init();

        initDatabase();

//        设置适配器
        alarmAdapter = new AlarmAdapter(getApplicationContext(),clockBeanList);
        alarmList.setAdapter(alarmAdapter);

        addalarmIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AlarmActivity.this,AddAlarmActivity.class);
                startActivity(intent);
                finish();
            }
        });

//        listview的长按监听事件 switch focusable属性要设为false，不然长按事件不会响应
        alarmList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                System.out.println("长按了！！！！");
                new CommomDialog(AlarmActivity.this, R.style.dialog, "您确定删除吗", new CommomDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {
                        if (confirm) {
                            if (deleteItem(clockBeanList.get(position))==1){
                                Toast.makeText(getApplicationContext(),"删除成功",Toast.LENGTH_SHORT).show();
                            }
                            dialog.dismiss();
                        } else {

                        }
                    }
                }).setTitleText("提示").show();
                return true;
            }
        });

        Intent jumpType=getIntent();
        if (jumpType.getStringExtra("addType")!=null){
            if (jumpType.getStringExtra("addType").equals("yes")){
                initDatabase();
                alarmAdapter.notifyDataSetChanged();
                stopService(new Intent(AlarmActivity.this, AlarmService.class));
                startService(new Intent(AlarmActivity.this,AlarmService.class).putExtra("flag","ClockActivity"));
            }else if (jumpType.getStringExtra("addType").equals("cancel")){

            }
        }
    }

    private int deleteItem(ClockBean clockBean) {
        db.delete("clocks","id=?",new String[]{clockBean.getId()+""});
        initDatabase();
        alarmAdapter.notifyDataSetChanged();
        return 1;
    }

    private void init() {

        alarmList = findViewById(R.id.alarm_lv_alarmlist);
        addalarmIv = findViewById(R.id.alarm_iv_addalarm);

    }

    private void initDatabase() {
        clockBeanList.clear();

        Cursor cursor=db.query("clocks",null,null,null,null,null,null,null);
        while (cursor.moveToNext()){
            ClockBean clockBean=new ClockBean(
                    cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("time")),
                    cursor.getString(cursor.getColumnIndex("repeat")),
                    cursor.getInt(cursor.getColumnIndex("isSwitchOn")),
                    cursor.getString(cursor.getColumnIndex("ifVibrate")),
                    cursor.getString(cursor.getColumnIndex("create_time"))
            );
            clockBeanList.add(clockBean);
            Log.i("ClockActivity",clockBeanList.toString());
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        initDatabase();
        alarmAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        startActivity(new Intent(AlarmActivity.this, MainActivity.class));
        finish();
    }

}