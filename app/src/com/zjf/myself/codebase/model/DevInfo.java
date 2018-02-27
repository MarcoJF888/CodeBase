package com.zjf.myself.codebase.model;

/**
 * Created by Administrator on 2016/12/9.
 */

public class DevInfo {


    private String name, deviceId,avatarImage;
    private int isroot;

    public DevInfo(String name) {
        this.name = name;
    }

    public DevInfo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getAvatarImage() {
        return avatarImage;
    }

    public void setAvatarImage(String avatarImage) {
        this.avatarImage = avatarImage;
    }

    public int getIsroot() {
        return isroot;
    }

    public void setIsroot(int isroot) {
        this.isroot = isroot;
    }
}
