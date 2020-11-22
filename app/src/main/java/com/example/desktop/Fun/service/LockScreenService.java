package com.example.desktop.Fun.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;

import com.example.desktop.Fun.activity.LockScreenActivity;


public class LockScreenService extends Service {
    private String TAG = this.getClass().getSimpleName();
    private PowerManager.WakeLock wakeLock = null;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public void onCreate() {
        super.onCreate();
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, LockScreenService.class.getName());
        wakeLock.acquire();

        IntentFilter mScreenOnFilter = new IntentFilter();
        mScreenOnFilter.addAction(Intent.ACTION_SCREEN_OFF);
        mScreenOnFilter.addAction(Intent.ACTION_SCREEN_ON);
        LockScreenService.this.registerReceiver(mScreenActionReceiver, mScreenOnFilter);

    }

    public void onDestroy() {
        if (wakeLock != null) {
            wakeLock.release();
            wakeLock = null;
        }
            super.onDestroy();
        this.unregisterReceiver(mScreenActionReceiver);
        Intent intent = new Intent(this,LockScreenService.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // 在此重新启动,使服务常驻内存
        startService(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    private BroadcastReceiver mScreenActionReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Intent.ACTION_SCREEN_ON)) {
                Log.e(TAG, "screen on");
                Intent LockIntent = new Intent(LockScreenService.this, LockScreenActivity.class);
                LockIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                LockIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(LockIntent);
            } else if (action.equals(Intent.ACTION_SCREEN_OFF)) {
                Log.e(TAG, "screen off");
            }
        }
    };



}