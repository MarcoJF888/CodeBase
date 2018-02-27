package com.zjf.myself.codebase.activity.UILibrary;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.view.CirclePercentView;


public class CircleProgressAct extends Activity {

	private Button mButton;
	private CirclePercentView mCirclePercentView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.win_circle_progress);
		mCirclePercentView = (CirclePercentView) findViewById(R.id.circleView);
//
//		long stepScale = 1000 / ( 5000 / 100 );
////		int ii = new Long(stepScale).intValue();
//		mCirclePercentView.setPercent( stepScale );

		mButton = (Button) findViewById(R.id.button);
		mButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				int n = (int)(Math.random()*100);
				mCirclePercentView.setPercent( n );

			}
		});
	}
}
