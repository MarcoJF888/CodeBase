package com.zjf.myself.codebase.activity.ExerciseLibrary;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.activity.BaseAct;
import com.zjf.myself.codebase.util.ViewUtils;

/**
 * Created by Administrator on 2017/1/22.
 */

public class SharedPreferencesTestAct extends Activity{
    private Button getData,putData;
    private int mCreatedCount = 1507911111;
    private int mGetCreatedCount,mGetCreatedCount1,mGetCreatedCount2;
    private String PREFS_NAME;
    private String AAA = "AAA";

    private EditText edtTest;
    private TextView txtTest;
     @Override
         protected void onCreate(Bundle savedInstanceState) {
             super.onCreate(savedInstanceState);
             setContentView(R.layout.win_share_preferences_test);

         getData = (Button) findViewById(R.id.getData);
         putData = (Button) findViewById(R.id.putData);
         txtTest = (TextView) findViewById(R.id.txtTest);
         edtTest = (EditText) findViewById(R.id.edtTest);


         putData.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 String text = edtTest.getText().toString();
                 int b =Integer.valueOf(text);
                 SharedPreferences settings = getSharedPreferences(PREFS_NAME, Activity.MODE_PRIVATE);
                 SharedPreferences.Editor editor = settings.edit();
                 editor.putInt("AAA",b);
                 editor.putInt("CREATED_COUNT", mCreatedCount);
                 editor.putInt("CREATED_COUNT1", mCreatedCount);
                 editor.commit();
             }
         });

         getData.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 SharedPreferences settings = getSharedPreferences(PREFS_NAME, Activity.MODE_PRIVATE);
                 mGetCreatedCount = settings.getInt("CREATED_COUNT", 1);
                 mGetCreatedCount1 = settings.getInt("CREATED_COUNT1", 1);
                 mGetCreatedCount2 = settings.getInt("AAA", 1);
                 txtTest.setText(""+mGetCreatedCount+mGetCreatedCount1+"前面是设置系统自带的,后面是你输入的："+mGetCreatedCount2);
             }
         });
      }

}
