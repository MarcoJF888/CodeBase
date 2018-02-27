package com.zjf.myself.codebase.model;

/**
 * Created by Administrator on 2016/12/23.
 */

public class SleepInfo {

    private String timeST;
    private String timeED;
    private int allTime;
    private int shallow;
    private int deep;
    private int efficiency;

    public String getTimeST() {
        return timeST;
    }

    public void setTimeST(String timeST) {
        this.timeST = timeST;
    }

    public String getTimeED() {
        return timeED;
    }

    public void setTimeED(String timeED) {
        this.timeED = timeED;
    }

    public int getAllTime() {
        return allTime;
    }

    public void setAllTime(int allTime) {
        this.allTime = allTime;
    }

    public int getShallow() {
        return shallow;
    }

    public void setShallow(int shallow) {
        this.shallow = shallow;
    }

    public int getDeep() {
        return deep;
    }

    public void setDeep(int deep) {
        this.deep = deep;
    }

    public int getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(int efficiency) {
        this.efficiency = efficiency;
    }
}
