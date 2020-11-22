package com.example.desktop.Fun.receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.desktop.Fun.service.AlarmService;

public class AlarmReceiver extends BroadcastReceiver {

    private int code;


    @Override
    public void onReceive(Context context, Intent intent) {
        code = intent.getIntExtra("requestcode", 0);
        Intent intent1=new Intent(context, AlarmService.class);
        intent1.putExtra("flag","ClockReceiver");
        intent1.putExtra("code",code+"");
        context.startService(intent1);
    }
}