package com.example.yp.androidtrafficmonitor.beans;

import android.graphics.drawable.Drawable;
import android.util.Log;

import java.io.Serializable;

/**
 * Created by yp on 2016/7/25.
 */
public class AppInfo implements Serializable {
    public int uid;
    public String packageName;
    public String appName;
    public long traffic;

}