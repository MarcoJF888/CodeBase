package com.zjf.myself.codebase.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.activity.ExerciseLibrary.BreakPointTestAct;
import com.zjf.myself.codebase.activity.ExerciseLibrary.BuilderTestAct;
import com.zjf.myself.codebase.activity.ExerciseLibrary.CloseBackKeyTestAct;
import com.zjf.myself.codebase.activity.ExerciseLibrary.CustomViewAct;
import com.zjf.myself.codebase.activity.ExerciseLibrary.DataPutExtraAct;
import com.zjf.myself.codebase.activity.ExerciseLibrary.GenQrCodeAct;
import com.zjf.myself.codebase.activity.ExerciseLibrary.GlideImgTestAct;
import com.zjf.myself.codebase.activity.ExerciseLibrary.HttpURLConnectionTestAct;
import com.zjf.myself.codebase.activity.ExerciseLibrary.DataStorageAct;
import com.zjf.myself.codebase.activity.ExerciseLibrary.HttpURLConnectionTestMeAct;
import com.zjf.myself.codebase.activity.ExerciseLibrary.KotlinTestAct;
import com.zjf.myself.codebase.activity.ExerciseLibrary.LifeCycleAct;
import com.zjf.myself.codebase.activity.ExerciseLibrary.LocWordAct;
import com.zjf.myself.codebase.activity.ExerciseLibrary.MapTestAct;
import com.zjf.myself.codebase.activity.ExerciseLibrary.MultiThreadAct;
import com.zjf.myself.codebase.activity.ExerciseLibrary.MySQLDatabaseTestAct;
import com.zjf.myself.codebase.activity.ExerciseLibrary.PhoneNumAct;
import com.zjf.myself.codebase.activity.ExerciseLibrary.PngToGifAct;
import com.zjf.myself.codebase.activity.ExerciseLibrary.ScreenTestAct;
import com.zjf.myself.codebase.activity.ExerciseLibrary.SharedPreferencesTestAct;
import com.zjf.myself.codebase.activity.ExerciseLibrary.TXTFileStorageAct;
import com.zjf.myself.codebase.activity.ExerciseLibrary.VolleyAct;
import com.zjf.myself.codebase.adapter.CommonAdaper;
import com.zjf.myself.codebase.adapter.ViewHolder;
import com.zjf.myself.codebase.model.BasicListViewInfo;
import com.zjf.myself.codebase.util.AppLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/22.
 */

public class ListExerciseLibraryAct extends BaseAct implements AbsListView.OnScrollListener{

    private ListView projectPageList;
    private BasicListViewAdapter adaper;
    private TextView title;
    private int mScreenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.win_exercise_library_list);

        title = (TextView)findViewById(R.id.title);
        title.setText("日常学习");


        projectPageList = (ListView) findViewById(R.id.lvExerciseLibrary);
        adaper=new BasicListViewAdapter(this,null,R.layout.item_main);
        projectPageList.setAdapter(adaper);
        projectPageList.setOnItemClickListener(onItemClickListener);

        mScreenHeight = getResources().getDisplayMetrics().heightPixels;
        projectPageList.setOnScrollListener(this);

        List<BasicListViewInfo> menuItemList=new ArrayList<BasicListViewInfo>();

        menuItemList.add(new BasicListViewInfo(0,"Builder练习","",new Intent(this, BuilderTestAct.class)));
        menuItemList.add(new BasicListViewInfo(0,"自定义View","",new Intent(this, CustomViewAct.class)));
        menuItemList.add(new BasicListViewInfo(0,"KotlinTest","",new Intent(this, KotlinTestAct.class)));
        menuItemList.add(new BasicListViewInfo(0,"数据库本地存储测试","",new Intent(this, DataStorageAct.class)));
        menuItemList.add(new BasicListViewInfo(0,"简单的创建SQL数据库测试","",new Intent(this, MySQLDatabaseTestAct.class)));
        menuItemList.add(new BasicListViewInfo(0,"数据本地存储测试","",new Intent(this, SharedPreferencesTestAct.class)));
        menuItemList.add(new BasicListViewInfo(0,"TXT文件存储","",new Intent(this, TXTFileStorageAct.class)));

        menuItemList.add(new BasicListViewInfo(0,"数据传递练习","",new Intent(this, DataPutExtraAct.class)));
        menuItemList.add(new BasicListViewInfo(0,"多线程基础","",new Intent(this, MultiThreadAct.class)));
        menuItemList.add(new BasicListViewInfo(0,"HttpURLConnection","",new Intent(this, HttpURLConnectionTestAct.class)));
        menuItemList.add(new BasicListViewInfo(0,"自定义图片HttpURLConnection","",new Intent(this, HttpURLConnectionTestMeAct.class)));

        menuItemList.add(new BasicListViewInfo(0,"简单生命周期示例","",new Intent(this, LifeCycleAct.class)));

        menuItemList.add(new BasicListViewInfo(0,"手机号码正则判断","",new Intent(this, PhoneNumAct.class)));
        menuItemList.add(new BasicListViewInfo(0,"地图定位测试","",new Intent(this, LocWordAct.class)));

        menuItemList.add(new BasicListViewInfo(0,"关闭物理返回键测试","",new Intent(this, CloseBackKeyTestAct.class)));
        menuItemList.add(new BasicListViewInfo(0,"断点调试测试学习","",new Intent(this, BreakPointTestAct.class)));
        menuItemList.add(new BasicListViewInfo(0,"Map学习","",new Intent(this, MapTestAct.class)));
        menuItemList.add(new BasicListViewInfo(0,"屏幕学习","",new Intent(this, ScreenTestAct.class)));
        menuItemList.add(new BasicListViewInfo(0,"Glide加载图片缓存练习","",new Intent(this, GlideImgTestAct.class)));
        menuItemList.add(new BasicListViewInfo(0,"png帧动画","",new Intent(this, PngToGifAct.class)));
        menuItemList.add(new BasicListViewInfo(0,"生成二维码","",new Intent(this, GenQrCodeAct.class)));
        menuItemList.add(new BasicListViewInfo(0,"Volley","",new Intent(this, VolleyAct.class)));

        adaper.addList(menuItemList,true);

        AppLog.e("start onCreate~~~1");
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
            int num = position +1;
            holder.setText(R.id.txtNum, num+".");
        }
    }


    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        View childAt = view.getChildAt(0);
        if (childAt == null)
            return;
        int scrollY = firstVisibleItem * childAt.getHeight() - childAt.getTop();
        if (scrollY <= (mScreenHeight / 3f)) {
            float alpha = 1f - (scrollY / (mScreenHeight / 3f));
            title.setAlpha(alpha);
        }
    }



    @Override
    protected void onStart() {
        super.onStart();
        AppLog.e("start onStart~~~1");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//            editView.setText(mString);
        AppLog.e("start onRestart~~~1");
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppLog.e("start onResume~~~1");
    }

    @Override
    public void onPause() {
        super.onPause();
//            mString = editView.getText().toString();
        AppLog.e("start onPause~~~1");
    }

    @Override
    protected void onStop() {
        super.onStop();
        AppLog.e("start onStop~~~1");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppLog.e("start onDestroy~~~1");
    }

}
