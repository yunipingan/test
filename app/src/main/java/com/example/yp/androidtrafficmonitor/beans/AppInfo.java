package com.example.yp.androidtrafficmonitor.beans;

import android.graphics.drawable.Drawable;
import android.util.Log;

import java.io.Serializable;

/**
 * Created by yp on 2016/7/25.
 */
public class AppInfo implements Serializable {
    public String appName="";
    public String packageName="";
    public int uid;
    public long rx;
    public long tx;
    //public Drawable appIcon=null;
    public void print()
    {

    }

}