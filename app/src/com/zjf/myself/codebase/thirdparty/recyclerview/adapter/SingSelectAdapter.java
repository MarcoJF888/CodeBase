package com.zjf.myself.codebase.thirdparty.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

/**
 * Created by Administrator on 2016/12/24.
 */

public abstract class SingSelectAdapter extends CommonAdapter {

    private int curSelect=0;

    private OnCkeckChangeListener onCkeckChangeListener;

    public SingSelectAdapter(Context context, int layoutId, List datas) {
        super(context, layoutId, datas);

        setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                if(curSelect!=position){
                    curSelect=position;
                    notifyDataSetChanged();
                }

                if(onCkeckChangeListener!=null){
                    onCkeckChangeListener.onCkeckChange(position);
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

    }


    @Override
    protected void convert(ViewHolder holder, Object o, int position) {
        View ckeckView =holder.getView(getCkeckViewInfo()[0]);
        if(curSelect==position){
            ckeckView.setBackgroundResource(getCkeckViewInfo()[1]);
        }else {
            ckeckView.setBackgroundResource(getCkeckViewInfo()[2]);
        }

        convert(holder,position);
    }

    public abstract void convert (ViewHolder holder, int pos);


    public int getCurSelect() {
        return curSelect;
    }

    public void setCurSelect(int curSelect) {
        this.curSelect = curSelect;
    }


    //长度为3 0：viewId 1:defBgId 2:selectBgId
    public abstract int[] getCkeckViewInfo();


    public interface OnCkeckChangeListener{
        void onCkeckChange(int pos);
    }


    public OnCkeckChangeListener getOnCkeckChangeListener() {
        return onCkeckChangeListener;
    }

    public void setOnCkeckChangeListener(OnCkeckChangeListener onCkeckChangeListener) {
        this.onCkeckChangeListener = onCkeckChangeListener;
    }



}
