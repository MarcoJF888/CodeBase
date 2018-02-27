package com.zjf.myself.codebase.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.zjf.myself.codebase.util.DataUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 打造ListView的万能适配器
 */
public abstract class CommonAdaper<T> extends BaseAdapter {
    public Context context;
    private List<T> list;
    private LayoutInflater inflater;
    private int itemLayoutId;

    private OnItemClickListener onItemClickListener;

    public CommonAdaper(Context context, List<T> list, int itemLayoutId) {
        this.context = context;
        this.list = new ArrayList<T>();
        if(!DataUtil.listIsNull(list))
            this.list.addAll(list);
        this.itemLayoutId = itemLayoutId;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public T getItem(int position) {
        return list.get(position);
    }

    public List<T> getAll(){
        return list;
    }

    public void addItem(T t, boolean isUpdateUI) {
        this.list.add(t);
        if (isUpdateUI) {
            notifyDataSetChanged();
        }
    }

    public void addList(List<T> list, boolean isUpdateUI) {
        this.list.addAll(list);
        if (isUpdateUI) {
            notifyDataSetChanged();
        }
    }

    public void clearAll() {
        this.list.clear();
    }

    public void clearAll(boolean isUpdateUI) {
        this.list.clear();
        if (isUpdateUI) {
            notifyDataSetChanged();
        }
    }

    public boolean removedItem(T t, boolean isUpdateUI){
        if(this.list.contains(t)) {
            boolean result= this.list.remove(t);
            if (isUpdateUI) {
                notifyDataSetChanged();
            }
            return  result;
        }

        return false;
    }


    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = getViewHolder(position,convertView,parent);
        // convert(holder,getItem(position));
        convert(holder,getItem(position),position);
        return holder.getConvertView();
    }

    public  int getLayoutId(){
        return  itemLayoutId;
    }

    // public abstract void convert(ViewHolder holder,T item);
    public abstract void convert(ViewHolder holder,T item,int position);

    public ViewHolder getViewHolder(int position,View convertView,ViewGroup parent){
        return ViewHolder.get(context,convertView,parent,getLayoutId(),position);
    }

    public interface OnItemClickListener{
        void onItemClick(int position,View convertView,ViewHolder holder);
    }

}
