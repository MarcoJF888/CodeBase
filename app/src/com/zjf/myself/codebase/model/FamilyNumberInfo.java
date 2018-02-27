package com.zjf.myself.codebase.model;

/**
 * Created by Administrator on 2016/11/24.
 */

public class FamilyNumberInfo {

    public static final int TYPE_IMPORTANT_CONTACT=1;
    private String relation;
    private String phone;
    private int sign;
    private int curPos;

    public int getCurPos() {
        return curPos;
    }

    public void setCurPos(int curPos) {
        this.curPos = curPos;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getSign() {
        return sign;
    }

    public void setSign(int sign) {
        this.sign = sign;
    }
}
