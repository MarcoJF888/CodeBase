package com.zjf.myself.codebase.activity;

/**
 * ************************************************************************
 * **                              _oo0oo_                               **
 * **                             o8888888o                              **
 * **                             88" . "88                              **
 * **                             (| -_- |)                              **
 * **                             0\  =  /0                              **
 * **                           ___/'---'\___                            **
 * **                        .' \\\|     |// '.                          **
 * **                       / \\\|||  :  |||// \\                        **
 * **                      / _ ||||| -:- |||||- \\                       **
 * **                      | |  \\\\  -  /// |   |                       **
 * **                      | \_|  ''\---/''  |_/ |                       **
 * **                      \  .-\__  '-'  __/-.  /                       **
 * **                    ___'. .'  /--.--\  '. .'___                     **
 * **                 ."" '<  '.___\_<|>_/___.' >'  "".                  **
 * **                | | : '-  \'.;'\ _ /';.'/ - ' : | |                 **
 * **                \  \ '_.   \_ __\ /__ _/   .-' /  /                 **
 * **            ====='-.____'.___ \_____/___.-'____.-'=====             **
 * **                              '=---='                               **
 * ************************************************************************
 * **                        佛祖保佑      镇类之宝                         **
 * ************************************************************************
 *
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.dialog.VerifyDialog;
import com.zjf.myself.codebase.helper.LoginHelper;
import com.zjf.myself.codebase.util.CallBack;
import com.zjf.myself.codebase.util.ViewUtils;

public class HomeAct extends BaseAct{

    private long mExitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void jumpToActivity(Class activityClass) {
        startActivity(new Intent(this, activityClass));
    }

    public void btnExerciseLibrary(View view) {
        jumpToActivity(ListExerciseLibraryAct.class);
    }

    public void btnAlgorithmLibrary(View view) {
        jumpToActivity(ListAlgorithmLibraryAct.class);
    }

    public void btnUILibrary(View view) {
        jumpToActivity(ListUILibraryAct.class);
    }


    public void btnListTaskAct(View view) {
        jumpToActivity(ListTaskAct.class);
    }

    public void btnFreeTime(View view) {
        jumpToActivity(ListFreeTimeAct.class);
    }

    public void btnWebView(View view) {
//        WebviewAct.start("网页加载", "http://laole.yinengsz.com/feedback.php",this);
        WebviewAct.start("网页加载", "http://10.0.0.106:8080/project/discover.html",this);
//        WebviewAct.start("网页加载", "https://www.baidu.com/s?ie=utf8&oe=utf8&wd=%E5%8F%8C%E8%89%B2%E7%90%83&tn=98010089_dg&ch=1",this);
    }
//    public void btnWebView(View view) {
//        WebviewAct.start("网页加载", "https://h5.youzan.com/v2/feature/94cs5bv2?redirect_count=2",this);
//    }

    public void btnExitLogin(View view) {
        new VerifyDialog().show("退出登录", " ,是否退出登录?","确定", new CallBack() {
            @Override
            public void onCall(Object data) {
                LoginHelper.cleanLogin();
                Intent btnExitLogin = new Intent(HomeAct.this, LoginAct.class);
                startActivity(btnExitLogin);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                ViewUtils.showToast("再按一次退出程序");
                mExitTime = System.currentTimeMillis();
                return true;
            } else {
                finish();
            }
        }
        return super.onKeyDown(keyCode , event);
    }
}
