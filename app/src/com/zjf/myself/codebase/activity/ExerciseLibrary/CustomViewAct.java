package com.zjf.myself.codebase.activity.ExerciseLibrary;

import android.os.Bundle;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.activity.BaseAct;
import com.zjf.myself.codebase.view.test.CustomView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * <pre>
 *     author : ZouJianFeng
 *     e-mail : 1434690833@qq.com
 *     time   : 2017/10/14
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class CustomViewAct extends BaseAct {
    @Bind(R.id.customView)
    CustomView customView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.win_custom_view);


    }

}
