package com.zjf.myself.codebase.activity.UILibrary;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.adapter.CommonAdaper;
import com.zjf.myself.codebase.adapter.ViewHolder;
import com.zjf.myself.codebase.util.AppLog;
import com.zjf.myself.codebase.view.MListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/7.
 */

public class BaseTitleListViewAct extends Activity{
    private ListView listView;
    private SettingAdapter settingAdapter;
    private List<SettingInfo> settingInfoList=new ArrayList<SettingInfo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.win_base_title_lv);

        listView = (ListView) findViewById(R.id.lvBaseTitleLv);
        settingAdapter=new SettingAdapter(BaseTitleListViewAct.this,settingInfoList,R.layout.item_settings);
        listView.setAdapter(settingAdapter);
        showResult();
    }

    private void showResult(){
        settingInfoList.clear();
        settingAdapter.clearAll(false);

        SettingInfo part1=new SettingInfo();
        part1.setSettingItemList(new ArrayList<SettingItem>());
        part1.getSettingItemList().add(new SettingItem(1,"家庭成员","",null,R.mipmap.icon_setting_family_meber));
        part1.getSettingItemList().add(new SettingItem(2,"亲情号码","",null,R.mipmap.icon_setting_qinqing));
//        part1.getSettingItemList().add(new SettingItem(3,"查询资费","",SetQueryChargeAct.class,R.drawable.icon_setting_qcharge));
        settingInfoList.add(part1);

        SettingInfo partSport=new SettingInfo();
        partSport.setSettingItemList(new ArrayList<SettingItem>());


        partSport.getSettingItemList().add(new SettingItem(1,"运动目标","5000步",null,R.mipmap.icon_setting_step_target));
        settingInfoList.add(partSport);

        SettingInfo part2=new SettingInfo();
        part2.setSettingItemList(new ArrayList<SettingItem>());
        part2.getSettingItemList().add(new SettingItem(1,"定位设置","",null,R.mipmap.icon_setting_lianxu));
        settingInfoList.add(part2);

        SettingInfo part3=new SettingInfo();
        part3.setSettingItemList(new ArrayList<SettingItem>());
        part3.getSettingItemList().add(new SettingItem(1,"静默时段","",null,R.mipmap.icon_setting_jingyin));
        settingInfoList.add(part3);

        SettingInfo part4=new SettingInfo();
        part4.setSettingItemList(new ArrayList<SettingItem>());

        Bundle remindData=new Bundle();
//      remindData.putInt("type",remindType);
        part4.getSettingItemList().add(new SettingItem(1,"来电响铃方式","响铃和振动",null,remindData,R.mipmap.icon_setting_callreminder));

        part4.getSettingItemList().add(new SettingItem(1,"手表系统升级","",null,R.mipmap.icon_setting_updata));
        part4.getSettingItemList().add(new SettingItem(1,"恢复出厂设置","",null,R.mipmap.icon_setting_reset_factory));
        settingInfoList.add(part4);

        settingAdapter.addList(settingInfoList,true);
    }


    static class SettingAdapter  extends CommonAdaper {
        public SettingAdapter(Context context, List list, int itemLayoutId) {
            super(context, list, itemLayoutId);
        }

        @Override
        public void convert(ViewHolder holder, Object item, int position) {
            MListView mListView= holder.getView(R.id.myListView);
            SettingInfo settingInfo= (SettingInfo) item;
            SettingItemAdapter settingItemAdapter=null;
            if(mListView.getTag()==null){
                settingItemAdapter=new SettingItemAdapter(context,settingInfo.getSettingItemList(),R.layout.item_content_view);
                mListView.setAdapter(settingItemAdapter);
            }else {
                settingItemAdapter= (SettingItemAdapter) mListView.getTag();
                settingItemAdapter.clearAll(false);
                settingItemAdapter.addList(settingInfo.getSettingItemList(),true);
            }

        }

    }

    static class SettingItemAdapter  extends CommonAdaper implements View.OnClickListener{

        public SettingItemAdapter(Context context, List list, int itemLayoutId) {
            super(context, list, itemLayoutId);
        }

        @Override
        public void convert(ViewHolder holder, Object item, int position) {
            SettingItem settingItem= (SettingItem) item;
            holder.getConvertView().setOnClickListener(this);
            holder.getView(R.id.tv_setting_name).setTag(item);
            holder.setText(R.id.tv_setting_name,settingItem.getName());
            holder.setText(R.id.tv_setting_end,settingItem.getDesc());
            holder.setImageResource(R.id.iv_setting_img,((SettingItem) item).getIconId());
        }


        @Override
        public void onClick(View view) {
            SettingItem item= (SettingItem) view.findViewById(R.id.tv_setting_name).getTag();
//            Intent it=new Intent(context,item.getCls());
//            startActivity(it);
//            if(item.getData()!=null)
//                it.putExtras(item.getData());
//            ViewUtils.startActivityForResult(it,item.getTYPE());
             AppLog.e("aaaaaaaaaaaa"+item.getName());

        }
    }

    static class SettingInfo{
        private String name;
        private List<SettingItem> settingItemList;

        public List<SettingItem> getSettingItemList() {
            return settingItemList;
        }

        public void setSettingItemList(List<SettingItem> settingItemList) {
            this.settingItemList = settingItemList;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static final int TYPE_CALL_REMIND=3;
    public static final int TYPE_SET_STEP=4;
    public static final int TYPE_CHANGE_ADMIN=5;
    static class SettingItem{
        private int TYPE;
        private String name;
        private String desc;
        private Class cls;
        private int iconId;
        private Bundle data;

        public SettingItem(int TYPE, String name, String desc, Class cls,int iconId) {
            this.TYPE = TYPE;
            this.name = name;
            this.desc = desc;
            this.cls = cls;
            this.iconId=iconId;
        }

        public SettingItem(int TYPE, String name, String desc, Class cls,Bundle data,int iconId) {
            this.TYPE = TYPE;
            this.name = name;
            this.desc = desc;
            this.cls = cls;
            this.iconId=iconId;
            this.data=data;
        }

        public int getIconId() {
            return iconId;
        }

        public void setIconId(int iconId) {
            this.iconId = iconId;
        }

        public SettingItem() {
        }

        public int getTYPE() {
            return TYPE;
        }

        public void setTYPE(int TYPE) {
            this.TYPE = TYPE;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public Class getCls() {
            return cls;
        }

        public void setCls(Class cls) {
            this.cls = cls;
        }

        public Bundle getData() {
            return data;
        }

        public void setData(Bundle data) {
            this.data = data;
        }
    }


}

