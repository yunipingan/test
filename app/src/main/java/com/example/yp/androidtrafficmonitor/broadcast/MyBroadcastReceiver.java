package com.example.yp.androidtrafficmonitor.broadcast;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.yp.androidtrafficmonitor.service.TrafficMonitorService;

public class MyBroadcastReceiver extends BroadcastReceiver {
    public MyBroadcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        boolean isServiceRunning = false;

        Log.v("Broadcast","MyBroadcastReceiver1");
        if (intent.getAction().equals(Intent.ACTION_TIME_TICK)) {

            //检查Service状态
            Log.v("Broadcast","MyBroadcastReceiver2");
            ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            for (ActivityManager.RunningServiceInfo service :manager.getRunningServices(Integer.MAX_VALUE)) {
                Log.v("Broadcast",service.service.getClassName());

                if("com.example.yp.androidtrafficmonitor.service.TrafficMonitorService".equals(service.service.getClassName()))
                {
                    Log.v("Broadcast","MyBroadcastReceiver3");
                    isServiceRunning = true;
                }

            }
            if (!isServiceRunning) {
                Log.v("Broadcast","MyBroadcastReceiver4");
                Intent i = new Intent(context, TrafficMonitorService.class);
                context.startService(i);
            }

        }
    }
}
