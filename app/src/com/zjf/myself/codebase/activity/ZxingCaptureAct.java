package com.zjf.myself.codebase.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.zxing.Result;
import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.application.AppController;
import com.zjf.myself.codebase.thirdparty.zxing.ScanListener;
import com.zjf.myself.codebase.thirdparty.zxing.ScanManager;
import com.zjf.myself.codebase.thirdparty.zxing.decode.DecodeThread;
import com.zjf.myself.codebase.util.AppLog;
import com.zjf.myself.codebase.util.CallBack;
import com.zjf.myself.codebase.util.StringUtil;
import com.zjf.myself.codebase.util.ViewUtils;


public final class ZxingCaptureAct extends BaseAct implements View.OnClickListener,ScanListener {

//    private BindDevHelper bindDevHelper;
    public ScanManager scanManager;
    SurfaceView scanPreview = null;
    private RelativeLayout scanContainer;
    private RelativeLayout scanCropView;
    ImageView scanLine;

    public static CallBack callBack;
    public static final int SCAN_SUCCESS=0;
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.win_zxing_capture);

        findViewById(R.id.btnBack).setOnClickListener(this);
        findViewById(R.id.txtRight).setOnClickListener(this);

        scanPreview= (SurfaceView) findViewById(R.id.capture_preview);
        scanContainer = (RelativeLayout) findViewById(R.id.capture_container);
        scanCropView = (RelativeLayout) findViewById(R.id.capture_crop_view);
        scanLine= (ImageView) findViewById(R.id.capture_scan_line);

        scanManager = new ScanManager(this, scanPreview, scanContainer, scanCropView, scanLine, DecodeThread.ALL_MODE,this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        scanManager.onResume();
    }



    @Override
    public void onPause() {
        super.onPause();
        scanManager.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        scanManager.onDestroy();
        callBack=null;
    }

    private String getEimei(String qrcode){
        String eimei="";
        if(StringUtil.isNull(qrcode))
            return eimei;

        String tag="&c=";
        int tagIndex=qrcode.indexOf(tag);
        if(tagIndex==-1)
            return eimei;

        eimei=qrcode.substring(tagIndex+3,qrcode.length());

        return eimei;
    }

    @Override
    public void scanResult(Result rawResult, Bundle bundle) {
        AppLog.d(rawResult.getText()+"AAAAAAAAA");

        Intent intent=new Intent();
        intent.putExtra("result",rawResult.getText());
        setResult(SCAN_SUCCESS,intent);
        ZxingCaptureAct.this.finish();
//        if(bindDevHelper==null)
//            bindDevHelper = new BindDevHelper();
//
//        if (bindDevHelper.isAppQrCode(rawResult.getText())) {
//            String qrData = bindDevHelper.getBindDataByAppQrCode(rawResult.getText());
//            bindDevHelper.bindByAppQRCode(qrData);
//        } else {
//            //result不是JSON 就认为是包装盒子,暂时这么判断
//            String eimei=getEimei(rawResult.getText());
//
//            if(StringUtil.isNull(eimei)){
//                ViewUtils.showToast("无效的包装盒二维码!");
//                scanManager.reScan(1500);
//                return;
//            }
//
//            bindDevHelper.bindByBox(eimei);
//        }
    }

    @Override
    public void scanError(Exception e) {
        ViewUtils.showToast(e.getMessage());
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnBack:
                finish();
                break;

            case R.id.txtRight:
//                Intent intent = new Intent(ZxingCaptureAct.this,BindDevManualAct.class);
//                ViewUtils.startActivity(intent,ZxingCaptureAct.this);
//                ZxingCaptureAct.this.finish();
                break;
        }

    }

    public static void start(CallBack callBack){
        ZxingCaptureAct.callBack=callBack;
        Activity curAct= AppController.getInstance().getTopAct();
        ViewUtils.startActivity(new Intent(curAct,ZxingCaptureAct.class),curAct);
    }

}
