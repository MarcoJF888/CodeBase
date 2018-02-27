package com.zjf.myself.codebase.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.toolbox.NetworkImageView;
import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.activity.HomeAct;
import com.zjf.myself.codebase.application.AppController;
import com.zjf.myself.codebase.util.gilde.GlideImgManager;

import java.util.List;

public class ViewUtils {

    public static final int TYPE_DEFAULT=0;
    public static final int TYPE_ROUND_RECT=1;
    public static final int TYPE_CIRCLE=2;


    public static void showToast(String msg) {
        if (StringUtil.isNull(msg)) {
            return;
        }
        Context context = AppController.getInstance();
        View toastView = LayoutInflater.from(context).inflate(R.layout.toast, null);
        TextView toastText = (TextView) toastView.findViewById(R.id.toast_text);
        toastText.setText(msg);
        Toast toast = new Toast(context);
        int cha = DesityUtil.dp2px(context, 32);
        toast.setGravity(Gravity.BOTTOM, 0, 200);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(toastView);
        toast.show();
    }

    public static void showToast(String msg,int type) {
        if (StringUtil.isNull(msg)) {
            return;
        }
        Context context = AppController.getInstance();
        View toastView = LayoutInflater.from(context).inflate(R.layout.toast, null);
        TextView toastText = (TextView) toastView.findViewById(R.id.toast_text);
        toastText.setText(msg);
        Toast toast = new Toast(context);
        int cha = DesityUtil.dp2px(context, 32);
        toast.setGravity(Gravity.BOTTOM, 0, 200);
        toast.setDuration(type);
        toast.setView(toastView);
        toast.show();
    }

    public static void showTopToast(String msg) {
        if (StringUtil.isNull(msg)) {
            return;
        }
        Context context = AppController.getInstance();
        View toastView = LayoutInflater.from(context).inflate(R.layout.toast, null);
        TextView toastText = (TextView) toastView.findViewById(R.id.toast_text);
        toastText.setText(msg);
        Toast toast = new Toast(context);
        int cha = DesityUtil.dp2px(context, 48);
        toast.setGravity(Gravity.TOP, 0, cha);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(toastView);
        toast.show();
    }

    /**
     * 横穿文字画线
     *
     * @param tv
     * @param str
     */
    public static void strikeThruText(TextView tv, String str) {
        if (tv != null && !StringUtil.isNull(str)) {
            tv.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            tv.setText(str);
        }
    }

    public static void startActivity(Intent intent, Activity curAct) {
        if (intent != null) {
            curAct.startActivity(intent);
            curAct.overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
        }
    }

    public static void toHome(Activity curAct){
        startActivity(new Intent(curAct, HomeAct.class),curAct);
    }

    public static void startActivityForResult(Intent intent,int requestCode) {
        if (intent != null) {
            Activity curAct=AppController.getInstance().getTopAct();
            curAct.startActivityForResult(intent,requestCode);
            curAct.overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
        }
    }

    public static void startActivityForResult(Intent intent, Activity curAct,int requestCode) {
        if (intent != null) {
            curAct.startActivityForResult(intent, requestCode);
            curAct.overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
        }
    }


    static public void setText(View v, Object o) {
        if (v == null || o == null) {
            return;
        }
        if (!(v instanceof TextView)) {
            return;
        }
        TextView t = (TextView) v;
        if (t == null || o == null) {
            return;
        }
        if (o instanceof String) {
            t.setText((String) o);
        } else {
            t.setText(o.toString());
        }

    }



    static public void setText(View v, String text) {
        if (v == null)
            return;

        if(v instanceof  TextView){
            TextView tv=(TextView)v;
            if (DataUtil.stringIsNull(text)) {
                tv.setText("");
            } else {
                tv.setText(text);
            }
        }
    }

    static public void setText(EditText editText, String text) {
        if (editText == null)
            return;

        if (DataUtil.stringIsNull(text)) {
            editText.setText("");
        } else {
            editText.setText(text);
            editText.setSelection(text.length());
        }
    }


    public static void setClickEnable(View view,boolean enable){
        if (view==null)
            return;

        view.setEnabled(enable);
        if(enable){
            view.setBackgroundResource(R.drawable.icon_button);
        }else {
            view.setBackgroundResource(R.drawable.button_dis);
        }
    }

    public void setImageByUrl(int viewId, String url){
//        Picasso.with(context).load(url).into((ImageView) getView(viewId));
//        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(context));
//        ImageLoader.getInstance().displayImage(url, (ImageView) getView(viewId));
    }



    public static  void setImageByUrl(View v,String url,int defaultImageResId) {
        if (v == null) {
            return;
        }

        if (!(v instanceof NetworkImageView)) {
            return;
        }

        NetworkImageView img = (NetworkImageView) v;
        if(StringUtil.isNull(url)){
            img.setDefaultImageResId(defaultImageResId);
            img.setImageUrl("http://empty",AppController.imageLoader);
            return;
        }

        if(url.startsWith("http://")||url.startsWith("https://")){
            img.setDefaultImageResId(defaultImageResId);
            img.setImageUrl(url,AppController.imageLoader);
        }else {
            img.setImageResource(defaultImageResId);
        }
    }

    public static  void setImageByUrl(View v,String url,int defaultImageResId,int type,int r) {
        if (v == null||!(v instanceof ImageView)) {
            return;
        }

        ImageView img= (ImageView) v;
        GlideImgManager.glideLoader(AppController.getInstance(),img,url,defaultImageResId,type,r);
    }

    public static  void setImageRes(View v,String url,int imageResId) {
        if (v == null) {
            return;
        }

        if (!(v instanceof NetworkImageView)) {
            return;
        }

        NetworkImageView img = (NetworkImageView) v;
        img.setImageUrl("");
        img.setImageResource(imageResId);
    }

    public static  void setImageBmp(View v, String url, Bitmap bitmap) {
        if (v == null) {
            return;
        }

        if (!(v instanceof NetworkImageView)) {
            return;
        }

        NetworkImageView img = (NetworkImageView) v;
        img.setImageUrl("");
        img.setImageBitmap(bitmap);
    }


    public static String getText(View parent, int viewId) {
        if (parent == null) {
            return "";
        }
        View v = parent.findViewById(viewId);
        if (v == null) {
            return "";
        }
        if (!(v instanceof TextView)) {
            return "";
        }
        return ((TextView) v).getText().toString().trim();
    }

    static public void setGone(View v, int resId) {
        View view = v.findViewById(resId);
        if (view == null) {
            return;
        }
        view.setVisibility(View.GONE);
    }

    static public boolean isVisible(View v) {
        return v.getVisibility() == View.VISIBLE;
    }

    static public void setVisible(View v, int resId) {
        View view = v.findViewById(resId);
        if (view == null) {
            return;
        }
        view.setVisibility(View.VISIBLE);
    }

    static public void setGone(View v) {
        if (v == null) {
            return;
        }
        v.setVisibility(View.GONE);
    }


    static public void setVisible(View v) {
        if (v == null) {
            return;
        }
        v.setVisibility(View.VISIBLE);
    }


    public static void setEnable(List<View> views,boolean enable){
        if (views!=null&&views.size()>0){
            for (View view:views) {
                view.setEnabled(enable);
            }
        }
    }


    public static int getScreenWidth(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    public static int getScreenHeight(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

}
