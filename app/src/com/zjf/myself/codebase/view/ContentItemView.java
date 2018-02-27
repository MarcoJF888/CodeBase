package com.zjf.myself.codebase.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.thirdparty.separate.ContentItem;
import com.zjf.myself.codebase.thirdparty.separate.Item;
import com.zjf.myself.codebase.thirdparty.separate.ItemView;


public class ContentItemView extends RelativeLayout implements ItemView {

	private TextView titleTv;
	private TextView contentTv;
	private ImageView iv;

	public ContentItemView(Context context) {
        this(context, null);
    }

    public ContentItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ContentItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

	@Override
	public void prepareItemView() {
		titleTv = (TextView) findViewById(R.id.tv_setting_name);
		contentTv = (TextView) findViewById(R.id.tv_setting_end);
		iv = (ImageView) findViewById(R.id.iv_setting_img);
	}

	@Override
	public void setObject(Item object) {
		ContentItem item = (ContentItem) object;

		titleTv.setText(item.title);
		contentTv.setText(item.text);
		iv.setImageResource(item.drawableId);
	}

}
