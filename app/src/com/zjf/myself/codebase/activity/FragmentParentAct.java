package com.zjf.myself.codebase.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.thirdparty.networkbanner.NetworkBannerFragment;
import com.zjf.myself.codebase.fragment.FragmentTabHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     author : ZouJianFeng
 *     e-mail :
 *     time   : 2017/04/21
 *     desc   :
 *     version: 1.0
 * </pre>
 */
//搭载fragment的公共ACT
public class FragmentParentAct extends BaseAct{
    public FragmentTabHandler fragmentContorler;
    private List<Fragment> fragments = new ArrayList<Fragment>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.win_fragment_parent);
        fragments.add(NetworkBannerFragment.newInstance());
        //fragment 管理
        fragmentContorler = new FragmentTabHandler(this, fragments, R.id.layout_tab_content);
        fragmentContorler.showTab(0);

    }
}
