package com.example.yp.androidtrafficmonitor.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by yp on 2016/9/26.
 */
public class DBOpenHelper extends SQLiteOpenHelper {

    private String DBName;

    public DBOpenHelper(Context context, String name) {
        super(context, name, null, 1);
        DBName = name;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table if not exists "+DBName+" (uid integer primary key,packageName text not null,appName text not null,traffic integer)");
        Log.v("DataBase","创建成功");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
