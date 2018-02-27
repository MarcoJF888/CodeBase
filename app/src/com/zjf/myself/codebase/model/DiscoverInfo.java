package com.zjf.myself.codebase.model;

import android.content.Intent;

public class DiscoverInfo {
    private int imgID;
    private String menuName;
    private String mainContent;
    private String secondContent;
    private String btnText;

    private String linkURL;
    private Intent it;

    public DiscoverInfo(int imgID, String menuName, String mainContent, String secondContent, String btnText, String linkURL) {
        this.imgID = imgID;
        this.menuName = menuName;
        this.mainContent = mainContent;
        this.secondContent = secondContent;
        this.btnText = btnText;
        this.linkURL = linkURL;
    }

    public DiscoverInfo(int imgID, String menuName, String mainContent, String secondContent, String btnText, Intent it) {
        this.imgID = imgID;
        this.menuName = menuName;
        this.mainContent = mainContent;
        this.secondContent = secondContent;
        this.btnText = btnText;
        this.it = it;
    }

    public int getImgID() {
        return imgID;
    }

    public void setImgID(int imgID) {
        this.imgID = imgID;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMainContent() {
        return mainContent;
    }

    public void setMainContent(String mainContent) {
        this.mainContent = mainContent;
    }

    public String getSecondContent() {
        return secondContent;
    }

    public void setSecondContent(String secondContent) {
        this.secondContent = secondContent;
    }

    public String getBtnText() {
        return btnText;
    }

    public void setBtnText(String btnText) {
        this.btnText = btnText;
    }

    public String getLinkURL() {
        return linkURL;
    }

    public void setLinkURL(String linkURL) {
        this.linkURL = linkURL;
    }

    public Intent getIt() {
        return it;
    }

    public void setIt(Intent it) {
        this.it = it;
    }
}
