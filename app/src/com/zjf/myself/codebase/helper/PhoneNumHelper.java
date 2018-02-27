package com.zjf.myself.codebase.helper;

import android.widget.EditText;

import com.zjf.myself.codebase.util.StringUtil;
import com.zjf.myself.codebase.util.ViewUtils;


/**
 * Created by Administrator on 2017/2/8.
 */

public class PhoneNumHelper {

    public static boolean phoneNoIsReady(EditText ePhoneNo){

        String phoneNo;

        phoneNo=ePhoneNo.getText().toString().trim();
        if(StringUtil.isNull(phoneNo)){
            ViewUtils.showToast("手机号码不能为空！");
            return false;
        }else if(!StringUtil.isMobileNO(phoneNo)){
            ViewUtils.showToast("请输入正确的手机号码！");
            return false;
        }

//        else if(!(StringUtil.isMobileNO(phoneNo) || StringUtil.isFixedPhone(phoneNo))){
//            ViewUtils.showToast("请输入正确的电话号码！");
//            return false;
//        }

        return true;
    }
}
