package com.example.yp.androidtrafficmonitor.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by yp on 2016/10/10.
 */
public class TestActivity extends AppCompatActivity{

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        while(true){
            Log.v("Test","TestActivity");
        }
    }
}
