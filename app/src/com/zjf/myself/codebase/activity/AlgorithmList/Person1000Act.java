package com.zjf.myself.codebase.activity.AlgorithmList;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.activity.BaseAct;
import com.zjf.myself.codebase.util.AppLog;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     author : ZouJianFeng
 *     e-mail :
 *     time   : 2017/05/16
 *     desc   :
 *     version: 1.0
 * </pre>
 */
//如果你是排着队围成一个圆圈的1000名士兵中的一个，每两个杀掉一个（将所有人两两分组，然后杀掉每组第一个）直到剩下最后一个，那么你必须在哪个位置才可以活下来
public class Person1000Act extends BaseAct implements View.OnClickListener{
    private Button btnStart;
    private TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState){
         super.onCreate(savedInstanceState);
         setContentView(R.layout.act_person_1000);

         btnStart = (Button)findViewById(R.id.btnStart);
         btnStart.setOnClickListener(this);

         txtResult = (TextView)findViewById(R.id.txtResult);

  }


    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btnStart:
                algorithmStart();
                break;
            default:
                break;
        }
    }


      private void algorithmStart(){
          List<Integer> list = new ArrayList<Integer>();
          for(int j = 0;j < 1000;j++){
              list.add(j+1);
          }
          //历遍1-1000
//          for(int i = 0 ; i < list.size() ; i++) {
//              AppLog.d(list.get(i)+"");
//          }

          //此算法最后单出来的直接杀掉 ==512
//          while(true){
//              AppLog.d("间隔间隔间隔间隔间隔间隔间隔间隔间隔间隔间隔间隔间隔");
//            if(list.size() == 1){
//                break;
//            }
//              for (int i = 0; i<list.size();i=i+1){
//                  list.remove(i);
//              }
//            //将每次杀了之后的打印出来
//              for(int i = 0 ; i < list.size() ; i++) {
//              AppLog.d(list.get(i)+"aaaaaa");
//              }
//          }


          //此算法最后单出来的直接算他走运不杀 ==1000
//          while(true){
//              AppLog.d("间隔间隔间隔间隔间隔间隔间隔间隔间隔间隔间隔间隔间隔");
//              if(list.size() == 1){
//                  break;
//              }
//              if(list.size()%2==0){
//                  for (int i = 0; i<list.size();i=i+1){
//                      list.remove(i);
//                  }
//              }else{
//                  for (int i = 0; i<list.size()-1;i=i+1){
//                      list.remove(i);
//                  }
//              }
//
//              //将每次杀了之后的打印出来
//              for(int i = 0 ; i < list.size() ; i++) {
//                  AppLog.d(list.get(i)+"aaaaaa");
//              }
//          }


          //此算法最后单出来继续和活下来第一个的组队 ==976
          while(true){
              AppLog.d("间隔间隔间隔间隔间隔间隔间隔间隔间隔间隔间隔间隔间隔");
              if(list.size() == 1){
                  break;
              }
              if(list.size()%2==0){
                  for (int i = 0; i<list.size();i=i+1){
                      list.remove(i);
                  }
              }else{
                  for (int i = 0; i<list.size()-1;i=i+1){
                      list.remove(i);
                  }
                  int last = list.get(list.size()-1);
                  list.remove(list.size()-1);
                  list.add(0,last);
              }

              //将每次杀了之后的打印出来
              for(int i = 0 ; i < list.size() ; i++) {
                  AppLog.d(list.get(i)+"aaaaaa");
              }
          }





          //结果
          AppLog.d("结果="+list.get(0));
          txtResult.setText(list.get(0)+"");
      }


    //删除数组中的0
    public static int[] removeZero(int[] a) {
        int j = 0;
        // 这个for循环计算出你传入的这个数组去掉0后的长度
        for (int i = 0; i < a.length; i++) {
            if (a[i] != 0) {
                j++;
            }
        }
        // 定义数组的长度
        int[] newarr = new int[j];
        j = 0;
        // 将不为零的copy到新数组中去
        for (int i = 0; i < a.length; i++) {
            if (a[i] != 0) {
                newarr[j] = a[i];
                j++;
            }
        }
        // 循环打印
        for (int i = 0; i < newarr.length; i++) {
            AppLog.d(newarr[i]+"");
        }
        return newarr;
    }

}

