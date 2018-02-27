package com.zjf.myself.codebase.activity.ExerciseLibrary;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.activity.BaseAct;
import com.zjf.myself.codebase.util.AppLog;

/**
 * <pre>
 *     author : ZouJianFeng
 *     e-mail :
 *     time   : 2017/05/20
 *     desc   : 多张png做成动画效果
 *     version: 1.0
 * </pre>
 */
public class PngToGifAct extends BaseAct implements View.OnClickListener{

    private ImageView imgAnim;
    private Button btnStart,btnPause;
    private AnimationDrawable animationDrawable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_png_to_gif);

        imgAnim = (ImageView)findViewById(R.id.imgAnim);
        btnStart = (Button)findViewById(R.id.btnStart);
        btnStart.setOnClickListener(this);
        btnPause = (Button)findViewById(R.id.btnPause);
        btnPause.setOnClickListener(this);


        //获取资源
        animationDrawable = new AnimationDrawable();
        for (int i = 1; i <= 14; i++) {
            int id = getResources().getIdentifier("a" + i, "drawable", getPackageName());
            Drawable drawable = getResources().getDrawable(id);
            animationDrawable.addFrame(drawable, 100);
        }

    }


    @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.btnStart:

                    animationDrawable.setOneShot(true);
                    imgAnim.setImageDrawable(animationDrawable);
                    //在动画start()之前要先stop()，不然在第一次动画之后会停在最后一帧，这样动画就只会触发一次
                    animationDrawable.stop();
                    animationDrawable.start();

                    break;
                case R.id.btnPause:
                    animationDrawable.setOneShot(true);
                    imgAnim.setImageDrawable(animationDrawable);
                    animationDrawable.stop();
                    break;
                default:
                    break;
            }
        }
}
