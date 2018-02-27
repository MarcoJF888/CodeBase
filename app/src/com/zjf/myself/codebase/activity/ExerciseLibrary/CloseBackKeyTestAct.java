package com.zjf.myself.codebase.activity.ExerciseLibrary;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import android.view.WindowManager;
import android.widget.Button;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.activity.BaseAct;
import com.zjf.myself.codebase.util.ViewUtils;

/**
 * <pre>
 *     author : ZouJianFeng
 *     e-mail :
 *     time   : 2017/04/11
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class CloseBackKeyTestAct extends BaseAct implements View.OnClickListener{

    private  static final int CLOSE_BACK_KEY = 1;
    private  static final int OPEN_BACK_KEY = 2;
    private int backKeyState = OPEN_BACK_KEY;
    private Button btnCloseBackKey,btnOpenBackKey,btnBack;



     @Override
         protected void onCreate(Bundle savedInstanceState) {
             super.onCreate(savedInstanceState);
             setContentView(R.layout.act_close_back_key);

             btnCloseBackKey = (Button)findViewById(R.id.btnCloseBackKey);
             btnCloseBackKey.setOnClickListener(this);
             btnOpenBackKey = (Button)findViewById(R.id.btnOpenBackKey);
             btnOpenBackKey.setOnClickListener(this);
             btnBack = (Button)findViewById(R.id.btnBack);
             btnBack.setOnClickListener(this);

      }

    @Override
    public boolean onKeyDown(int keyCode,KeyEvent event){
        if (backKeyState == OPEN_BACK_KEY){

        }else if (backKeyState == CLOSE_BACK_KEY){
            switch(keyCode){
                case KeyEvent.KEYCODE_BACK:return true;
//            case KeyEvent.KEYCODE_HOME:return true;
//            case KeyEvent.KEYCODE_CALL:return true;
//            case KeyEvent.KEYCODE_SYM: return true;
//            case KeyEvent.KEYCODE_VOLUME_DOWN: return true;
//            case KeyEvent.KEYCODE_VOLUME_UP: return true;
//            case KeyEvent.KEYCODE_STAR: return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.btnCloseBackKey:
                    backKeyState = CLOSE_BACK_KEY;
                    ViewUtils.showToast("虚拟返回键和物理返回键关闭");
                    break;

                case R.id.btnOpenBackKey:
                    backKeyState = OPEN_BACK_KEY;
                    ViewUtils.showToast("虚拟返回键和物理返回键打开");
                    break;

                case R.id.btnBack:
                    if (backKeyState == OPEN_BACK_KEY){
                        finish();
                    }else if (backKeyState == CLOSE_BACK_KEY){
                        ViewUtils.showToast("虚拟返回键和物理返回键已关闭");
                    }

                    break;
                default:
                    break;
            }
        }
}
