package com.zjf.myself.codebase.activity.UILibrary;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;


import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.activity.BaseAct;
import com.zjf.myself.codebase.thirdparty.circlemenu.CircleMenu;
import com.zjf.myself.codebase.thirdparty.circlemenu.OnMenuSelectedListener;
import com.zjf.myself.codebase.thirdparty.circlemenu.OnMenuStatusChangeListener;
import com.zjf.myself.codebase.util.ViewUtils;

public class CircleMenuAct extends BaseAct {

    private CircleMenu circleMenuAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_circle_menu);

        circleMenuAct = (CircleMenu) findViewById(R.id.circle_menu);

        circleMenuAct.setMainMenu(Color.parseColor("#CDCDCD"), R.mipmap.icon_menu, R.mipmap.icon_cancel)
                .addSubMenu(Color.parseColor("#258CFF"), R.mipmap.icon_home)
                .addSubMenu(Color.parseColor("#30A400"), R.mipmap.icon_search)
                .addSubMenu(Color.parseColor("#FF4B32"), R.mipmap.icon_notify)
                .addSubMenu(Color.parseColor("#8A39FF"), R.mipmap.icon_setting)
                .addSubMenu(Color.parseColor("#FF6A00"), R.mipmap.icon_gps)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {

                    @Override
                    public void onMenuSelected(int index) {
                        switch(index){
                            //跳转页面可能加延迟
                            case 0:
                                ViewUtils.showToast("0");
//                                new Thread(new Runnable(){
//                                    public void run(){
//                                        try {
//                                            Thread.sleep(600);
//                                        } catch (InterruptedException e) {
//                                            e.printStackTrace();
//                                        }
//                                        Message msg = new Message();
//                                        msg.arg1 = 0;
//                                        handler.sendMessage(msg);
//                                    }
//                                }).start();
                            break;

                            default:
                            break;
                        }
                    }

                }).setOnMenuStatusChangeListener(new OnMenuStatusChangeListener() {

                    @Override
                    public void onMenuOpened() {}

                    @Override
                    public void onMenuClosed() {}

                });
    }


//    //停止事件
//    private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            switch(msg.arg1) {
//                case 0:
//                    finish();
//                    break;
//                case 1:
//
//                    break;
//            }
//
//        }
//    };



    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        circleMenuAct.openMenu();
        return super.onMenuOpened(featureId, menu);
    }

    @Override
    public void onBackPressed() {
        if (circleMenuAct.isOpened()){
            circleMenuAct.closeMenu();
        }else{
            finish();
        }
    }
}
