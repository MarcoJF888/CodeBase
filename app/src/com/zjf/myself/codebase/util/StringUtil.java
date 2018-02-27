package com.zjf.myself.codebase.util;

import android.app.Activity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.TextView;


import com.zjf.myself.codebase.application.AppController;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author dsnx
 * @version YIBA-O2O 2014-12-25
 * @since YIBA-O2O
 */
public class StringUtil {
    static String[] units = {"", "十", "百", "千", "万", "十万", "百万", "千万", "亿", "十亿", "百亿", "千亿", "万亿"};
    static char[] numArray = {'零', '一', '二', '三', '四', '五', '六', '七', '八', '九'};

    public static boolean isNull(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static String getFormatTime(long time, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(time);
    }

    public static String getFormatPrice(float price) {
        DecimalFormat df = new DecimalFormat("#0.00");
        return df.format(price);

    }

    public static String getPrice(float price){
        return  "￥"+getFormatPrice(price);
    }

    public static long getMillisByDate(String date) {
        long millis = 0l;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            // String millisString = "2011-09-20 00:00:00";
            millis = sdf.parse(date).getTime();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return millis;
    }

    public static String color(String text, String color) {
        return "<font color='" + color + "'>" + text + "</font>";
    }

    public static String getDateByMillis(long millis){
        Date date = new Date(millis);
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        return f.format(date);
    }

    public static String getDateByMillis(long milllis, String format) {
        if (milllis > 0) {
            DateFormat formatter = new SimpleDateFormat(format);
            // long now = System.currentTimeMillis();
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(milllis);
            return formatter.format(calendar.getTime());
        } else {
            return "";
        }
    }




    public static String foematInteger(int num) {
        char[] val = String.valueOf(num).toCharArray();
        int len = val.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            String m = val[i] + "";
            int n = Integer.valueOf(m);
            boolean isZero = n == 0;
            String unit = units[(len - 1) - i];
            if (isZero) {
                if ('0' == val[i - 1]) {
                    // not need process if the last digital bits is 0
                    continue;
                } else {
                    // no unit for 0
                    sb.append(numArray[n]);
                }
            } else {
                sb.append(numArray[n]);
                sb.append(unit);
            }
        }
        return sb.toString();
    }

    public static String toFromatTime(long time, String fromat_time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(fromat_time);
        return dateFormat.format(time);
    }


    public static int getRelativeSize(int size, Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return (dm.widthPixels > 900) ? (2 * size) : ((dm.widthPixels > 720) ? (size + (2 * size) / 3) : (dm.widthPixels > 540) ? (size + size / 3) : (dm.widthPixels > 480) ? size : (size - size / 10));
    }




    public static boolean inSleepTime(){
        Calendar calendar = Calendar.getInstance();
        int curHour = calendar.get(Calendar.HOUR_OF_DAY);
        if(curHour < 8 || curHour > 22){
            return true;
        }
        return false;
    }

    public static String getHiddenString(String string) {
        if (!isNull(string) && string.length() > 4) {
            return string.substring(0, 4) + "****" + string.substring(string.length() - 4);
        } else {
            return "";
        }
    }

    public static boolean isSupportBank(String bankNo){
        if ("01020000".equals(bankNo) || "01030000".equals(bankNo) || "01040000".equals(bankNo) || "03080000".equals(bankNo) || "03030000".equals(bankNo) || "03100000".equals(bankNo)) {
            return true;
        }
        return false;
    }

    public static String trimFromStr(String oldStr,int saveLen){
        if(oldStr!=null&&oldStr.length()>saveLen){
            String end=oldStr.subSequence(saveLen, oldStr.length()).toString();
            return oldStr.replace(end, "...");
        }

        return oldStr;
    }


    public static String getRMBPrice(float price) {
        DecimalFormat df = new DecimalFormat("#0.00");
        return "￥"+df.format(price);
    }

    public static boolean isMobileNO(String mobiles){
        String args="^1[34578]\\d{9}$";
        Pattern p = Pattern.compile(args);
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    public static boolean isFixedPhone(String fixedPhone){
        String reg="(?:(\\(\\+?86\\))(0[0-9]{2,3}\\-?)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?)|" +
                "(?:(86-?)?(0[0-9]{2,3}\\-?)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?)";
        return Pattern.matches(reg, fixedPhone);
    }


    //设置时间颜色大小
    public static void changeFontSize(TextView v,int size,int a, int b,int c,int d) {
        SpannableString spanString = new SpannableString(v.getText());
//      spanString.setSpan(new ForegroundColorSpan(Color.RED), a, b, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spanString.setSpan(new AbsoluteSizeSpan(size, true), a, b, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        spanString.setSpan(new AbsoluteSizeSpan(size, true), c, d,Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        v.setText(spanString);
    }
}
