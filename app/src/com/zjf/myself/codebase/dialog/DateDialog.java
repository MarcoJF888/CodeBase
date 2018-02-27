package com.zjf.myself.codebase.dialog;




import android.app.Activity;

import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.config.PickerConfig;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.data.WheelCalendar;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.activity.BaseAct;
import com.zjf.myself.codebase.application.AppController;
import com.zjf.myself.codebase.util.CallBack;
import com.zjf.myself.codebase.util.TimeUtil;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/27.
 */

public class DateDialog extends TimePickerDialog implements OnDateSetListener {

    PickerConfig mPickerConfig;

    private CallBack callBack;

    public DateDialog(){
        mPickerConfig=new PickerConfig();
        mPickerConfig.mCallBack=this;
        mPickerConfig.mCancelString="取消";
        mPickerConfig.mSureString="确定";
        mPickerConfig.mTitleString="时间";

        mPickerConfig.mYear="年";
        mPickerConfig.mMonth="月";
        mPickerConfig.mDay="日";
        mPickerConfig.mHour="时";
        mPickerConfig.mMinute="分";

        mPickerConfig.cyclic=true;

        long minTime= TimeUtil.getLongTime("1900:01:01","yyyy:MM:dd"); //生日最小是清朝
        mPickerConfig.mMinCalendar= new WheelCalendar(minTime);

        mPickerConfig.mMaxCalendar=new WheelCalendar(System.currentTimeMillis());

        mPickerConfig.mCurrentCalendar=new WheelCalendar(System.currentTimeMillis());

        Activity curAct= AppController.getInstance().getTopAct();
       mPickerConfig.mThemeColor=curAct.getResources().getColor(R.color.green_main);

        mPickerConfig.mType=Type.YEAR_MONTH_DAY;

        mPickerConfig.mWheelTVNormalColor=curAct.getResources().getColor(R.color.grey_444444);
        mPickerConfig.mWheelTVSelectorColor=curAct.getResources().getColor(R.color.green_2F8505);
        mPickerConfig.mWheelTVSize=16;

        initialize(mPickerConfig);

    }


    public void setCurrentSelectDate(long time){
        mPickerConfig.mCurrentCalendar=new WheelCalendar(time);
    }

    public void setCallBack(CallBack callBack){
        this.callBack=callBack;
    }

    public void show(){
        BaseAct activity= (BaseAct) AppController.getInstance().getTopAct();
        show(activity.getSupportFragmentManager(),"hour_minute");
    }

    public String getDateToString(long time) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss");
        Date d = new Date(time);
        return sf.format(d);
    }

    @Override
    public void onDateSet(TimePickerDialog timePickerDialog, long l) {
        if(callBack!=null)
            callBack.onCall(l);
    }




}
