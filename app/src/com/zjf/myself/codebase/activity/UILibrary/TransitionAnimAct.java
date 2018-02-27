package com.zjf.myself.codebase.activity.UILibrary;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.activity.BaseAct;
import com.zjf.myself.codebase.thirdparty.TransitionAnim.EasyTransition;
import com.zjf.myself.codebase.thirdparty.TransitionAnim.EasyTransitionOptions;

import java.util.ArrayList;

/**
 * <pre>
 *     author : ZouJianFeng
 *     e-mail : 1434690833@qq.com
 *     time   : 2017/05/26
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class TransitionAnimAct extends BaseAct{
    private ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_transition_anim);

        list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("name" + i);
        }
        ListView listView = (ListView) findViewById(R.id.lv);
        listView.setAdapter(new MyAdapter());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // ready for intent
                Intent intent = new Intent(TransitionAnimAct.this, DetailActivity.class);
                intent.putExtra("name", list.get(position));

                // ready for transition options
                EasyTransitionOptions options = EasyTransitionOptions.makeTransitionOptions(
                        TransitionAnimAct.this,
                        view.findViewById(R.id.iv_icon),
                        view.findViewById(R.id.tv_name),
                        findViewById(R.id.v_top_card));

                // start transition
                EasyTransition.startActivity(intent, options);
            }
        });
    }


    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            int count = 0;
            if (null != list)
                count = list.size();
            return count;
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = null;
            if (null != convertView) {
                view = convertView;
            } else {
                view = LayoutInflater.from(TransitionAnimAct.this).inflate(R.layout.item_main_list, null, false);
            }
            TextView tvName = (TextView) view.findViewById(R.id.tv_name);
            tvName.setText(list.get(position));
            return view;
        }
    }
}
