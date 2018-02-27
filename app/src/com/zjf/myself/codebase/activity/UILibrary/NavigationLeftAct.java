package com.zjf.myself.codebase.activity.UILibrary;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.activity.BaseAct;
import com.zjf.myself.codebase.util.StatusbarUtils;
import com.zjf.myself.codebase.util.ViewUtils;

/**
 * <pre>
 *     author : ZouJianFeng
 *     e-mail : 1434690833@qq.com
 *     time   : 2017/05/22
 *     desc   : 原生侧边栏和状态栏透明沉浸
 *     version: 1.0
 * </pre>
 */
public class NavigationLeftAct extends BaseAct implements NavigationView.OnNavigationItemSelectedListener
        ,View.OnClickListener     {

    private DrawerLayout mDrawerLayout;
    private NavigationView mNagigationView;
    private Button btnOpen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.act_navigation);


        initViews();
    }

    private void initViews() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.id_drawerlayout);
        ActionBarDrawerToggle mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mActionBarDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);

        mNagigationView = (NavigationView) findViewById(R.id.id_navigationview);
        mNagigationView.setNavigationItemSelectedListener(this);

        Resources resource=(Resources)getBaseContext().getResources();
        ColorStateList csl=(ColorStateList)resource.getColorStateList(R.color.navigation_menu_item_color);
        mNagigationView.setItemTextColor(csl);
        /**设置MenuItem默认选中项**/
//        mNagigationView.getMenu().getItem(0).setChecked(true);


        //状态栏透明
        StatusbarUtils.enableTranslucentStatusbar(this);

        //设置头部菜单和头部菜单点击事件
        View headview=mNagigationView.inflateHeaderView(R.layout.header_nav);
        ImageView headIcon = (ImageView) headview.findViewById(R.id.imgQrcode);
        headIcon.setOnClickListener(this);
        TextView headName = (TextView) headview.findViewById(R.id.txtAutograph);
        headName.setOnClickListener(this);

        //外部打开侧边栏按钮
        btnOpen = (Button)findViewById(R.id.btnOpen);
        btnOpen.setOnClickListener(this);

    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_menu_svip) {
            ViewUtils.showToast("我的超级会员");
        } else if (id == R.id.nav_menu_qianbao) {
            ViewUtils.showToast("QQ钱包");
        } else if (id == R.id.nav_menu_zhuangban) {
            ViewUtils.showToast("个性装扮");
        } else if (id == R.id.nav_menu_shoucang) {
            ViewUtils.showToast("我的收藏");
        }else if (id == R.id.nav_menu_xiangce) {
            ViewUtils.showToast("我的相册");
        }else if (id == R.id.nav_menu_wenjian) {
            ViewUtils.showToast("我的文件");
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.btnOpen:
                    mDrawerLayout.openDrawer(GravityCompat.START);
                    break;

                case R.id.imgQrcode:
                    ViewUtils.showToast("修改头像");
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                    break;

                case R.id.txtAutograph:
                    ViewUtils.showToast("修改昵称");
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                    break;

                default:
                    break;
            }
        }


}
