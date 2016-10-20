package com.example.yp.androidtrafficmonitor.activity;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yp.androidtrafficmonitor.R;
import com.example.yp.androidtrafficmonitor.beans.AppInfo;
import com.example.yp.androidtrafficmonitor.service.TrafficMonitorService;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button showBtn;
    private Button queryTrafficBtn;
    private Button aboutUsBtn;
    private Button settingBtn;
    private Button interConnMonitorBtn;
    private Button lineChartBtn;
    private TextView uidTraffic;
    private TextView allTrafficTV;

    SharedPreferences sharedPreferences;

    private long trafficTemp;

    private long mobileTraffic;
    private long mobileTemp;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //allTrafficTV.setText(Formatter.formatFileSize(getApplicationContext(),traffic)+",其中手机流量为"+Formatter.formatFileSize(getApplicationContext(),mobile));
            if(msg.what == 1){
                allTrafficTV.setText(Formatter.formatFileSize(getApplicationContext(),mobileTraffic));
            }
        }
    };


    ArrayList<AppInfo> appList = new ArrayList<AppInfo>();


    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(MainActivity.this,TrafficMonitorService.class);
        startService(intent);

        setContentView(R.layout.menu_layout);
        /*getAppTrafficList();
        Intent intent = new Intent(MainActivity.this,TrafficMonitorBindService.class);
        intent.putExtra("applist",appList);
        bindService(intent, conn, Service.BIND_AUTO_CREATE);*/
        Init();
        sharedPreferences = getSharedPreferences("trafficInfor", Context.MODE_PRIVATE);


        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                //traffic = sharedPreferences.getLong("traffic",0);
                mobileTraffic = sharedPreferences.getLong("mobile",0);
                if(mobileTraffic!=mobileTemp) {
                    mobileTemp = mobileTraffic;
                    Message msg = new Message();
                    msg.what = 1;
                    handler.sendMessage(msg);
                }
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();




    }

    void Init(){
        showBtn=(Button) findViewById(R.id.trafficMonitorBtn);
        queryTrafficBtn = (Button) findViewById(R.id.queryTraffic);
        aboutUsBtn = (Button) findViewById(R.id.aboutUs);
        settingBtn = (Button) findViewById(R.id.setting);
        interConnMonitorBtn = (Button) findViewById(R.id.interConnMonitorBtn);
        lineChartBtn = (Button) findViewById(R.id.lineChartBtn);

        allTrafficTV = (TextView) findViewById(R.id.allTraffic);
        uidTraffic = (TextView) findViewById(R.id.uidTraffic);

        aboutUsBtn.setOnClickListener(this);
        showBtn.setOnClickListener(this);
        queryTrafficBtn.setOnClickListener(this);
        settingBtn.setOnClickListener(this);
        interConnMonitorBtn.setOnClickListener(this);
        lineChartBtn.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.trafficMonitorBtn: {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, TrafficMonitorActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.interConnMonitorBtn:{
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, InterConnMonitorActivity.class);
                startActivity(intent);
                /*Log.v("start","开始");
                Intent intent = new Intent(MainActivity.this, TrafficMonitorService.class);
                intent.setAction("com.yp.test");
                startService(intent);
                IntentFilter intentFilter = new IntentFilter(Intent.ACTION_TIME_TICK);
                MyBroadcastReceiver myBroadcastReceiver = new MyBroadcastReceiver();
                registerReceiver(myBroadcastReceiver,intentFilter);
                Log.v("end","结束");*/
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
            case R.id.lineChartBtn:{
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,GraphChartActivity.class);
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
    MainActivity.this.getContentResolver().registerContentObserver(
                Uri.parse("content://sms"), true, new SmsObserver(new Handler()));
        Log.v("Observer","StartObserver");
}

    private class SmsObserver extends ContentObserver {

        public SmsObserver(Handler handler) {
            super(handler);
            Log.v("Observer","StartObserver1");
            // TODO Auto-generated constructor stub
        }

        @Override
        public void onChange(boolean selfChange) {
            Log.v("Observer","StartObserver2");
            StringBuilder sb = new StringBuilder();
            Cursor cursor = getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, null);
            cursor.moveToNext();
            sb.append("body=" + cursor.getString(cursor.getColumnIndex("body")));
            Log.i("Observer", sb.toString());
            cursor.close();
            Pattern pat = Pattern.compile("已用(.*?)M");//解析出"已使用...M"样式的语句
            Matcher mat = pat.matcher(sb);


            if(mat.find()){
                String st = mat.group();
                Log.v("ObserverLast1",st);
                mobileTraffic = Long.valueOf(st.substring(2,st.length()))*1024*1024;
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putLong("mobile",mobileTraffic);
                Message msg = new Message();
                msg.what = 1;
                handler.sendMessage(msg);
            }
            /*Pattern pats = Pattern.compile("[\\d]+\\.[\\d]+");//解析出"已使用...M"语句中的数字
            Matcher mats = pat.matcher(mat.group());
            if(mats.find()){
                Log.v("ObserverLast2",mat.group());
            }*/
            Log.v("Observer","StartObserver3");
            super.onChange(selfChange);
        }
    }


}


