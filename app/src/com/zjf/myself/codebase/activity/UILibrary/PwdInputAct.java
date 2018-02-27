package com.zjf.myself.codebase.activity.UILibrary;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.activity.BaseAct;
import com.zjf.myself.codebase.util.ViewUtils;
import com.zjf.myself.codebase.view.MyEditText;
import com.zjf.myself.codebase.view.SecurityCodeView;

public class PwdInputAct extends BaseAct implements SecurityCodeView.InputCompleteListener {

    private MyEditText myEditText;
    private TextView textView;
    private TextView textViewComplete;

    private SecurityCodeView editText;
    private TextView text;
    private TextView txtResult;

    private EditText edt1,edt2,edt3,edt4,edt5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.win_pwd_input);

        textView = (TextView) findViewById(R.id.TextView);
        textViewComplete = (TextView) findViewById(R.id.TextView_complete);
        myEditText = (MyEditText) findViewById(R.id.MyEditText);

        txtResult = (TextView) findViewById(R.id.txtResult);

        edt1 = (EditText) findViewById(R.id.edt1);
        edt1.addTextChangedListener(tw);
        edt2 = (EditText) findViewById(R.id.edt2);
        edt2.addTextChangedListener(tw);
        edt3 = (EditText) findViewById(R.id.edt3);
        edt3.addTextChangedListener(tw);
        edt4 = (EditText) findViewById(R.id.edt4);
        edt4.addTextChangedListener(tw);
        edt5 = (EditText) findViewById(R.id.edt5);
        edt5.addTextChangedListener(tw);


        topView();
        bottomView();


    }

    private void topView(){
        myEditText.setOnEditTextContentListener(new MyEditText.EditTextContentListener() {
            @Override
            public void onComplete(CharSequence text) {
                if(text != null) {
                    textViewComplete.setText("您输入的为"+text);
//                    if (text.toString().equals("000000")){
//                        Intent gogogo = new Intent(PwdInputAct.this, AMainAct.class);
//                        startActivity(gogogo);
//                        PwdInputAct.this.finish();
//                    }
                }
            }

            @Override
            public void onTextChanged(CharSequence text) {
                if(text != null) {
                    textViewComplete.setText("");
                    textView.setText("输入内容为:"+text);
                }
            }
        });
    }


    private void  bottomView(){
        findViews();
        setListener();
    }

    private void setListener() {
        editText.setInputCompleteListener(this);
    }

    private void findViews() {
        editText = (SecurityCodeView) findViewById(R.id.scv_edittext);
        text = (TextView) findViewById(R.id.tv_text);
    }

    @Override
    public void inputComplete() {
        Toast.makeText(getApplicationContext(), "验证码是：" + editText.getEditContent(), Toast.LENGTH_LONG).show();
        if (!editText.getEditContent().equals("1234")) {
            text.setText("验证码输入错误");
            text.setTextColor(Color.RED);
        }
    }

    @Override
    public void deleteContent(boolean isDelete) {
        if (isDelete){
            text.setText("输入验证码表示同意《用户协议》");
            text.setTextColor(Color.BLACK);
        }
    }



    TextWatcher tw = new TextWatcher(){
        //@Override
        public void beforeTextChanged(CharSequence s, int start, int count,int after){
        }
        //@Override
        public void onTextChanged(CharSequence s, int start, int before, int count){
        }
        //@Override
        public void afterTextChanged(Editable s){
            if(s.toString().length() == 4){
                if(edt1.isFocused()) {
                    edt1.clearFocus();
                    edt2.requestFocus();
                } else if(edt2.isFocused()) {
                    edt2.clearFocus();
                    edt3.requestFocus();
                } else if(edt3.isFocused()) {
                    edt3.clearFocus();
                    edt4.requestFocus();
                } else if(edt4.isFocused()) {
                    edt4.clearFocus();
                    edt5.requestFocus();
                } else if(edt5.isFocused()) {
//                    edt5.clearFocus();
                }
            }else if(s.toString().length() == 0){
                if(edt1.isFocused()){

                } else if(edt2.isFocused()) {
                    edt2.clearFocus();
                    edt1.requestFocus();
                } else if(edt3.isFocused()) {
                    edt3.clearFocus();
                    edt2.requestFocus();
                } else if(edt4.isFocused()) {
                    edt4.clearFocus();
                    edt3.requestFocus();
                } else if(edt5.isFocused()) {
                    edt5.clearFocus();
                    edt4.requestFocus();
                }
            }

            String inputResult = edt1.getText().toString().trim()+edt2.getText().toString().trim()+edt3.getText().toString().trim()+edt4.getText().toString().trim()+edt5.getText().toString().trim();

            if (inputResult.toString().length() ==20){
                ViewUtils.setText(txtResult,"输入完毕,输入结果为"+inputResult);
                txtResult.setTextColor(getResources().getColor(R.color.txt_color_main));
            }else{
                ViewUtils.setText(txtResult,"请仔细输入正确的ICCID");
                txtResult.setTextColor(getResources().getColor(R.color.red500));
            }

        }
    };


}
