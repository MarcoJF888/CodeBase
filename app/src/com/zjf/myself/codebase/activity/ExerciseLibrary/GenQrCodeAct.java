package com.zjf.myself.codebase.activity.ExerciseLibrary;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.activity.BaseAct;
import com.zjf.myself.codebase.thirdparty.zxing.endocing.EncodingUtils;

/**
 * <pre>
 *     author : ZouJianFeng
 *     e-mail : 1434690833@qq.com
 *     time   : 2017/05/23
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class GenQrCodeAct extends BaseAct implements View.OnClickListener{
    private EditText edtInput;
    private Button btnGen;
    private ImageView imgResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_genqr_code);
        edtInput = (EditText)findViewById(R.id.edtInput);
        btnGen = (Button)findViewById(R.id.btnGen);
        btnGen.setOnClickListener(this);
        imgResult = (ImageView)findViewById(R.id.imgResult);

    }
    @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.btnGen:
                    String input = edtInput.getText().toString().trim();
                    //根据字符串生成二维码图片并显示在界面上，第二个参数为图片的大小（350*350）
                    Bitmap qrCodeBitmap = EncodingUtils.createQRCode(input, 650, 650,
                            BitmapFactory.decodeResource(getResources(), R.mipmap.avatar_male));
                    imgResult.setImageBitmap(qrCodeBitmap);
                    break;
                default:
                    break;
            }
        }
}
