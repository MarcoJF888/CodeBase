package com.zjf.myself.codebase.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * Created by Administrator on 2017/1/4.
 */

public class AutoTunThread {

    private boolean isRunning=false;
    private int interval=1000;

    private boolean runAways=false;

    private long residueTime;

    private RunningThread runningThread;

    private AutoRunCallBack autoRunCallBack;

    public AutoTunThread(long time, int interval, AutoRunCallBack autoRunCallBack){
        if(interval>1000)
            this.interval=interval;

        this.residueTime=time;
        this.autoRunCallBack=autoRunCallBack;
    }

    public AutoTunThread(boolean runAways, int interval, AutoRunCallBack autoRunCallBack){
        this.runAways=runAways;

        if(interval>1000)
            this.interval=interval;

        this.autoRunCallBack=autoRunCallBack;
    }


    public synchronized void start() {
        if(isRunning)
            return;

        isRunning=true;
        runningThread=new RunningThread();
        runningThread.start();
    }

    public void stopRun(){
        if(isRunning){
            isRunning=false;
            runningThread.interrupt();
        }
    }


    private void callOnDoing(){
        if(autoRunCallBack!=null){
            getUiHandler().post(new Runnable() {
                @Override
                public void run() {
                    autoRunCallBack.onDiong();
                }
            });
        }
    }

    private void callOnStop(){
        if(autoRunCallBack!=null){
            getUiHandler().post(new Runnable() {
                @Override
                public void run() {
                    autoRunCallBack.onFinish();
                }
            });
        }
    }

    class RunningThread extends Thread{

        @Override
        public void run() {
            long remind=residueTime;
            try {
                while (isRunning){
                    if(runAways){
                        callOnDoing();
                    }else {
                        if(remind>0){
                            callOnDoing();
                            remind=remind-interval;
                        }else {
                            break;
                        }
                    }

                    Thread.sleep(interval);
                }
            } catch (Exception e) {

            }finally {
                isRunning=false;
                callOnStop();
            }

        }

    }


    private UIHandler uiHandler;
    protected UIHandler getUiHandler() {
        if (this.uiHandler == null) {
            this.uiHandler = new  UIHandler();
        }
        return this.uiHandler;
    }

    private static class UIHandler extends Handler {
        public UIHandler() {
            super(Looper.getMainLooper());
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }

    }


    public interface AutoRunCallBack{
        void onDiong();
        void onFinish();
    }

}
