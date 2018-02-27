package com.zjf.myself.codebase.activity.ExerciseLibrary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zjf.myself.codebase.R;

/**
 * Created by Administrator on 2016/12/28.
 */

public class DataPutExtraAct2 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.win_data_putextra2);

        TextView txtPutExtra = (TextView) findViewById(R.id.txtPutExtra);
        Button btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent intent = getIntent();
        String data = intent.getStringExtra("extra_data");
        if (data == null){
            txtPutExtra.setText("您没有输入任何文字");
        } else{
            txtPutExtra.setText(data);
        }
    }
}