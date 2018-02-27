package com.zjf.myself.codebase.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.application.AppController;


public abstract class BaseDialog {

	protected Dialog dialog;
	protected Window dialogWindow;
	protected boolean isShow = false;
	protected View contentView;
	protected Context context;
	private int animStyle= R.style.dialogWindowAnim;

	public BaseDialog() {
		context = AppController.getInstance().getTopAct();
		dialog = new Dialog(context, R.style.dialog);
		dialogWindow = this.dialog.getWindow();
		dialogWindow.setGravity(getGravity());
		dialogWindow.getDecorView().setPadding(0, 0, 0, 0);
		setHeightAndWidth();
		contentView = createContentView();
		initView();
		if(isShowAnimation())
			initAnim();
	}

	public BaseDialog(int gravity,int topPadding) {

		context = AppController.getInstance().getTopAct();
		dialog = new Dialog(context, R.style.dialog);
		dialogWindow = this.dialog.getWindow();
		dialogWindow.setGravity(gravity);
		dialogWindow.getDecorView().setPadding(0, topPadding, 0, 0);
		setHeightAndWidth();
		contentView = createContentView();
		initView();
		if(isShowAnimation())
			initAnim();
	}

	protected  void setHeightAndWidth() {
		WindowManager.LayoutParams lp;
		lp = dialogWindow.getAttributes();
		lp.width = WindowManager.LayoutParams.MATCH_PARENT;
		lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
		dialogWindow.setAttributes(lp);
	}
	public void initAnim() {
		dialogWindow.setWindowAnimations(animStyle);
	}

	abstract protected void initView();

	abstract public int getLayoutId();




	/***
	 * 默认显示动画
	 * @return
	 */
	protected boolean isShowAnimation() {
		return true;
	}

	protected int setAnimation(int animStyle) {
		return this.animStyle =animStyle;
	}

	protected View findViewById(int id) {
		return contentView.findViewById(id);
	}

	protected View createContentView() {
		return LayoutInflater.from(context).inflate(getLayoutId(), null);
	}

	public void show() {
		if (dialog.isShowing())
			return;
		dialog.getWindow().setContentView(contentView);
		dialog.show();
		isShow = true;

		this.dialog.setOnDismissListener(new OnDismissListener() {
			@Override
			public void onDismiss(DialogInterface dialog){
				isShow = false;
				doOnDismiss();
			}
		});
	}


	protected void doOnDismiss() {
	}

	public void dismiss() {
		this.dialog.cancel();
		AppController.cancelAll(this);
		isShow = false;
	}

	protected void okDismiss() {
		this.dialog.dismiss();
		isShow = false;
	}

	public boolean isShow() {
		return isShow;
	}

	public int getGravity() {
		return Gravity.BOTTOM;
	}

	public Context getContext(){
		return context;
	}

}
