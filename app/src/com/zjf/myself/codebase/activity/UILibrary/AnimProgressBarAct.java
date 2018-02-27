package com.zjf.myself.codebase.activity.UILibrary;

import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.activity.BaseAct;
import com.zjf.myself.codebase.util.ViewUtils;
import com.zjf.myself.codebase.view.AnimProgressBar;

/**
 * <pre>
 *     author : ZouJianFeng
 *     e-mail : 1434690833@qq.com
 *     time   : 2017/07/07
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class AnimProgressBarAct extends BaseAct{

    private AnimProgressBar mProgressBar ,mProgressBar1;
    private TextView mTextView,mTextView1;
    private int mProgress;
    private boolean mStop = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_anim_progress_bar);

        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAnimDialog();
                startAnim();
            }
        });


        //非弹框
        mProgressBar1 = (AnimProgressBar) findViewById(R.id.progressbar1);
        mProgressBar1.setMax(100);
        Bitmap bitmap3 = ((BitmapDrawable) getResources().getDrawable(R.mipmap.icon_cur_loc)).getBitmap();
//        Bitmap bitmap1 = ((BitmapDrawable) getResources().getDrawable(com.mao.anim_pb.R.drawable.pb_anim_pic1)).getBitmap();
//        Bitmap bitmap2 = ((BitmapDrawable) getResources().getDrawable(com.mao.anim_pb.R.drawable.pb_anim_pic2)).getBitmap();
        mProgressBar1.setBitmapBuffer1(bitmap3);
//        mProgressBar.setBitmapBuffer2(bitmap2);
        mTextView1 = (TextView)findViewById(R.id.tv_progress1);

        int scale = 50;
        mProgressBar1.setProgress(scale);
        mTextView1.setX((ViewUtils.getScreenWidth(AnimProgressBarAct.this)-40)/100*scale);
        mTextView1.setText(scale+"%");

    }




    private void startAnim() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mProgress < 100) {
                    if (mStop) {
                        break;
                    }
                    SystemClock.sleep(400);
                    mProgress++;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mProgressBar.setProgress(mProgress);
                            mTextView.setText(mProgress+"%");
                        }
                    });
                }
            }
        }).start();
    }

    private void showAnimDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(this)
                .setCancelable(true)
                .create();
        dialog.show();
        mStop = false;
        mProgress = 0;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable());
        dialog.getWindow().setContentView(R.layout.dialog_anim_pb);
        mProgressBar = (AnimProgressBar) dialog.getWindow().findViewById(R.id.progressbar);
        mProgressBar.setMax(100);
        Bitmap bitmap1 = ((BitmapDrawable) getResources().getDrawable(R.mipmap.pb_anim_pic1)).getBitmap();
        Bitmap bitmap2 = ((BitmapDrawable) getResources().getDrawable(R.mipmap.pb_anim_pic2)).getBitmap();
        mProgressBar.setBitmapBuffer1(bitmap1);
        mProgressBar.setBitmapBuffer2(bitmap2);
        mTextView = (TextView) dialog.getWindow().findViewById(R.id.tv_progress);
        dialog.getWindow().findViewById(R.id.tv_download_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                mStop = true;
            }
        });
    }

    @Override
    protected void onDestroy() {
        mStop = true;
        super.onDestroy();
    }
}
