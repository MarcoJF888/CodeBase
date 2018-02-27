package com.zjf.myself.codebase.activity.UILibrary;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.activity.BaseAct;
import com.zjf.myself.codebase.util.AutoTunThread;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/22.
 */

public class BannerAct extends BaseAct{
    //banner
    private ViewPager mViewPaper;
    private ViewPagerAdapter adapter;
    private List<ImageView> images;
    private List<View> dots;
    private int currentItem;
    private int oldPosition = 0;
    private int[] bannerImages = new int[]{R.mipmap.icon_banner1, R.mipmap.icon_banner2,R.mipmap.icon_banner3};
    private String[] bannerTitles = new String[]{"1111111XXXXXXXXXXXXXXXXXXXXXX", "2222222XXXXXXXXXXXXXXXXXXXXXX", "3333333XXXXXXXXXXXXXXXXXXXXXX"};
    private TextView bannerTitle;
    private AutoTunThread autoTunThread;
    private AutoTunThread.AutoRunCallBack autoRunCallBack;
     @Override
         protected void onCreate(Bundle savedInstanceState) {
             super.onCreate(savedInstanceState);
             setContentView(R.layout.win_banner);

         images = new ArrayList<ImageView>();
         for(int i = 0; i < bannerImages.length; i++){
             ImageView imageView = new ImageView(this);
             imageView.setBackgroundResource(bannerImages[i]);
             images.add(imageView);
         }

         mViewPaper = (ViewPager) findViewById(R.id.banner);
         adapter = new ViewPagerAdapter();
         mViewPaper.setAdapter(adapter);
         mViewPaper.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

             @Override
             public void onPageSelected(int position) {
                 bannerTitle.setText(bannerTitles[position]);
                 dots.get(position).setBackgroundResource(R.mipmap.dot_focused);
                 dots.get(oldPosition).setBackgroundResource(R.mipmap.dot_normal);
                 oldPosition = position;
                 currentItem = position;
             }

             @Override
             public void onPageScrolled(int arg0, float arg1, int arg2) {

             }

             @Override
             public void onPageScrollStateChanged(int arg0) {

             }
         });

         bannerTitle = (TextView) findViewById(R.id.txtBannerTitle);
         //显示的小点
         dots = new ArrayList<View>();
         dots.add(findViewById(R.id.dot_0));
         dots.add(findViewById(R.id.dot_1));
         dots.add(findViewById(R.id.dot_2));

      }


    private class ViewPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup view, int position, Object object) {
            // TODO Auto-generated method stub
//          super.destroyItem(container, position, object);
//          view.removeView(view.getChildAt(position));
//          view.removeViewAt(position);
            view.removeView(images.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            // TODO Auto-generated method stub
            view.addView(images.get(position));
            return images.get(position);
        }

    }

    private void runBanner(){
        if(autoTunThread==null){
            autoRunCallBack=new AutoTunThread.AutoRunCallBack() {
                @Override
                public void onDiong() {
                    currentItem = (currentItem + 1) % bannerImages.length;
                    mViewPaper.setCurrentItem(currentItem);
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
    public void onResume() {
        super.onResume();
        runBanner();

    }

    @Override
    public void onStop() {
        super.onStop();
        autoTunThread.stopRun();
    }
}
