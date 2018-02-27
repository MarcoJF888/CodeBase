package com.zjf.myself.codebase.activity.ExerciseLibrary;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.zjf.myself.codebase.R;

/**
 * Created by Administrator on 2017/1/19.
 */

public class LifeCycleAct extends Activity{
    private static final String TAG = "LifeCycleAct";
    private EditText editView;
    private String mString;
     @Override
         protected void onCreate(Bundle savedInstanceState) {
             super.onCreate(savedInstanceState);
             setContentView(R.layout.win_life_cycle);
//         editView = (EditText) findViewById(R.id.editView);
             Log.e(TAG, "start onCreate~~~");
     }
    
        @Override
        protected void onStart() {
            super.onStart();
            Log.e(TAG, "start onStart~~~");
        }
    
        @Override
        protected void onRestart() {
            super.onRestart();
//            editView.setText(mString);
            Log.e(TAG, "start onRestart~~~");
        }
    
        @Override
        protected void onResume() {
            super.onResume();
            Log.e(TAG, "start onResume~~~");
        }
    
        @Override
        protected void onPause() {
            super.onPause();
//            mString = editView.getText().toString();
            Log.e(TAG, "start onPause~~~");
        }
    
        @Override
        protected void onStop() {
            super.onStop();
            Log.e(TAG, "start onStop~~~");
        }
    
        @Override
        protected void onDestroy() {
            super.onDestroy();
            Log.e(TAG, "start onDestroy~~~");
        }
    
    }