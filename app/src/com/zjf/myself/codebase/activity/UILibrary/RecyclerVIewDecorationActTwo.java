package com.zjf.myself.codebase.activity.UILibrary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.activity.BaseAct;
import com.zjf.myself.codebase.adapter.MyRecycleViewAdapter1;
import com.zjf.myself.codebase.util.ViewUtils;
import com.zjf.myself.codebase.view.decoration.MyDecorationTwo;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建者 CSDN_LQR
 * @描述 RecyclerView的表格样式
 */
public class RecyclerVIewDecorationActTwo extends BaseAct {

    private List<String> mData = new ArrayList<>();
    private RecyclerView mRv;
    private MyRecycleViewAdapter1 mMyAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_decoration_recycler_view);
        mRv = (RecyclerView) findViewById(R.id.rv);
        initView();
        initData();
        setRecyclerView();
    }

    private void initView() {
        MyDecorationTwo decorationTwo = new MyDecorationTwo(this);
        mRv.addItemDecoration(decorationTwo);
    }

    private void initData() {
        for (int i = 0; i < 59; i++) {
            mData.add("item " + i);
        }
    }

    private void setRecyclerView() {
        mMyAdapter = new MyRecycleViewAdapter1(mData);
        mRv.setLayoutManager(new GridLayoutManager(this, 3));
        mRv.setAdapter(mMyAdapter);

        mMyAdapter.setOnItemClickListener(new MyRecycleViewAdapter1.OnItemClickListener(){
            @Override
            public void onItemClick(View view , int position){

                ViewUtils.showToast(""+ position);

            }
        });
    }
}
