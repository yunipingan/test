package com.example.yp.androidtrafficmonitor.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.TrafficStats;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Formatter;
import android.widget.ListView;

import com.example.yp.androidtrafficmonitor.R;
import com.example.yp.androidtrafficmonitor.adapter.MyAdapter;
import com.example.yp.androidtrafficmonitor.beans.AppInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yp on 2016/9/26.
 */
public class TrafficMonitorActivity extends AppCompatActivity{
    String result;
    private ListView uidListView;
    private ArrayList<AppInfo> arrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.traffic_monitor_layout);
        uidListView = (ListView) findViewById(R.id.uidTraListView);

        arrayList = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            //arrayList.add(new AppInfo(R.mipmap.ic_launcher));
        }

        MyAdapter myAdapter = new MyAdapter(this,arrayList);
        uidListView.setAdapter(myAdapter);

    }

    public void getAppTrafficList(){
        //获取所有的安装在手机上的应用软件的信息，并且获取这些软件里面的权限信息
        PackageManager pm=getPackageManager();//获取系统应用包管理
        //获取每个包内的androidmanifest.xml信息，它的权限等等
        List<PackageInfo> pinfos=pm.getInstalledPackages
                ( PackageManager.GET_PERMISSIONS);
        //遍历每个应用包信息
        for(PackageInfo info:pinfos){
            //请求每个程序包对应的androidManifest.xml里面的权限
            String[] premissions=info.requestedPermissions;
            if(premissions!=null && premissions.length>0){
                //找出需要网络服务的应用程序
                for(String premission : premissions){
                    if("android.permission.INTERNET".equals(premission)){
                        //获取每个应用程序在操作系统内的进程id
                        int uId=info.applicationInfo.uid;
                        //如果返回-1，代表不支持使用该方法，注意必须是2.2以上的
                        long rx= TrafficStats.getUidRxBytes(uId);
                        //如果返回-1，代表不支持使用该方法，注意必须是2.2以上的
                        long tx= TrafficStats.getUidTxBytes(uId);

                        Drawable appIcon = info.applicationInfo.loadIcon(getPackageManager());

                        if(rx<0 || tx<0){
                            continue;
                        }else{
                            result = result + info.applicationInfo.loadLabel(pm)+ Formatter.formatFileSize(this, rx+tx) + "\n";
                        }
                    }
                }
            }
        }

        //uidTraffic.setText(result);
    }

}
