package com.zjf.myself.codebase.activity.UILibrary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.zjf.myself.codebase.R;

/**
 * Created by Administrator on 2017/1/22.
 */

public class JiangJiangLoadingAct extends Activity{
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.win_jiangjiang_loading);
        startMainActivity();
    }

    private void startMainActivity() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                intent = new Intent(JiangJiangLoadingAct.this, JiangJiangAct.class);
                startActivity(intent);
                JiangJiangLoadingAct.this.finish();//结束本Activity
            }
        },500);//设置执行时间
    }
}