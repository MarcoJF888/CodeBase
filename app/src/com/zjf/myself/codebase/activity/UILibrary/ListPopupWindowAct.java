package com.zjf.myself.codebase.activity.UILibrary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.zjf.myself.codebase.R;

import java.lang.reflect.Field;

public class ListPopupWindowAct extends AppCompatActivity {

    private ListPopupWindow mListPopupWindow;
    private ArrayAdapter<String> mAdapter;
    private String[] mItemArr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_list_popup_window);
        mItemArr = new String[]{"item 1", "item 2", "item 3", "item 4", "item 5", "item 6", "item 7", "item 8"};
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mItemArr);
    }

    public void showPopupWindow(View view) {
        if (mListPopupWindow == null)
            mListPopupWindow = new ListPopupWindow(this);
        //设置 ListPopupWindow 的数据适配器
        mListPopupWindow.setAdapter(mAdapter);
        //设置 ListPopupWindow 的显示位置（在指定控件下方）
        mListPopupWindow.setAnchorView(view);
        //设置 ListPopupWindow 的宽度
        mListPopupWindow.setWidth(200);
        //设置 ListPopupWindow 的高度
        mListPopupWindow.setHeight(500);
        //设置 ListPopupWindow 的条目点击事件（必须在show方法前设置，否则无效）
        mListPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), mItemArr[position], Toast.LENGTH_SHORT).show();
                mListPopupWindow.dismiss();
            }
        });
        mListPopupWindow.show();

    }




    public void showPopupMenu(View view) throws NoSuchFieldException, IllegalAccessException {
        PopupMenu popupMenu = new PopupMenu(this, view);
        //设置 PopupMenu 的显示菜单项
        popupMenu.inflate(R.menu.main);
        // popupMenu.getMenuInflater().inflate(R.menu.main, popupMenu.getMenu());//与一行没什么区别
        //默认 PopupMenu 不显示条目icon，可以通过反射来强制使其显示icon
        Field field = popupMenu.getClass().getDeclaredField("mPopup");
        field.setAccessible(true);
        MenuPopupHelper mHelper = (MenuPopupHelper) field.get(popupMenu);
        mHelper.setForceShowIcon(true);
        //设置 PopupMenu 的条目点击事件（点击后会自动dismiss）
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        //显示 PopupMenu
        popupMenu.show();
    }
}
