package com.zjf.myself.codebase.model;

import android.content.Intent;

/**
 * Created by Administrator on 2016/12/22.
 */

public class BasicListViewInfo {
    private int imgLeft;
    private String txtLeft;
    private String txtRight;
    Intent it;

    public BasicListViewInfo(int imgLeft, String txtLeft, String txtRight, Intent it) {
        this.imgLeft = imgLeft;
        this.txtLeft = txtLeft;
        this.txtRight = txtRight;
        this.it = it;
    }

    public BasicListViewInfo(int imgLeft, String txtLeft, String txtRight) {
        this.imgLeft = imgLeft;
        this.txtLeft = txtLeft;
        this.txtRight = txtRight;
    }

    public int getImgLeft() {
        return imgLeft;
    }

    public void setImgLeft(int imgLeft) {
        this.imgLeft = imgLeft;
    }

    public String getTxtRight() {
        return txtRight;
    }

    public void setTxtRight(String txtRight) {
        this.txtRight = txtRight;
    }

    public String getTxtLeft() {
        return txtLeft;
    }

    public void setTxtLeft(String txtLeft) {
        this.txtLeft = txtLeft;
    }

    public Intent getIt() {
        return it;
    }

    public void setIt(Intent it) {
        this.it = it;
    }

    @Override
    public String toString() {
        return "BasicListViewInfo{" +
                "imgLeft=" + imgLeft +
                ", txtLeft='" + txtLeft + '\'' +
                ", txtRight='" + txtRight + '\'' +
                '}';
    }
}
