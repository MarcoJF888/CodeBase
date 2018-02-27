package com.zjf.myself.codebase.helper;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Administrator on 2017/3/15.
 */

public class JJSQLUtil {
//    private MyDatabaseHelper dbHelper;
//    SQLiteDatabase db = dbHelper.getWritableDatabase();
//    JJSQLUtil.getHeartRateDatas(db);

    public static String getHeartRateDatas(SQLiteDatabase v){
        String name = "";

    // 查询Book表中所有的数据
    Cursor cursor = v.query("Book", null, null, null, null, null, null);
    if (cursor.moveToFirst()) {
        do {
            // 遍历Cursor对象，取出数据并打印
            name = cursor.getString(cursor.
                    getColumnIndex("name"));
            String author = cursor.getString(cursor.
                    getColumnIndex("author"));
            int pages = cursor.getInt(cursor.getColumnIndex
                    ("pages"));
            double price = cursor.getDouble(cursor.
                    getColumnIndex("price"));
            Log.d("MainActivity", "book name is " + name);
            Log.d("MainActivity", "book author is " + author);
            Log.d("MainActivity", "book pages is " + pages);
            Log.d("MainActivity", "book price is " + price);

        } while (cursor.moveToNext());
    }
    cursor.close();
        return name;
    }
}
