package com.zjf.myself.codebase.activity.ExerciseLibrary;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.helper.MyDatabaseHelper;

/**
 * Created by Administrator on 2017/1/11.
 */

public class MySQLDatabaseTestAct extends Activity{

    private MyDatabaseHelper dbHelper;
    private Button btnCreateDb,btnAddData,btnUpdateData,btnDeleteData,btnQueryData,btnEmptyData,btnReplaceData;
    private TextView txtResult;
     @Override
         protected void onCreate(Bundle savedInstanceState) {
             super.onCreate(savedInstanceState);
             setContentView(R.layout.win_my_sql_database_test);



         dbHelper = new MyDatabaseHelper(this,"BookStore.db",null,1);
        //创建
         btnCreateDb = (Button)findViewById(R.id.btnCreateDb);
         btnCreateDb.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 dbHelper.getWritableDatabase();
             }
         });
        //增加
         btnAddData = (Button)findViewById(R.id.btnAddData);
         btnAddData.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 SQLiteDatabase db = dbHelper.getWritableDatabase();
                 ContentValues values = new ContentValues();
            // 开始组装第一条数据
                 values.put("name", "AAAAAAAA");
                 values.put("author", "authorAAAAA");
                 values.put("pages", 100);
                 values.put("price", 10);
                 db.insert("Book", null, values); // 插入第一条数据
                 values.clear();
             // 开始组装第二条数据
                 values.put("name", "BBBBBBBB");
                 values.put("author", "authorBBBBB");
                 values.put("pages", 200);
                 values.put("price", 20);
                 db.insert("Book", null, values); // 插入第二条数据

                 values.put("name", "CCCCCCCC");
                 values.put("author", "authorCCCCC");
                 values.put("pages", 600);
                 values.put("price", 60);
                 db.insert("Book", null, values); // 插入第二条数据
             }
         });
            //升级
         btnUpdateData = (Button)findViewById(R.id.btnUpdateData);
         btnUpdateData.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 SQLiteDatabase db = dbHelper.getWritableDatabase();
                 ContentValues values = new ContentValues();
                 values.put("price", 10.111);
                 db.update("Book", values, "name = ?", new String[] { "AAAAAAAA" });
             }
         });

        //删除
         btnDeleteData = (Button)findViewById(R.id.btnDeleteData);
         btnDeleteData.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 SQLiteDatabase db = dbHelper.getWritableDatabase();
                 db.delete("Book", "pages > ?", new String[] { "500" });
             }
         });
        //查询
         btnQueryData = (Button)findViewById(R.id.btnQueryData);
         btnQueryData.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 SQLiteDatabase db = dbHelper.getWritableDatabase();
            // 查询Book表中所有的数据
                 Cursor cursor = db.query("Book", null, null, null, null, null, null);
                 if (cursor.moveToFirst()) {
                     do {
            // 遍历Cursor对象，取出数据并打印
                         String name = cursor.getString(cursor.
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

//                         txtResult = (TextView)findViewById(R.id.txtResult);
//                         txtResult.setText("book name is " + name+"\n"+"book author is " + author+"\n"+"book pages is " + pages+"\n"+"book price is " + price);


                     } while (cursor.moveToNext());
                 }
                 cursor.close();
             }
         });

         btnEmptyData = (Button)findViewById(R.id.btnEmptyData);
         btnEmptyData.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 SQLiteDatabase db = dbHelper.getWritableDatabase();
                 db.delete("Book", "pages > ?", new String[] { "10" });
             }
         });

         //替换数据
         btnReplaceData = (Button)findViewById(R.id.btnReplaceData);
         btnReplaceData.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 SQLiteDatabase db = dbHelper.getWritableDatabase();
                 db.beginTransaction(); // 开启事务
                 try {
                     db.delete("Book", null, null);
                     if (true) {
// 在这里手动抛出一个异常，让事务失败
//                         throw new NullPointerException();
                     }
                     ContentValues values = new ContentValues();
                     values.put("name", "DDDDDDDDD");
                     values.put("author", "authorDDDDD");
                     values.put("pages", 999);
                     values.put("price", 99);
                     db.insert("Book", null, values);
                     db.setTransactionSuccessful(); // 事务已经执行成功
                 } catch (Exception e) {
                     e.printStackTrace();
                 } finally {
                     db.endTransaction(); // 结束事务
                 }
             }
         });
     }
}
