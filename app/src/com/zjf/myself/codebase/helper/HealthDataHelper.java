package com.zjf.myself.codebase.helper;



import android.widget.TextView;

import com.zjf.myself.codebase.util.StringUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/1/9.
 */

public class HealthDataHelper {

    public static List<Integer> getHeartRateDatas(String data){
        List<Integer> heartRataDatas=null;
        if(StringUtil.isNull(data)){
            return heartRataDatas;
        }

        byte[] allBytes=data.getBytes();
        //不能被2整除也return
        heartRataDatas=new ArrayList<Integer>();

        int count=0;
        byte[] item=new byte[2];

        for (int i = 0; i < allBytes.length; i++) {
            item[count]=allBytes[i];
            count++;
            if(count>1){
                int base = 16;
                int decimal = Integer.parseInt(new String(item), base);
                heartRataDatas.add(decimal);
                count=0;
            }
        }

        return heartRataDatas;
    }


    //心率状态判断
    public static String getHRState(int HRData ){
        String HRState ="";

        if (HRData < 60){
            HRState = "心率偏低";
        }else if(60 <= HRData && HRData < 100){
            HRState = "心率正常";
        }else if(100 <= HRData && HRData < 120){
            HRState = "心率偏高";
        }else if (120 <= HRData && HRData < 160){
            HRState = "运动心率";
        }else if (HRData >= 160){
            HRState = "异常数据";
        }
        String hrState = HRState;
        return hrState ;
    }




    //运动距离计算
    //    身高-步距算法
    //    身高166cm以上     步距W(cm)= 10/21*身高h(cm) - 15cm
    //    身高148cm-166cm  步距W(cm)= 15/28*身高h(cm) - 15cm
    //    身高140cm以下     步距W(cm)= 19/42*身高h(cm) - 15cm
    //
    //    年龄 50岁以下     步距(cm) = 身高-步距算法结果W(cm)
    //    年龄 50岁以上     步距(cm) = 身高-步距算法结果W(cm) - 10(cm)

    //先通过身高算出步距，在通过年龄去优化步距
    public static long getMileage(int height , int age , long stepNum){
        double mileage,stepRange = 0;

        if(height > 166) {

            stepRange = (double)10 / 21 * height  - 15;

        }else if (166 >= height && height >= 140){

            stepRange = (double) 15 / 28 * height - 15;

        } else if (height < 140){

            stepRange = (double) 19 / 42 * height - 15;

        }

        if (age < 50){

            mileage = stepNum * stepRange / 100;

        }else {

            mileage = stepNum * (stepRange - 10) / 100;
        }

        long Mileage = Math.round(mileage);

        return Mileage;

    }


    //    卡路里计算
    //    Calorie = 体重（kg）* 距离（km）* 运动系数（k）
    //   运动系数K 健走：k=0.8214
    //            跑步：k=1.036
    //            自行车：k=0.6142
    //            轮滑、溜冰：k=0.518
    //            室外滑雪：k=0.888

    public static long getCalorie(int weight , long mileage , double k){
        double calorie;

        calorie = (double) weight * mileage * k / 1000;

        long Calorie = Math.round(calorie);

        return Calorie;
    }


    /**血压状态判断
     *    bpState = 0 = 血压偏低
     *    bpState = 1 = 血压正常
     *    bpState = 2 = 血压偏高
     *    bpState = 3 = 血压过高
     *    bpState = 5 = 数据异常 */
    public static final int BP_STATUS_LOW=0;
    public static final int BP_STATUS_NOMAL=1;
    public static final int BP_STATUS_HIGH=2;
    public static final int BP_STATUS_DANGERAUS=3;
    public static final int BP_DATA_ERROR=5;

    public static int getBPstate(int high , int low){
        int bpState = -1;
        int lowState;

        lowState = lowCompare(low);

        if (high < 90){
            if(lowState == BP_STATUS_LOW || lowState ==BP_STATUS_NOMAL){
                bpState = BP_STATUS_LOW;
            }else if (lowState ==BP_STATUS_HIGH || lowState == BP_STATUS_DANGERAUS){
                bpState = BP_STATUS_DANGERAUS;
            }

        }else if (high >= 90 && high < 140){
            if(lowState == BP_STATUS_LOW){
                bpState = BP_STATUS_LOW;
            }else if (lowState ==BP_STATUS_NOMAL){
                bpState = BP_STATUS_NOMAL;
            }else if (lowState ==BP_STATUS_HIGH){
                bpState = BP_STATUS_HIGH;
            }else if (lowState == BP_STATUS_DANGERAUS){
                bpState = BP_DATA_ERROR;
            }

        }else if (high >= 140 && high < 160){
            if(lowState == BP_STATUS_LOW){
                bpState = BP_DATA_ERROR;
            }else if (lowState ==BP_STATUS_NOMAL || lowState ==BP_STATUS_HIGH){
                bpState = BP_STATUS_HIGH;
            }else if (lowState == BP_STATUS_DANGERAUS){
                bpState = BP_STATUS_DANGERAUS;
            }

        }else if (high >= 160){
            if(lowState == BP_STATUS_LOW || lowState == BP_STATUS_NOMAL){
                bpState = BP_DATA_ERROR;
            }else if (lowState ==BP_STATUS_HIGH || lowState == BP_STATUS_DANGERAUS){
                bpState = BP_STATUS_DANGERAUS;
            }

        } else {
            bpState = BP_DATA_ERROR;
        }

        return bpState;
    }


    private static int lowCompare(int low){
        int lowState = -1;

        if (low < 60 ){
            lowState = BP_STATUS_LOW;
        } else if (low >= 60 && low < 90 ){
            lowState = BP_STATUS_NOMAL;
        } else if (low >= 90 && low < 110 ){
            lowState = BP_STATUS_HIGH;
        } else if (low >= 110 ){
            lowState = BP_STATUS_DANGERAUS;
        }

        return lowState;
    }


    //睡眠状态
    public static String getSleepState(int sleepEfficiency){
        String sleepState = "";

            if (sleepEfficiency < 70){
                sleepState = "差";
            }else if(sleepEfficiency >= 70 && sleepEfficiency < 85){
                sleepState = "良";
            }else if (sleepEfficiency >= 85){
                sleepState = "优";
            }

        return sleepState;
    }



//        XX时XX分
//        StringUtil.changeFontSize(txtDeepTime,26,0,2,3,5);

//        X时XX分
//        StringUtil.changeFontSize(txtDeepTime,26,0,1,2,4);

//        X时X分
//        StringUtil.changeFontSize(txtDeepTime,26,0,1,2,3);

//        XX时X分
//        StringUtil.changeFontSize(txtDeepTime,26,0,2,3,4);

//        XX分钟
//        StringUtil.changeFontSize(txtDeepTime,26,0,2,0,0);

//        X分钟
//        StringUtil.changeFontSize(txtDeepTime,26,0,1,0,0);

//        XX小时
//        StringUtil.changeFontSize(txtDeepTime,26,0,2,0,0);

//        X小时
//        StringUtil.changeFontSize(txtDeepTime,26,0,1,0,0);



    public static final int MM_dd =0;
    public static final int M_d   =1;
    public static final int MM_d  =2;
    public static final int M_dd  =3;
    public static final int dd    =4;
    public static final int d     =5;
    public static final int MM    =6;
    public static final int M     =7;

    public static int timeType(int allTime){
        int hour,min;
        int timeType = 0;
        String time;

        if( allTime >= 60){
            hour = allTime / 60;
            min = allTime % 60;

            if(min == 0){
                time =hour+"小时";
                if(time.length() == 4){
                    timeType = MM;

                }else {
                    timeType =  M;
                }

            }else {
                time =hour+"时"+min+"分";
                Integer txtHour = hour;

                if(time.length() == 6){
                    timeType = MM_dd;

                }else if(time.length() == 4){
                    timeType = M_d;

                }else if(time.length() == 5 && txtHour.toString().length() == 2 ){
                    timeType = MM_d;

                }else if(time.length() == 5 && txtHour.toString().length() == 1){
                    timeType = M_dd;
                }
            }

        }else if(allTime < 60) {
            min = allTime;
            time = min + "分钟";
            if(time.length() == 3) {
                timeType = d;

            }else{
                timeType = dd;
            }
        }
        return timeType;
    }


    public static void changeSleepTime(TextView v, int allTime){
        int timeType = HealthDataHelper.timeType(allTime);

        switch(timeType){
            case 0:
                StringUtil.changeFontSize(v,26,0,2,3,5);
                break;

            case 1:
                StringUtil.changeFontSize(v,26,0,1,2,3);
                break;

            case 2:
                StringUtil.changeFontSize(v,26,0,2,3,4);
                break;

            case 3:
                StringUtil.changeFontSize(v,26,0,1,2,4);
                break;

            case 4:
                StringUtil.changeFontSize(v,26,0,2,0,0);
                break;

            case 5:
                StringUtil.changeFontSize(v,26,0,1,0,0);
                break;

            case 6:
                StringUtil.changeFontSize(v,26,0,1,0,0);
                break;

            case 7:
                StringUtil.changeFontSize(v,26,0,1,0,0);
                break;

            default:
                break;
        }
    }

}
