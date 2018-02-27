package com.zjf.myself.codebase.activity.ExerciseLibrary;

import android.app.Activity;
import android.os.Bundle;

import com.zjf.myself.codebase.R;

/**
 * Created by Administrator on 2016/12/26.
 */

public class MultiThreadAct extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.win_multi_thread);

        TestThread t = new TestThread();
        new Thread(t).start();
        // 循环输出
        for (int i = 0; i < 10; i++) {
            System.out.println("main 线程在运行");
        }

    }

     class TestThread implements Runnable
    {
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println("TestThread 在运行");
            }
        }
    }
}

