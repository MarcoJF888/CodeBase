package com.zjf.myself.codebase.model;

import com.google.gson.annotations.SerializedName;

/**
 * <pre>
 *     author : ZouJianFeng
 *     e-mail :
 *     time   : 2017/05/06
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class NewsRecycleViewGridInfo {
    @SerializedName("id")
    private String newsIconUrl;//图片的网址即picSmall
    @SerializedName("name")
    private String newsTitle;//图片的标题即json中的name属性
    @SerializedName("description")
    private String newsContent;//图片的内容即json中的description
    private int iconID;

    public NewsRecycleViewGridInfo(String newsIconUrl, String newsTitle, String newsContent) {
        this.newsIconUrl = newsIconUrl;
        this.newsTitle = newsTitle;
        this.newsContent = newsContent;
    }

    public NewsRecycleViewGridInfo(int iconID,String newsTitle, String newsContent) {
        this.newsTitle = newsTitle;
        this.newsContent = newsContent;
        this.iconID = iconID;
    }

    public String getNewsIconUrl() {
        return newsIconUrl;
    }

    public void setNewsIconUrl(String newsIconUrl) {
        this.newsIconUrl = newsIconUrl;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public int getIconID() {
        return iconID;
    }

    public void setIconID(int iconID) {
        this.iconID = iconID;
    }

}