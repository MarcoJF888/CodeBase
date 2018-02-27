package com.zjf.myself.codebase.model;

/**
 * Created by Administrator on 2017/2/15.
 */

public class JiangJiangInfo {
    private String txtLabel;
    private String txtResult;


    public JiangJiangInfo(String txtLabel, String txtResult) {
        this.txtLabel = txtLabel;
        this.txtResult = txtResult;
    }

    public String getTxtLabel() {
        return txtLabel;
    }

    public void setTxtLabel(String txtLabel) {
        this.txtLabel = txtLabel;
    }

    public String getTxtResult() {
        return txtResult;
    }

    public void setTxtResult(String txtResult) {
        this.txtResult = txtResult;
    }

    @Override
    public String toString() {
        return "JiangJiangInfo{" +
                "txtLabel='" + txtLabel + '\'' +
                ", txtResult='" + txtResult + '\'' +
                '}';
    }
}
