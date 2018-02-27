package com.zjf.myself.codebase.activity.UILibrary;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.view.ClockView;

public class MIUIAct extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_miui_clock);
    }
}