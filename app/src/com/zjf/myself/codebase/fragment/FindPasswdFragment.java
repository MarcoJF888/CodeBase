package com.zjf.myself.codebase.fragment;

import android.view.View;
import android.widget.EditText;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.activity.LoginAct;
import com.zjf.myself.codebase.application.UserDataManager;
import com.zjf.myself.codebase.model.UserData;
import com.zjf.myself.codebase.util.StringUtil;
import com.zjf.myself.codebase.util.ViewUtils;


/**
 * Created by Administrator on 2017/2/11.
 */

public class FindPasswdFragment extends BaseFragment implements View.OnClickListener{
    private EditText eTxtPhoneNo, eTxtNewPasswd,eTxtConfirnPassWd,eTxtOldPassWd;
    private View btnFinish;

    private String teleCodeNum;

    private String phoneNo,newPassWd,oldPassWd,confirnPassWd,verificationCode;
    private UserDataManager mUserDataManager;

    public static FindPasswdFragment newInstance() {
        FindPasswdFragment f = new FindPasswdFragment();
        return f;
    }

    @Override
    protected void initView() {
        eTxtPhoneNo = (EditText) findViewById(R.id.eTxtPhoneNo);
        eTxtOldPassWd = (EditText) findViewById(R.id.eTxtOldPassWd);
        eTxtNewPasswd = (EditText) findViewById(R.id.eTxtNewPassWd);
        eTxtConfirnPassWd= (EditText) findViewById(R.id.eTxtConfirnNewPassWd);

        btnFinish=findViewById(R.id.btnFinish);
        btnFinish.setOnClickListener(this);

        if (mUserDataManager == null) {
            mUserDataManager = new UserDataManager(getActivity());
            mUserDataManager.openDataBase();                              //建立本地数据库
        }

    }

    private boolean phoneNoIsReady(){
        phoneNo= eTxtPhoneNo.getText().toString().trim();

        //用户不存在时返回，给出提示文字
        if(StringUtil.isNull(phoneNo)){
            ViewUtils.showToast("手机号码不能为空！");
            return false;
        }else if(!StringUtil.isMobileNO(phoneNo)){
            ViewUtils.showToast("请输入正确的手机号码！");
            return false;
        }

        int count=mUserDataManager.findUserByName(phoneNo);
        if(count<=0){
            ViewUtils.showToast("用户不存在，请重新输入");
            return false;
        }

        return true;
    }

    private boolean resetDataIsReady(){
        newPassWd= eTxtNewPasswd.getText().toString().trim();
        oldPassWd= eTxtOldPassWd.getText().toString().trim();
        confirnPassWd=eTxtConfirnPassWd.getText().toString().trim();

        if(!phoneNoIsReady()){
            return false;
        }else if(StringUtil.isNull(newPassWd) || StringUtil.isNull(oldPassWd) ){
            ViewUtils.showToast("密码不能为空");
            return false;
        }else if((eTxtNewPasswd.getText().toString().trim().length()< 6)){
            ViewUtils.showToast("密码格式有误,请输入6~16位密码!");
            return false;
        }else if(StringUtil.isNull(confirnPassWd)){
            ViewUtils.showToast("请输入确认密码");
            return false;
        }else if (!newPassWd.equals(confirnPassWd)){
            ViewUtils.showToast("两次输入密码不一致");
            return false;
        }

        return true;
    }

    private void resetPassWd(){
        if (!resetDataIsReady()){
            return;
        }

        String userName = eTxtPhoneNo.getText().toString().trim();
        String userPwd_old = eTxtOldPassWd.getText().toString().trim();
        String userPwd_new = eTxtNewPasswd.getText().toString().trim();
        String userPwdCheck = eTxtConfirnPassWd.getText().toString().trim();
        int result=mUserDataManager.findUserByNameAndPwd(userName, userPwd_old);
        if(result==1){                                             //返回1说明用户名和密码均正确,继续后续操作
            if(userPwd_new.equals(userPwdCheck)==false){           //两次密码输入不一样
                ViewUtils.showToast("密码确认不正确，请重新输入密码！");
                return ;
            } else {
                UserData mUser = new UserData(userName, userPwd_new);
                mUserDataManager.openDataBase();
                boolean flag = mUserDataManager.updateUserData(mUser);
                if (flag == false) {
                    ViewUtils.showToast("密码修改失败，请重新尝试");
                }else{
                    ViewUtils.showToast("密码修改成功！");

                    mUser.pwdresetFlag=1;
                    ((LoginAct)getActivity()).fragmentContorler.showTab(0);
                }
            }
        }else if(result==0){                                       //返回0说明用户名和密码不匹配，重新输入
            ViewUtils.showToast("密码不正确，请重新输入！");
            return;
        }



    }

    @Override
    public void onResume() {
        super.onResume();
        ((LoginAct)getActivity()).setTitle("找回密码");
        ((LoginAct)getActivity()).hideBtnBack(false);
        eTxtPhoneNo.setText("");
        eTxtNewPasswd.setText("");
        eTxtConfirnPassWd.setText("");
    }


    @Override
    protected int layoutId() {
        return R.layout.fragment_forget_pwd;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btnFinish:
                resetPassWd();
                break;

            default:
                break;

        }

    }
}
