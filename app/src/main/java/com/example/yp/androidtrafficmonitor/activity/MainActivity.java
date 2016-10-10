package com.example.yp.androidtrafficmonitor.activity;

import android.app.Dialog;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.TrafficStats;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yp.androidtrafficmonitor.R;
import com.example.yp.androidtrafficmonitor.beans.AppInfo;
import com.example.yp.androidtrafficmonitor.broadcast.MyBroadcastReceiver;
import com.example.yp.androidtrafficmonitor.service.TrafficMonitorBindService;
import com.example.yp.androidtrafficmonitor.service.TrafficMonitorService;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button showBtn;
    private Button queryTrafficBtn;
    private Button aboutUsBtn;
    private Button settingBtn;
    private Button interConnMonitorBtn;
    private TextView uidTraffic;
    private String result;

    ArrayList<AppInfo> appList = new ArrayList<AppInfo>();

    TrafficMonitorBindService service;


    ServiceConnection conn = new ServiceConnection() {

        @Override//当服务跟启动源断开的时候 会自动回调
        public void onServiceDisconnected(ComponentName name) {
            // TODO Auto-generated method stub

        }

        @Override//当服务跟启动源连接的时候 会自动回调
        public void onServiceConnected(ComponentName name, IBinder binder) {
            // TODO Auto-generated method stub
            service = ((TrafficMonitorBindService.MyBinder)binder).getService();
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);
        /*getAppTrafficList();
        Intent intent = new Intent(MainActivity.this,TrafficMonitorBindService.class);
        intent.putExtra("applist",appList);
        bindService(intent, conn, Service.BIND_AUTO_CREATE);*/
        Init();
    }

    void Init(){
        showBtn=(Button) findViewById(R.id.trafficMonitorBtn);
        queryTrafficBtn = (Button) findViewById(R.id.queryTraffic);
        aboutUsBtn = (Button) findViewById(R.id.aboutUs);
        uidTraffic = (TextView) findViewById(R.id.uidTraffic);
        settingBtn = (Button) findViewById(R.id.setting);
        interConnMonitorBtn = (Button) findViewById(R.id.internetConnectionMonitorBtn);

        aboutUsBtn.setOnClickListener(this);
        showBtn.setOnClickListener(this);
        queryTrafficBtn.setOnClickListener(this);
        settingBtn.setOnClickListener(this);
        interConnMonitorBtn.setOnClickListener(this);
    }

    /*public void getAppTrafficList(){
        AppInfo appInfo = null;
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
                        if(rx<0 || tx<0){
                            continue;
                        }else{
                            appInfo = new AppInfo();
                            appInfo.appName = String.valueOf(info.applicationInfo.loadLabel(pm));
                            //appInfo.appIcon = info.applicationInfo.loadIcon(pm);
                            //result = result + info.applicationInfo.loadLabel(pm)+ Formatter.formatFileSize(this, rx+tx) + "\n";
                            appList.add(appInfo);
                        }
                    }
                }
            }
        }

        //uidTraffic.setText(result);
    }*/

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.trafficMonitorBtn: {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, TrafficMonitorActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.internetConnectionMonitorBtn:{
                Log.v("start","开始");
                /*Intent intent = new Intent(MainActivity.this, TrafficMonitorService.class);
                intent.setAction("com.yp.test");
                startService(intent);*/
                IntentFilter intentFilter = new IntentFilter(Intent.ACTION_TIME_TICK);
                MyBroadcastReceiver myBroadcastReceiver = new MyBroadcastReceiver();
                registerReceiver(myBroadcastReceiver,intentFilter);
                Log.v("end","结束");
                break;
            }

            case R.id.queryTraffic:sendMessage();
                break;

            case R.id.aboutUs : {
                Dialog dialog = new Dialog(this);
                //设置它的ContentView
                dialog.setContentView(R.layout.about_us_dialog);
                dialog.show();
                break;
            }
            case R.id.setting:{
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,SettingActivity.class);
                startActivity(intent);

                break;

            }
        }

    }

    void sendMessage(){
    String phone_number = "10086";
    String sms_content = "cxll";
    SmsManager smsManager = SmsManager.getDefault();
    int i = sms_content.length();
    smsManager.sendTextMessage(phone_number, null, sms_content, null, null);
    Toast.makeText(MainActivity.this, "发送完毕", Toast.LENGTH_SHORT).show();
}

}


