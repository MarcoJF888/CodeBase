package com.zjf.myself.codebase.activity.UILibrary;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.activity.BaseAct;
import com.zjf.myself.codebase.thirdparty.swipeMenu.MyDividerDecoration;
import com.zjf.myself.codebase.thirdparty.swipeMenu.SwipeAdapter;
import com.zjf.myself.codebase.thirdparty.swipeMenu.SwipeRecycleView1;

/**
 * <pre>
 *     author : ZouJianFeng
 *     e-mail : 1434690833@qq.com
 *     time   : 2017/07/03
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class SwipeMenuAct extends BaseAct{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_swipe_menu);

        SwipeRecycleView1 swipe_recycleview = (SwipeRecycleView1) findViewById(R.id.swipe_recycleview);
        swipe_recycleview.setLayoutManager(new LinearLayoutManager(this));
        swipe_recycleview.addItemDecoration(new MyDividerDecoration());
        SwipeAdapter swipeAdapter = new SwipeAdapter(this);
        swipe_recycleview.setAdapter(swipeAdapter);
    }
}
