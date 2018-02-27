package com.zjf.myself.codebase.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.model.DiscoverInfo;

import java.util.List;


public class DiscoverRecycleViewAdapter extends RecyclerView.Adapter<DiscoverRecycleViewAdapter.MyViewHolder>
        implements View.OnClickListener{

    private List<DiscoverInfo> mData;
    private OnItemClickListener mOnItemClickListener = null;

    public DiscoverRecycleViewAdapter(List<DiscoverInfo> data) {
        mData = data;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(parent.getContext(), R.layout.item_discover_fragment, null);
        itemView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        itemView.setOnClickListener(this);
        TextView txtContent = (TextView) itemView.findViewById(R.id.txtContent);
        txtContent.setOnClickListener(this);
        TextView btnText = (TextView) itemView.findViewById(R.id.btnText);
        btnText.setOnClickListener(this);
        return new MyViewHolder(itemView);
    }

   @Override
   public void onClick(View v){
       if (mOnItemClickListener != null) {
           //注意这里使用getTag方法获取position
           mOnItemClickListener.onItemClick(v,(int)v.getTag());
       }
   }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public void setOnItemClickListener1(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.menuName.setText(mData.get(position).getMenuName());
        holder.imgTitle.setImageResource(mData.get(position).getImgID());
        holder.txtContent.setText(mData.get(position).getMainContent());
        holder.txtSecondContent.setText(mData.get(position).getSecondContent());
        holder.btnText.setText(mData.get(position).getBtnText());


        holder.itemView.setTag(position);
        holder.txtContent.setTag(position);
        holder.btnText.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView menuName;
        ImageView imgTitle;
        TextView txtContent;
        TextView txtSecondContent;
        TextView btnText;


        public MyViewHolder(View itemView) {
            super(itemView);
            menuName = (TextView) itemView.findViewById(R.id.txtTitle);
            imgTitle = (ImageView) itemView.findViewById(R.id.imgTitle);
            txtContent = (TextView) itemView.findViewById(R.id.txtContent);
            txtSecondContent = (TextView) itemView.findViewById(R.id.txtSecondContent);
            btnText = (TextView) itemView.findViewById(R.id.btnText);
        }
    }

    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }



}
