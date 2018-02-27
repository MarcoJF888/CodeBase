package com.zjf.myself.codebase.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.thirdparty.signcalendar.DBManager;
import com.zjf.myself.codebase.thirdparty.signcalendar.SignCalendar;
import com.zjf.myself.codebase.thirdparty.signcalendar.sqlit;
import com.zjf.myself.codebase.thirdparty.recyclerview.adapter.MultipleSelectAdapter;
import com.zjf.myself.codebase.thirdparty.recyclerview.adapter.ViewHolder;
import com.zjf.myself.codebase.util.DataUtil;
import com.zjf.myself.codebase.util.ViewUtils;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ListTaskAct extends BaseAct implements View.OnClickListener{

    RecyclerView recyclerView;
    private List<TaskInfo> selectList=new ArrayList<TaskInfo>();
    private List<TaskInfo> cycleList;
    TaskCaleAdapter taskCaleAdapter;
    RecyclerView taskCaleRecyclerView;
    private String date = null;// 设置默认选中的日期  格式为 “2014-04-05” 标准DATE格式
    private TextView popupwindow_calendar_month;
    private SignCalendar calendar;
    private Button btn_signIn;
    private List<String> list = new ArrayList<String>(); //设置标记列表
    DBManager dbManager;
    boolean isinput=false;
    private String date1 = null;//单天日期

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_sign_cale);

        // 初始化DBManager
        dbManager = new DBManager(this);
        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        date1 =formatter.format(curDate);

        initCala();
        initRecyclerView();

        //监听当前月份
        calendar.setOnCalendarDateChangedListener(new SignCalendar.OnCalendarDateChangedListener() {
            public void onCalendarDateChanged(int year, int month) {
                popupwindow_calendar_month.setText(year + "年" + month + "月");
            }
        });

//        监听所选中的日期
//		calendar.setOnCalendarClickListener(new SignCalendar.OnCalendarClickListener() {
//			public void onCalendarClick(int row, int col, String dateFormat) {
//				int month = Integer.parseInt(dateFormat.substring(dateFormat.indexOf("-") + 1, dateFormat.lastIndexOf("-")));
//				if (calendar.getCalendarMonth() - month == 1 || calendar.getCalendarMonth() - month == -11) {
//					calendar.lastMonth();
//
//				} else if (month - calendar.getCalendarMonth() == 1 || month - calendar.getCalendarMonth() == -11) {
//					calendar.nextMonth();
//
//				} else {
//					list.add(dateFormat);
//					calendar.addMarks(list, 0);
//					calendar.removeAllBgColor();
//					calendar.setCalendarDayBgColor(dateFormat, R.mipmap.calendar_date_focused);
//					date = dateFormat;//最后返回给全局 date
//				}
//			}
//		});
//

    }




    private void initCala(){
        btn_signIn = (Button) findViewById(R.id.btn_signIn);
        btn_signIn.setOnClickListener(this);
        calendar = (SignCalendar) findViewById(R.id.popupwindow_calendar);

        popupwindow_calendar_month = (TextView) findViewById(R.id.popupwindow_calendar_month);
        popupwindow_calendar_month.setText(calendar.getCalendarYear() + "年" + calendar.getCalendarMonth() + "月");
        if (null != date) {
            int years = Integer.parseInt(date.substring(0, date.indexOf("-")));
            int month = Integer.parseInt(date.substring(date.indexOf("-") + 1, date.lastIndexOf("-")));
            popupwindow_calendar_month.setText(years + "年" + month + "月");
            calendar.showCalendar(years, month);
            calendar.setCalendarDayBgColor(date, R.mipmap.calendar_date_focused);
        }
//        add("2015-11-10");
//        add("2015-11-02");
//        add("2015-12-02");
        query();
        if(isinput){
            btn_signIn.setText("今日完成所有任务，明日继续");
            btn_signIn.setBackgroundResource(R.drawable.button_gray);
            btn_signIn.setEnabled(false);
        }
    }


    private void initRecyclerView(){
        recyclerView= (RecyclerView) findViewById(R.id.taskCaleRecyclerView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        recyclerView.setHasFixedSize(true);
        cycleList=new ArrayList<TaskInfo>();
        cycleList.add(new TaskInfo("任务一:收藏一个View并看懂代码",1));
        cycleList.add(new TaskInfo("任务二:转载或原创一篇博客",2));
        cycleList.add(new TaskInfo("任务三:记住十个英语单词",3));

        taskCaleAdapter=new TaskCaleAdapter(this,R.layout.item_cycle_setting,cycleList);
        recyclerView.setAdapter(taskCaleAdapter);

//        if(isinput){
//            setSelectData(56);
//            recyclerView.setEnabled(false);
//
//        }

    }


    public static class TaskCaleAdapter extends MultipleSelectAdapter {

        public TaskCaleAdapter (Context context, int layoutId, List datas) {
            super(context, layoutId, datas);
        }

        @Override
        public void convert(ViewHolder holder, int pos) {
            TaskInfo cycleInfo= (TaskInfo) getItem(pos);
            holder.setText(R.id.txtName,cycleInfo.getTask());
        }

        @Override
        public int[] getCkeckViewInfo() {
            return new int[]{R.id.imgSeletIcon,R.mipmap.icon_checkbox_pressed,R.mipmap.icon_checkbox_check};
        }

    }


    public static class TaskInfo implements Comparable<TaskInfo>{
        private String task;
        private int Id;

        public TaskInfo(String task, int id) {
            this.task = task;
            Id = id;
        }

        public String getTask() {
            return task;
        }

        public void setTask(String task) {
            this.task = task;
        }

        public int getId() {
            return Id;
        }

        public void setId(int id) {
            Id = id;
        }

        @Override
        public int compareTo(TaskInfo cycleInfo) {
            if(cycleInfo.getId()>getId()){
                return -1;
            }else if (cycleInfo.getId()==getId()){
                return 0;
            }else {
                return 1;
            }
        }

    }


    private boolean isTaskOk(){
        selectList.clear();
        selectList.addAll(taskCaleAdapter.getSelectedList());
        if (getSelectData() == 56){
            return true;
        }
        return false;
    }

    public int getSelectData(){
        StringBuffer result=new StringBuffer("0000000");

        if(!DataUtil.listIsNull(selectList)){
            for (int i = 0; i <selectList.size() ; i++) {
                TaskInfo cycleInfo= (TaskInfo) selectList.get(i);
                result.setCharAt(cycleInfo.getId(),'1');

            }

            int base = 2;
            int decimal = Integer.parseInt(result.toString(), base);
            //       Log.d("resultString","--"+result.toString() +"----"+decimal);
            return  decimal;
        }

        return 0;
    }

//    private void setSelect(int pos){
//
//        selectList.add(cycleList.get(pos));
//        // cycleSettingsAdapter.setSelect(cycleList.get(pos));
//    }
//
//    public void setSelectData(int selectData){
//
//        selectList.clear();
//        String cycleData=getCycleString(selectData);
//        char[] chars=cycleData.toCharArray();
//        for (int i = 0; i < chars.length; i++) {
//            if('1'==chars[i]){
//                if(i==0){
//                    setSelect(6);
//                }else {
//                    setSelect(i-1);
//                }
//            }
//        }
//    }
//
//    /**
//     * 把int数字转换成7位的二进制字符串1000000
//     * */
//    private String getCycleString(int cycleData){
//
//        StringBuffer sb=new StringBuffer();
//        String cycleStr=Integer.toBinaryString(cycleData);
//        char[] charArr=cycleStr.toCharArray();
//        if(charArr.length<7){
//            char[] start=new char[7-charArr.length];
//            for (int i = 0; i < start.length; i++) {
//                start[i]='0';
//            }
//            sb.append(start);
//            sb.append(cycleStr);
//            return sb.toString();
//        }else if(charArr.length==7) {
//            return cycleStr;
//        }
//
//        return "";
//    }

    public void add(String date) {
        ArrayList<sqlit> persons = new ArrayList<sqlit>();
        sqlit person1 = new sqlit(date,"true");
        persons.add(person1);
        dbManager.add(persons);
    }

    public void query() {
        List<sqlit> persons = dbManager.query();
        for (sqlit person : persons)
        {
            list.add(person.getDate());
            if(date1.equals(person.getDate())){
                isinput=true;
            }
        }
        calendar.addMarks(list, 0);
    }


    @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.btn_signIn:
                        if(!isTaskOk()){
                            ViewUtils.showToast("请先完成所有任务");
                            return;
                        }

                        Date today= calendar.getThisday();
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        //                calendar.removeAllMarks();
        //               list.add(df.format(today));
        //               calendar.addMarks(list, 0);
                        //将当前日期标示出来
                        add(df.format(today));
                        //calendar.addMark(today, 0);
                        query();
                        HashMap<String, Integer> bg = new HashMap<String, Integer>();

                        calendar.setCalendarDayBgColor(today, R.drawable.bg_sign_today);
                        btn_signIn.setText("今日完成所有任务，明日继续");
                        btn_signIn.setBackgroundResource(R.drawable.button_gray);
                        btn_signIn.setEnabled(false);
                    break;
                default:
                    break;
            }
        }


    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        dbManager.closeDB();// 释放数据库资源
    }


}
