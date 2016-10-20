package com.example.yp.androidtrafficmonitor.utils;

import android.util.SparseArray;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by yp on 2016/10/11.
 */

public class ViewHolder {
    public ImageView image;
    public TextView appName;
    public CheckBox phoneCB;
    public CheckBox wifiCB;
    public TextView trafficTV;

    public ViewHolder(ImageView image, TextView appName, CheckBox phoneCB, CheckBox wifiCB, TextView trafficTv) {
        this.image = image;
        this.appName = appName;
        this.phoneCB = phoneCB;
        this.wifiCB = wifiCB;
        this.trafficTV = trafficTv;
    }
}
