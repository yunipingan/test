package com.example.yp.androidtrafficmonitor.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.TrafficStats;
import android.os.IBinder;
import android.util.Log;

import com.example.yp.androidtrafficmonitor.utils.TrafficUtil;

public class TrafficMonitorService extends Service {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private boolean flag = false;
    private long mobileTemp;
    private long mobileTraffic;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v("TrafficService","startCommand");
        sharedPreferences = getSharedPreferences("trafficInfor", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        startMonitor(this);
        //UidTrafficdDB.query(getApplicationContext());
        return START_STICKY;
    }

//    @Override
//    public void onDestroy() {
//        Intent intent = new Intent(this,TrafficMonitorService.class);
//        startService(intent);
//    }

    public synchronized void startMonitor(final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!flag) {
                    TrafficUtil.startMonitor(context);
                    //long traffic = sharedPreferences.getLong("traffic",0)+TrafficStats.getTotalTxBytes() + TrafficStats.getTotalRxBytes();
                    //long mobile = sharedPreferences.getLong("mobile",0)+TrafficStats.getMobileRxBytes()+TrafficStats.getMobileTxBytes();
                    //long traffic = TrafficStats.getTotalTxBytes() + TrafficStats.getTotalRxBytes();
                    mobileTraffic = TrafficStats.getMobileRxBytes()+TrafficStats.getMobileTxBytes();
                    long mobile = mobileTraffic - mobileTemp;
                    mobileTemp = mobileTraffic;

                    editor.putLong("mobile", mobile + sharedPreferences.getLong("mobile",0));
                    editor.commit();
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.v("flag", "标记");

                }
            }
        }).start();
    }

}
