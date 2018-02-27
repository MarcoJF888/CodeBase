package com.zjf.myself.codebase.model;

/**
 * Created by Administrator on 2016/12/23.
 */

public class RecycleViewInfo {
    private String name,avatarImage;
    private int deviceId;

    public RecycleViewInfo(String name) {
        this.name = name;
    }

    public RecycleViewInfo(String name,int deviceId) {
        this.deviceId = deviceId;
        this.name = name;
    }

    public RecycleViewInfo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String getAvatarImage() {
        return avatarImage;
    }

    public void setAvatarImage(String avatarImage) {
        this.avatarImage = avatarImage;
    }
}
