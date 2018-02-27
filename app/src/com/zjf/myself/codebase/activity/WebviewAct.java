package com.zjf.myself.codebase.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.fragment.WebviewFragment;
import com.zjf.myself.codebase.util.StringUtil;
import com.zjf.myself.codebase.util.ViewUtils;


/**
 * Created by Administrator on 2016/11/21.
 */

public class WebviewAct extends BaseAct {

    private WebView webView;
    public static TextView txtTitle;
    public static ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.win_webview);

        Intent it=getIntent();
        String title= it.getStringExtra("title");
        String url= it.getStringExtra("url");
        if(StringUtil.isNull(url) || StringUtil.isNull(title)){
            finish();
            return;
        }
      replaceFragment(R.id.layoutContent, WebviewFragment.newInstance(title,WebviewFragment.TITLE_TYPE_SECOND,url));
    }
    public static void start(String title, String url, Activity curAct){
        Intent it=new Intent(curAct,WebviewAct.class);
        it.putExtra("url",url);
        it.putExtra("title",title);
        ViewUtils.startActivity(it,curAct);
    }

//    @Override
//    protected boolean needToLogin() {
//        return false;
//    }

}
