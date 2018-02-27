package com.zjf.myself.codebase.activity.ExerciseLibrary;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.helper.FileHelper;

import java.io.IOException;

/**
 * Created by Administrator on 2017/3/16.
 */

public class TXTFileStorageAct extends AppCompatActivity implements View.OnClickListener {
    private EditText editname;
    private EditText editdetail;
    private Button btnsave;
    private Button btnreset;
    private Button btnread;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.win_txt_file_storage);
        //得到程序当前的Context，即MainActivity.this
        mContext = getApplicationContext();
        bindViews();
    }


    private void bindViews() {
        editname = (EditText) findViewById(R.id.edit_name);
        editdetail = (EditText) findViewById(R.id.edit_text);
        btnreset = (Button) findViewById(R.id.write);
        btnsave = (Button) findViewById(R.id.reset);
        btnread = (Button) findViewById(R.id.read);

        btnreset.setOnClickListener(this);
        btnsave.setOnClickListener(this);
        btnread.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reset:
                //点击重置按钮，将两个输入框清空
                editdetail.setText("");
                editname.setText("");
                break;
            case R.id.write:
                //创建文件助手对象，传入mContext程序当前的内容
                FileHelper fHelper = new FileHelper(mContext);
                //获得文件名和写入内容
                String filename = editname.getText().toString();
                String filetext = editdetail.getText().toString();
                try {
                    //保存文件名和内容
                    fHelper.save(filename, filetext);
                    Toast.makeText(getApplicationContext(), "数据写入成功", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    //写入异常时
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "数据写入失败", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.read:
                //定论一个detail，默认为空用来存放要输出的内容
                String detail = "";
                FileHelper fHelper2 = new FileHelper(getApplicationContext());
                try {
                    //得到输入框中文件名获得文件内容，因为可以写入多个不同名文件，所以要根据文件名来获得文件内容
                    String fname = editname.getText().toString();
                    //调用read()方法，传入上面获得的文件保，将返回的内容赋值给detail
                    detail = fHelper2.read(fname);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), detail, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
