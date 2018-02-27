package com.zjf.myself.codebase.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/2/21.
 */

public class PickerViewHelper {
//    第一种方法
//    public static List<String> timeSort(int intervalTime){
//
//        List<String> data = new ArrayList<String>();
//
//        if (intervalTime == 3){
//            data.add("20");
//            data.add("30");
//            data.add("3");
//            data.add("5");
//            data.add("10");
//
//        } else if (intervalTime == 5){
//            data.add("30");
//            data.add("3");
//            data.add("5");
//            data.add("10");
//            data.add("20");
//
//
//        }
//        else if (intervalTime == 10) {
//            data.add("3");
//            data.add("5");
//            data.add("10");
//            data.add("20");
//            data.add("30");
//
//        }else if (intervalTime == 20){
//            data.add("5");
//            data.add("10");
//            data.add("20");
//            data.add("30");
//            data.add("3");
//
//
//        }else if (intervalTime == 30){
//            data.add("10");
//            data.add("20");
//            data.add("30");
//            data.add("3");
//            data.add("5");
//
//        }
//
//        return data;
//    }
//

//第二种方法
//    public static List<String> timeSort(int intervalTime){
//        ArrayList<String> data = new ArrayList<String>();
//        if (intervalTime == 3){
//
//            data = new ArrayList<String>(Arrays.asList("20", "30", "3" , "5" , "10"));
//
//        } else if (intervalTime == 5){
//            data = new ArrayList<String>(Arrays.asList("30", "3", "5" , "10" , "20"));
//
//
//        } else if (intervalTime == 10) {
//            data = new ArrayList<String>(Arrays.asList("3", "5", "10" , "20" , "30"));
//
//
//        }else if (intervalTime == 20){
//            data = new ArrayList<String>(Arrays.asList("5", "10", "20" , "30" , "3"));
//
//
//        }else if (intervalTime == 30){
//            data = new ArrayList<String>(Arrays.asList("10", "20", "30" , "3" , "5"));
//
//        }
//        return data;
//    }
//第三种方法
    public static List<String> timeSort(int intervalTime){
        ArrayList<String> data = new ArrayList<String>();
        switch (intervalTime){
            case 3:
                data = new ArrayList<String>(Arrays.asList("20", "30", "3" , "5" , "10"));
                break;
            case 5:
                data = new ArrayList<String>(Arrays.asList("30", "3", "5" , "10" , "20"));
                break;
            case 10:
                data = new ArrayList<String>(Arrays.asList("3", "5", "10" , "20" , "30"));
                break;
            case 20:
                data = new ArrayList<String>(Arrays.asList("5", "10", "20" , "30" , "3"));
                break;
            case 30:
                data = new ArrayList<String>(Arrays.asList("10", "20", "30" , "3" , "5"));
                break;
        }

        return data;
    }





//          滚筒排序算法
//    private List<Integer> mDataList = new ArrayList<Integer>(Arrays.asList(3,5,10,20,30));;
//
//    private List<Integer> sortData(int curData){
//        Integer[] allData=new Integer[mDataList.size()];
//        for (int i = 0; i < mDataList.size(); i++) {
//            allData[i]=mDataList.get(i);
//        }
//        //pos从0开始
//        int midPos=allData.length/2;
//        int leftCount=midPos;
//        int rightCount=allData.length-midPos-1;
//
//        Integer[] newDatas=new Integer[allData.length];
//
//        int oldPos=0;
//        for (int i = 0; i < allData.length; i++) {
//            if(curData==allData[i]){
//                oldPos=i;
//                break;
//            }
//        }
//
//        newDatas[midPos]=allData[oldPos];
//
//        int resideCount=0;
//
//        //左边
//        for (int i = 0; i < leftCount; i++) {
//            if(oldPos-1-i>=0){
//                newDatas[midPos-1-i]=allData[oldPos-1-i];
//            }else {
//                newDatas[midPos-1-i]=allData[allData.length-1-resideCount];
//                resideCount++;
//            }
//        }
//
//        //右边
//        resideCount=0;
//        for (int i = 0; i < rightCount; i++) {
//            if(oldPos+1+i<allData.length){
//                newDatas[midPos+i+1]=allData[oldPos+1+i];
//            }else {
//                newDatas[midPos+i+1]=allData[resideCount];
//                resideCount++;
//            }
//        }
//
//        ArrayList<Integer> resultList=new ArrayList<Integer>();
//        for (Integer item:newDatas) {
//            resultList.add(item);
//        }
//
//        return resultList;
//    }
}
