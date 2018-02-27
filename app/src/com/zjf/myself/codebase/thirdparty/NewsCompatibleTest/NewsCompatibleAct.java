package com.zjf.myself.codebase.thirdparty.NewsCompatibleTest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.activity.BaseAct;

/**
 * <pre>
 *     author : ZouJianFeng
 *     e-mail : 1434690833@qq.com
 *     time   : 2017/06/15
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class NewsCompatibleAct extends BaseAct{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_news_compatible);

        String newsTitle = getIntent().getStringExtra("news_title");
        String newsContent = getIntent().getStringExtra("news_content");

        NewsContentFragment newsContentFragment = (NewsContentFragment) getSupportFragmentManager().findFragmentById(R.id.news_content_fragment);
        newsContentFragment.refresh(newsTitle,newsContent);
    }

    public static void actionStart(Context context,String txtTitle,String txtContent){
        Intent intent = new Intent(context,NewsCompatibleAct.class);
        intent.putExtra("news_title",txtTitle);
        intent.putExtra("news_content",txtContent);
        context.startActivity(intent);
    }
}
