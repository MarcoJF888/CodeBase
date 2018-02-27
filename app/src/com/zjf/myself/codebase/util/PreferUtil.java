package com.zjf.myself.codebase.util;

import android.content.Context;
import android.content.SharedPreferences;

//import com.google.gson.Gson;
//import com.yineng.android.model.UserInfo;


public final class PreferUtil {

    public static PreferUtil INSTANCE;
    private static SharedPreferences mPrefer;
    private static final String APP_NAME = "yineng_config";
    private static final String USER_INFO = "user_info";
    private static final String SOCKET_ADDR = "socket_addr";

    private static final String LOGIN_USER = "login_user";
    private static final String LOGIN_TOKEN = "login_token";
    private static final String TOPIC_BV = "Topicbv";
    private static final String ARTICLE_BV = "Articlebv";
    private static final String ARTICLEREPLY_BV = "ArticleReplybv";
    private static final String HOME_MAINTAIN = "homepage_maintain";

    private static final String NOTIFY_ALL = "notification_all";
    private static final String NOTIFY_LOGOUT = "notification_logout";
    private static final String NOTIFY_SLEEP = "notification_sleep";
    private static final String NOTIFY_ORDER = "notification_order";
    private static final String NOTIFY_MONEY = "notification_money";
    private static final String NOTIFY_SYSTEM = "notification_system";
    private static final String NOTIFY_FEEDBACK = "notification_feedback";
    private static final String CONTACT_UPDATE = "contact_update";

    private PreferUtil() {
    }

    public static PreferUtil getInstance() {
        if (INSTANCE == null) {
            return new PreferUtil();
        }
        return INSTANCE;
    }

    public void init(Context ctx) {
        mPrefer = ctx.getSharedPreferences(APP_NAME, 2);
        mPrefer.edit().commit();
    }


    public String getString(String key, String defValue) {
        return mPrefer.getString(key, defValue);
    }

    public int getInt(String key, int defValue) {
        return mPrefer.getInt(key, defValue);
    }

    public boolean getBoolean(String key, boolean defValue) {
        return mPrefer.getBoolean(key, defValue);
    }

    public void putString(String key, String value) {
        mPrefer.edit().putString(key, value).commit();
    }

    public void putInt(String key, int value) {
        mPrefer.edit().putInt(key, value).commit();
    }

    public void putBoolean(String key, boolean value) {
        mPrefer.edit().putBoolean(key, value).commit();
    }

    public void putLong(String key, long value) {
        mPrefer.edit().putLong(key, value).commit();
    }

    public long getLong(String key, long defValue) {
        return mPrefer.getLong(key, defValue);
    }

    public void removeKey(String key) {
        mPrefer.edit().remove(key).commit();
    }


//    public void setUserInfo(UserInfo userInfo){
//        if(userInfo==null){
//            putString(USER_INFO,"");
//        }else {
//            String data= new Gson().toJson(userInfo);
//            putString(USER_INFO,data);
//        }
//    }
//
//    public UserInfo getUserInfo(){
//       String userData=getString(USER_INFO,"");
//        try {
//            if(!StringUtil.isNull(userData)){
//                UserInfo userInfo=GsonParser.getInstance().getBean(userData,UserInfo.class);
//                return  userInfo;
//            }
//        } catch (AppException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }

    public void setSocketAddr(String socketAddr){
        putString(SOCKET_ADDR,socketAddr);
    }

    public String getSocketAddr(){
        return getString(SOCKET_ADDR,"");
    }




    public String getMyCircleTopicBV() {
        return getString(TOPIC_BV, "");
    }

    public void setMyCircleTopicBV(String tokenStr) {
        putString(TOPIC_BV, tokenStr);
    }

    public String getMyCircleArticleBV() {
        return getString(ARTICLE_BV, "");
    }

    public void setMyCircleArticleBV(String tokenStr) {
        putString(ARTICLE_BV, tokenStr);
    }

    public String getMyCircleArticleReplyBV() {
        return getString(ARTICLEREPLY_BV, "");
    }

    public void setMyCircleArticleReplyBV(String tokenStr) {
        putString(ARTICLEREPLY_BV, tokenStr);
    }

    public String getHomeMaintain() {
        return getString(HOME_MAINTAIN, "");
    }

    public void setHomeMaintain(String homeMaintain) {
        putString(HOME_MAINTAIN, homeMaintain);
    }


    /**
     * 接收消息通知
     *
     * @return
     */
    public boolean getNotifyAll() {
        return getBoolean(NOTIFY_ALL, true);
    }

    public void setNotifyAll(boolean flag) {
        putBoolean(NOTIFY_ALL, flag);
    }


    /**
     * 退出后仍接收消息通知
     *
     * @return
     */
    public boolean getNotifyLogout() {
        return getBoolean(NOTIFY_LOGOUT, true);
    }

    public void setNotifyLogout(boolean flag) {
        putBoolean(NOTIFY_LOGOUT, flag);
    }

    /**
     * 夜间防骚扰模式
     *
     * @return
     */
    public boolean getNotifySleep() {
        return getBoolean(NOTIFY_SLEEP, true);
    }

    public void setNotifySleep(boolean flag) {
        putBoolean(NOTIFY_SLEEP, flag);
    }

    /**
     * 接收订单消息提醒
     *
     * @return
     */
    public boolean getNotifyOrder() {
        return getBoolean(NOTIFY_ORDER, true);
    }

    public void setNotifyOrder(boolean flag) {
        putBoolean(NOTIFY_ORDER, flag);
    }

    /**
     * 接收资金消息提醒
     *
     * @return
     */
    public boolean getNotifyMoney() {
        return getBoolean(NOTIFY_MONEY, true);
    }

    public void setNotifyMoney(boolean flag) {
        putBoolean(NOTIFY_MONEY, flag);
    }

    /**
     * 接收系统消息提醒
     *
     * @return
     */
    public boolean getNotifySystem() {
        return getBoolean(NOTIFY_SYSTEM, true);
    }

    public void setNotifySystem(boolean flag) {
        putBoolean(NOTIFY_SYSTEM, flag);
    }

    /**
     * 接收店铺反馈消息提醒
     *
     * @return
     */
    public boolean getNotifyFeedback() {
        return getBoolean(NOTIFY_FEEDBACK, true);
    }

    public void setNotifyFeedback(boolean flag) {
        putBoolean(NOTIFY_FEEDBACK, flag);
    }


    /**
     * 商户端联系人列表更新日期
     * */
    public long getContactUpdateTime() {
        return getLong(CONTACT_UPDATE, 0);
    }

    public void setContactUpdateTime(long flag) {
        putLong(CONTACT_UPDATE, flag);
    }


    public boolean getIsFirstOpen() {
        boolean isInit = getBoolean("isInit", true);
        putBoolean("isInit", false);
        return isInit;
    }

}
