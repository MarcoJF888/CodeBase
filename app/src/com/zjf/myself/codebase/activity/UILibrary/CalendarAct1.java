package com.zjf.myself.codebase.activity.UILibrary;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.activity.BaseAct;
import com.zjf.myself.codebase.thirdparty.calendar.CalendarView;
import com.zjf.myself.codebase.thirdparty.calendar.CalendarWindow;
import com.zjf.myself.codebase.util.TimeUtil;
import com.zjf.myself.codebase.util.ViewUtils;

/**
 * Created by Administrator on 2017/3/23.
 */

public class CalendarAct1 extends BaseAct{
    private Button btnStart;
    private CalendarWindow calendarWindow;
     @Override
         protected void onCreate(Bundle savedInstanceState) {
             super.onCreate(savedInstanceState);
             setContentView(R.layout.win_calendar1);

             calendarWindow= (CalendarWindow) findViewById(R.id.calendarWindow);
             calendarWindow.setOnTimeSelectListener(new CalendarView.OnTimeSelectListener() {
                 @Override
                 public void onTimeSelect(long time) {
                    String aa = (TimeUtil.formatTime(TimeUtil.FORMAT_11,time));
                     ViewUtils.showToast("选择了"+aa);

                 }
             });

             btnStart = (Button)findViewById(R.id.btnStart);
             btnStart.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     calendarWindow.show();
                 }
             });
      }
}
