package com.example.yp.androidtrafficmonitor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.yp.androidtrafficmonitor.R;
import com.example.yp.androidtrafficmonitor.beans.AppInfo;

import java.util.List;

/**
 * Created by yp on 2016/7/25.
 */
public class MyAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private List<AppInfo> appInfoList;

    public MyAdapter(Context context, List<AppInfo> appInfoList) {
        layoutInflater = LayoutInflater.from(context);
        this.appInfoList = appInfoList;
    }

    @Override
    public int getCount() {
        return appInfoList.size();
    }

    @Override
    public Object getItem(int i) {
        return appInfoList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View new_view = layoutInflater.inflate(R.layout.uid_traffic_item, null);
        ImageView image = (ImageView) new_view.findViewById(R.id.uidImage);
        TextView appName;
        CheckBox phoneCB;
        CheckBox wifiCB;
        //image.setImageResource(appInfoList.get(i).ItemImageResId);
        return new_view;

    }
}
        //逗比模式
//        View new_view = layoutInflater.inflate(R.layout.item, null);
//        ImageView image = (ImageView)new_view.findViewById(R.id.image);
//        TextView title = (TextView) new_view.findViewById(R.id.title);
//        TextView content = (TextView) new_view.findViewById(R.id.content);
//
//        image.setImageResource(itemBeanList.get(i).ItemImageResId);
//        title.setText(itemBeanList.get(i).ItemTile);
//        content.setText(itemBeanList.get(i).ItemContent);
//        return new_view;

        //普通模式
//        if(view == null){
//            view = layoutInflater.inflate(R.layout.item, null);
//        }
//        ImageView image = (ImageView)view.findViewById(R.id.image);
//        TextView title = (TextView) view.findViewById(R.id.title);
//        TextView content = (TextView) view.findViewById(R.id.content);
//        image.setImageResource(itemBeanList.get(i).ItemImageResId);
//        title.setText(itemBeanList.get(i).ItemTile);
//        content.setText(itemBeanList.get(i).ItemContent);
//        return view;
//    }

        //文艺模式
        /*ViewHolder viewHolder;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.uid_traffic_item, null);
            ImageView image = (ImageView) view.findViewById(R.id.image);

            viewHolder = new ViewHolder(image, title, content);
            view.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.image.setImageResource(itemBeanList.get(i).ItemImageResId);
        viewHolder.title.setText(itemBeanList.get(i).ItemTile);
        viewHolder.content.setText(itemBeanList.get(i).ItemContent);
        return view;

    }
}*/

