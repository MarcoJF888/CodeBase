package com.zjf.myself.codebase.util.gilde;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.module.GlideModule;
import com.zjf.myself.codebase.application.AppConstants;


/**
 * Created by Administrator on 2017/4/17.
 */
//LAOLE工程用来设置所有头像用的
public class GlideCache implements GlideModule {

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        //设置图片的显示格式ARGB_8888(指图片大小为32bit)
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
        //设置缓存的大小为100M
        int cacheSize = 100*1000*1000;
        builder.setDiskCache(new DiskLruCacheFactory(AppConstants.STORE_IMG_PATH, cacheSize));
    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }

}
