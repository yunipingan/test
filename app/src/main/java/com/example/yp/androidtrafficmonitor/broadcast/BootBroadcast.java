package com.example.yp.androidtrafficmonitor.broadcast;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import com.example.yp.androidtrafficmonitor.activity.TestActivity;
import com.example.yp.androidtrafficmonitor.service.ServiceForBroadcast;
import com.example.yp.androidtrafficmonitor.service.TrafficMonitorService;

public class BootBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent mintent) {

        Log.v("Broadcast","BootBroadcast");
        /*Intent intent = new Intent(context , ServiceForBroadcast.class);
        context.startService(intent);
        Toast.makeText(context,"bootBroadcast开机",Toast.LENGTH_LONG).show();*/
        Intent intent1 = new Intent(context, TestActivity.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent1);
    }

}