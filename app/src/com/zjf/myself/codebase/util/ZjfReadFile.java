package com.zjf.myself.codebase.util;

import android.widget.Toast;

import com.zjf.myself.codebase.application.AppController;
import com.zjf.myself.codebase.helper.FileHelper;

import java.io.IOException;

/**
 * Created by Administrator on 2017/3/16.
 */

public class ZjfReadFile {

    public static String readFile(String fileName){

        String detail = "";
        FileHelper fHelper2 = new FileHelper(AppController.getInstance().getTopAct());
        try {
            detail = fHelper2.read(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return detail;
    }
}
