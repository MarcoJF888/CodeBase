package com.zjf.myself.codebase.activity.UILibrary;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.adapter.CommonAdaper;
import com.zjf.myself.codebase.adapter.ViewHolder;
import com.zjf.myself.codebase.model.BasicListViewInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/22.
 */

public class BasicListviewAct extends Activity {

    private ListView projectPageList;
    private BasicListViewAdapter adaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.win_basic_listview_demo);

        projectPageList = (ListView) findViewById(R.id.BasicListviewDemo);
        adaper=new BasicListViewAdapter(this,null,R.layout.item_basic_listview_demo);
        projectPageList.setAdapter(adaper);
        projectPageList.setOnItemClickListener(onItemClickListener);

        List<BasicListViewInfo> menuItemList=new ArrayList<BasicListViewInfo>();
        menuItemList.add(new BasicListViewInfo(R.mipmap.icon_setting_callreminder,"测试左","测试右",null));
        menuItemList.add(new BasicListViewInfo(R.mipmap.icon_setting_dongwei,"测试左1","测试右1",null));
        menuItemList.add(new BasicListViewInfo(R.mipmap.icon_setting_lianxu,"测试左2","测试右2",null));
        menuItemList.add(new BasicListViewInfo(R.mipmap.icon_setting_qcharge,"测试左3","测试右3",null));

        menuItemList.add(new BasicListViewInfo(R.mipmap.icon_setting_callreminder,"测试左","测试右",null));
        menuItemList.add(new BasicListViewInfo(R.mipmap.icon_setting_dongwei,"测试左1","测试右1",null));
        menuItemList.add(new BasicListViewInfo(R.mipmap.icon_setting_lianxu,"测试左2","测试右2",null));
        menuItemList.add(new BasicListViewInfo(R.mipmap.icon_setting_qcharge,"测试左3","测试右3",null));

        menuItemList.add(new BasicListViewInfo(R.mipmap.icon_setting_callreminder,"测试左","测试右",null));
        menuItemList.add(new BasicListViewInfo(R.mipmap.icon_setting_dongwei,"测试左1","测试右1",null));
        menuItemList.add(new BasicListViewInfo(R.mipmap.icon_setting_lianxu,"测试左2","测试右2",null));
        menuItemList.add(new BasicListViewInfo(R.mipmap.icon_setting_qcharge,"测试左3","测试右3",null));

        menuItemList.add(new BasicListViewInfo(R.mipmap.icon_setting_callreminder,"测试左","测试右",null));
        menuItemList.add(new BasicListViewInfo(R.mipmap.icon_setting_dongwei,"测试左1","测试右1",null));
        menuItemList.add(new BasicListViewInfo(R.mipmap.icon_setting_lianxu,"测试左2","测试右2",null));
        menuItemList.add(new BasicListViewInfo(R.mipmap.icon_setting_qcharge,"测试左3","测试右3",null));

        menuItemList.add(new BasicListViewInfo(R.mipmap.icon_setting_callreminder,"测试左","测试右",null));
        menuItemList.add(new BasicListViewInfo(R.mipmap.icon_setting_dongwei,"测试左1","测试右1",null));
        menuItemList.add(new BasicListViewInfo(R.mipmap.icon_setting_lianxu,"测试左2","测试右2",null));
        menuItemList.add(new BasicListViewInfo(R.mipmap.icon_setting_qcharge,"测试左3","测试右3",null));


        adaper.addList(menuItemList,true);
    }

    private AdapterView.OnItemClickListener onItemClickListener=new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            BasicListViewInfo item= (BasicListViewInfo) adapterView.getAdapter().getItem(i);

//            Intent intent = new Intent(item.getIt());
//            startActivity(intent);
            Toast.makeText(BasicListviewAct.this,"您点击了"+item.getTxtLeft(),Toast.LENGTH_SHORT).show();
        }
    };


    static class BasicListViewAdapter extends CommonAdaper{

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

