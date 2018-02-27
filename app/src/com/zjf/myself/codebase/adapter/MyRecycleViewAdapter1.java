package com.zjf.myself.codebase.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zjf.myself.codebase.R;

import java.util.List;

public class MyRecycleViewAdapter1 extends RecyclerView.Adapter<MyRecycleViewAdapter1.MyViewHolder>
        implements View.OnClickListener{

    private List<String> mData;

    public MyRecycleViewAdapter1(List<String> data) {
        mData = data;
    }

    private OnItemClickListener mOnItemClickListener = null;


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(parent.getContext(), R.layout.item_layout, null);
        itemView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        itemView.setOnClickListener(this);
        return new MyViewHolder(itemView);
    }


    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }


    public static interface OnItemClickListener {
        void onItemClick(View view , int position);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mTv.setText(mData.get(position));
        holder.itemView.setTag(position);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mTv;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTv = (TextView) itemView.findViewById(R.id.text1);
        }
    }

}
