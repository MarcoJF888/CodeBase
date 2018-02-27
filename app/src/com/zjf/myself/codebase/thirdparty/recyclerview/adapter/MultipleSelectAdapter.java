package com.zjf.myself.codebase.thirdparty.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.zjf.myself.codebase.util.DataUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 多选列表
 */

public abstract class MultipleSelectAdapter<T> extends CommonAdapter{



    private List<T> selectedList=new ArrayList<T>();

    private OnCkeckChangeListener onCkeckChangeListener;

    public MultipleSelectAdapter(Context context, int layoutId, List datas) {
        super(context, layoutId, datas);

        setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                boolean isSelect=true;
                for (T t:selectedList) {
                    if(t==getItem(position)){
                        isSelect=false;
                        selectedList.remove(t);
                        break;
                    }
                }

                if(isSelect){
                    selectedList.add((T) getItem(position));
                }

                notifyDataSetChanged();
                if(onCkeckChangeListener!=null){
                    onCkeckChangeListener.onCkeckChange(position,isSelect);
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
        ckeckView.setBackgroundResource(getCkeckViewInfo()[2]);
        if(!DataUtil.listIsNull(selectedList)){

            for (T t:selectedList) {
                if(getItem(position)==t){
                    ckeckView.setBackgroundResource(getCkeckViewInfo()[1]);
                    break;
                }
            }
        }


        convert(holder,position);
    }

    public abstract void convert (ViewHolder holder, int pos);

    public List<T> getSelectedList() {
        return selectedList;
    }

    public void setSelect(T t){
        if(!this.selectedList.contains(t)){
            this.selectedList.add(t);
        }
    }

    public void setSelectedList(List<T> selectedList) {
        for (T t:selectedList){
            if(this.selectedList.contains(t)){
                continue;
            }else {
                this.selectedList.add(t);
            }
        }
    }

    //长度为3 0：viewId 1:defBgId 2:selectBgId
    public abstract int[] getCkeckViewInfo();


    public interface OnCkeckChangeListener{
        void onCkeckChange(int pos, boolean isCheck);
    }


    public OnCkeckChangeListener getOnCkeckChangeListener() {
        return onCkeckChangeListener;
    }

    public void setOnCkeckChangeListener(OnCkeckChangeListener onCkeckChangeListener) {
        this.onCkeckChangeListener = onCkeckChangeListener;
    }



}
