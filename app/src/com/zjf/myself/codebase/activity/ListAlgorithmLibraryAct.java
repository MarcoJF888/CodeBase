package com.zjf.myself.codebase.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.activity.AlgorithmList.DigitalGapAct;
import com.zjf.myself.codebase.activity.AlgorithmList.ListSortAct;
import com.zjf.myself.codebase.activity.AlgorithmList.Person1000Act;
import com.zjf.myself.codebase.activity.AlgorithmList.WeekStepSumAct;
import com.zjf.myself.codebase.adapter.CommonAdaper;
import com.zjf.myself.codebase.adapter.ViewHolder;
import com.zjf.myself.codebase.model.BasicListViewInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     author : ZouJianFeng
 *     e-mail :
 *     time   : 2017/05/16
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class ListAlgorithmLibraryAct extends BaseAct{

    private ListView projectPageList;
    private BasicListViewAdapter adaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.win_exercise_library_list);

        projectPageList = (ListView) findViewById(R.id.lvExerciseLibrary);
        adaper=new BasicListViewAdapter(this,null,R.layout.item_main);
        projectPageList.setAdapter(adaper);
        projectPageList.setOnItemClickListener(onItemClickListener);

        List<BasicListViewInfo> menuItemList=new ArrayList<BasicListViewInfo>();
        menuItemList.add(new BasicListViewInfo(0,"本周步数","",new Intent(this,WeekStepSumAct.class)));
        menuItemList.add(new BasicListViewInfo(0,"1000人练习","",new Intent(this,Person1000Act.class)));
        menuItemList.add(new BasicListViewInfo(0,"数字间隔","",new Intent(this,DigitalGapAct.class)));
        menuItemList.add(new BasicListViewInfo(0,"集合排序","",new Intent(this,ListSortAct.class)));


        adaper.addList(menuItemList,true);
    }

    private AdapterView.OnItemClickListener onItemClickListener=new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            BasicListViewInfo item= (BasicListViewInfo) adapterView.getAdapter().getItem(i);
            Intent intent = new Intent(item.getIt());
            startActivity(intent);
//            Toast.makeText(ListExerciseLibraryAct.this,"您点击了"+item.getTxtLeft(),Toast.LENGTH_SHORT).show();
        }
    };

    static class BasicListViewAdapter extends CommonAdaper {

        public BasicListViewAdapter(Context context, List list, int itemLayoutId) {
            super(context, list, itemLayoutId);
        }

        @Override
        public void convert(ViewHolder holder, Object item, int position) {
            BasicListViewInfo menuItem= (BasicListViewInfo) item;
            holder.setImageResource(R.id.imgleft, menuItem.getImgLeft());
            holder.setText(R.id.txtLeft,menuItem.getTxtLeft());
            holder.setText(R.id.txtRight, menuItem.getTxtRight());
        }
    }
}

