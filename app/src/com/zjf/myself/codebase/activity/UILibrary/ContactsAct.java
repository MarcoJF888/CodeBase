package com.zjf.myself.codebase.activity.UILibrary;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.activity.BaseAct;
import com.zjf.myself.codebase.thirdparty.Contacts.CommonUtil;
import com.zjf.myself.codebase.thirdparty.Contacts.ContactAdapter;
import com.zjf.myself.codebase.thirdparty.Contacts.ContactBean;
import com.zjf.myself.codebase.thirdparty.Contacts.CustomItemDecoration;
import com.zjf.myself.codebase.thirdparty.Contacts.SideBar;
import com.zjf.myself.codebase.thirdparty.Contacts.SlideInOutLeftItemAnimator;
import com.zjf.myself.codebase.util.ViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     author : ZouJianFeng
 *     e-mail : 1434690833@qq.com
 *     time   : 2017/07/04
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class ContactsAct extends BaseAct implements View.OnClickListener{
    private RecyclerView rl_recycle_view;
    private ContactAdapter mAdapter;
    private CustomItemDecoration decoration;
    private SideBar side_bar;
    List<ContactBean> nameList = new ArrayList<>();
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_contact);
        ((ImageView)findViewById(R.id.btnBack)).setOnClickListener(this);
        ((FrameLayout)findViewById(R.id.btnRight)).setOnClickListener(this);
        ((TextView)findViewById(R.id.txtRight)).setText("Add");
        ((TextView)findViewById(R.id.txtTitle)).setText("通讯录");



        mAdapter = new ContactAdapter(this);
        rl_recycle_view = (RecyclerView) findViewById(R.id.rl_recycle_view);
        //侧边导航栏
        side_bar = (SideBar) findViewById(R.id.side_bar);
        layoutManager = new LinearLayoutManager(this);
        rl_recycle_view.setLayoutManager(layoutManager);
        rl_recycle_view.addItemDecoration(decoration = new CustomItemDecoration(this));
        rl_recycle_view.setItemAnimator(new SlideInOutLeftItemAnimator(rl_recycle_view));
        initDatas();
        rl_recycle_view.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new ContactAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View view , int position){
                ViewUtils.showToast(nameList.get(position).getName().trim());
            }
        });


        side_bar.setIndexChangeListener(new SideBar.indexChangeListener() {
            @Override
            public void indexChanged(String tag) {
                if (TextUtils.isEmpty(tag) || nameList.size() <= 0) return;
                for (int i = 0; i < nameList.size(); i++) {
                    if (tag.equals(nameList.get(i).getIndexTag())) {
                        layoutManager.scrollToPositionWithOffset(i, 0);
//                        layoutManager.scrollToPosition(i);
                        return;
                    }
                }
            }
        });

    }

    private void initDatas() {
        String[] names = {"孙尚香", "安其拉", "白起", "不知火舞", "@小马快跑", "_德玛西亚之力_", "妲己", "狄仁杰", "典韦", "韩信",
                "老夫子", "刘邦", "刘禅", "鲁班七号", "墨子", "孙膑", "孙尚香", "孙悟空", "项羽", "亚瑟",
                "周瑜", "庄周", "蔡文姬", "甄姬", "廉颇", "程咬金", "后羿", "扁鹊", "钟无艳", "小乔", "王昭君", "虞姬",
                "李元芳", "张飞", "刘备", "牛魔", "张良", "兰陵王", "露娜", "貂蝉", "达摩", "曹操", "芈月", "荆轲", "高渐离",
                "钟馗", "花木兰", "关羽", "李白", "宫本武藏", "吕布", "嬴政", "娜可露露", "武则天", "赵云", "姜子牙",};
        for (String name : names) {
            ContactBean bean = new ContactBean();
            bean.setName(name);
            nameList.add(bean);
        }
        //对数据源进行排序
        CommonUtil.sortData(nameList);
        //返回一个包含所有Tag字母在内的字符串并赋值给tagsStr
        String tagsStr = CommonUtil.getTags(nameList);
        side_bar.setIndexStr(tagsStr);
        decoration.setDatas(nameList, tagsStr);
        mAdapter.addAll(nameList);
    }



    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btnBack:
                finish();
                break;


            //存在添加之后点击事件有问题的小BUG，实际场景不必在意这个问题
            case R.id.btnRight:
                ContactBean bean = new ContactBean();
                bean.setName("安其拉666");
                nameList.add(bean);
                //对数据源进行排序
                CommonUtil.sortData(nameList);
                //返回一个包含所有Tag字母在内的字符串并赋值给tagsStr
                String tagsStr = CommonUtil.getTags(nameList);
                side_bar.setIndexStr(tagsStr);
                decoration.setDatas(nameList, tagsStr);
                //这里写死位置1 只是为了看动画效果
                mAdapter.add(bean, 1);
                break;
            default:
                break;
        }
    }
}