package com.zjf.myself.codebase.thirdparty.networkbanner;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/3/29.
 */

public class Banner {

    public static final int BANNER_TYPE_DEFAULT=-1;

    int type;

    @SerializedName("title")
    String tile;

    @SerializedName("link")
    String url;

    @SerializedName("original_img")
    String imgUrl;

    int defImgId;

    public Banner(String tile, String url, int defImgId, int type) {
        this.tile = tile;
        this.url = url;
        this.defImgId = defImgId;
        this.type = type;
    }

    public Banner() {
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTile() {
        return tile;
    }

    public void setTile(String tile) {
        this.tile = tile;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getDefImgId() {
        return defImgId;
    }

    public void setDefImgId(int defImgId) {
        this.defImgId = defImgId;
    }
}
