package com.zjf.myself.codebase.thirdparty.NewsCompatibleTest;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.util.ViewUtils;

/**
 * <pre>
 *     author : ZouJianFeng
 *     e-mail : 1434690833@qq.com
 *     time   : 2017/06/15
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class NewsContentFragment extends Fragment {
    private View view;
    private TextView txtTitle,txtContent;

    @Override
    public  View onCreateView(LayoutInflater inflater , ViewGroup container, Bundle saveInstanceState){
        view = inflater.inflate(R.layout.news_contnet_fragment,container,false);
        return view;

    }

    public void refresh(String newsTitle,String newsContent){
        View visibilityLayout = view.findViewById(R.id.layoutContent);
        visibilityLayout.setVisibility(View.VISIBLE);

        txtTitle = (TextView) view.findViewById(R.id.txtNewsTitle);
        txtContent = (TextView) view.findViewById(R.id.txtNewsContent);

        ViewUtils.setText(txtTitle,newsTitle);
        ViewUtils.setText(txtContent,newsContent);

    }

}
