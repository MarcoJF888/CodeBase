package com.zjf.myself.codebase.activity.FreeTimeProjectList;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.reflect.TypeToken;
import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.activity.BaseAct;
import com.zjf.myself.codebase.model.NewsRecycleViewGridInfo;
import com.zjf.myself.codebase.thirdparty.recyclerview.adapter.CommonAdapter;
import com.zjf.myself.codebase.thirdparty.recyclerview.adapter.MultiItemTypeAdapter;
import com.zjf.myself.codebase.thirdparty.recyclerview.adapter.ViewHolder;
import com.zjf.myself.codebase.util.AppLog;
import com.zjf.myself.codebase.util.GsonParser;
import com.zjf.myself.codebase.util.ViewUtils;
import com.zjf.myself.codebase.view.LoadingView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     author : ZouJianFeng
 *     e-mail :
 *     time   : 2017/05/06
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class NewsUIpageRecycleViewGridAct extends BaseAct{
    private LoadingView loadingView;
    private static RecyclerView recyclerview;
    private GridLayoutManager mLayoutManager;
    private static String JsonURL = "http://www.imooc.com/api/teacher?type=4&num=30";

    private RecycleViewAdapter mAdapter;

    @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);

         setContentView(R.layout.act_news_ui_page_recycleview_grid);
         loadingView= (LoadingView)findViewById(R.id.loadingView);

         initView();

         requestStart();


     }

    private void requestStart(){
        loadingView.startLoading();
        RequestQueue mQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(JsonURL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            String modeData=response.optString("data");

                            TypeToken<List<NewsRecycleViewGridInfo>> token = new TypeToken<List<NewsRecycleViewGridInfo>>() {};
                            List<NewsRecycleViewGridInfo> newsRecycleViewGridInfo=new ArrayList<NewsRecycleViewGridInfo>();
                            newsRecycleViewGridInfo= GsonParser.getInstance().fromJson(modeData,token);

                            mAdapter =new RecycleViewAdapter(NewsUIpageRecycleViewGridAct.this,
                                        R.layout.item_news_recycleview_grid,newsRecycleViewGridInfo);

                            setAdapterClick(mAdapter);

                            recyclerview.setAdapter(mAdapter);

                            loadingView.onLoadingComplete();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AppLog.d("请求失败：："+error.getMessage(), error);
            }
        });
        mQueue.add(jsonObjectRequest);
    }


    private void initView(){
        recyclerview=(RecyclerView)findViewById(R.id.gridRecycler);
        mLayoutManager=new GridLayoutManager(NewsUIpageRecycleViewGridAct.this,2,GridLayoutManager.VERTICAL,false);
        recyclerview.setLayoutManager(mLayoutManager);
        recyclerview.setHasFixedSize(true);


//        newsRecycleViewGridInfo.add(new NewsRecycleViewGridInfo(R.mipmap.icon_banner1,"标题标题标题标题标题标题","内容内容内容内容内容内容内容内容内容内容内容内容内容"));
//        newsRecycleViewGridInfo.add(new NewsRecycleViewGridInfo(R.mipmap.icon_banner2,"标题标题标题标题标题标题","内容内容内容内容内容内容内容内容内容内容内容内容内容"));
//        newsRecycleViewGridInfo.add(new NewsRecycleViewGridInfo(R.mipmap.icon_banner3,"标题标题标题标题标题标题","内容内容内容内容内容内容内容内容内容内容内容内容内容"));
//        newsRecycleViewGridInfo.add(new NewsRecycleViewGridInfo(R.mipmap.icon_banner1,"标题标题标题标题标题标题","内容内容内容内容内容内容内容内容内容内容内容内容内容"));
//        newsRecycleViewGridInfo.add(new NewsRecycleViewGridInfo(R.mipmap.icon_banner2,"标题标题标题标题标题标题","内容内容内容内容内容内容内容内容内容内容内容内容内容"));
//        newsRecycleViewGridInfo.add(new NewsRecycleViewGridInfo(R.mipmap.icon_banner3,"标题标题标题标题标题标题","内容内容内容内容内容内容内容内容内容内容内容内容内容"));
//        newsRecycleViewGridInfo.add(new NewsRecycleViewGridInfo(R.mipmap.icon_banner1,"标题标题标题标题标题标题","内容内容内容内容内容内容内容内容内容内容内容内容内容"));
//        newsRecycleViewGridInfo.add(new NewsRecycleViewGridInfo(R.mipmap.icon_banner2,"标题标题标题标题标题标题","内容内容内容内容内容内容内容内容内容内容内容内容内容"));
//        newsRecycleViewGridInfo.add(new NewsRecycleViewGridInfo(R.mipmap.icon_banner3,"标题标题标题标题标题标题","内容内容内容内容内容内容内容内容内容内容内容内容内容"));
//        newsRecycleViewGridInfo.add(new NewsRecycleViewGridInfo(R.mipmap.icon_banner1,"标题标题标题标题标题标题","内容内容内容内容内容内容内容内容内容内容内容内容内容"));
//        newsRecycleViewGridInfo.add(new NewsRecycleViewGridInfo(R.mipmap.icon_banner2,"标题标题标题标题标题标题","内容内容内容内容内容内容内容内容内容内容内容内容内容"));
//        newsRecycleViewGridInfo.add(new NewsRecycleViewGridInfo(R.mipmap.icon_banner3,"标题标题标题标题标题标题","内容内容内容内容内容内容内容内容内容内容内容内容内容"));

    }

    static class RecycleViewAdapter extends CommonAdapter {

        public RecycleViewAdapter(Context context, int layoutId, List datas) {
            super(context, layoutId, datas);
        }
        @Override
        protected void convert(ViewHolder holder, Object o, int position) {
            NewsRecycleViewGridInfo newsRecycleViewGridInfo = (NewsRecycleViewGridInfo) o;

			/*
			方法一：
			TextView txtDevName=holder.getView(R.id.txtDevName);
			txtDevName.setText(devInfo.getName());
			*/

//            holder.setImageResource(R.id.imgHeadIcon,newsRecycleViewGridInfo.getIconID());
            holder.setText(R.id.txtTitle,newsRecycleViewGridInfo.getNewsTitle());
            holder.setText(R.id.txtContent,newsRecycleViewGridInfo.getNewsContent());
        }
    }

    private void setAdapterClick(RecycleViewAdapter mAdapter){
        //点击事件
        mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                //DevInfo devInfo= (DevInfo) mAdapter.getItem(position);

                //setDefaultDevRequest.setDefaultDev(devInfo.getDeviceId());
                //setDefaultDevRequest.start(getActivity());

                //                NewsRecycleViewGridInfo newsRecycleViewGridInfo= (NewsRecycleViewGridInfo) mAdapter.getData().remove(position);
                //                mAdapter.getData().add(0,newsRecycleViewGridInfo);
                //                mAdapter.notifyDataSetChanged();
                //                recyclerview.scrollToPosition(0);
                ViewUtils.showToast("点击了第"+position+"项");
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                ViewUtils.showToast("长按了第"+position+"项");
                return true;

            }
        });
    }
}
