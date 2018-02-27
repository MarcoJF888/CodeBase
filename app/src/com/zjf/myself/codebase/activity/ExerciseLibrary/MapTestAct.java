package com.zjf.myself.codebase.activity.ExerciseLibrary;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.activity.BaseAct;
import com.zjf.myself.codebase.util.AppLog;
import com.zjf.myself.codebase.util.ViewUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 *     author : ZouJianFeng
 *     e-mail :
 *     time   : 2017/04/24
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class MapTestAct extends BaseAct implements View.OnClickListener{
    private Button btnAdd,btnAddAgain,btnDel,btnPrint;
    private TextView txtResult;
    private List<Map<String,Integer>> calibrationDatas=new ArrayList<>();
    private Map<String,Integer> calibrationData=new HashMap<String, Integer>();
    private Map<String,Integer> calibrationData1=new HashMap<String, Integer>();
     @Override
         protected void onCreate(Bundle savedInstanceState) {
             super.onCreate(savedInstanceState);
             setContentView(R.layout.act_map_test);

             txtResult = (TextView) findViewById(R.id.txtResult);

             btnAdd = (Button) findViewById(R.id.btnAdd);
             btnAdd.setOnClickListener(this);

             btnAddAgain = (Button) findViewById(R.id.btnAddAgain);
             btnAddAgain.setOnClickListener(this);

             btnDel = (Button) findViewById(R.id.btnDel);
             btnDel.setOnClickListener(this);

             btnPrint = (Button) findViewById(R.id.btnPrint);
             btnPrint.setOnClickListener(this);

      }

      @Override
          public void onClick(View v){
              switch (v.getId()){
                  case R.id.btnAdd:

                      calibrationData.put("A1",Integer.valueOf("111"));
                      calibrationData.put("A2",Integer.valueOf("222"));
                      calibrationData.put("A3",Integer.valueOf("333"));

                      calibrationDatas.add(calibrationData);
                      AppLog.d("calibrationData.toString()==="+calibrationData.toString());
                      AppLog.d("calibrationDatas.toString()==="+calibrationDatas.toString());

                      break;

                  case R.id.btnAddAgain:
                      //再次添加到list相同的键对之前的没影响，看你获取哪个map里面的值,向同一个map添加相同键之前的被覆盖

                      calibrationData.put("A1",Integer.valueOf("444"));

                      calibrationData1.put("A1",Integer.valueOf("555"));
                      calibrationData1.put("B1",Integer.valueOf("666"));
                      calibrationData1.put("B2",Integer.valueOf("777"));
                      calibrationData1.put("B3",Integer.valueOf("888"));

                      calibrationDatas.add(calibrationData1);
                      AppLog.d("calibrationData.toString()==="+calibrationData1.toString());
                      AppLog.d("calibrationDatas.toString()==="+calibrationDatas.toString());

                      break;

                  case R.id.btnDel:
                      if ( calibrationDatas.size()==0){
                          ViewUtils.showToast("别他妈瞎按了，集合是空的，删个毛啊");
                          return;
                      }
                      calibrationDatas.remove(0);
                      AppLog.d("calibrationDatas.toString()==="+calibrationDatas.toString());
                      break;

                  case R.id.btnPrint:
//                      list是空的不能打印，会崩溃
                      if ( calibrationDatas.size()==0){
                          ViewUtils.showToast("别他妈瞎按了，集合是空的，打印个毛啊");
                          return;
                      }
//                      Map是空的打印A1是null
                      Map<String,Integer> preCalibrationData=calibrationDatas.get(0);
                      txtResult.setText("A1="+preCalibrationData.get("A1") + "\nMAP to String="+calibrationDatas.toString());
                      break;

             default:
              }
          }

}
