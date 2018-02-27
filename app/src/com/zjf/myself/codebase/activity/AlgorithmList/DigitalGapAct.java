package com.zjf.myself.codebase.activity.AlgorithmList;

import android.os.Bundle;
import android.widget.TextView;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.activity.BaseAct;

/**
 * <pre>
 *     author : ZouJianFeng
 *     e-mail : 1434690833@qq.com
 *     time   : 2017/06/21
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class DigitalGapAct extends BaseAct{
    private  TextView txt1 ,txt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.act_test);
        setContentView(R.layout.act_digital_gap);

        txt1 = (TextView)findViewById(R.id.txt1);
        txt2 = (TextView)findViewById(R.id.txt2);

       String num1 =  txt1.getText().toString().trim();

       txt2.setText(conversion(num1));
    }


    public String conversion(String str) {
        StringBuilder sb=new StringBuilder(str);
        int length=str.length()/4+str.length();

        for(int i=0;i<length;i++){
            if(i%5==0){
                sb.insert(i,"-");
            }
        }
        sb.deleteCharAt(0);
        return sb.toString();
    }
}
