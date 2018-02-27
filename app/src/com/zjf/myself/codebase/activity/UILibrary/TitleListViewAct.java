package com.zjf.myself.codebase.activity.UILibrary;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.zjf.myself.codebase.R;

import com.zjf.myself.codebase.thirdparty.separate.ContentItem;
import com.zjf.myself.codebase.thirdparty.separate.Item;
import com.zjf.myself.codebase.thirdparty.separate.SeparateItemAdapter;
import com.zjf.myself.codebase.thirdparty.separate.SeparatorItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/19.
 */
public class TitleListViewAct extends Activity{

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.win_title_listview);

        List<Item> list = new ArrayList<Item>();
        list.add(new ContentItem(R.mipmap.icon_setting_qinqing, "1111111111", null));
        list.add(new ContentItem(R.mipmap.icon_setting_family_meber, "222222222", null));
        list.add(new ContentItem(R.mipmap.icon_setting_qcharge, "33333333333", null));

        list.add(new SeparatorItem(" "));

        list.add(new ContentItem(R.mipmap.icon_setting_dongwei, "444444444", "ggggg"));
        list.add(new ContentItem(R.mipmap.icon_setting_lianxu, "5555555555", "关闭"));

        list.add(new SeparatorItem(" "));

        list.add(new ContentItem(R.mipmap.icon_setting_shuimian, "6666666666", "关闭"));
        list.add(new ContentItem(R.mipmap.icon_setting_jingyin, "777777777", null));

        list.add(new SeparatorItem(" "));

        list.add(new ContentItem(R.mipmap.icon_setting_callreminder, "88888888", "振动"));
        list.add(new ContentItem(R.mipmap.icon_setting_time_reset, "999999999", null));
        list.add(new ContentItem(R.mipmap.icon_setting_time_reset, "aaaaaaaa", null));
        list.add(new ContentItem(R.mipmap.icon_setting_reset_factory, "bbbbbbbbbbbb", null));


        list.add(new ContentItem(R.mipmap.icon_setting_qinqing, "cccccccccccccccccc", null));
        list.add(new ContentItem(R.mipmap.icon_setting_family_meber, "ddddddddddd", null));
        list.add(new ContentItem(R.mipmap.icon_setting_qcharge, "eeeeeeeeeee", null));

        list.add(new SeparatorItem(" "));

        list.add(new ContentItem(R.mipmap.icon_setting_dongwei, "ffffffff", "ggggg"));
        list.add(new ContentItem(R.mipmap.icon_setting_lianxu, "ggggggggggg", "关闭"));

        list.add(new SeparatorItem(" "));

        list.add(new ContentItem(R.mipmap.icon_setting_shuimian, "hhhhhhhhhhhhh", "关闭"));
        list.add(new ContentItem(R.mipmap.icon_setting_jingyin, "iiiiiiiiiii", null));

        list.add(new SeparatorItem(" "));

        list.add(new ContentItem(R.mipmap.icon_setting_callreminder, "jjjjjjjjjjjjj", "振动"));
        list.add(new ContentItem(R.mipmap.icon_setting_time_reset, "kkkkkkkkkkkkkkkk", null));
        list.add(new ContentItem(R.mipmap.icon_setting_time_reset, "lllllllllllll", null));
        list.add(new ContentItem(R.mipmap.icon_setting_reset_factory, "mmmmmmmmmmmmmm", null));




        listView = (ListView) findViewById(R.id.lvTitle);
        SeparateItemAdapter adapter = new SeparateItemAdapter(this, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new ListViewItemOnClick());

    }


    public class ListViewItemOnClick implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {
            switch (position)
            {
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    break;


                case 4:
                    break;
                case 5:
                    break;


                case 7:
                    break;
                case 8:
                    break;


                case 10:
                    break;
                case 11:
                    break;
                case 12:
                    break;
                case 13:
                    break;
            }
        }
    }
}
