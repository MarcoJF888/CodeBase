package com.zjf.myself.codebase.activity.UILibrary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;


import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.listener.ItemDragListener;
import com.zjf.myself.codebase.adapter.ItemTouchHelperAdapter;
import com.zjf.myself.codebase.callback.MyItemTouchHelperCallback;
import com.zjf.myself.codebase.data.Cheeses;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class ItemTouchHelperAct extends AppCompatActivity implements ItemDragListener {

    private List<String> mData = new ArrayList<>();
    private RecyclerView mRv;
    private ItemTouchHelperAdapter mAdapter;
    private MyItemTouchHelperCallback mCallback;
    private ItemTouchHelper mItemTouchHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_decoration_recycler_view);
        mRv = (RecyclerView) findViewById(R.id.rv);
        initData();
        setRecyclerView();
    }

    private void initData() {
        for (int i = 0; i < Cheeses.sCheeseStrings.length; i++) {
            mData.add(Cheeses.sCheeseStrings[i]);
        }
        Collections.sort(mData, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
    }

    private void setRecyclerView() {
        mAdapter = new ItemTouchHelperAdapter(mData, this);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mRv.setAdapter(mAdapter);

        mCallback = new MyItemTouchHelperCallback(mAdapter);
        mItemTouchHelper = new ItemTouchHelper(mCallback);
        mItemTouchHelper.attachToRecyclerView(mRv);
    }

    @Override
    public void onStartDrags(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}
