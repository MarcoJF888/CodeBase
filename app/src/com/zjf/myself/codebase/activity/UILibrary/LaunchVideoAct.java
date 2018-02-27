package com.zjf.myself.codebase.activity.UILibrary;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.activity.BaseAct;
import com.zjf.myself.codebase.util.AppLog;
import com.zjf.myself.codebase.util.ViewUtils;
import com.zjf.myself.codebase.view.CustomVideoView;

/**
 * <pre>
 *     author : ZouJianFeng
 *     e-mail : 1434690833@qq.com
 *     time   : 2017/06/29
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class LaunchVideoAct extends BaseAct implements View.OnClickListener{
    private Button btnLogin,btnRegest;
    private CustomVideoView videoviewWelcome;
    private RelativeLayout layoutOne,layoutTwo,layoutMove;
    private int per;
    private View layoutBg;
    private RelativeLayout layoutLogo;
    private TextView txtLogo,btnLogin2,btnForgetPassWd,btnRegest1,btnTips;
    private ImageView imgLogo;
    private EditText eTxtAccount,eTxtPassWd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_launch_video);

        videoviewWelcome = (CustomVideoView) this.findViewById(R.id.videoviewWelcome);
        videoviewWelcome.setVideoURI(Uri.parse("android.resource://"+this.getPackageName()+"/"+R.raw.mqr));
        videoviewWelcome.start();
        videoviewWelcome.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                videoviewWelcome.start();

            }
        });

        btnLogin=(Button)this.findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        btnRegest=(Button)this.findViewById(R.id.btnRegest);
        btnRegest.setOnClickListener(this);

        btnLogin2=(TextView) this.findViewById(R.id.btnLogin2);
        btnLogin2.setOnClickListener(this);
        btnRegest1=(TextView)this.findViewById(R.id.btnRegest1);
        btnRegest1.setOnClickListener(this);
        btnForgetPassWd=(TextView)this.findViewById(R.id.btnForgetPassWd);
        btnForgetPassWd.setOnClickListener(this);
        btnTips=(TextView) this.findViewById(R.id.btnTips);
        btnTips.setOnClickListener(this);


        //隐藏显示的
        layoutOne = (RelativeLayout) findViewById(R.id.layoutOne);
        layoutTwo = (RelativeLayout) findViewById(R.id.layoutTwo);
        layoutBg = (View) findViewById(R.id.layoutBg);

        //要移动的
        layoutMove = (RelativeLayout) findViewById(R.id.layoutMove);
        layoutLogo = (RelativeLayout) findViewById(R.id.layoutLogo);

        txtLogo = (TextView) findViewById(R.id.txtLogo);
        imgLogo = (ImageView) findViewById(R.id.imgLogo);

        eTxtAccount = (EditText) findViewById(R.id.eTxtAccount);
        eTxtPassWd = (EditText) findViewById(R.id.eTxtPassWd);


    }


    private void moveLayoutTwo(){
        //输入框动画
        //用Tween动画做出位移
        Animation translateAnimation = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_SELF,0,
                TranslateAnimation.RELATIVE_TO_PARENT,0,
                TranslateAnimation.RELATIVE_TO_SELF,0
                ,TranslateAnimation.RELATIVE_TO_PARENT,-0.36f);
        //这两句控制会不会复位
//        translateAnimation.setFillEnabled(true);
//        translateAnimation.setFillAfter(true);

        translateAnimation.setDuration(600);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                updateParams();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        layoutMove.startAnimation(translateAnimation);




        //logo动画
        Animation translateAnimation1 = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_SELF,0,
                TranslateAnimation.RELATIVE_TO_PARENT,-0.33f,
                TranslateAnimation.RELATIVE_TO_SELF,0
                ,TranslateAnimation.RELATIVE_TO_PARENT,-0.05f);
        //这两句控制会不会复位
        translateAnimation1.setFillEnabled(true);
        translateAnimation1.setFillAfter(true);

        translateAnimation1.setDuration(600);
        translateAnimation1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                txtLogo.setTextSize(TypedValue.COMPLEX_UNIT_SP,28);
                imgLogo.setBackgroundResource(R.mipmap.icon_qq_white_small);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        layoutLogo.startAnimation(translateAnimation1);
    }


    private void updateParams(){
        int top = layoutMove.getHeight()/19*20+8;
//        AppLog.e( layoutMove.getHeight()+"AAAAA");
//        AppLog.e( top+"BBBBB");
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) layoutMove.getLayoutParams();
        params.bottomMargin = top;
        layoutMove.setLayoutParams(params);
        //解决移动后闪烁现象
        layoutMove.clearAnimation();
        TranslateAnimation anim = new TranslateAnimation(0,0,0,0);
        layoutMove.setAnimation(anim);
    }



    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btnLogin:

//                if(videoviewWelcome.isPlaying()){
//                    videoviewWelcome.stopPlayback();
//                    videoviewWelcome=null;
//                }
//                ViewUtils.showToast("点击按钮，进入登录"+getAppVersionName(this));
//                finish();
                layoutOne.setVisibility(View.GONE);
                layoutBg.setVisibility(View.VISIBLE);
                layoutTwo.setVisibility(View.VISIBLE);
                moveLayoutTwo();

                break;

            case R.id.btnRegest:

//                if(videoviewWelcome.isPlaying()){
//                    videoviewWelcome.stopPlayback();
//                    videoviewWelcome=null;
//                }
                ViewUtils.showToast("开始注册");

                break;


            case R.id.btnLogin2:
                ViewUtils.showToast("登录");
                break;

            case R.id.btnForgetPassWd:
                ViewUtils.showToast("忘记密码");
                break;

            case R.id.btnRegest1:
                ViewUtils.showToast("注册");
                break;

            case R.id.btnTips:
                ViewUtils.showToast("服务条款");
                break;
            default:
                break;
        }
    }



    private String getAppVersionName(Context context) {
        String versionName = "";
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(this.getPackageName(), 0);
            versionName = packageInfo.versionName;
            if (TextUtils.isEmpty(versionName)) {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionName;
    }


    @Override
    public void onPause() {
        super.onPause();
        videoviewWelcome.pause();
        per = videoviewWelcome.getCurrentPosition();
    }

    @Override
    protected void onResume() {
        super.onResume();
        videoviewWelcome.seekTo(per);
        videoviewWelcome.start();
    }

}
