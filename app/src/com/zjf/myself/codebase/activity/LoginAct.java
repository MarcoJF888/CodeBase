package com.zjf.myself.codebase.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.application.AppController;
import com.zjf.myself.codebase.fragment.FindPasswdFragment;
import com.zjf.myself.codebase.fragment.FragmentTabHandler;
import com.zjf.myself.codebase.fragment.LoginFragment;
import com.zjf.myself.codebase.fragment.RegestFragment;
import com.zjf.myself.codebase.util.CallBack;
import com.zjf.myself.codebase.util.ViewUtils;

import java.util.ArrayList;
import java.util.List;



public class LoginAct extends BaseAct {
    public static final int LOGIN_STATE_NO_LOGIN=1;
    public static final int LOGIN_STATE_SUCCESS=2;

    public FragmentTabHandler fragmentContorler;

    private long mExitTime;

    private ImageView btnBack;

    private List<Fragment> fragments = new ArrayList<Fragment>();

    public static CallBack callBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.win_login);
        btnBack = (ImageView)findViewById(R.id.btnBack);
        fragments.add(new LoginFragment());
        fragments.add(new RegestFragment());
        fragments.add(new FindPasswdFragment());
        fragmentContorler = new FragmentTabHandler(this, fragments, R.id.layoutContent);

        fragmentContorler.showTab(0);

    }

    public void hideBtnBack(boolean b){
        if(b){
            btnBack.setVisibility(View.GONE);
        }else{
            btnBack.setVisibility(View.VISIBLE);
        }
    }

    public void setTitle(String title){
        TextView txtTitle= (TextView) findViewById(R.id.txtTitle);
        txtTitle.setText(title);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fragmentContorler.getCurrentTab()!=0){
                    fragmentContorler.showTab(0);
                }else {

                }
            }
        });

    }

    public static void start(CallBack callBack){
        LoginAct.callBack=callBack;
        Activity curAct= AppController.getInstance().getTopAct();
        Intent it=new Intent(curAct,LoginAct.class);
        ViewUtils.startActivity(it,curAct);
    }

    public void onLoginSuccess(){
        if(callBack!=null)
            callBack.onCall(null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        callBack=null;
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
