package com.example.yp.androidtrafficmonitor.adapter;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yp.androidtrafficmonitor.R;
import com.example.yp.androidtrafficmonitor.beans.AppInfo;
import com.example.yp.androidtrafficmonitor.utils.ViewHolder;

import java.util.ArrayList;

/**
 * Created by yp on 2016/7/25.
 */
public class MyAdapter2 extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private ArrayList<AppInfo> appInfoArrayList;
    private AppInfo appInfo;
    private Context context;

    public MyAdapter2(Context context, ArrayList<AppInfo> appInfoArrayList ) {
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
    public View getView(int i, View view, ViewGroup viewGroup) {

        appInfo = appInfoArrayList.get(i);
        PackageManager pManager = context.getPackageManager();
        Drawable icon = null;
        try {
            icon = pManager.getApplicationIcon(appInfoArrayList.get(i).packageName);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        ViewHolder viewHolder;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.app_item2, null);
            ImageView appIcon = (ImageView) view.findViewById(R.id.app_icon2);
            TextView appName = (TextView) view.findViewById(R.id.app_name2);
            CheckBox phoneCB = (CheckBox) view.findViewById(R.id.moblieBtn2);
            CheckBox wifiCB = (CheckBox) view.findViewById(R.id.wifiBtn2);

            viewHolder = new ViewHolder(appIcon, appName,phoneCB, wifiCB,null);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.appName.setText(appInfoArrayList.get(i).appName);
        viewHolder.image.setImageDrawable(icon);

        return view;

    }


}


        //文艺模式
        /*

    }
}*/

