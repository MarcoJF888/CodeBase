package com.zjf.myself.codebase.activity.UILibrary;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

//import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.activity.BaseAct;
import com.zjf.myself.codebase.thirdparty.SwipeMenuRecyclerView.Data;
import com.zjf.myself.codebase.thirdparty.SwipeMenuRecyclerView.DividerItemDecoration;
import com.zjf.myself.codebase.thirdparty.SwipeMenuRecyclerView.Repository;
import com.zjf.myself.codebase.thirdparty.SwipeMenuRecyclerView.SwipeItemLayout;
import com.zjf.myself.codebase.thirdparty.SwipeMenuRecyclerView.Type;

import java.util.List;

public class SwipeMenuRecycleViewAct extends BaseAct {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_swipemenu_recycleview);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        recyclerView.addItemDecoration(dividerItemDecoration);

        DemoAdapter adapter = new DemoAdapter(new Repository().fakeDate(), mItemTouchListener);
        recyclerView.setAdapter(adapter);
    }

    ItemTouchListener mItemTouchListener = new ItemTouchListener() {
        @Override
        public void onItemClick(String str) {
            Toast.makeText(SwipeMenuRecycleViewAct.this,str, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onLeftMenuClick(String str) {
            Toast.makeText(SwipeMenuRecycleViewAct.this,str, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRightMenuClick(String str) {
            Toast.makeText(SwipeMenuRecycleViewAct.this,str, Toast.LENGTH_SHORT).show();
        }
    };

    interface ItemTouchListener {
        void onItemClick(String str);

        void onLeftMenuClick(String str);

        void onRightMenuClick(String str);
    }

    private static class DemoAdapter extends RecyclerView.Adapter<SimpleViewHolder> {

        private ItemTouchListener mItemTouchListener;
        private List<Data> mData;

        DemoAdapter(List<Data> data, ItemTouchListener itemTouchListener) {
            this.mData = data;
            this.mItemTouchListener = itemTouchListener;
        }

        @Override
        public int getItemViewType(int position) {
            return mData.get(position).type;
        }


        @Override
        public int getItemCount() {
            return mData.size();
        }

        @Override
        public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            @LayoutRes
            int layout;

            switch (viewType) {
                case Type.LEFT_MENU:
                    layout = R.layout.item_left_menu;
                    break;
                case Type.RIGHT_MENU:
                    layout = R.layout.item_right_menu;
                    break;
                case Type.LEFT_AND_RIGHT_MENU:
                    layout = R.layout.item_left_and_right_menu;
                    break;
                case Type.LEFT_LONG_MENU:
                    layout = R.layout.item_left_long_menu;
                    break;
                case Type.RIGHT_LONG_MENU:
                    layout = R.layout.item_right_long_menu;
                    break;
                case Type.LEFT_AND_RIGHT_LONG_MENU:
                    layout = R.layout.item_left_and_right_long_menu;
                    break;
                default:
                    layout = R.layout.item_left_menu;
                    break;
            }
            View rootView = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
            return new SimpleViewHolder(rootView);
        }

        @Override
        public void onBindViewHolder(final SimpleViewHolder holder, int position) {
            holder.mContent.setText(mData.get(position).content.concat(" " + position));
            if (mItemTouchListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mItemTouchListener.onItemClick(holder.mContent.getText().toString());
                    }
                });

                if (holder.mLeftMenu != null) {
                    holder.mLeftMenu.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mItemTouchListener.onLeftMenuClick("left " + holder.getAdapterPosition());
                            holder.mSwipeItemLayout.close();
                        }
                    });
                }

                if (holder.mRightMenu != null) {
                    holder.mRightMenu.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mItemTouchListener.onRightMenuClick("right " + holder.getAdapterPosition());
                            holder.mSwipeItemLayout.close();
                        }
                    });
                }
            }
        }

    }

    private static class SimpleViewHolder extends RecyclerView.ViewHolder {

        private final View mLeftMenu;
        private final View mRightMenu;
        private final TextView mContent;
        private final SwipeItemLayout mSwipeItemLayout;

        SimpleViewHolder(View itemView) {
            super(itemView);
            mSwipeItemLayout = (SwipeItemLayout) itemView.findViewById(R.id.swipe_layout);
            mContent = (TextView) itemView.findViewById(R.id.tv_content);
            mLeftMenu = itemView.findViewById(R.id.left_menu);
            mRightMenu = itemView.findViewById(R.id.right_menu);
        }
    }
}
