package com.zjf.myself.codebase.util.gilde;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zjf.myself.codebase.util.ViewUtils;


/**
 * Created by Administrator on 2017/2/16.
 */
public class GlideImgManager {

    public static void glideLoader(Context context,ImageView iv,String url, int defImg,int type,int r) {
        if(type== ViewUtils.TYPE_CIRCLE){
            Glide.with(context).load(url).placeholder(defImg).error(defImg).transform(new GlideCircleTransform(context)).into(iv);
        }else if(type==ViewUtils.TYPE_ROUND_RECT){
            Glide.with(context).load(url).placeholder(defImg).error(defImg).transform(new GlideRoundTransform(context,r)).into(iv);
        }else {
            Glide.with(context).load(url).placeholder(defImg).error(defImg).into(iv);
        }
    }

}
