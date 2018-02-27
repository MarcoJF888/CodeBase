package com.zjf.myself.codebase.fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.activity.HomeAct;
import com.zjf.myself.codebase.activity.LoginAct;
import com.zjf.myself.codebase.application.UserDataManager;
import com.zjf.myself.codebase.dialog.VerifyDialog;
import com.zjf.myself.codebase.helper.LoginHelper;
import com.zjf.myself.codebase.util.CallBack;
import com.zjf.myself.codebase.util.ViewUtils;


/**
 * Created by Administrator on 2016/12/3.
 */

public class LoginFragment extends BaseFragment implements View.OnClickListener{
    private SharedPreferences login_sp;
    private EditText eTxtUserName,eTxtPassWd;
    private View btnRegest,btnForgetPassWd,btnQQLogin,btnWXLogin;
    private TextView btnLogin,btnCancelAccount;

    private CheckBox cbDisplayPassword,mRememberCheck;

    private String phoneNo,passWd;

    boolean eyeOpen = false ;

    private UserDataManager mUserDataManager;

    public static LoginFragment newInstance() {
        LoginFragment f = new LoginFragment();
        return f;
    }

    @Override
    protected void initView() {
        eTxtUserName= (EditText) findViewById(R.id.eTxtPhoneNo);
        eTxtPassWd= (EditText) findViewById(R.id.eTxtPassWd);

        btnLogin=(TextView)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        btnLogin.setClickable(false);

        btnCancelAccount=(TextView)findViewById(R.id.btnCancelAccount);
        btnCancelAccount.setOnClickListener(this);

        mRememberCheck = (CheckBox) findViewById(R.id.Login_Remember);

        btnRegest=findViewById(R.id.btnRegest);
        btnRegest.setOnClickListener(this);

        btnForgetPassWd=findViewById(R.id.btnForgetPassWd);
        btnForgetPassWd.setOnClickListener(this);

        btnQQLogin=findViewById(R.id.btnQQLogin);
        btnQQLogin.setOnClickListener(this);

        btnWXLogin=findViewById(R.id.btnWXLogin);
        btnWXLogin.setOnClickListener(this);

        cbDisplayPassword = (CheckBox) findViewById(R.id.cbDisplayPassword);
        cbDisplayPassword.setOnClickListener(this);

        TextChange textChange = new TextChange();
        eTxtPassWd.addTextChangedListener(textChange);
        eTxtUserName.addTextChangedListener(textChange);

        login_sp = getActivity().getSharedPreferences("userInfo", 0);
        String name=login_sp.getString("USER_NAME", "");
        String pwd =login_sp.getString("PASSWORD", "");
        boolean choseRemember =login_sp.getBoolean("mRememberCheck", false);
        boolean choseAutoLogin =login_sp.getBoolean("mAutologinCheck", false);
        //如果上次选了记住密码，那进入登录页面也自动勾选记住密码，并填上用户名和密码
        if(choseRemember){
            eTxtUserName.setText(name);
            eTxtPassWd.setText(pwd);
            mRememberCheck.setChecked(true);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ((LoginAct)getActivity()).setTitle("登录");
        ((LoginAct)getActivity()).hideBtnBack(true);
        if (mUserDataManager == null) {
            mUserDataManager = new UserDataManager(getActivity());
            mUserDataManager.openDataBase();
        }
    }



    @Override
    protected int layoutId() {
        return R.layout.fragment_login;
    }



    private class TextChange implements TextWatcher {


        @Override
        public void onTextChanged(CharSequence cs, int start, int before, int count) {

        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
        }


        @Override
        public void afterTextChanged(Editable arg0) {
            if(("".equals(eTxtPassWd.getText().toString().trim())) || ("".equals(eTxtUserName.getText().toString().trim())) ) {
                btnLogin.setBackgroundResource(R.drawable.button_dis);
                btnLogin.setClickable(false);
            }else {
                btnLogin.setBackgroundResource(R.drawable.icon_button);
                btnLogin.setClickable(true);
            }
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin:
                login();
                break;

            case R.id.btnCancelAccount:
                new VerifyDialog().show("注销账号", "账号注销需重新注册","确定", new CallBack() {
                    @Override
                    public void onCall(Object data) {
                        cancel();
                    }
                });

                break;


            case R.id.btnRegest:
                ((LoginAct)getActivity()).fragmentContorler.showTab(1);
                break;

            case R.id.btnForgetPassWd:
                ((LoginAct)getActivity()).fragmentContorler.showTab(2);
                break;

            case R.id.btnQQLogin:
//                LoginHelper.loginByQQ(new DataCallBack() {
//                    @Override
//                    public void onSuccess(Object data) {
//                        startLogin((U         serInfo) data);
//                    }
//
//                    @Override
//                    public void onFaild() {
//                        ViewUtils.showToast("QQ登陆失败！");
//                    }
//                });
                ViewUtils.showToast("第三方登录暂不可用");
                break;

            case R.id.btnWXLogin:
//                LoginHelper.loginByWX(new DataCallBack() {
//                    @Override
//                    public void onSuccess(Object data) {
//                        startLogin((UserInfo) data);
//                    }
//
//                    @Override
//                    public void onFaild() {
//                        ViewUtils.showToast("微信登陆失败！");
//                    }
//                });
                ViewUtils.showToast("第三方登录暂不可用");
                break;

            case R.id.cbDisplayPassword:
                if ( eyeOpen ){
                    eTxtPassWd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    eyeOpen = false ;
                }else {
                    eTxtPassWd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    eyeOpen = true;
                }
                break;
        }
    }

    public void login() {                                              //登录按钮监听事件

            String userName = eTxtUserName.getText().toString().trim();    //获取当前输入的用户名和密码信息
            String userPwd = eTxtPassWd.getText().toString().trim();
            SharedPreferences.Editor editor =login_sp.edit();
            int result=mUserDataManager.findUserByNameAndPwd(userName, userPwd);
            if(result!=0){                                             //返回1说明用户名和密码均正确
                //保存用户名和密码
                editor.putString("USER_NAME", userName);
                editor.putString("PASSWORD", userPwd);

                //是否记住密码
                if(mRememberCheck.isChecked()){
                    editor.putBoolean("mRememberCheck", true);
                }else{
                    editor.putBoolean("mRememberCheck", false);
                }
                editor.commit();

                //记住登录状态，下次进来不用登陆
                long loginName = Long.valueOf(userName).longValue();
                LoginHelper.rememberLogin(loginName);


//                TODO 这里写跳转
                Intent gogogo = new Intent(getActivity(), HomeAct.class);
                startActivity(gogogo);
                getActivity().finish();
                ViewUtils.showToast("登录成功");//登录成功提示
            }else if(result==0){
                ViewUtils.showToast("登录失败！请输入正确的用户名与密码！");  //登录失败提示
            }
    }

    public void cancel() {//注销
        if (isUserNameAndPwdValid()){
            String userName = eTxtUserName.getText().toString().trim();    //获取当前输入的用户名和密码信息
            String userPwd = eTxtPassWd.getText().toString().trim();
            int result=mUserDataManager.findUserByNameAndPwd(userName, userPwd);
            if(result!=0){                                             //返回1说明用户名和密码均正确
//                Intent intent = new Intent(Login.this,User.class) ;    //切换Login Activity至User Activity
//                startActivity(intent);
                ViewUtils.showToast("注销成功");//注销成功提示
                eTxtPassWd.setText("");
                eTxtUserName.setText("");
                mUserDataManager.deleteUserDatabyname(userName);
            }else if(result==0){
                ViewUtils.showToast("注销失败！请输入正确的用户名与密码！");  //注销失败提示
             }
        }
    }

    public boolean isUserNameAndPwdValid() {
        if (eTxtUserName.getText().toString().trim().equals("")) {
            ViewUtils.showToast("用户名为空,请重新输入！");
            return false;
        } else if (eTxtPassWd.getText().toString().trim().equals("")) {
            ViewUtils.showToast("密码为空,请重新输入！");
            return false;
        }
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onPause() {
        if (mUserDataManager != null) {
            mUserDataManager.closeDataBase();
            mUserDataManager = null;
        }
        super.onPause();
    }

}





























































//    public static LoginFragment newInstance() {
//        LoginFragment f = new LoginFragment();
//        return f;
//    }
//
//    @Override
//    protected void initView() {}
//
//    @Override
//    public void onResume() {
//        super.onResume();
//
//    }
//
//    @Override
//    protected int layoutId() {
//        return R.layout.fragment_login;
//    }
//}

