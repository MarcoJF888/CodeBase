package com.zjf.myself.codebase.activity.UILibrary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.zjf.myself.codebase.R;


public class TextInputLayoutAct extends AppCompatActivity {

    private TextInputLayout mTextInputLayout,layoutInput;
    private EditText edtInput;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_text_input_layout);

        mTextInputLayout = (TextInputLayout) findViewById(R.id.textInputLayout);

        //开启计数
        mTextInputLayout.setCounterEnabled(true);
        mTextInputLayout.setCounterMaxLength(6);

        //定义错误提示
        mTextInputLayout.getEditText().addTextChangedListener(new MyTextWatch(mTextInputLayout, "长度不能超过6个字符"));


        layoutInput = (TextInputLayout) findViewById(R.id.layoutInput);
        edtInput = (EditText)findViewById(R.id.edtInput);
        addTextChangedListener(edtInput,layoutInput);
        //在数据检查中提示
//        layoutInput.setError("姓名不能为空");
    }

    class MyTextWatch implements TextWatcher {

        TextInputLayout mTextInputLayout;
        String mErrorTip;

        public MyTextWatch(TextInputLayout textInputLayout, String errorTip) {
            mTextInputLayout = textInputLayout;
            mErrorTip = errorTip;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (mTextInputLayout.getEditText().getText().toString().trim().length() > 6) {
                mTextInputLayout.setErrorEnabled(true);
                mTextInputLayout.setError(mErrorTip);
            } else {
                mTextInputLayout.setErrorEnabled(false);
            }
        }
    }



    private void addTextChangedListener(EditText edt, final TextInputLayout txtlayout) {
        edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                txtlayout.setErrorEnabled(false);
            }
        });
    }

}
