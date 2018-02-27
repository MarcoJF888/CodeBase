package com.zjf.myself.codebase.fragment;

import android.content.Intent;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.activity.UILibrary.NavigationQQLeftAct;


public class MenuLeftFragment extends BaseFragment implements View.OnClickListener{
	private RelativeLayout layout1,layout2;

	@Override
	protected void initView() {

	layout1 = (RelativeLayout)findViewById(R.id.layout1);
	layout1.setOnClickListener(this);
	layout2 = (RelativeLayout)findViewById(R.id.layout2);
 	layout2.setOnClickListener(this);
	}

	@Override
	public void onResume() {
		super.onResume();

	}

	@Override
	protected int layoutId() {
		return R.layout.layout_menu;
	}

	@Override
	public void onClick(View v){
		switch (v.getId()){

			case R.id.layout1:
				Toast.makeText(getActivity(),"1", Toast.LENGTH_SHORT).show();
				NavigationQQLeftAct.closeLeft();

				break;

			case R.id.layout2:
				Toast.makeText(getActivity(),"2", Toast.LENGTH_SHORT).show();

				break;

			default:
				break;
		}
	}
}
