package com.zjf.myself.codebase.activity.UILibrary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.adapter.CommonAdaper;
import com.zjf.myself.codebase.adapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/23.
 */
//┏┓　　　┏┓
//┏┛┻━━━┛┻┓
//┃　　　　　　　┃ 　
//┃　　　━　　　┃
//┃　┳┛　┗┳　┃
//┃　　　　　　　┃
//┃　　　┻　　　┃
//┃　　　　　　　┃
//┗━┓　　　┏━┛
//  ┃　　　┃   神兽保佑　　　　　　　　
//  ┃　　　┃   代码无BUG！
//  ┃　　　┗━━━┓
//  ┃　　　　　　　┣┓
//  ┃　　　　　　　┏┛
//  ┗┓┓┏━┳┓┏┛
//    ┃┫┫　┃┫┫
//    ┗┻┛　┗┻┛
public class GridViewAct extends Activity {
    private GridView mGridView;
    private MGridViewAdapter mGridViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.win_grid_view);

        mGridView = (GridView) findViewById(R.id.gridMenuView);
        mGridViewAdapter=new MGridViewAdapter(this,null,R.layout.item_grid_view);
        mGridView.setAdapter(mGridViewAdapter);
        mGridView.setOnItemClickListener(onItemClickListener);

        List<MenuItem> menuItemList=new ArrayList<MenuItem>();
        menuItemList.add(new MenuItem("血压检测",R.mipmap.icon_fun_blood,null));
        menuItemList.add(new MenuItem("心率检测",R.mipmap.icon_fun_heart,null));
        menuItemList.add(new MenuItem("睡眠分析",R.mipmap.icon_fun_sleep,null));

        menuItemList.add(new MenuItem(MenuItem.TYPE_CALL_DEV,"双向通话",R.mipmap.icon_fun_call,null));
        menuItemList.add(new MenuItem("实时定位",R.mipmap.icon_fun_loc,null));
        menuItemList.add(new MenuItem("历史轨迹",R.mipmap.icon_fun_history_track,null));

        menuItemList.add(new MenuItem("远程关爱",R.mipmap.icon_fun_remote_care,null));
        menuItemList.add(new MenuItem("健康运动",R.mipmap.icon_fun_sport,null));
        menuItemList.add(new MenuItem("久坐提醒",R.mipmap.icon_fun_sit,null));
        mGridViewAdapter.addList(menuItemList,true);
    }


    private AdapterView.OnItemClickListener onItemClickListener=new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            MenuItem item= (MenuItem) adapterView.getAdapter().getItem(i);

//            if(item.getType()==MenuItem.TYPE_CALL_DEV){
//                Intent intent = new Intent(Intent.ACTION_DIAL);
//                intent.setData(Uri.parse("tel:"+10086));
//                item.setIt(intent);
//            }
//
//            Intent intent = new Intent(item.getIt());
//            startActivity(intent);

            Toast.makeText(GridViewAct.this,"您点击了第"+i+"个",Toast.LENGTH_SHORT).show();
        }
    };

    static class MGridViewAdapter extends CommonAdaper {

        public MGridViewAdapter(Context context, List list, int itemLayoutId) {
            super(context, list, itemLayoutId);
        }

        @Override
        public void convert(ViewHolder holder, Object item, int position) {
            MenuItem menuItem= (MenuItem) item;
            holder.setText(R.id.txtMainmenu,menuItem.getName());
            holder.setImageResource(R.id.imgMainmenu,menuItem.getDrawableId());
        }
    }

    static class MenuItem{
        public static final int TYPE_CALL_DEV=3;

        int drawableId;
        String name;
        Intent it;
        int type;

        public MenuItem() {
        }

        public MenuItem(String name,int drawableId, Intent it) {
            this.drawableId = drawableId;
            this.it = it;
            this.name = name;
        }

        public MenuItem(int type,String name,int drawableId, Intent it) {
            this.drawableId = drawableId;
            this.it = it;
            this.name = name;
            this.type=type;
        }

        public int getDrawableId() {
            return drawableId;
        }

        public void setDrawableId(int drawableId) {
            this.drawableId = drawableId;
        }

        public Intent getIt() {
            return it;
        }

        public void setIt(Intent it) {
            this.it = it;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}

