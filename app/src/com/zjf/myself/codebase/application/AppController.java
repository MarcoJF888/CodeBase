package com.zjf.myself.codebase.application;

import java.util.Stack;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.bugtags.library.Bugtags;
import com.bumptech.glide.RequestManager;
//import com.yineng.openapi.YNAPI;
import com.zjf.myself.codebase.util.AppLog;
import com.zjf.myself.codebase.util.BitmapLruCache;
import com.zjf.myself.codebase.util.PreferUtil;
import com.zjf.myself.codebase.util.StatusbarUtils;


import android.app.Activity;
import android.app.Application;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;


public class AppController extends Application {


    private static RequestQueue mHttpsRequestQueue;

    private static RequestQueue mRequestQueue;

    private static RequestQueue mImageUploadQueue;



    private static AppController instance;

    public static ImageLoader imageLoader;

    public static Stack<Activity> actList = new Stack<Activity>();
    public static BitmapLruCache lruImageCache;

    public static RequestManager requestManager;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

//        requestManager=new RequestManager();

//        mImageUploadQueue = Volley.newRequestQueue(this);
//        mHttpsRequestQueue=Volley.newRequestQueue(this);
        mRequestQueue= Volley.newRequestQueue(this);

        lruImageCache = BitmapLruCache.instance();
        imageLoader = new ImageLoader(mRequestQueue, lruImageCache);

        PreferUtil.getInstance().init(this);
//        Config.init(this);

//        YNAPI.getInstance().init(this);
    }

    public static void addAct(Activity act) {
        if (act != null) {
            actList.add(act);
        }
    }

    public static void exitAct() {
        for (Activity act : actList) {
            act.finish();
        }
    }

    public void exitAndGoToMainPage() {
        for(int i=0;i<actList.size()-1;i++){
            actList.pop().finish();
        }
    }

    public static AppController getInstance() {
        return instance;
    }


    public static RequestQueue getRequestQueue() {
        if (mRequestQueue != null) {
            return mRequestQueue;
        } else {
            throw new IllegalStateException("RequestQueue not initialized");
        }
    }

    public static void cancelAll(final Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    public static void cancelPublishAll(final Object tag) {
        if (mImageUploadQueue != null) {
            mImageUploadQueue.cancelAll(tag);
        }
    }

    public void addRequest(Request reqeust) {
        mRequestQueue.add(reqeust);
    }

    public void addHttpsRequest(Request reqeust) {
        mHttpsRequestQueue.add(reqeust);
    }

    public void addImageUploadRequest(Request reqeust) {
        mImageUploadQueue.add(reqeust);
    }

    public static void remove(Activity act) {
        if (act != null) {
            actList.remove(act);
        }
    }

    public Activity getTopAct() {
        return actList.peek();
    }

    public Drawable getDrawable(String name) {
        return getResources().getDrawable(findResId(name));
    }

    public int findResId(String name) {
        Resources res = getResources();
        String resName = name;
        int i = name.indexOf(".");
        if (i != -1) {
            resName = name.substring(0, i);
        }
        return res.getIdentifier(
                this.getPackageName() + ":drawable/" + resName, null, null);
    }


}
