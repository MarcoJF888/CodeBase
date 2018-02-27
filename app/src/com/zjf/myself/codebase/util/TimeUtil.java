package com.zjf.myself.codebase.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/23.
 */

public class TimeUtil {

    public static final String FORMAT_1="yyyyMMddHHmmss";

    public static final String FORMAT_2="yyyyMMdd";

    public static final String FORMAT_3="yyyyMMddHHmm";

    public static final String FORMAT_4="yyyy年MM月dd日HH时mm分ss";

    public static final String FORMAT_5="yyyy-MM-dd HH:mm";

    public static final String FORMAT_6="yyyy-MM-dd";

    public static final String FORMAT_7="MM月dd日";

    public static final String FORMAT_8="MM月dd日 HH:mm";

    public static final String FORMAT_9="HHmm";

    public static final String FORMAT_10="HH:mm";

    public static final String FORMAT_11="MM-dd";

    public static final String FORMAT_12="HH时mm分";

    public static String formatTime(String format,Long time){
        if(time==null)
            return "";

        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Date date = new Date(time);

        return formatter.format(date);
    }



    public static long getLongTime(String time,String formatStr){
        //1970-01-06 11:45:55   yyyy-MM-dd HH:mm:ss
        SimpleDateFormat format =  new SimpleDateFormat(formatStr);
        try {
            Date date = format.parse(time);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return  0;
    }


    /**
     * 把时间戳格式化为20160101的格式
     */
    public static String formatRequestTime(Long time){
        if(time==null)
            return "";

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date(time);
        return formatter.format(date);
    }


    public static String reFormatTime(String oldTime,String oldFormat,String newFormat){
        //2010/05/04 12:34:23 yyyy/MM/dd HH:mm:ss
        Date date = new Date();
        //注意format的格式要与日期String的格式相匹配
        DateFormat sdf = new SimpleDateFormat(oldFormat);
        DateFormat newFormater = new SimpleDateFormat(newFormat);
        try {
            date = sdf.parse(oldTime);
            return newFormater.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * 把1300的String紧缩格式转成13:00用于app展示
     */
    public static String reFormatTime(String time){
        if(StringUtil.isNull(time))
            return null;

        return TimeUtil.formatTime("HH:mm",getAppTime(time));
    }

    /**
     * 把1300的String紧缩格式 补成能格式化显示的完整时间戳
     */
    public static long getAppTime(String time){
        if(StringUtil.isNull(time))
            return 0;

        String dateTime=TimeUtil.formatTime("yyyyMMdd",System.currentTimeMillis());
        String resultTime=dateTime+time;
        return getLongTime(resultTime,"yyyyMMddHHmm");
    }


    /**
     * 把long时间格式化 13:00
     */
    public static String reFormatTime(long time){
        return  TimeUtil.formatTime("HH:mm",time);
    }


    /**
     * 获取提交服务器的紧缩时间格式如 1300
     */
    public static String getResultTime(long time){
        return TimeUtil.formatTime("HHmm",time);
    }


    public static String getBeforeDate(String curDate){
        long currentTime=getLongTime(curDate,"yyyyMMdd");
        long beforeDateTime=currentTime-(24*60*60*1000);

        return formatRequestTime(beforeDateTime);
    }

    public static String getNextDate(String curDate){
        long currentTime=getLongTime(curDate,"yyyyMMdd");
        long beforeDateTime=currentTime+(24*60*60*1000);

        return formatRequestTime(beforeDateTime);
    }

    //分钟转为小时分钟
    public static String changeTime(int allTime){
        int hour,min;
        String time = "";
        hour = allTime / 60;
        min = allTime % 60;

        if ( allTime >= 60){
            if (min == 0){
                time =hour+"小时";
            }else{
              time =hour+"时"+min+"分";
            }

        }else {
            min = allTime;
            time = min+"分钟";
        }
        return time;
    }


    /**
     * @param date
     * @return
     * 计算一个日子是不是周日
     */
    public static boolean isSunday(String date) {

        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        Date bdate = null;
        try {
            bdate = format1.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(bdate);
        if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY){
            return true;
        }else {
            return false;
        }
    }
}
