package com.zjf.myself.codebase.dialog;

import android.text.Spanned;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.util.CallBack;
import com.zjf.myself.codebase.util.DesityUtil;
import com.zjf.myself.codebase.util.StringUtil;
import com.zjf.myself.codebase.util.ViewUtils;


public class VerifyDialog extends BaseDialog implements View.OnClickListener{

    public static final int RESULT_OK=1;
    private int result;
    private ResultListener resultListener;

    private Button btnCancel,btnOk;
    private CallBack callBack;
    private TextView txtMsg,txtTitle;
    private boolean isShowAnimation,isShowTitle=false;
    private View layoutTitle;

    String title="";
    String msg="";
    String ok="";

    public VerifyDialog(){
    }

    public VerifyDialog(String title,String msg,CallBack callBack){
        this.callBack=callBack;
        this.title=title;
        this.msg=msg;
    }

    @Override
    protected void initView() {
        dialogWindow.getDecorView().setPadding(DesityUtil.dp2px(getContext(),30),0,DesityUtil.dp2px(getContext(),30),0);
        btnCancel=(Button) findViewById(R.id.btn_cancel);
        btnOk=(Button) findViewById(R.id.btn_ok);
        txtMsg= (TextView) findViewById(R.id.txt_msg);
        btnCancel.setOnClickListener(this);
        btnOk.setOnClickListener(this);

        layoutTitle=findViewById(R.id.layout_title);

        txtTitle=(TextView) findViewById(R.id.txtTitle);
    }

    public void setResultListener(ResultListener resultListener) {
        this.resultListener = resultListener;
    }

    @Override
    protected void doOnDismiss() {
        super.doOnDismiss();
        if(resultListener!=null){
            if(result==RESULT_OK){
                resultListener.onOk();
                result=0;
            }else {
                resultListener.onCancle();
            }
        }

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
        return R.layout.dialog_verify;
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
        this.callBack=callBack;
        this.title=title;
        this.msg=msg;
        show();
    }

    public void show(String title,String msg,String ok,CallBack callBack) {
        this.callBack=callBack;
        this.title=title;
        this.msg=msg;
        this.ok=ok;
        show();
    }

    public void show(CallBack callBack) {
        if(isShowTitle)
            layoutTitle.setVisibility(View.VISIBLE);
        this.callBack=callBack;
        show();
    }

    @Override
    public void show() {
        ViewUtils.setText(txtTitle,title);
        ViewUtils.setText(txtMsg,msg);

        if(!StringUtil.isNull(ok))
            btnOk.setText(ok);

        super.show();
    }

    @Override
    public void onClick(View v) {
        if(v==btnOk) {
            if(callBack!=null){
                callBack.onCall(null);
            }
            result=RESULT_OK;
        }
        dismiss();

    }


    public interface ResultListener{
        void onOk();
        void onCancle();
    }

}
