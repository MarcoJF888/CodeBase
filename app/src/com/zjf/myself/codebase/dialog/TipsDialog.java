package com.zjf.myself.codebase.dialog;

import android.text.Spanned;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.util.CallBack;
import com.zjf.myself.codebase.util.DesityUtil;


public class TipsDialog extends BaseDialog implements View.OnClickListener{

	private Button btnOk;
	private CallBack callBack;
	private TextView txtMsg,txtTitle;
    private boolean isShowAnimation,isShowTitle=false;
    private View layoutTitle;
    
	@Override
	protected void initView() {

		dialogWindow.getDecorView().setPadding(DesityUtil.dp2px(getContext(),30),0,DesityUtil.dp2px(getContext(),30),0);
		btnOk=(Button) findViewById(R.id.btn_ok);
		txtMsg= (TextView) findViewById(R.id.txt_msg);
		btnOk.setOnClickListener(this);
	
		layoutTitle=findViewById(R.id.layout_title);
		
		txtTitle=(TextView) findViewById(R.id.txtTitle);
	}

	public Button getOkbutton(){
		return btnOk;
	}

	
	public TextView getContextText(){
		return txtMsg;
	}
	
	public TextView getTitle(){
		return txtTitle;
	}
	
	public void setIsShowTitle(boolean isShowTitle){
		this.isShowTitle=isShowTitle;
	}
	
	@Override
	protected boolean isShowAnimation() {
		return isShowAnimation;
	}
	
	public void setIsShowAnimation(boolean isShowAnimation){
		this.isShowAnimation=isShowAnimation;
	}
	
	@Override
	public int getLayoutId() {
		return R.layout.dialog_tips;
	}

	@Override
	public int getGravity() {	
		return Gravity.CENTER;
	}
	
	public void show(CallBack callBack,Spanned msg) {
		super.show();
		this.callBack=callBack;
		txtMsg.setText(msg);
	}

	public void show(String title,String msg,CallBack callBack) {
		super.show();
		this.callBack=callBack;
		txtTitle.setText(title);
		txtMsg.setText(msg);
	}

	public void show(CallBack callBack) {
		if(isShowTitle)
			layoutTitle.setVisibility(View.VISIBLE);
		super.show();
		this.callBack=callBack;
	}
	
	@Override
	public void onClick(View v) {
		 if(v==btnOk)
		{
			this.callBack.onCall(null);
		}
		 dismiss();

	}

}
