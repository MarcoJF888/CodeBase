
package com.zjf.myself.codebase.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.zjf.myself.codebase.thirdparty.separate.Item;
import com.zjf.myself.codebase.thirdparty.separate.ItemView;
import com.zjf.myself.codebase.thirdparty.separate.TextItem;


public class SeparatorItemView extends TextView implements ItemView {

	public SeparatorItemView(Context context) {
        this(context, null);
    }

    public SeparatorItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SeparatorItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

	@Override
	public void prepareItemView() {
	}

	@Override
	public void setObject(Item object) {
		TextItem item = (TextItem) object;
		setText(item.text);
	}

}
