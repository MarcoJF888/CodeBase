package com.zjf.myself.codebase.helper;

import android.app.Activity;
import android.content.SharedPreferences;

import com.zjf.myself.codebase.application.AppConstants;
import com.zjf.myself.codebase.application.AppController;

/**
 * <pre>
 *     author : ZouJianFeng
 *     e-mail :
 *     time   : 2017/04/06
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class LoginHelper {

    //存储登录状态
    public static void rememberLogin(long accountNum ){
         String LOGIN_INFO ="";
        SharedPreferences settings = AppController.getInstance().getTopAct().getSharedPreferences(LOGIN_INFO, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putLong("ACCOUNT_NUM",accountNum);
        editor.putInt("IS_LOGIN", AppConstants.IS_LOGIN);
        editor.commit();
    }
    //判断是否已经登录过
    public static boolean checkLogin(){
        String LOGIN_INFO ="";
        int  isLogin;
        int  accountNum;
        SharedPreferences settings = AppController.getInstance().getTopAct().getSharedPreferences(LOGIN_INFO, Activity.MODE_PRIVATE);
        isLogin = settings.getInt("IS_LOGIN", 1);
        if (isLogin == AppConstants.IS_LOGIN){
            return true;
        }
       return false;
    }

    //获取自动登录的用户名
    public static long checkAcount(){
        String LOGIN_INFO ="";
        long  accountNum;
        int  isLogin;
        SharedPreferences settings = AppController.getInstance().getTopAct().getSharedPreferences(LOGIN_INFO, Activity.MODE_PRIVATE);
        accountNum = settings.getLong("ACCOUNT_NUM", 1);
        isLogin = settings.getInt("IS_LOGIN", 1);
        return accountNum;
    }

    //退出登录，清除登录信息
    public static void cleanLogin(){
        String LOGIN_INFO ="";
        long  accountNum;
        int  isLogin;
        SharedPreferences settings = AppController.getInstance().getTopAct().getSharedPreferences(LOGIN_INFO, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
//清除指定信息
//        editor.remove("ACCOUNT_NUM");
//        editor.commit();
//全部清除
        editor.clear();
        editor.commit();
    }



}
