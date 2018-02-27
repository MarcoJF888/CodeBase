package com.zjf.myself.codebase.model;

/**
 * Created by Administrator on 2016/12/15.
 */

public class FuncSleepInfo {

    private String txtSleepLabel;
    private String txtSleepData;
    private String txtMin;

    public FuncSleepInfo(){
    }

    public FuncSleepInfo(String txtSleepLabel, String txtSleepData, String txtMin) {
        this.txtSleepLabel = txtSleepLabel;
        this.txtMin = txtMin;
        this.txtSleepData = txtSleepData;
    }

    public String getTxtSleepData() {
        return txtSleepData;
    }

    public void setTxtSleepData(String txtSleepData) {
        this.txtSleepData = txtSleepData;
    }

    public String getTxtMin() {
        return txtMin;
    }

    public void setTxtMin(String txtMin) {
        this.txtMin = txtMin;
    }

    public String getTxtSleepLabel() {
        return txtSleepLabel;
    }

    public void setTxtSleepLabel(String txtSleepLabel) {
        this.txtSleepLabel = txtSleepLabel;
    }

    @Override
    public String toString() {
        return "FuncSleepInfo{" +
                "txtSleepLabel='" + txtSleepLabel + '\'' +
                ", txtSleepData='" + txtSleepData + '\'' +
                ", txtMin='" + txtMin + '\'' +
                '}';
    }
}
