package com.zjf.myself.codebase.helper;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.zjf.myself.codebase.application.AppController;
import com.zjf.myself.codebase.thirdparty.cropView.Crop;
import com.zjf.myself.codebase.util.CallBack;
import com.zjf.myself.codebase.util.PicTools;

import java.io.File;

/**
 * Created by Administrator on 2017/2/17.
 */

public class CropHelper {

    public static void beginCrop(Uri source) {
        Uri outputUri = Uri.fromFile(PicTools.getOutputPhotoFile());
        new Crop(source).output(outputUri).asSquare().withMaxSize(400, 400).start(AppController.getInstance().getTopAct());
    }

    public static void handleCrop(int resultCode, Intent result, CallBack callBack) {
        if (resultCode == Activity.RESULT_OK) {
            File tmpFile = PicTools.getOutputPhotoFile();
            callBack.onCall(tmpFile);
        } else if (resultCode == Crop.RESULT_ERROR) {
            Toast.makeText(AppController.getInstance().getTopAct(), Crop.getError(result).getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}
