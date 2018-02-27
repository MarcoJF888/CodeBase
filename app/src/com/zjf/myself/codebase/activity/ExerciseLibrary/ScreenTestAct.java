package com.zjf.myself.codebase.activity.ExerciseLibrary;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.activity.BaseAct;
import com.zjf.myself.codebase.util.ViewUtils;

/**
 * <pre>
 *     author : ZouJianFeng
 *     e-mail :
 *     time   : 2017/05/11
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class ScreenTestAct extends BaseAct implements View.OnClickListener{
    private View txtTest;
    private Button btnTest;

     @Override
         protected void onCreate(Bundle savedInstanceState){
             super.onCreate(savedInstanceState);
             setContentView(R.layout.act_screen_test);
             txtTest  = (View) findViewById(R.id.txtTest);
            //设置控件的宽起点
             txtTest.setX(ViewUtils.getScreenWidth(ScreenTestAct.this)/2);
//             AppLog.e(""+(txtTest.getWidth()/2));
             ViewUtils.showToast("高"+ViewUtils.getScreenHeight(ScreenTestAct.this)+"宽"+ViewUtils.getScreenWidth(ScreenTestAct.this));

             LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(350,350);
             txtTest.setLayoutParams(params);

             btnTest = (Button)findViewById(R.id.btnTest);
             btnTest.setOnClickListener(this);

      }

      @Override
          public void onClick(View v){
              switch (v.getId()){
                  case R.id.btnTest:
                      //设置X为屏幕宽度的一半减去控件宽度的一为左右居中
//                      txtTest.setX(ViewUtils.getScreenWidth(ScreenTestAct.this)/2  - txtTest.getWidth()/2 );

                      //动态设置View的位置
                      txtTest.setY(ViewUtils.getScreenHeight(ScreenTestAct.this)/2 - txtTest.getWidth()/2);
                      txtTest.setX(ViewUtils.getScreenWidth(ScreenTestAct.this)/2 - txtTest.getWidth()/2);
                      //动态设置View的高宽
//                      LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);


                      break;
                  default:
                      break;
              }
          }
}
