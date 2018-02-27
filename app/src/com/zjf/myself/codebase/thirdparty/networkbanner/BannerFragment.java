package com.zjf.myself.codebase.thirdparty.networkbanner;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.activity.WebviewAct;
import com.zjf.myself.codebase.fragment.BaseFragment;
import com.zjf.myself.codebase.util.AutoTunThread;
import com.zjf.myself.codebase.util.DataUtil;
import com.zjf.myself.codebase.util.ViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/22.
 */

public class BannerFragment extends BaseFragment implements View.OnClickListener {

    private ViewPager mViewPaper;
    CirclePageIndicator circlePageIndicator;
    private BannerAdapter mFragmentAdapter;
    private TextView bannerTitle;

    static ArrayList<Banner> bannerList;

    private int currentItem;
    private AutoTunThread autoTunThread;
    private AutoTunThread.AutoRunCallBack autoRunCallBack;

    public static BannerFragment newInstance(ArrayList<Banner> bannerList) {
        BannerFragment f = new BannerFragment();
        BannerFragment.bannerList=bannerList;
        return f;
    }

    @Override
    protected void initView() {

        if(DataUtil.listIsNull(bannerList))
            return;

        currentItem=0+bannerList.size()*100;

        mViewPaper = (ViewPager) findViewById(R.id.vp);

        mFragmentAdapter=new BannerAdapter(bannerList);
        mFragmentAdapter.setOnClickListener(this);
        mViewPaper.setAdapter(mFragmentAdapter);

        circlePageIndicator= (CirclePageIndicator) findViewById(R.id.indicator);
        circlePageIndicator.setViewPager(mViewPaper,bannerList.size(),currentItem);
        circlePageIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                Banner bannerItem=bannerList.get(position%bannerList.size());
                bannerTitle.setText(bannerItem.getTile());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        bannerTitle = (TextView) findViewById(R.id.txtBannerTitle);
        bannerTitle.setText(bannerList.get(0).getTile());
    }

    /**
     *
     * todo
     *
     * 优化默认广告，以及广告只有一张的时候的样式
     *
     * */
    @Override
    public void onResume() {
        super.onResume();
        if(!DataUtil.listIsNull(bannerList)&&bannerList.size()>1){
            runBanner();
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        if(autoTunThread!=null)
            autoTunThread.stopRun();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(bannerList!=null)
            bannerList=null;
    }

    private void runBanner(){
        if(autoTunThread==null){
            autoRunCallBack=new AutoTunThread.AutoRunCallBack() {
                @Override
                public void onDiong() {
                    currentItem++;
                    circlePageIndicator.setCurrentItem(currentItem);
                }

                @Override
                public void onFinish() {

                }
            };
            autoTunThread=new AutoTunThread(true,2000,autoRunCallBack);
        }

        autoTunThread.start();
    }



    @Override
    protected int layoutId() {
        return R.layout.layout_banner;
    }

    @Override
    public void onClick(View view) {
        Banner bannerItem= (Banner) view.getTag();
        WebviewAct.start("老乐健康",bannerItem.getUrl(),getActivity());
    }

    public class BannerAdapter extends PagerAdapter {
        private List<Banner> banners;
        private View.OnClickListener onClickListener;
        public BannerAdapter(List<Banner> banners){
            this.banners=banners;
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView((View) arg2);
        }

        @Override
        public void finishUpdate(View arg0) {
        }

        @Override
        public int getCount() {
            if(banners.size()==1){
                return 1;
            }

            return banners.size()*1000;
        }

        public void setOnClickListener(View.OnClickListener onClickListener) {
            this.onClickListener = onClickListener;
        }

        @Override
        public Object instantiateItem(View arg0, int position) {
            Log.d("initItem","init:"+position);
            if(banners.size()!=1){
                position=position%banners.size();
            }

            NetworkImageView iv=new NetworkImageView(getActivity());
            iv.setLayoutParams(mViewPaper.getLayoutParams());
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            iv.setTag(banners.get(position));
            if(onClickListener!=null)
                iv.setOnClickListener(onClickListener);

            Banner banner=banners.get(position);
            if(banner.getType()==Banner.BANNER_TYPE_DEFAULT){
                ViewUtils.setImageByUrl(iv,banners.get(position).getImgUrl(),banner.getDefImgId());
            }else {
                ViewUtils.setImageByUrl(iv,banners.get(position).getImgUrl(),R.mipmap.banner_default_bg);
            }

            ((ViewPager) arg0).addView(iv, 0);
            return iv;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == (arg1);
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {

        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {

        }

    }


}
