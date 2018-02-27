package com.zjf.myself.codebase.activity.ExerciseLibrary;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zjf.myself.codebase.R;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by Administrator on 2016/12/21.
 */

public class DataStorageAct extends Activity {
    private EditText edtDataStorage;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.win_data_storage);

        edtDataStorage = (EditText) findViewById(R.id.edtDataStorage);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputText = edtDataStorage.getText().toString();
                save(inputText);
            }
        });
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//
//    }

    public void save(String inputText) {
        FileOutputStream out =null;
        BufferedWriter writer = null;
        try {
            out = openFileOutput("zjfdata", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputText);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                    Toast.makeText(DataStorageAct.this,"已保存",Toast.LENGTH_SHORT).show();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}









