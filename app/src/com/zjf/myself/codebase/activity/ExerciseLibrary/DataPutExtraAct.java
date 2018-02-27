package com.zjf.myself.codebase.activity.ExerciseLibrary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.activity.BaseAct;

/**
 * Created by Administrator on 2016/12/26.
 */

public class DataPutExtraAct extends BaseAct {
        private TextView txtPutExtra,txtPutExtra2;
        private Button btnPutExtra;
        private EditText edtPutData;
        Intent data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.win_data_putextra);

        btnPutExtra = (Button) findViewById(R.id.btnPutExtra);

        edtPutData = (EditText) findViewById(R.id.edtPutData);


        btnPutExtra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(("".equals(edtPutData.getText().toString().trim()))){
                    String scanResult  =  null;
                    Intent intent = new Intent(DataPutExtraAct.this,DataPutExtraAct2.class);
                    intent.putExtra("extra_data", scanResult);
                    startActivity(intent);
                }else {
                    String scanResult  =  edtPutData.getText().toString();
                    Intent intent = new Intent(DataPutExtraAct.this,DataPutExtraAct2.class);
                    intent.putExtra("extra_data", scanResult);
                    startActivity(intent);
                }


            }
        });

    }
}