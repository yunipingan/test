package com.example.yp.androidtrafficmonitor.service;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.example.yp.androidtrafficmonitor.beans.AppInfo;

import java.util.ArrayList;

public class TrafficMonitorBindService extends Service {

    private ArrayList<AppInfo>appInfoArrayList = null;

    public class MyBinder extends Binder {

        public TrafficMonitorBindService getService(){
            return TrafficMonitorBindService.this;
        }

    }

    @Override
    public IBinder onBind(Intent intent) {
        appInfoArrayList = (ArrayList<AppInfo>) intent.getSerializableExtra("applist");
        return new MyBinder();
    }

    @Override
    public void unbindService(ServiceConnection conn) {
        // TODO Auto-generated method stub
        Log.i("info", "BindService--unbindService()");
        super.unbindService(conn);
    }
    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        Log.i("info", "BindService--onDestroy()");
        super.onDestroy();
    }



}
