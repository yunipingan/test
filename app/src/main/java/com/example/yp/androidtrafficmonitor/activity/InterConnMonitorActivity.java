package com.example.yp.androidtrafficmonitor.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;

import com.example.yp.androidtrafficmonitor.R;
import com.example.yp.androidtrafficmonitor.adapter.MyAdapter2;
import com.example.yp.androidtrafficmonitor.utils.ArrayListUtil;

/**
 * Created by yp on 2016/9/26.
 */
public class InterConnMonitorActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener,View.OnClickListener{
    private CheckBox allMobileCB;
    private CheckBox allWifiCB;
    private ListView appLV;
    private MyAdapter2 myAdapter;
    private Handler handler;
    private Button backBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inter_conn_monitor_layout);
        Init();
        myAdapter = new MyAdapter2(this, ArrayListUtil.appInfoArrayList);
        appLV.setAdapter(myAdapter);
    }

    void Init(){
        allMobileCB = (CheckBox) findViewById(R.id.allmobileCB);
        allWifiCB = (CheckBox) findViewById(R.id.allWifiCB);
        appLV = (ListView) findViewById(R.id.app_list2);
        backBtn = (Button) findViewById(R.id.backBtn2);
        backBtn.setOnClickListener(this);
        allMobileCB.setOnCheckedChangeListener(this);
        allWifiCB.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.allmobileCB:{
                if(isChecked){

                }
                else{

                }
                break;
            }
            case R.id.allWifiCB:{
                if(isChecked){

                }
                else{

                }
                break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.backBtn2){
            //Intent intent = new Intent(this,MainActivity.class);
            //startActivity(intent);
            this.finish();
        }
    }
}
