package com.example.yp.androidtrafficmonitor.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.yp.androidtrafficmonitor.R;
import com.example.yp.androidtrafficmonitor.adapter.MyAdapter1;
import com.example.yp.androidtrafficmonitor.beans.AppInfo;
import com.example.yp.androidtrafficmonitor.utils.ArrayListUtil;

import java.util.ArrayList;

/**
 * Created by yp on 2016/9/26.
 */
public class TrafficMonitorActivity extends AppCompatActivity implements View.OnClickListener{
    String result;
    private ListView uidListView;
    private ArrayList<AppInfo> arrayList;
    private MyAdapter1 myAdapter1;
    private Handler handler;
    private ListView listView;
    private Button backBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.traffic_monitor_layout);
        listView = (ListView) findViewById(R.id.app_list);
        backBtn = (Button) findViewById(R.id.backBtn1);
        backBtn.setOnClickListener(this);
        myAdapter1 = new MyAdapter1(getApplicationContext(), ArrayListUtil.appInfoArrayList);
        listView.setAdapter(myAdapter1);


    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.backBtn1){
            //Intent intent = new Intent(this,MainActivity.class);
            //startActivity(intent);
            this.finish();
        }
    }
}
