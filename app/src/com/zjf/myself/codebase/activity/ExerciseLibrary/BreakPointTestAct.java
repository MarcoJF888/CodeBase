package com.zjf.myself.codebase.activity.ExerciseLibrary;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.activity.BaseAct;
import com.zjf.myself.codebase.util.AppLog;

/**
 * <pre>
 *     author : ZouJianFeng
 *     e-mail :
 *     time   : 2017/04/13
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class BreakPointTestAct extends BaseAct implements View.OnClickListener{
    private Button btnStartBreakPoint;
     @Override
         protected void onCreate(Bundle savedInstanceState) {
             super.onCreate(savedInstanceState);
             setContentView(R.layout.act_breakpoint_test);

         btnStartBreakPoint = (Button) findViewById(R.id.btnStartBreakPoint);
         btnStartBreakPoint.setOnClickListener(this);

      }

    @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.btnStartBreakPoint:
                    for(int i = 0;i < 10 ; i++){
                        int curNum = i;
                        AppLog.d("i = "+curNum);
                        int Num = i+1;
                    }
                    break;
                default:
                    break;
            }
        }

}
