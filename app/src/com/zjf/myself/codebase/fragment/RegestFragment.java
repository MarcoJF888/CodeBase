package com.zjf.myself.codebase.fragment;


import android.text.Html;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.activity.LoginAct;
import com.zjf.myself.codebase.application.AppAccount;
import com.zjf.myself.codebase.application.AppConstants;
import com.zjf.myself.codebase.application.UserDataManager;
import com.zjf.myself.codebase.model.UserData;
import com.zjf.myself.codebase.util.StringUtil;
import com.zjf.myself.codebase.util.ViewUtils;

import static com.zjf.myself.codebase.application.Config.getConfig;


/**
 * Created by Administrator on 2016/12/3.
 */

public class RegestFragment extends BaseFragment implements View.OnClickListener  {

    private EditText eTxtUserName,eTxtPassWd,eTxtVerificationCode,eTxtConfirnPassWd;
    private View btnRegest;
    private CheckBox chkAgreement;

    private String teleCodeNum;
    private TextView txtAgreement;
    private UserDataManager mUserDataManager;
    private String phoneNo,passWd,confirnPassWd,verificationCode;


    public static RegestFragment newInstance() {
        RegestFragment f = new RegestFragment();
        return f;
    }

    @Override
    protected void initView() {
        eTxtUserName= (EditText) findViewById(R.id.eTxtPhoneNo);
        eTxtVerificationCode= (EditText) findViewById(R.id.eTxtVerificationCode);
        eTxtPassWd= (EditText) findViewById(R.id.eTxtPassWd);
        eTxtConfirnPassWd= (EditText) findViewById(R.id.eTxtConfirnPassWd);

        chkAgreement = (CheckBox) findViewById(R.id.chkAgreement);

        btnRegest=findViewById(R.id.btnRegest);
        btnRegest.setOnClickListener(this);


        txtAgreement = (TextView)findViewById(R.id.txtAgreement);
        txtAgreement.setText(Html.fromHtml("<font color=\'#00923f\'><U>用户协议</U></font>"));
        txtAgreement.setOnClickListener(this);

        setRegestEnable(true);

        if (mUserDataManager == null) {
            mUserDataManager = new UserDataManager(getActivity());
            mUserDataManager.openDataBase();                              //建立本地数据库
        }


    }

    private void setRegestEnable(boolean enable){
        if(enable){
            btnRegest.setBackgroundResource(R.drawable.icon_button);
        }else {
            btnRegest.setBackgroundResource(R.drawable.button_dis);
        }
        btnRegest.setEnabled(enable);
    }

    private boolean phoneNoIsReady(){
        phoneNo=eTxtUserName.getText().toString().trim();
        if(StringUtil.isNull(phoneNo)){
            ViewUtils.showToast("手机号码不能为空！");
            return false;
        }else if(!StringUtil.isMobileNO(phoneNo)){
            ViewUtils.showToast("请输入正确的手机号码！");
            return false;
        }

        return true;
    }

    private boolean regestDataIsReady(){
        passWd=eTxtPassWd.getText().toString().trim();
        verificationCode=eTxtVerificationCode.getText().toString().trim();
        confirnPassWd=eTxtConfirnPassWd.getText().toString().trim();

        if(!phoneNoIsReady()){
            return false;
        }else if(StringUtil.isNull(verificationCode)){
            ViewUtils.showToast("邀请码不能为空");
            return false;
        }else if(StringUtil.isNull(passWd)){
            ViewUtils.showToast("密码不能为空");
            return false;
        }else if((eTxtPassWd.getText().toString().trim().length()< 6)){
            ViewUtils.showToast("密码格式有误,请输入6~16位密码!");
            return false;
        }else if(StringUtil.isNull(confirnPassWd)){
            ViewUtils.showToast("请输入确认密码");
            return false;
        }else if (!passWd.equals(confirnPassWd)){
            ViewUtils.showToast("两次输入密码不一致");
            return false;
        }else if(!chkAgreement.isChecked()){
            ViewUtils.showToast("请阅读并同意用户协议");
            return false;
        }

        return true;
    }

    private void startRegester() {
        if (!regestDataIsReady())
            return;

            String userName = eTxtUserName.getText().toString().trim();
            String InvitationCode = eTxtVerificationCode.getText().toString().trim();
            String userPwd = eTxtPassWd.getText().toString().trim();

            //检查用户是否存在
            int count=mUserDataManager.findUserByName(userName);
            //用户已经存在时返回，给出提示文字
            if(count>0){
               ViewUtils.showToast("用户名已存在，请重新输入");
                return ;
            }

            if (InvitationCode.equals(AppConstants.INVITATION_CODE)){
                UserData mUser = new UserData(userName, userPwd);
                mUserDataManager.openDataBase();
                long flag = mUserDataManager.insertUserData(mUser); //新建用户信息
                if (flag == -1) {
                    ViewUtils.showToast("注册用户失败，请重新尝试！");
                }else{
                    ViewUtils.showToast("注册成功！");
                    ((LoginAct)getActivity()).fragmentContorler.showTab(0);
                }
            }else{
                ViewUtils.showToast("邀请码错误，请重新输入");
            }

        }


        @Override
    public void onResume() {
        super.onResume();
        ((LoginAct)getActivity()).setTitle("注册");
        ((LoginAct)getActivity()).hideBtnBack(false);
        eTxtUserName.setText("");
        eTxtVerificationCode.setText("");
        eTxtPassWd.setText("");
        eTxtConfirnPassWd.setText("");
    }


    @Override
    protected int layoutId() {
        return R.layout.fragment_regest;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btnRegest:
                startRegester();
                break;

            case R.id.txtAgreement:
//                WebviewAct.start("用户协议", "http://laole.yinengsz.com/agreement.php", getActivity());
                break;

            default:
                break;
        }
    }
}
