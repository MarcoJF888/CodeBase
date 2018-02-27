package com.zjf.myself.codebase.activity.ExerciseLibrary;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.activity.BaseAct;
import com.zjf.myself.codebase.model.BuilderUserTestInfo;

/**
 * <pre>
 *     author : ZouJianFeng
 *     e-mail : 1434690833@qq.com
 *     time   : 2018/01/13
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class BuilderTestAct extends BaseAct{
    private Button btnAdd;
    private TextView txtResult;
    private  BuilderUserTestInfo builderUserTestInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_builder_test);
        initView();
        initData();
    }

    private void initView() {
        txtResult = (TextView)findViewById(R.id.txtResult);
        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  builderUserTestInfo = new BuilderUserTestInfo.UserBuilder("Z","jf")
                        .age(130)
                        .phone("18888888888")
                        .address("china sz")
                        .build();

                showResult();
            }
        });


    }

    private void showResult() {
        txtResult.setText(builderUserTestInfo.toString());
    }

    private void initData() {

    }

}
