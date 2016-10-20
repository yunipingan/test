package com.example.yp.androidtrafficmonitor.adapter;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yp.androidtrafficmonitor.R;
import com.example.yp.androidtrafficmonitor.beans.AppInfo;

import java.util.ArrayList;

/**
 * Created by yp on 2016/7/25.
 */
public class MyAdapter1 extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private ArrayList<AppInfo> appInfoArrayList;
    private AppInfo appInfo;
    private Context context;

    public MyAdapter1(Context context, ArrayList<AppInfo> appInfoArrayList ) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.appInfoArrayList = appInfoArrayList;
    }

    @Override
    public int getCount() {
        return appInfoArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return appInfoArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view1, ViewGroup viewGroup) {

        View view = layoutInflater.inflate(R.layout.app_item1, null);

        appInfo = appInfoArrayList.get(i);
        PackageManager pManager = context.getPackageManager();
        Drawable icon = null;
        try {
            icon = pManager.getApplicationIcon(appInfoArrayList.get(i).packageName);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

            ImageView appIcon = (ImageView) view.findViewById(R.id.app_icon1);
            TextView appName = (TextView) view.findViewById(R.id.app_name1);
            TextView trafficTV = (TextView) view.findViewById(R.id.app_traffic1);
            appIcon.setImageDrawable(icon);
            appName.setText(appInfoArrayList.get(i).appName);
            trafficTV.setText(Formatter.formatFileSize(context,appInfoArrayList.get(i).traffic));

        return view;

    }


}


        //文艺模式
        /*

    }
}*/

