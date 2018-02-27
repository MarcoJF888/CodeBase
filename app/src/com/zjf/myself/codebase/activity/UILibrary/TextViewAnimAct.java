package com.zjf.myself.codebase.activity.UILibrary;

import android.app.Activity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.view.NumberAnimTextView;

/**
 * Created by Administrator on 2017/2/25.
 */

public class TextViewAnimAct extends Activity implements View.OnClickListener{

    private TextView txtTest;

    private Button btnStart;

    private NumberAnimTextView mNumberAnimTextView;
    private NumberAnimTextView mNumberAnimTextView1;
    private NumberAnimTextView mNumberAnimTextView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.win_big_small_txt);

        txtTest = (TextView)findViewById(R.id.txtTest);
        SpannableString spanString = new SpannableString("小小大大大小小小小小小");
        spanString.setSpan(new AbsoluteSizeSpan(20, true), 2, 5, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        txtTest.setText(spanString);


        btnStart = (Button)findViewById(R.id.btnStart);
        btnStart.setOnClickListener(this);
        mNumberAnimTextView = (NumberAnimTextView) findViewById(R.id.text);
        mNumberAnimTextView1 = (NumberAnimTextView) findViewById(R.id.text1);
        mNumberAnimTextView2 = (NumberAnimTextView) findViewById(R.id.text2);


    }

    @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.btnStart:
                    mNumberAnimTextView.setPrefixString("¥");
                    mNumberAnimTextView.setNumberString("99998.123456789");

//                  mNumberAnimTextView1.setEnableAnim(true);
                    mNumberAnimTextView1.setNumberString("1234567");

                    mNumberAnimTextView2.setPostfixString("%");
                    mNumberAnimTextView2.setNumberString("99.75");
                    break;
                default:
                    break;
            }
        }

}