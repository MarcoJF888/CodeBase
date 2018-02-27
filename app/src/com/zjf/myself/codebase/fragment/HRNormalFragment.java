package com.zjf.myself.codebase.fragment;

import android.text.Html;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.activity.BaseAct;
import com.zjf.myself.codebase.activity.LoginAct;
import com.zjf.myself.codebase.application.AppConstants;
import com.zjf.myself.codebase.application.UserDataManager;
import com.zjf.myself.codebase.model.UserData;
import com.zjf.myself.codebase.util.StringUtil;
import com.zjf.myself.codebase.util.ViewUtils;

/**
 * <pre>
 *     author : ZouJianFeng
 *     e-mail : 1434690833@qq.com
 *     time   : 2017/08/10
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class HRNormalFragment extends BaseFragment{

    public static HRNormalFragment newInstance() {
        HRNormalFragment f = new HRNormalFragment();
        return f;
    }

    @Override
    protected void initView() {


    }

    @Override
    public void onResume() {
        super.onResume();

    }


    @Override
    protected int layoutId() {
        return R.layout.fragment_hr_normal;
    }


}
