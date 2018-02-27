package com.zjf.myself.codebase.activity;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.bugtags.library.Bugtags;
import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.application.AppController;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;


public abstract class BaseAct extends FragmentActivity {
	public Handler mMainHandler;
	protected Fragment mContentFragment;
	protected FragmentManager fm;
	private DrawerLayout mDrawerLayout;
//	private IUiListener tencentCallBack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		fm = getSupportFragmentManager();
		mMainHandler = new Handler(new Handler.Callback() {
			@Override
			public boolean handleMessage(Message msg) {
				BaseAct.this.handleMessage(msg);
				return true;
			}
		});
		AppController.addAct(this);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		ButterKnife.bind(this);
		//状态栏透明
//		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//			WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
//			localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
//			if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
//				//将侧边栏顶部延伸至status bar
//				mDrawerLayout.setFitsSystemWindows(true);
//				//将主页面顶部延伸至status bar;虽默认为false,但经测试,DrawerLayout需显示设置
//				mDrawerLayout.setClipToPadding(false);
//			}
//		}
	}



	@Override
	protected void onSaveInstanceState(Bundle outState) {
//		记录当前的position
//		outState.putInt("position", position);
	}

	public void setOnBack(View backView) {
		backView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		AppController.cancelAll(this);
		AppController.remove(this);
//
//		if(AppController.requestManager!=null){
//			AppController.requestManager.removeRequestByTag(this);
//			AppController.requestManager.removePushMessageReceiverByTag(this);
//		}
	}

	protected boolean replaceFragment(int id, Fragment fragment) {
		boolean isAdded = false;
		if (mContentFragment == fragment) {
			return true;
		}
		Fragment f = fm.findFragmentByTag(fragment.getClass().getName());
		FragmentTransaction bt = fm.beginTransaction();
		if (mContentFragment != null) {
			bt.hide(mContentFragment);
		}
		if (f == null) {
			// bt.add(fragment, fragment.getClass().getName());
			bt.replace(id, fragment);
		} else {
			isAdded = true;
			bt.show(fragment);
		}
		bt.commit();
		mContentFragment = fragment;
		return isAdded;
	}

	@Override
	protected void onResume() {
		super.onResume();
		//	MobclickAgent.onResume(this);
		Bugtags.onResume(this);
	}

	public void onPause() {
		super.onPause();
		//	MobclickAgent.onPause(this);
		Bugtags.onPause(this);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		Bugtags.onDispatchTouchEvent(this, ev);
		return super.dispatchTouchEvent(ev);
	}
//
//	@Override
//	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//		if(tencentCallBack!=null){
//			switch (requestCode){
//
//				case Constants.REQUEST_QQ_SHARE:
//				case Constants.REQUEST_LOGIN :
//				case  Constants.REQUEST_APPBAR:
//					Tencent.onActivityResultData(requestCode,resultCode,data,tencentCallBack);
//					break;
//			}
//		}
//	}


//	public void setTencentCallBack(IUiListener tencentCallBack){
//		this.tencentCallBack=tencentCallBack;
//	}


	protected void handleMessage(Message msg) {

	}

	public void keyBoardCancle() {
		View view = getWindow().peekDecorView();
		if (view != null) {
			InputMethodManager inputmanger = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
			inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
		}
	}

	public void  showKeyboard() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
			}
		}, 500);
	}

	public Message obtainMessage() {
		return mMainHandler.obtainMessage();
	}

	public Message obtainMessage(int what) {
		return mMainHandler.obtainMessage(what);
	}

	public Message obtainMessage(int what, Object obj) {
		return mMainHandler.obtainMessage(what, obj);
	}

	public Message obtainMessage(int what, int arg1, int arg2) {
		return mMainHandler.obtainMessage(what, arg1, arg2);
	}

	public void post(Runnable r) {
		mMainHandler.post(r);
	}

	public void close() {
		super.finish();
	}

	@Override
	public void finish() {
		super.finish();
		overridePendingTransition(R.anim.slide_right_in, R.anim.slide_right_out);
	}

}