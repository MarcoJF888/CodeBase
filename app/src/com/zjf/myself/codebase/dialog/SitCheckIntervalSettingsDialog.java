package com.zjf.myself.codebase.dialog;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;


import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.thirdparty.recyclerview.adapter.SingSelectAdapter;
import com.zjf.myself.codebase.thirdparty.recyclerview.adapter.ViewHolder;
import com.zjf.myself.codebase.util.CallBack;
import com.zjf.myself.codebase.util.DesityUtil;

import java.util.ArrayList;
import java.util.List;


public class SitCheckIntervalSettingsDialog extends BaseDialog implements View.OnClickListener {

    View btnOk,btnCancle;
    RecyclerView recyclerView;
   IntervalSettingsAdapter intervalSettingsAdapter;

    CallBack callBack;

    private int selectPos;

    private String curSelectTime;

    private List<IntervalInfo> settingList;

    public SitCheckIntervalSettingsDialog(){

    }

    public SitCheckIntervalSettingsDialog(CallBack callBack){
        this.callBack=callBack;
    }


    public void setSelect(int pos){
      selectPos=pos;
    }

    public void setSelectTime(String time){
        curSelectTime=time;
    }
    public String getSelectTime(){
        return curSelectTime;
    }

    @Override
    protected void initView() {

        dialogWindow.getDecorView().setPadding(DesityUtil.dp2px(context,40),0,DesityUtil.dp2px(context,40),0);

        btnOk=findViewById(R.id.btnOk);
        btnOk.setOnClickListener(this);

        btnCancle=findViewById(R.id.btnCancel);
        btnCancle.setOnClickListener(this);

        recyclerView= (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(context);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        recyclerView.setHasFixedSize(true);

        settingList=new ArrayList<IntervalInfo>();
        settingList.add(new IntervalInfo("申通快递"));
        settingList.add(new IntervalInfo("EMS"));
        settingList.add(new IntervalInfo("顺丰快递"));
        settingList.add(new IntervalInfo("圆通快递"));
        settingList.add(new IntervalInfo("中通快递"));
        settingList.add(new IntervalInfo("韵达快递"));
        settingList.add(new IntervalInfo("天天快递"));
        settingList.add(new IntervalInfo("汇通快递"));
        settingList.add(new IntervalInfo("全峰快递"));
        settingList.add(new IntervalInfo("德邦快递"));
        settingList.add(new IntervalInfo("宅急送"));

        intervalSettingsAdapter=new IntervalSettingsAdapter(context,R.layout.item_sit_ckeck_interval,settingList);
        recyclerView.setAdapter(intervalSettingsAdapter);
    }


    public String getResult(){
        IntervalInfo intervalInfo= (IntervalInfo) intervalSettingsAdapter.getItem(intervalSettingsAdapter.getCurSelect());
        return intervalInfo.getValue();
    }

    @Override
    public void show() {
        super.show();

        for (int i = 0; i <settingList.size() ; i++) {
            IntervalInfo info=settingList.get(i);
            if(curSelectTime==info.getValue()){
                intervalSettingsAdapter.setCurSelect(i);
                intervalSettingsAdapter.notifyDataSetChanged();
                return;
            }
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_sit_check_interval_setting;
    }

    @Override
    public int getGravity() {
        return Gravity.CENTER;
    }

    @Override
    protected boolean isShowAnimation() {
        return false;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btnOk:
//                int base = 2;
//                int decimal = Integer.parseInt(time, base);
                if(callBack!=null)
                    callBack.onCall(getResult());
                dismiss();
                break;

            case R.id.btnCancel:
                dismiss();
                break;

        }

    }


    public static class IntervalSettingsAdapter extends SingSelectAdapter {

        public IntervalSettingsAdapter(Context context, int layoutId, List datas) {
            super(context, layoutId, datas);
        }

        @Override
        public void convert(ViewHolder holder, int pos) {
            IntervalInfo intervalInfo= (IntervalInfo) getItem(pos);
            holder.setText(R.id.txtName,intervalInfo.getName());
        }

        @Override
        public int[] getCkeckViewInfo() {
            return new int[]{R.id.imgSeletIcon,R.mipmap.icon_checked,R.mipmap.icon_unchecked};
        }
    }

    public static class IntervalInfo{
        private String name;
        private String value;

        public IntervalInfo() {
        }

        public IntervalInfo(String value) {
            this.value = value;
        }

        public String getName() {
            return getValue();
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }


}
