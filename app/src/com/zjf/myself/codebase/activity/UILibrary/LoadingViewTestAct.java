package com.zjf.myself.codebase.activity.UILibrary;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.activity.BaseAct;
import com.zjf.myself.codebase.dialog.BlueLoadingDialog;
import com.zjf.myself.codebase.dialog.LoadingDialog;
import com.zjf.myself.codebase.util.ViewUtils;
import com.zjf.myself.codebase.thirdparty.GeometricLoadingView.GeometricLoadingView;
import com.zjf.myself.codebase.thirdparty.GeometricLoadingView.ShapeLoadingDialog;
import com.zjf.myself.codebase.view.LoadButton;
import com.zjf.myself.codebase.view.LoadingView;
import com.zjf.myself.codebase.thirdparty.dotsloadingview.DotsTextView;

/**
 * Created by Administrator on 2017/3/13.
 */

public class LoadingViewTestAct extends BaseAct implements View.OnClickListener{
    private Button btnLoadingView,btnLoadingDialog,btnLoadingDialog1,button3,button4,button5;
    private LoadingView loadingView;
    public LoadingDialog dialog;
    public BlueLoadingDialog dialog1;

    private TextView btnGetVerificationCode;
    CountDownTimer countDownTimer;

    DotsTextView dotsTextView;
    Button buttonPlay,buttonHideAndStop,buttonHide;

    Button btn1,btn2;

    GeometricLoadingView loadingview;
    boolean flag = false;
    ShapeLoadingDialog shapeLoadingDialog;
    LoadButton mLoadButton;

    @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.win_loadingview);

         loadingView = (LoadingView)findViewById(R.id.loadingView);

         btnLoadingView = (Button)findViewById(R.id.btnLoadingView);
         btnLoadingView.setOnClickListener(this);
         btnLoadingDialog = (Button)findViewById(R.id.btnLoadingDialog);
         btnLoadingDialog.setOnClickListener(this);
         btnLoadingDialog1 = (Button)findViewById(R.id.btnLoadingDialog1);
         btnLoadingDialog1.setOnClickListener(this);


        //获取验证码
         btnGetVerificationCode = (TextView)findViewById(R.id.btnGetVerificationCode);
         btnGetVerificationCode.setOnClickListener(this);

        //点波动
        dotsTextView = (DotsTextView) findViewById(R.id.dots);
        buttonPlay = (Button) findViewById(R.id.buttonPlay);
        buttonPlay.setOnClickListener(this);
        buttonHide = (Button) findViewById(R.id.buttonHide);
        buttonHide.setOnClickListener(this);
        buttonHideAndStop = (Button) findViewById(R.id.buttonHideAndStop);
        buttonHideAndStop.setOnClickListener(this);

        //高仿几何形状弹跳
        loadingview = (GeometricLoadingView) findViewById(R.id.loadView);
        btn1 = (Button)findViewById(R.id.button1);
        btn1.setOnClickListener(this);
        btn2 = (Button)findViewById(R.id.button2);
        btn2.setOnClickListener(this);

        //LoadButton
        mLoadButton = (LoadButton) findViewById(R.id.btn_status);
        mLoadButton.setListenner(new LoadButton.LoadListenner() {
            @Override
            public void onClick(boolean isSuccessed) {
                if ( isSuccessed ) {
                    Toast.makeText(LoadingViewTestAct.this,"加载成功",Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(LoadingViewTestAct.this,"加载失败",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void needLoading() {
                Toast.makeText(LoadingViewTestAct.this,"重新下载",Toast.LENGTH_LONG).show();
            }
        });

        button3 = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(this);
        button4 = (Button)findViewById(R.id.button4);
        button4.setOnClickListener(this);
        button5 = (Button)findViewById(R.id.button5);
        button5.setOnClickListener(this);


    }


    private static final  int LOADING_VIEW = 0;
    private static final  int LOADING_DIALOG = 1;
    private static final  int LOADING_DIALOG1 = 2;

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btnLoadingView:
                ViewUtils.showToast("点击了LoadingView");
                loadingView.startLoading("正在加载请稍后");

                newThread(LOADING_VIEW);

                break;

            case R.id.btnLoadingDialog:
                ViewUtils.showToast("点击了LoadingDialog");
                showLoadingDialog();
                newThread(LOADING_DIALOG);

                break;

            case R.id.btnLoadingDialog1:
                ViewUtils.showToast("点击了LoadingDialog1");
                showBlueLoadingDialog();
                newThread(LOADING_DIALOG1);
                break;

            case R.id.btnGetVerificationCode:
                getVerificationCode();
                break;



            case R.id.buttonPlay:
                if(dotsTextView.isPlaying()) {
                    dotsTextView.stop();
                } else {
                    dotsTextView.start();
                }
                break;


            case R.id.buttonHide:
                if(dotsTextView.isHide()) {
                    dotsTextView.show();
                } else {
                    dotsTextView.hide();
                }
                break;

            case R.id.buttonHideAndStop:
                if(dotsTextView.isHide()) {
                    dotsTextView.showAndPlay();
                } else {
                    dotsTextView.hideAndStop();
                }
                break;

            case R.id.button1:
                flag = !flag;
                loadingview.setVisibility(flag?View.VISIBLE:View.INVISIBLE);
                break;

            case R.id.button2:
                shapeLoadingDialog=new ShapeLoadingDialog(this);
                shapeLoadingDialog.setLoadingText("加载中...");
                shapeLoadingDialog.show();
                break;

            case R.id.button3:
                mLoadButton.loadSuccessed();
                break;

            case R.id.button4:
                mLoadButton.loadFailed();
                break;
            case R.id.button5:
                mLoadButton.reset();
                break;

            default:
                break;
        }
    }



    private void newThread(final int num){
        new Thread(new Runnable(){
            public void run(){
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message msg = new Message();
                msg.arg1 = num;
                handler.sendMessage(msg);
            }
        }).start();
    }

    //停止事件
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch(msg.arg1) {
                case LOADING_VIEW:
                    loadingView.onLoadingComplete();
                    break;
                case LOADING_DIALOG:
                    if (isShowLoadingDialog()) {
                        dialog.dismiss();
                    }
                case LOADING_DIALOG1:
                    if (isShowLoadingDialog()) {
                        dialog1.dismiss();
                    }
                    break;
            }

        }
    };


    protected boolean isShowLoadingDialog() {
        return true;
    }

    public void showLoadingDialog(){
        if (isShowLoadingDialog()) {
            dialog = new LoadingDialog();
            dialog.show();
        }
    }

    public void showBlueLoadingDialog(){
        if (isShowLoadingDialog()) {
            dialog1 = new BlueLoadingDialog();
            dialog1.show();
        }
    }

    private void setGetVerificationCodeEnable(String msg, boolean enable){
        if(enable){
            if(countDownTimer!=null)
                countDownTimer.cancel();

            btnGetVerificationCode.setBackgroundResource(R.drawable.icon_button);
        }else {
            btnGetVerificationCode.setBackgroundResource(R.drawable.button_dis);
        }

        //  btnGetVerificationCode.setPadding(DesityUtil.getDpValue(10),0,DesityUtil.getDpValue(10),0);
        btnGetVerificationCode.setEnabled(enable);
        btnGetVerificationCode.setText(msg);
    }

    private void getVerificationCode(){
        setGetVerificationCodeEnable("获取验证码",false);

        if(countDownTimer==null){
            countDownTimer=new CountDownTimer(10*1000,1000) {
                @Override
                public void onTick(long l) {
                    btnGetVerificationCode.setText(l / 1000 + "秒后可重新获取");
                }

                @Override
                public void onFinish() {
                    setGetVerificationCodeEnable("重新获取",true);
                }
            };
        }

        countDownTimer.start();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(countDownTimer!=null)
            countDownTimer.cancel();
    }
}
