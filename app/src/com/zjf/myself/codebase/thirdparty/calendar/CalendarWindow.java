/*
 * 文件名：xx.java
 * 版权：Copyright 2015 Yiba Tech. Co. Ltd. All Rights Reserved. 
 * 描述： xx.java
 * 修改人：wuchenhui
 * 修改时间：2015-2-9
 * 修改内容：新增
 */
package com.zjf.myself.codebase.thirdparty.calendar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.zjf.myself.codebase.R;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 
 * @author     wuchenhui
 */
public class CalendarWindow extends FrameLayout implements View.OnClickListener{
    
    private Animation mExpandAnimation;
    private Animation mCollapseAnimation;
    private boolean mIsExpand;

    private View contentView;
    private CalendarView calendarView;
    private TextView txtMonth;

    private CalendarView.OnTimeSelectListener onTimeSelectListener;

    public CalendarWindow(Context context) {
        this(context,null);
    }
    
    public CalendarWindow(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }
    
    public CalendarWindow(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init(){
        contentView= LayoutInflater.from(getContext()).inflate(R.layout.view_calendar,null);
        calendarView= (CalendarView) contentView.findViewById(R.id.calendarView);
        txtMonth= (TextView) contentView.findViewById(R.id.txtMonth);
        contentView.findViewById(R.id.btnNextMonth).setOnClickListener(this);
        contentView.findViewById(R.id.btnLastMonth).setOnClickListener(this);
        contentView.findViewById(R.id.layoutBottom).setOnClickListener(this);
        calendarView.setOnTimeSelectListener(new CalendarView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(long time) {
                Date date=new Date(time);
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("MM月");
                txtMonth.setText(simpleDateFormat.format(date));

                if(onTimeSelectListener !=null)
                    onTimeSelectListener.onTimeSelect(time);

                collapse();
            }
        });

        calendarView.setOnCalendarDateChangedListener(new CalendarView.OnCalendarDateChangedListener() {
            @Override
            public void onCalendarDateChanged(int year, int month) {
                txtMonth.setText(month+"月");
            }
        });

        setContentView(contentView);

        calendarView.setSelectTime(System.currentTimeMillis());
        Date date=new Date(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("MM月");
        txtMonth.setText(simpleDateFormat.format(date));
    }

    public void setSelectTime(long time){
        calendarView.setSelectTime(time);
    }


    public void setOnTimeSelectListener(CalendarView.OnTimeSelectListener onTimeSelectListener) {
        this.onTimeSelectListener = onTimeSelectListener;
    }

    public void setContentView(View view){
        if(view==null)
        	return;
        addView(view);
        setVisibility(GONE);
        initAnimation();
    }

    public void show() {
        if (!mIsExpand) {
//            view.setBackground(getResources().getDrawable(R.drawable.icon_cale_label_left));
            mIsExpand = true;
            clearAnimation();
            startAnimation(mExpandAnimation);
        }else {
            collapse();
//            view.setBackground(getResources().getDrawable(R.drawable.icon_cale_label));
        }
    }


    private void collapse() {
        if (mIsExpand) {
            mIsExpand = false;
            clearAnimation();
            startAnimation(mCollapseAnimation);

        }
    }

    public boolean isExpand() {
        return mIsExpand;
    }


    private void initAnimation() {

        mExpandAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.expand);
        mExpandAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

        });

        mCollapseAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.collapse);
        mCollapseAnimation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                setVisibility(View.GONE);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnNextMonth:
                calendarView.nextMonth();
                break;

            case R.id.btnLastMonth:
                calendarView.lastMonth();
                break;

            case R.id.layoutBottom:
                collapse();
                break;

        }
    }



}
