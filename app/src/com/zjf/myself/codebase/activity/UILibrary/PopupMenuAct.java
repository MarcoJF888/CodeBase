package com.zjf.myself.codebase.activity.UILibrary;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.activity.BaseAct;
import com.zjf.myself.codebase.adapter.PopupWindowAdaper;
import com.zjf.myself.codebase.util.UIUtils;
import com.zjf.myself.codebase.util.ViewUtils;


/**
 * <pre>
 *     author : ZouJianFeng
 *     e-mail :
 *     time   : 2017/04/10
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class PopupMenuAct extends BaseAct{

    private View menuView;

    private LinearLayout btnRight;
    private PopupWindow mPopupWindow;
    RelativeLayout layoutBottom;
    public ImageView imgCalibration,imgNormal;

     @Override
         protected void onCreate(Bundle savedInstanceState) {
             super.onCreate(savedInstanceState);
             setContentView(R.layout.act_popup_menu);

             layoutBottom = (RelativeLayout)findViewById(R.id.layoutBottom);

             btnRight = (LinearLayout)findViewById(R.id.btnRight);
             btnRight.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 showMenu();
             }
         });
      }

    private boolean isNormalMode;

    private void showMenu() {
        menuView = View.inflate(this, R.layout.popup_menu, null);
        //正常模式
        mPopupWindow = PopupWindowAdaper.getPopupWindowAtLocation(menuView, layoutBottom, Gravity.RIGHT | Gravity.TOP,
                UIUtils.dip2Px(12),UIUtils.dip2Px(64)); //+ getStatusBarHeight()

        hintSelectIcon(isNormalMode);

        menuView.findViewById(R.id.btnNormalMode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              ViewUtils.showToast("正常模式");
                hintSelectIcon(true);
                //测试
                isNormalMode = true;
                mPopupWindow.dismiss();


            }
        });

        //校准模式
        menuView.findViewById(R.id.btnCalibrationMode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ViewUtils.showToast("校准模式");
                hintSelectIcon(false);
                //测试
                isNormalMode = false;
                mPopupWindow.dismiss();
            }
        });

    }

    private void hintSelectIcon(boolean isNormalMode){
        if (isNormalMode){
            menuView.findViewById(R.id.imgNormal).setVisibility(View.VISIBLE);
            menuView.findViewById(R.id.imgCalibration).setVisibility(View.GONE);
        }else {
            menuView.findViewById(R.id.imgCalibration).setVisibility(View.VISIBLE);
            menuView.findViewById(R.id.imgNormal).setVisibility(View.GONE);
        }
    }
}
