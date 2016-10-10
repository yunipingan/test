package com.example.yp.androidtrafficmonitor.service;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.TrafficStats;
import android.os.IBinder;
import android.text.format.Formatter;
import android.util.Log;

import com.example.yp.androidtrafficmonitor.beans.AppInfo;
import com.example.yp.androidtrafficmonitor.broadcast.MyBroadcastReceiver;

import java.util.List;

public class TrafficMonitorService extends Service {

    private boolean flag = false;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v("TrafficService","startCommand");
//      flags = START_STICKY;
        startMonitor();
        return START_STICKY;
    }

//    @Override
//    public void onDestroy() {
//        Intent intent = new Intent(this,TrafficMonitorService.class);
//        startService(intent);
//    }

    public void startMonitor() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!flag) {
                    getAppTrafficList();
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


    public void getAppTrafficList() {
        AppInfo appInfo = null;
        //获取所有的安装在手机上的应用软件的信息，并且获取这些软件里面的权限信息
        PackageManager pm = getPackageManager();//获取系统应用包管理
        //获取每个包内的androidmanifest.xml信息，它的权限等等
        List<PackageInfo> pinfos = pm.getInstalledPackages
                (PackageManager.GET_PERMISSIONS);
        //遍历每个应用包信息
        for (PackageInfo info : pinfos) {
            //请求每个程序包对应的androidManifest.xml里面的权限
            String[] premissions = info.requestedPermissions;
            if (premissions != null && premissions.length > 0) {
                //找出需要网络服务的应用程序
                for (String premission : premissions) {
                    if ("android.permission.INTERNET".equals(premission)) {
                        //获取每个应用程序在操作系统内的进程id
                        int uId = info.applicationInfo.uid;
                        //如果返回-1，代表不支持使用该方法，注意必须是2.2以上的
                        long rx = TrafficStats.getUidRxBytes(uId);
                        //如果返回-1，代表不支持使用该方法，注意必须是2.2以上的
                        long tx = TrafficStats.getUidTxBytes(uId);
                        if (rx < 0 || tx < 0) {
                            continue;
                        } else {
                            appInfo = new AppInfo();
                            appInfo.appName = String.valueOf(info.applicationInfo.loadLabel(pm));
                            appInfo.rx = rx;
                            appInfo.tx = tx;
                            //appInfo.appIcon = info.applicationInfo.loadIcon(pm);
                            //result = result + info.applicationInfo.loadLabel(pm)+ Formatter.formatFileSize(this, rx+tx) + "\n";
                            //appList.add(appInfo);
                            Log.v("applist",appInfo.appName + " "+ appInfo.rx + " " + appInfo.tx +" "+ Formatter.formatFileSize(this, rx+tx));
                        }

                        //uidTraffic.setText(result);
                    }

                }
            }
        }
    }
}
