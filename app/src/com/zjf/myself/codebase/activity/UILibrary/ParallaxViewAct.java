package com.zjf.myself.codebase.activity.UILibrary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.activity.BaseAct;

/**
 * <pre>
 *     author : ZouJianFeng
 *     e-mail : 1434690833@qq.com
 *     time   : 2017/07/07
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class ParallaxViewAct extends BaseAct{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_parallax_view);
    }

    public void goScrollView(View view) {
        startActivity(new Intent(this, ScrollViewActivity.class));
    }

    public void goRecyclerView(View view) {
        startActivity(new Intent(this, RecyclerViewLinearActivity.class));
    }

    public void goRecyclerViewGrid(View view) {
        startActivity(new Intent(this, RecyclerViewGridActivity.class));
    }
}
