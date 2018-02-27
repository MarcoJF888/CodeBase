package com.zjf.myself.codebase.model;

/**
 * <pre>
 *     author : ZouJianFeng
 *     e-mail :
 *     time   : 2017/04/05
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class ExpressInfo {
    String time,ftime,context,location;

    public ExpressInfo(String time, String ftime, String context, String location) {
        this.time = time;
        this.ftime = ftime;
        this.context = context;
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFtime() {
        return ftime;
    }

    public void setFtime(String ftime) {
        this.ftime = ftime;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
