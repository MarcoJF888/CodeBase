package com.zjf.myself.codebase.activity.UILibrary;

/**
 * <pre>
 *     author : ZouJianFeng
 *     e-mail :
 *     time   : 2017/04/19
 *     desc   :
 *     version: 1.0
 * </pre>
 */
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.activity.BaseAct;
import com.zjf.myself.codebase.thirdparty.linkagePopupWindow.RootListViewAdapter;
import com.zjf.myself.codebase.thirdparty.linkagePopupWindow.SubListViewAdapter;
import com.zjf.myself.codebase.util.AppLog;
import com.zjf.myself.codebase.util.UIUtils;
import com.zjf.myself.codebase.view.HintPopupWindow;

import java.util.ArrayList;

public class LinkagePopupWindowAct extends BaseAct implements OnClickListener {
    //普通popwindow
    private HintPopupWindow hintPopupWindow;
    private RelativeLayout layoutTest;

    private RootListViewAdapter adapter;
    private SubListViewAdapter subAdapter;
    private Button showPopBtn,btn2,btn3;
    private ImageView imgPoint;

    /**
     * popupwindow
     *
     */
    private PopupWindow mPopupWindow;

    /**
     * 二级菜单的根目录
     */
    private ListView rootListView;

    /**
     * 根目录的节点
     */
    private String[] roots = new String[] { "2017.4.16", "2017.4.17", "2017.4.18","2017.4.19","2017.4.20","2017.4.21","2017.4.22","2017.4.23"};

    /**
     * 子目录节点
     */
    private String[][] sub_items = new String[][] {
            new String[] { "11.33", "12.33", "15.25", "18.39", "19.38","11.33", "12.33", "15.25", "18.39", "19.38" },
            new String[] { "05.35", "04.38", "09.45", "10.25", "11.58"},
            new String[] { "00.25", "00.39", "05.37", "11.25", "15.88", "00.39", "05.37" },
            new String[] { "05.35", "15.88", "00.39", "05.37", "11.25", "15.88","04.38", "09.45", "10.25" },
            new String[] { "11.33", "12.33", "15.25", "18.39", "19.38","11.33", "12.33", "15.25", "18.39", "19.38" },
            new String[] { "05.35", "04.38", "09.45", "10.25", "11.58","04.38", "09.45", "10.25", "11.58", "09.45", "10.25", "11.58"},
            new String[] { "00.25", "00.39", "05.37", "11.25", "15.88", "00.39", "05.37", "11.25", "15.88", "09.45", "10.25", "11.58" ,"09.45", "10.25", "11.58" ,"09.45", "10.25", "11.58" },
            new String[] { "05.35", "15.88", "00.39", "05.37", "11.25", "15.88","04.38",  "09.45", "10.25", "11.58" }};
    private ListView subListView;

    /**
     * 弹出的popupWindow布局
     */
    private LinearLayout popupLayout;

    /**
     * 子目录的布局
     */
    private FrameLayout subLayout;

    /**
     * 根目录被选中的节点
     */
    private int selectedPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_linkage_popwindow);

        showPopBtn = (Button) findViewById(R.id.btn1);
        showPopBtn.setOnClickListener(this);

        btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(this);

        initPopupWindow();
        layoutTest = (RelativeLayout) findViewById(R.id.layoutTest);
        btn3 = (Button) findViewById(R.id.btn3);
        btn3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //弹出选项弹窗
                hintPopupWindow.showPopupWindow(layoutTest);
            }
        });

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btn1:
                showPopBtn(UIUtils.getScreenWidth(LinkagePopupWindowAct.this), UIUtils.getScreenHeight(LinkagePopupWindowAct.this));
                adapter.setSelectedPosition(0);
                subLayout.setVisibility(View.VISIBLE);
                subAdapter = new SubListViewAdapter(LinkagePopupWindowAct.this, sub_items, 0);
                subListView.setAdapter(subAdapter);
                break;

            case R.id.btn2:
                showPopBtn2(UIUtils.getScreenWidth(LinkagePopupWindowAct.this), UIUtils.getScreenHeight(LinkagePopupWindowAct.this));
//                adapter.setSelectedPosition(0);
//                subLayout.setVisibility(View.VISIBLE);
                subAdapter = new SubListViewAdapter(LinkagePopupWindowAct.this, sub_items, 0);
                subListView.setAdapter(subAdapter);

                    break;
                default:
                    break;
            }
        }


    private void initPopupWindow(){
        //下面的操作是初始化弹出数据
        ArrayList<String> strList = new ArrayList<>();
        strList.add("1");
        strList.add("2");
        strList.add("3");
        strList.add("4");
        strList.add("5");
        strList.add("6");
        strList.add("7");
        strList.add("8");
        strList.add("9");
        strList.add("10");
        strList.add("11");
        strList.add("12");

        ArrayList<View.OnClickListener> clickList = new ArrayList<>();
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LinkagePopupWindowAct.this, "点击事件触发", Toast.LENGTH_SHORT).show();
                hintPopupWindow.dismissPopupWindow();
            }
        };
        AppLog.d( strList.size()+"==SIZE");
        for (int i=0; i<strList.size();i++){
            clickList.add(clickListener);
        }

        //具体初始化逻辑看下面的图
        hintPopupWindow = new HintPopupWindow(this, strList, clickList);
    }


    //两级联动的
    private void showPopBtn(int screenWidth, int screenHeight) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = LayoutInflater.from(LinkagePopupWindowAct.this);
        popupLayout = (LinearLayout) inflater.inflate(R.layout.popupwindow_layout, null, false);
        rootListView = (ListView) popupLayout.findViewById(R.id.root_listview);
        adapter = new RootListViewAdapter(LinkagePopupWindowAct.this);
        adapter.setItems(roots);
        rootListView.setAdapter(adapter);


        //子popupWindow

        subLayout = (FrameLayout) popupLayout.findViewById(R.id.sub_popupwindow);


        /**
         * 初始化subListview
         */
        subListView = (ListView) popupLayout.findViewById(R.id.sub_listview);
        subListView.setBackgroundColor(Color.argb(50,180,180,180));
        /**
         * 弹出popupwindow时，二级菜单默认隐藏，当点击某项时，二级菜单再弹出
         */
        subLayout.setVisibility(View.INVISIBLE);

        /**
         * 初始化mPopupWindow
         */
        mPopupWindow = new PopupWindow(popupLayout, screenWidth, LayoutParams.WRAP_CONTENT, true);

        /**
         * 有了mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
         * 这句可以使点击popupwindow以外的区域时popupwindow自动消失 但这句必须放在showAsDropDown之前
         */
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());

        /**
         * popupwindow的位置，第一个参数表示位于哪个控件之下 第二个参数表示向左右方向的偏移量，正数表示向左偏移，负数表示向右偏移
         * 第三个参数表示向上下方向的偏移量，正数表示向下偏移，负数表示向上偏移
         *
         */
        mPopupWindow.showAsDropDown(showPopBtn, -5, 5);// 在控件下方显示popwindow

        mPopupWindow.update();

        rootListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /**
                 * 选中root某项时改变该ListView item的背景色
                 */
                adapter.setSelectedPosition(position);
                adapter.notifyDataSetInvalidated();

                selectedPosition = position;

                subAdapter = new SubListViewAdapter(LinkagePopupWindowAct.this, sub_items, position);
                subListView.setAdapter(subAdapter);

            }
        });

        //如果不先显示第二层的话可以把它放在第一层点击事件里面
        /**
         * 选中某个根节点时，使显示相应的子目录可见
         */
        subListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mPopupWindow.dismiss();
                Toast.makeText(LinkagePopupWindowAct.this, sub_items[selectedPosition][position], Toast.LENGTH_SHORT).show();
            }
        });
}


    //从联动中吧第二层拿出来单独做的popupwindow
    private void showPopBtn2(int screenWidth, int screenHeight) {

        LayoutInflater inflater = LayoutInflater.from(LinkagePopupWindowAct.this);
        popupLayout = (LinearLayout) inflater.inflate(R.layout.popupwindow_layout2, null, false);
        //子popupWindow

//        subLayout = (FrameLayout) popupLayout.findViewById(R.id.sub_popupwindow2);
        /**
         * 初始化subListview
         */
        subListView = (ListView) popupLayout.findViewById(R.id.sub_listview2);
        subListView.setBackgroundColor(Color.argb(50,180,180,180));
        /**
         * 弹出popupwindow时，二级菜单默认隐藏，当点击某项时，二级菜单再弹出
         */
//        subLayout.setVisibility(View.INVISIBLE);

        /**
         * 初始化mPopupWindow
         */
        mPopupWindow = new PopupWindow(popupLayout, screenWidth, LayoutParams.WRAP_CONTENT, true);

        /**
         * 有了mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
         * 这句可以使点击popupwindow以外的区域时popupwindow自动消失 但这句必须放在showAsDropDown之前
         */
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());

        /**
         * popupwindow的位置，第一个参数表示位于哪个控件之下 第二个参数表示向左右方向的偏移量，正数表示向左偏移，负数表示向右偏移
         * 第三个参数表示向上下方向的偏移量，正数表示向下偏移，负数表示向上偏移
         *
         */
        mPopupWindow.showAsDropDown(btn2, -5, 5);// 在控件下方显示popwindow

        mPopupWindow.update();
        /**
         * 选中某个根节点时，使显示相应的子目录可见
         */
        subListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mPopupWindow.dismiss();
                Toast.makeText(LinkagePopupWindowAct.this, sub_items[selectedPosition][position], Toast.LENGTH_SHORT).show();
            }
        });
    }

}

