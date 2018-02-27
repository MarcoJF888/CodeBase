package com.zjf.myself.codebase.activity.AlgorithmList;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.activity.BaseAct;
import com.zjf.myself.codebase.util.AppException;
import com.zjf.myself.codebase.util.AppLog;
import com.zjf.myself.codebase.util.DataUtil;
import com.zjf.myself.codebase.util.GsonParser;
import com.zjf.myself.codebase.util.JsonUtil;
import com.zjf.myself.codebase.util.StringUtil;
import com.zjf.myself.codebase.util.TimeUtil;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * <pre>
 *     author : ZouJianFeng
 *     e-mail : 1434690833@qq.com
 *     time   : 2018/01/20
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class WeekStepSumAct extends BaseAct {


    private Button btnWeekStep;

    private TextView txtResult;

    private List<SportInfo> sportInfosList = new ArrayList<SportInfo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_week_step_sum);

        initView();
        initData();
    }

    private void initView() {
        txtResult = (TextView)findViewById(R.id.txtResult);
        btnWeekStep = (Button)findViewById(R.id.btnWeekStep);
        btnWeekStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stepSum(sportInfosList);
//                stepSevenDaySum(sportInfosList);
            }
        });

    }

    private void initData() {
        sportInfosList = getStepInfoList();
    }


    /**
     * @param list
     * 简单粗暴，往前拉，拉到周日就不要了
     */
    private void stepSum(List<SportInfo> list) {

        //算出当天是周几，然后统计本周的总数
//        Calendar c = Calendar.getInstance();
////        Format f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");
//        Format f = new SimpleDateFormat("E");
//        AppLog.d("zjf"+f.format(c.getTime())+"c.getTime()=" +c.getTime());
//
//       String currentDate= TimeUtil.formatTime(TimeUtil.FORMAT_2,System.currentTimeMillis());



        AppLog.d("zjf list.toString = "+list.toString());

        long stepNum = 0;

        if (!DataUtil.listIsNull(list)){
            for(int i = 0; i <list.size(); i++){
                if (TimeUtil.isSunday(list.get(i).getDate())){
                    break;
                }
                stepNum = stepNum + list.get(i).getTodayStepNum();
                AppLog.d("zjf step = " +stepNum );
            }
        }
        txtResult.setText(stepNum +"步");
    }





    /**
     * @param list
     * 直接算出最近七天的总和
     */
    private void stepSevenDaySum(List<SportInfo> list) {
        AppLog.d("zjf list.toString = "+list.toString());
        long stepNum = 0;
        int WEEK_DAY = 7;
        if (!DataUtil.listIsNull(list)){
            for(int i = 0; i <(WEEK_DAY <=list.size()? WEEK_DAY :list.size()); i++){
                stepNum = stepNum + list.get(i).getTodayStepNum();
                AppLog.d("zjf step = " +stepNum );
            }
        }

        txtResult.setText(stepNum +"步");
    }


    public List<SportInfo> getStepInfoList(){
        String json = JsonUtil.getJson(this,"step.json");
        try {
            if(!StringUtil.isNull(json)){
                TypeToken<List<SportInfo>> token = new TypeToken<List<SportInfo>>() {};
                List<SportInfo> list = GsonParser.getInstance().fromJson(json,token);
                return list;
            }
        } catch (AppException e) {
            e.printStackTrace();
        }
        return null;
    }



    public class SportInfo {
        private String Date;
        private long TodayStepNum;

        public SportInfo() {
        }

        public SportInfo(String date, long todayStepNum) {
            Date = date;
            TodayStepNum = todayStepNum;
        }

        public String getDate() {
            return Date;
        }

        public void setDate(String date) {
            Date = date;
        }

        public long getTodayStepNum() {
            return TodayStepNum;
        }

        public void setTodayStepNum(long todayStepNum) {
            TodayStepNum = todayStepNum;
        }

        @Override
        public String toString() {
            return "SportInfo{" +
                    "Date='" + Date + '\'' +
                    ", TodayStepNum=" + TodayStepNum +
                    '}';
        }
    }
}

