package com.zjf.myself.codebase.application;


import android.os.Environment;

import java.io.File;

/** 通用常量类 */
public class AppConstants {

	public static final String WX_APP_ID="";

	public static final String WX_APP_SECRET="";

	public static final String QQ_APP_ID="";

	/** cache路径 */
	public static final String STORE_CACHE_PATH ="AJF/cache" ;

	/** 图片路径 */
	public static final String STORE_IMG_PATH = Environment.getExternalStorageDirectory().getPath()
			+ File.separator + "AJF/image" + File.separator;

	public static final String PHONE_PATTERN = "^((13[0-9])|(14[5,7])|(15[^4,\\DevOrwnerInfo])|(17[0-9])|(18[0-9]))\\d{8}$";
	public static final String EMAIL_PATTERN = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
	// 5-10位QQ号码
	public static final String QQ_PATTERN = "^[1-9]\\d{4,9}$";
	public static final String PASSWORD_PATTERN = "^[A-Za-z0-9]{6,16}$";

	public static final String INVITATION_CODE="0928";

	public static final int LOGIN_TYPE_APP=0;
	public static final int LOGIN_TYPE_QQ=1;
	public static final int LOGIN_TYPE_WX=2;

	public static final int SWITCH_ON =1;
	public static final int SWITCH_OFF=0;

	public static final int RESULT_OK=1;
	public static final int RESULT_FAILD=0;

	public static final int IS_LOGIN = 2;

}
