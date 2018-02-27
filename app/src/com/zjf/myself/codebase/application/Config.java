package com.zjf.myself.codebase.application;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.DisplayMetrics;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.util.AppLog;
import com.zjf.myself.codebase.util.ImeiUtil;

import java.util.Properties;




public class Config {
    private static Properties prop;
    public static String dataBaseUrl;
    public static String imageUrl;
    public static DisplayMetrics displayMetrics;
    public static int screenHeight;
    public static int screenWidth;
    public static String imei;
    public static int mVersionCode;
    public static String mVersionName;


    public static void init(Context ctx) {
        LoadProperty();
        String baseUrl=getConfig("base_url").trim();
        dataBaseUrl = baseUrl;
        imageUrl=baseUrl;
        imei = ImeiUtil.getImei(ctx);
        screenWidth=getDisplayMetrics(ctx).widthPixels;
        screenHeight=getDisplayMetrics(ctx).heightPixels;
        getPkgVersion(ctx);
//            ApplicationInfo appInfo = ctx.getPackageManager().getApplicationInfo(ctx.getPackageName(), PackageManager.GET_META_DATA);
//            appSerialNo=appInfo.metaData.getString("SN");
//            screenHeight = displayMetrics.heightPixels;
    }

    private static void getPkgVersion(Context context) {
        try {
            PackageInfo pkgInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_CONFIGURATIONS);
            mVersionCode = pkgInfo.versionCode;
            mVersionName = pkgInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            AppLog.e(e);
        }
    }

    public static DisplayMetrics getDisplayMetrics(Context context){
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm;
    }


    public static void LoadProperty() {
        prop = new Properties();
        try {
            prop.load(AppController.getInstance().getResources().openRawResource(R.raw.config));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getConfig(String name) {
        return prop.getProperty(name);
    }

    public static int getIntConfig(String name) {
        return Integer.valueOf(prop.getProperty(name));
    }

}
