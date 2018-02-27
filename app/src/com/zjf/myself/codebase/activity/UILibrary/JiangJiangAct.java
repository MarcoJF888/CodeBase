package com.zjf.myself.codebase.activity.UILibrary;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.adapter.CommonAdaper;
import com.zjf.myself.codebase.adapter.ViewHolder;
import com.zjf.myself.codebase.model.BasicListViewInfo;
import com.zjf.myself.codebase.model.JiangJiangInfo;
import com.zjf.myself.codebase.util.ViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/22.
 */

public class JiangJiangAct extends Activity implements View.OnClickListener{
    private EditText edtNum;
    private TextView txtAmount;
    private ListView lvDetail;
    private String Num;
    private Button btnDetail,btnAmount;
    private List<JiangJiangInfo> list;
    private JiangJiangListViewAdapter adaper;

     @Override
         protected void onCreate(Bundle savedInstanceState) {
             super.onCreate(savedInstanceState);
             setContentView(R.layout.jiangjiang);

         edtNum = (EditText)findViewById(R.id.edtNum);
         txtAmount = (TextView)findViewById(R.id.txtAmount);
         lvDetail = (ListView)findViewById(R.id.lvDetail);
         btnDetail = (Button)findViewById(R.id.btnDetail);
         btnDetail.setOnClickListener(this);
         btnAmount = (Button)findViewById(R.id.btnAmount);
         btnAmount.setOnClickListener(this);

         list = new ArrayList<JiangJiangInfo>();
         initView();
         adaper = new JiangJiangListViewAdapter(this, list, R.layout.item_jiangjiang);
         lvDetail.setAdapter(adaper);

      }


    static class JiangJiangListViewAdapter extends CommonAdaper<JiangJiangInfo> {

        public JiangJiangListViewAdapter(Context context, List<JiangJiangInfo> list, int itemLayoutId){
            super(context,list,itemLayoutId);
        }

        @Override
        public void convert(ViewHolder holder, JiangJiangInfo item , int position){

            holder.setText(R.id.txtLabel, item.getTxtLabel());
            holder.setText(R.id.txtResult,item.getTxtResult());

        }

    }


    protected void initView(){
        list.add(new JiangJiangInfo("工程","江江"));
        list.add(new JiangJiangInfo("类型","男"));
        list.add(new JiangJiangInfo("信息","22岁"));
        list.add(new JiangJiangInfo("时间","2017.2.15"));
        list.add(new JiangJiangInfo("备注","比较猥琐"));
    }

    @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.btnDetail:
                    lvDetail.setVisibility(View.VISIBLE);
                    break;

                case R.id.btnAmount:
                    txtAmount.setText("888");
                    Num = edtNum.getText().toString();
//                    ViewUtils.showToast("机台的数量为888");
                    break;

                default:
                    break;
            }
        }
}
