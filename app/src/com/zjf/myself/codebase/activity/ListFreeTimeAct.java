package com.zjf.myself.codebase.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.zjf.myself.codebase.R;

import com.zjf.myself.codebase.activity.FreeTimeProjectList.NewsCompatibleMainAct;
import com.zjf.myself.codebase.thirdparty.NewsCompatibleTest.NewsCompatibleAct;
import com.zjf.myself.codebase.activity.FreeTimeProjectList.NewsUIPageListViewAct;
import com.zjf.myself.codebase.activity.FreeTimeProjectList.NewsUIpageRecycleViewGridAct;
import com.zjf.myself.codebase.activity.FreeTimeProjectList.VideoPlayerAct;
import com.zjf.myself.codebase.activity.FreeTimeProjectList.VolleyAndJsonTestAct;
import com.zjf.myself.codebase.adapter.CommonAdaper;
import com.zjf.myself.codebase.adapter.ViewHolder;
import com.zjf.myself.codebase.model.BasicListViewInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     author : ZouJianFeng
 *     e-mail :
 *     time   : 2017/05/06
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class ListFreeTimeAct extends BaseAct{

    private ListView projectPageList;
    private BasicListViewAdapter adaper;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.win_exercise_library_list);

        title = (TextView)findViewById(R.id.title);
        title.setText("空闲练习");

        projectPageList = (ListView) findViewById(R.id.lvExerciseLibrary);
        adaper=new BasicListViewAdapter(this,null,R.layout.item_main);
        projectPageList.setAdapter(adaper);
        projectPageList.setOnItemClickListener(onItemClickListener);

        List<BasicListViewInfo> menuItemList=new ArrayList<BasicListViewInfo>();
        menuItemList.add(new BasicListViewInfo(0,"快递查询小工具","",new Intent(this, VolleyAndJsonTestAct.class)));
        menuItemList.add(new BasicListViewInfo(0,"图片异步加载测试","",new Intent(this, NewsUIPageListViewAct.class)));
        menuItemList.add(new BasicListViewInfo(0,"RecycleView加载测试","",new Intent(this, NewsUIpageRecycleViewGridAct.class)));
        menuItemList.add(new BasicListViewInfo(0,"VideoPlayer","",new Intent(this, VideoPlayerAct.class)));
        menuItemList.add(new BasicListViewInfo(0,"新闻页面兼容平板测试","",new Intent(this, NewsCompatibleMainAct.class)));



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
