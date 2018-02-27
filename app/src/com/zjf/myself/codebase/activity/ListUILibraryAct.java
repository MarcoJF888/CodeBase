package com.zjf.myself.codebase.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.activity.UILibrary.AnimProgressBarAct;
import com.zjf.myself.codebase.activity.UILibrary.BannerAct;
import com.zjf.myself.codebase.activity.UILibrary.BaseTitleListViewAct;
import com.zjf.myself.codebase.activity.UILibrary.BasicListviewAct;
import com.zjf.myself.codebase.activity.UILibrary.BiLiBiLiSearchViewAct;
import com.zjf.myself.codebase.activity.UILibrary.ClickEffectAct;
import com.zjf.myself.codebase.activity.UILibrary.ContactsAct;
import com.zjf.myself.codebase.activity.UILibrary.ExpandableTextViewAndLayoutAct;
import com.zjf.myself.codebase.activity.UILibrary.ItemTouchHelperAct;
import com.zjf.myself.codebase.activity.UILibrary.KeyBoardAct;
import com.zjf.myself.codebase.activity.UILibrary.LaunchVideoAct;
import com.zjf.myself.codebase.activity.UILibrary.LinkagePopupWindowAct;
import com.zjf.myself.codebase.activity.UILibrary.ListPopupWindowAct;
import com.zjf.myself.codebase.activity.UILibrary.NavigationLeftAct;
import com.zjf.myself.codebase.activity.UILibrary.NavigationQQLeftAct;
import com.zjf.myself.codebase.activity.UILibrary.ObservableScrollViewAct;
import com.zjf.myself.codebase.activity.UILibrary.PaletteAct;
import com.zjf.myself.codebase.activity.UILibrary.PaletteAct2;
import com.zjf.myself.codebase.activity.UILibrary.ParallaxViewAct;
import com.zjf.myself.codebase.activity.UILibrary.PaymentKeyBoardAct;
import com.zjf.myself.codebase.activity.UILibrary.RecyclerVIewDecorationActOne;
import com.zjf.myself.codebase.activity.UILibrary.RecyclerVIewDecorationActThree;
import com.zjf.myself.codebase.activity.UILibrary.RecyclerVIewDecorationActTwo;
import com.zjf.myself.codebase.activity.UILibrary.RecyclerViewActivity;
import com.zjf.myself.codebase.activity.UILibrary.ScrollViewIndicatorAct;
import com.zjf.myself.codebase.activity.UILibrary.SearchViewAct;
import com.zjf.myself.codebase.activity.UILibrary.SwipeMenuAct;
import com.zjf.myself.codebase.activity.UILibrary.SwipeRefreshLayoutAct;
import com.zjf.myself.codebase.activity.UILibrary.TextInputLayoutAct;
import com.zjf.myself.codebase.activity.UILibrary.TextViewAnimAct;
import com.zjf.myself.codebase.activity.UILibrary.CalendarAct;
import com.zjf.myself.codebase.activity.UILibrary.CalendarAct1;
import com.zjf.myself.codebase.activity.UILibrary.CircleMenuAct;
import com.zjf.myself.codebase.activity.UILibrary.CircleProgressAct;
import com.zjf.myself.codebase.activity.UILibrary.CircularMenuAct;
import com.zjf.myself.codebase.activity.UILibrary.ExplosionAnimatorAct;
import com.zjf.myself.codebase.activity.UILibrary.GridViewAct;
import com.zjf.myself.codebase.activity.UILibrary.HelloChartsAct;
import com.zjf.myself.codebase.activity.UILibrary.JiangJiangLoadingAct;
import com.zjf.myself.codebase.activity.UILibrary.LineChartActivity;
import com.zjf.myself.codebase.activity.UILibrary.LineColumnDependencyAct;
import com.zjf.myself.codebase.activity.UILibrary.LoadingViewTestAct;
import com.zjf.myself.codebase.activity.UILibrary.MIUIAct;
import com.zjf.myself.codebase.activity.UILibrary.NowHwWeatherAct;
import com.zjf.myself.codebase.activity.UILibrary.NumberProgressBarAct;
import com.zjf.myself.codebase.activity.UILibrary.PieCharAct;
import com.zjf.myself.codebase.activity.UILibrary.PopupMenuAct;
import com.zjf.myself.codebase.activity.UILibrary.PreviewLineChartAct;
import com.zjf.myself.codebase.activity.UILibrary.FunctionHeartRateMonitorAct;
import com.zjf.myself.codebase.activity.UILibrary.PwdInputAct;
import com.zjf.myself.codebase.activity.UILibrary.RadioGroupAct;
import com.zjf.myself.codebase.activity.UILibrary.DeviceRecycleViewAct;

import com.zjf.myself.codebase.activity.UILibrary.SwipeMenuRecycleViewAct;
import com.zjf.myself.codebase.activity.UILibrary.TitleListViewAct;
import com.zjf.myself.codebase.activity.UILibrary.TransitionAnimAct;
import com.zjf.myself.codebase.activity.UILibrary.WaterFallRecyclerViewAct;
import com.zjf.myself.codebase.adapter.CommonAdaper;
import com.zjf.myself.codebase.adapter.ViewHolder;
import com.zjf.myself.codebase.model.BasicListViewInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/22.
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

public class ListUILibraryAct extends BaseAct implements AbsListView.OnScrollListener{

        private ListView lvUILibrary;

        private BasicListViewAdapter adaper;

        private int mScreenHeight;

        private TextView title;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.win_ui_library_list);

            mScreenHeight = getResources().getDisplayMetrics().heightPixels;

            title = (TextView)findViewById(R.id.title);


            lvUILibrary = (ListView) findViewById(R.id.lvUILibrary);
            adaper=new BasicListViewAdapter(this,null,R.layout.item_main);
            lvUILibrary.setAdapter(adaper);
            lvUILibrary.setOnItemClickListener(onItemClickListener);
            lvUILibrary.setOnScrollListener(this);

            List<BasicListViewInfo> menuItemList=new ArrayList<BasicListViewInfo>();


            menuItemList.add(new BasicListViewInfo(0,"LaunchVideo","开启视频页面",new Intent(this, LaunchVideoAct.class)));
            menuItemList.add(new BasicListViewInfo(0,"Contacts","通讯录",new Intent(this, ContactsAct.class)));
            menuItemList.add(new BasicListViewInfo(0,"ParallaxView","弹性布局",new Intent(this, ParallaxViewAct.class)));
            menuItemList.add(new BasicListViewInfo(0,"AnimProgressBar","自定义进度条",new Intent(this, AnimProgressBarAct.class)));

            menuItemList.add(new BasicListViewInfo(0,"Expandable","文字和布局伸缩",new Intent(this, ExpandableTextViewAndLayoutAct.class)));
            menuItemList.add(new BasicListViewInfo(0,"TextViewAnim","文字动画",new Intent(this, TextViewAnimAct.class)));
            menuItemList.add(new BasicListViewInfo(0,"ScrollViewIndicator","菜单布局联动",new Intent(this, ScrollViewIndicatorAct.class)));

            menuItemList.add(new BasicListViewInfo(0,"ListView","",new Intent(this, BasicListviewAct.class)));
            menuItemList.add(new BasicListViewInfo(0,"Title ListView","Exercise",new Intent(this, TitleListViewAct.class)));
            menuItemList.add(new BasicListViewInfo(0,"Title ListView2","Best",new Intent(this, BaseTitleListViewAct.class)));
            menuItemList.add(new BasicListViewInfo(0,"GridView","",new Intent(this, GridViewAct.class)));
            menuItemList.add(new BasicListViewInfo(0,"LoadingView","",new Intent(this, LoadingViewTestAct.class)));
            menuItemList.add(new BasicListViewInfo(0,"MIUI Clock","小米时钟",new Intent(this, MIUIAct.class)));
            menuItemList.add(new BasicListViewInfo(0,"Calendar","Exercise",new Intent(this, CalendarAct.class)));
            menuItemList.add(new BasicListViewInfo(0,"Calendar","Best",new Intent(this, CalendarAct1.class)));
            menuItemList.add(new BasicListViewInfo(0,"ExplosionAnimator","爆炸效果",new Intent(this, ExplosionAnimatorAct.class)));

            menuItemList.add(new BasicListViewInfo(0,"TextInputLayout","文字输入",new Intent(this, TextInputLayoutAct.class)));
            menuItemList.add(new BasicListViewInfo(0,"PwdInput","密码输入",new Intent(this, PwdInputAct.class)));
            menuItemList.add(new BasicListViewInfo(0,"KeyBoard","输入键盘",new Intent(this, KeyBoardAct.class)));
            menuItemList.add(new BasicListViewInfo(0,"PaymentKeyBoard","仿微信支付",new Intent(this, PaymentKeyBoardAct.class)));

            menuItemList.add(new BasicListViewInfo(0,"Banner","广告页",new Intent(this, BannerAct.class)));
            menuItemList.add(new BasicListViewInfo(0,"NetworkBanner","网络广告页",new Intent(this, FragmentParentAct.class)));

            menuItemList.add(new BasicListViewInfo(0,"Round Progress","圆进度条",new Intent(this, CircleProgressAct.class)));
            menuItemList.add(new BasicListViewInfo(0,"Round Progress2","圆进度条",new Intent(this, FunctionHeartRateMonitorAct.class)));
            menuItemList.add(new BasicListViewInfo(0,"NumberProgressBar","数字进度条",new Intent(this, NumberProgressBarAct.class)));

            menuItemList.add(new BasicListViewInfo(0,"PopupMenu","校准",new Intent(this, PopupMenuAct.class)));
            menuItemList.add(new BasicListViewInfo(0,"LinkagePopupWindowAct","联动",new Intent(this, LinkagePopupWindowAct.class)));
            menuItemList.add(new BasicListViewInfo(0,"ListPopupWindowAct","普通",new Intent(this, ListPopupWindowAct.class)));

            menuItemList.add(new BasicListViewInfo(0,"CircleMenu","圆环菜单",new Intent(this, CircleMenuAct.class)));
            menuItemList.add(new BasicListViewInfo(0,"RadioGroup","切换菜单",new Intent(this, RadioGroupAct.class)));
            menuItemList.add(new BasicListViewInfo(0,"Youku Menu","低仿优酷菜单",new Intent(this, CircularMenuAct.class)));

            menuItemList.add(new BasicListViewInfo(0,"QQSwipeMenu","仿QQ滑动点击",new Intent(this, SwipeMenuAct.class)));
            menuItemList.add(new BasicListViewInfo(0,"SwipeMenuRecycleView","滑动点击",new Intent(this, SwipeMenuRecycleViewAct.class)));
            menuItemList.add(new BasicListViewInfo(0,"TransverseRecycleView","手表列表",new Intent(this, DeviceRecycleViewAct.class)));
            menuItemList.add(new BasicListViewInfo(0,"RecycleViewFlow","瀑布流",new Intent(this, WaterFallRecyclerViewAct.class)));
            menuItemList.add(new BasicListViewInfo(0,"RecyclerView","各种View",new Intent(this, RecyclerViewActivity.class)));
            menuItemList.add(new BasicListViewInfo(0,"RecyclerVIewDec1","线",new Intent(this, RecyclerVIewDecorationActOne.class)));
            menuItemList.add(new BasicListViewInfo(0,"RecyclerVIewDec2","格(点击事件)",new Intent(this, RecyclerVIewDecorationActTwo.class)));
            menuItemList.add(new BasicListViewInfo(0,"RecyclerVIewDec3","字母",new Intent(this, RecyclerVIewDecorationActThree.class)));
            menuItemList.add(new BasicListViewInfo(0,"ItemTouchHelper","拖动换位置",new Intent(this, ItemTouchHelperAct.class)));

            menuItemList.add(new BasicListViewInfo(0,"Chart1","图表",new Intent(this, HelloChartsAct.class)));
            menuItemList.add(new BasicListViewInfo(0,"Chart2","图表",new Intent(this, LineColumnDependencyAct.class)));
            menuItemList.add(new BasicListViewInfo(0,"Chart3","图表",new Intent(this, PreviewLineChartAct.class)));
            menuItemList.add(new BasicListViewInfo(0,"Chart4","图表",new Intent(this, LineChartActivity.class)));
            menuItemList.add(new BasicListViewInfo(0,"Pie Chart","图表",new Intent(this, PieCharAct.class)));

            menuItemList.add(new BasicListViewInfo(0,"Navigation","侧边栏",new Intent(this, NavigationLeftAct.class)));
            menuItemList.add(new BasicListViewInfo(0,"NavigationQQ","QQ侧边栏",new Intent(this, NavigationQQLeftAct.class)));

            menuItemList.add(new BasicListViewInfo(0,"JiangJiang","",new Intent(this, JiangJiangLoadingAct.class)));
            menuItemList.add(new BasicListViewInfo(0,"NowHwWeather","天气控件",new Intent(this, NowHwWeatherAct.class)));
            menuItemList.add(new BasicListViewInfo(0,"ClickEffectAct","点击效果",new Intent(this, ClickEffectAct.class)));

            menuItemList.add(new BasicListViewInfo(0,"ObservableScrollView","上拉变色",new Intent(this, ObservableScrollViewAct.class)));
            menuItemList.add(new BasicListViewInfo(0,"TransitionAnim","过渡动画",new Intent(this, TransitionAnimAct.class)));
            menuItemList.add(new BasicListViewInfo(0,"SwipeRefreshLayout","下拉刷新",new Intent(this, SwipeRefreshLayoutAct.class)));

            menuItemList.add(new BasicListViewInfo(0,"SearchView","网易云本地搜索",new Intent(this, SearchViewAct.class)));
            menuItemList.add(new BasicListViewInfo(0,"SearchView","哔哩哔哩搜索",new Intent(this, BiLiBiLiSearchViewAct.class)));

            menuItemList.add(new BasicListViewInfo(0,"Palette","自动吸取颜色",new Intent(this, PaletteAct.class)));
            menuItemList.add(new BasicListViewInfo(0,"Palette2","自动吸取颜色示例",new Intent(this, PaletteAct2.class)));



            adaper.addList(menuItemList,true);
        }

    private AdapterView.OnItemClickListener onItemClickListener=new AdapterView.OnItemClickListener() {
        @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            BasicListViewInfo item= (BasicListViewInfo) adapterView.getAdapter().getItem(i);

            Intent intent = new Intent(item.getIt());
            startActivity(intent);
//            Toast.makeText(ListUILibraryAct.this,"您点击了"+item.getTxtLeft(),Toast.LENGTH_SHORT).show();
        }
    };

    static class BasicListViewAdapter extends CommonAdaper{

        public BasicListViewAdapter(Context context, List list, int itemLayoutId) {
            super(context, list, itemLayoutId);
        }

        @Override
        public void convert(ViewHolder holder, Object item, int position) {
                BasicListViewInfo listviewInfo= (BasicListViewInfo) item;
                holder.setImageResource(R.id.imgleft, listviewInfo.getImgLeft());
                holder.setText(R.id.txtLeft,listviewInfo.getTxtLeft());
                holder.setText(R.id.txtRight, listviewInfo.getTxtRight());
                int num = position +1;
                holder.setText(R.id.txtNum, num+".");
        }
    }



    //标题栏隐藏
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
}








