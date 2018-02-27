package com.zjf.myself.codebase.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.helper.LoginHelper;
import com.zjf.myself.codebase.util.ViewUtils;

/**
 * <pre>
 *     author : ZouJianFeng
 *     e-mail :
 *     time   : 2017/04/06
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class LaunchAct extends BaseAct{
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.act_loading);
        startMainActivity();
    }

    private void startMainActivity() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                boolean isLogin = LoginHelper.checkLogin();
                if (isLogin){
                    ViewUtils.showToast("自动登录成功,账号为:"+LoginHelper.checkAcount(), Toast.LENGTH_LONG);
                    intent = new Intent(LaunchAct.this, HomeAct.class);
                    startActivity(intent);

                }else{
                    intent = new Intent(LaunchAct.this, LoginAct.class);
                    startActivity(intent);
                }
                LaunchAct.this.finish();//结束本Activity
            }
        },1500);//设置执行时间
    }
}