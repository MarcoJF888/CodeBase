package com.zjf.myself.codebase.activity.UILibrary;

import android.app.Activity;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.zjf.myself.codebase.R;

/**
 * Created by Administrator on 2017/1/13.
 */

public class FunctionHeartRateMonitorAct extends Activity {
    private LinearLayout btnStartMonitor;
    private ImageView imgRotate;

    private boolean click  = true;

     @Override
         protected void onCreate(Bundle savedInstanceState) {
             super.onCreate(savedInstanceState);
             setContentView(R.layout.win_progress_test);


         btnStartMonitor = (LinearLayout) findViewById(R.id.btnStartMonitor);
         btnStartMonitor.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 rotateStart();
             }
         });
      }

    protected void rotateStart(){
        if(click == true){
            imgRotate = (ImageView) findViewById(R.id.imgRotate);
            imgRotate.setVisibility(View.VISIBLE);
            Animation operatingAnim = AnimationUtils.loadAnimation(this, R.anim.tip);
            LinearInterpolator lin = new LinearInterpolator();
            operatingAnim.setInterpolator(lin);
            if (operatingAnim != null) {
                imgRotate.startAnimation(operatingAnim);

            }
            click=false;
        }
        else{
            Toast.makeText(FunctionHeartRateMonitorAct.this,"正在检测，请稍候",Toast.LENGTH_SHORT).show();
        }
    }
}
//停止动画方法
// imgRotate.clearAnimation();