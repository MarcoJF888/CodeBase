package com.zjf.myself.codebase.dialog;


import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.application.AppController;
import com.zjf.myself.codebase.util.CallBack;


public class BlueLoadingDialog {

    private Dialog dialog;
    private Context context;
    protected Animation progressAnima;
    private View contentView;
    private ImageView imgProgress;

    public BlueLoadingDialog(Context context) {
        //		context = AppController.getInstance().getTopAct();
        this.context = context;
        dialog = new Dialog(context, R.style.dialog);
        dialog.setCancelable(true);
        this.dialog.getWindow().setGravity(Gravity.CENTER);
        progressAnima = AnimationUtils.loadAnimation(context,R.anim.loading_progressbar_anim);
        LinearInterpolator lin = new LinearInterpolator();
        progressAnima.setInterpolator(lin);
        contentView = LayoutInflater.from(context).inflate(R.layout.blue_dialog_loading, null);
        imgProgress = (ImageView) contentView.findViewById(R.id.img_progress);
    }

    public BlueLoadingDialog() {
        context = AppController.getInstance().getTopAct();
        dialog = new Dialog(context, R.style.dialog);
        dialog.setCancelable(true);
        this.dialog.getWindow().setGravity(Gravity.CENTER);
        progressAnima = AnimationUtils.loadAnimation(context,
                R.anim.loading_progressbar_anim);
        LinearInterpolator lin = new LinearInterpolator();
        progressAnima.setInterpolator(lin);
        contentView = LayoutInflater.from(context).inflate(R.layout.blue_dialog_loading, null);
        imgProgress = (ImageView) contentView.findViewById(R.id.img_progress);
    }


    public void show() {
        if (dialog.isShowing()) {
            return;
        }
        dialog.getWindow().setContentView(contentView);
        dialog.show();


    }
    public void show(CallBack callBack) {
        if (dialog.isShowing()) {
            return;
        }
        dialog.getWindow().setContentView(contentView);
        dialog.show();


        callBack.onCall(null);

    }

    public void dismiss() {
        this.dialog.cancel();
    }
}
