package com.zjf.myself.codebase.thirdparty.networkbanner;



import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.reflect.TypeToken;
import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.fragment.BaseFragment;
import com.zjf.myself.codebase.util.AppLog;
import com.zjf.myself.codebase.util.DataUtil;
import com.zjf.myself.codebase.util.GsonParser;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * <pre>
 *     author : ZouJianFeng
 *     e-mail :
 *     time   : 2017/04/21
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class NetworkBannerFragment extends BaseFragment {
    public ArrayList<Banner> bannerList;
    private FrameLayout layoutBanner;

    public static NetworkBannerFragment newInstance() {
        NetworkBannerFragment f = new NetworkBannerFragment();
        return f;
    }


    @Override
    protected void initView() {
        ArrayList<Banner> bannerItems=new ArrayList<Banner>();
        bannerItems.add(new Banner("LAOLE手表,送给父母最贴心的礼物","http://laole.yinengsz.com/index.php",R.mipmap.icon_banner3,Banner.BANNER_TYPE_DEFAULT));
        FragmentTransaction ft=getFragmentManager().beginTransaction();
        ft.add(R.id.layoutBanner, BannerFragment.newInstance(bannerItems));
        ft.commit();
    }


    @Override
    protected int layoutId() {
        return R.layout.win_network_banner;
    }

    @Override
    public void onResume() {
        super.onResume();
        uptateBanners();
    }

    private void uptateBanners() {
        requestStart();
    }
    private void requestStart(){
        RequestQueue mQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("http://laole.yinengsz.com/index.php/Ads/index/cat_id/6", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String modeData=response.optString("data");
                        try {
                            TypeToken<ArrayList<Banner>> token = new TypeToken<ArrayList<Banner>>() {};
                            bannerList= GsonParser.getInstance().fromJson(modeData,token);

                            if(!DataUtil.listIsNull(bannerList)){
                                FragmentTransaction ft=getFragmentManager().beginTransaction();
                                ft.add(R.id.layoutBanner,BannerFragment.newInstance(bannerList));
                                ft.commitAllowingStateLoss();

                            }
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

}
