package com.zjf.myself.codebase.activity.UILibrary;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.model.RecycleViewInfo;
import com.zjf.myself.codebase.thirdparty.recyclerview.adapter.CommonAdapter;
import com.zjf.myself.codebase.thirdparty.recyclerview.adapter.MultiItemTypeAdapter;
import com.zjf.myself.codebase.thirdparty.recyclerview.adapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/23.
 */

public class DeviceRecycleViewAct extends Activity {

    RecyclerView recycleViewList;
    private ImageView btnWatchAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.win_recycle_view);
        btnWatchAdd = (ImageView) findViewById(R.id.btnWatchAdd);

        recycleViewList= (RecyclerView) findViewById(R.id.recycleViewList);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycleViewList.setLayoutManager(mLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        recycleViewList.setHasFixedSize(true);
        ArrayList<RecycleViewInfo> recycleViewInfos=new ArrayList<RecycleViewInfo>();

        recycleViewInfos.add(new RecycleViewInfo("老乐2",R.mipmap.icon_watch_head2));
        recycleViewInfos.add(new RecycleViewInfo("老乐1",R.mipmap.icon_watch_head2_deep));
        recycleViewInfos.add(new RecycleViewInfo("老乐2",R.mipmap.icon_watch_head2_deep));
        recycleViewInfos.add(new RecycleViewInfo("老乐3",R.mipmap.icon_watch_head2_deep));
        recycleViewInfos.add(new RecycleViewInfo("老乐4",R.mipmap.icon_watch_head2_deep));
        recycleViewInfos.add(new RecycleViewInfo("老乐5",R.mipmap.icon_watch_head2_deep));
        recycleViewInfos.add(new RecycleViewInfo("老乐6",R.mipmap.icon_watch_head2_deep));
        recycleViewInfos.add(new RecycleViewInfo("老乐1",R.mipmap.icon_watch_head2_deep));
        recycleViewInfos.add(new RecycleViewInfo("老乐2",R.mipmap.icon_watch_head2_deep));
        recycleViewInfos.add(new RecycleViewInfo("老乐3",R.mipmap.icon_watch_head2_deep));
        recycleViewInfos.add(new RecycleViewInfo("老乐4",R.mipmap.icon_watch_head2_deep));
        recycleViewInfos.add(new RecycleViewInfo("老乐5",R.mipmap.icon_watch_head2_deep));
        recycleViewInfos.add(new RecycleViewInfo("老乐6",R.mipmap.icon_watch_head2_deep));
        recycleViewInfos.add(new RecycleViewInfo("老乐1",R.mipmap.icon_watch_head2_deep));
        recycleViewInfos.add(new RecycleViewInfo("老乐2",R.mipmap.icon_watch_head2_deep));
        recycleViewInfos.add(new RecycleViewInfo("老乐3",R.mipmap.icon_watch_head2_deep));
        recycleViewInfos.add(new RecycleViewInfo("老乐4",R.mipmap.icon_watch_head2_deep));
        recycleViewInfos.add(new RecycleViewInfo("老乐5",R.mipmap.icon_watch_head2_deep));
        recycleViewInfos.add(new RecycleViewInfo("老乐6",R.mipmap.icon_watch_head2_deep));


        //点击跳转第一逻辑
        final RecycleViewAdapter mAdapter =new RecycleViewAdapter(DeviceRecycleViewAct.this,R.layout.item_recycle_view,recycleViewInfos);
        mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                //DevInfo devInfo= (DevInfo) mAdapter.getItem(position);

                //setDefaultDevRequest.setDefaultDev(devInfo.getDeviceId());
                //setDefaultDevRequest.start(getActivity());

                RecycleViewInfo recycleViewInfos= (RecycleViewInfo) mAdapter.getData().remove(position);
                mAdapter.getData().add(0,recycleViewInfos);
                mAdapter.notifyDataSetChanged();
                recycleViewList.scrollToPosition(0);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });


        recycleViewList.setAdapter(mAdapter);


    }

    static class RecycleViewAdapter extends CommonAdapter {

        public RecycleViewAdapter(Context context, int layoutId, List datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(ViewHolder holder, Object o, int position) {
            RecycleViewInfo recycleViewInfos = (RecycleViewInfo) o;

			/*
			方法一：
			TextView txtDevName=holder.getView(R.id.txtDevName);
			txtDevName.setText(devInfo.getName());
			*/

            holder.setText(R.id.txtDevName,recycleViewInfos.getName());
            holder.setImageResource(R.id.imgHeadIcon,recycleViewInfos.getDeviceId());
        }

    }

}