package com.zjf.myself.codebase.activity.UILibrary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.activity.BaseAct;
import com.zjf.myself.codebase.adapter.MyRecycleViewAdapter1;
import com.zjf.myself.codebase.adapter.MyRecycleViewAdapter2;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     author : ZouJianFeng
 *     e-mail : 1434690833@qq.com
 *     time   : 2017/06/03
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class RecyclerViewActivity extends BaseAct{
    private List<String> mData = new ArrayList<>();
    private RecyclerView mRv;
    private MyRecycleViewAdapter1 mMyAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_recycler_view_change);
        mRv = (RecyclerView) findViewById(R.id.rv);
        initData();
        setRecyclerView();
    }

    private void initData() {
        for (int i = 0; i < 100; i++) {
            mData.add("item " + i);
        }
    }

    private void setRecyclerView() {
        mMyAdapter = new MyRecycleViewAdapter1(mData);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mRv.setAdapter(mMyAdapter);
    }



    public void linearVer(View view){
        mRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRv.setAdapter(mMyAdapter);
    }
    public void linearHor(View view){
        mRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mRv.setAdapter(mMyAdapter);
    }
    public void gridVer(View view){
        mRv.setLayoutManager(new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false));
        mRv.setAdapter(mMyAdapter);
    }
    public void gridHor(View view){
        mRv.setLayoutManager(new GridLayoutManager(this, 3, GridLayoutManager.HORIZONTAL, false));
        mRv.setAdapter(mMyAdapter);
    }
    public void staggeredVer(View view){
        mRv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRv.setAdapter(new MyRecycleViewAdapter2(mData));
    }
    public void staggeredHor(View view){
        mRv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL));
        mRv.setAdapter(new MyRecycleViewAdapter2(mData));
    }

}
