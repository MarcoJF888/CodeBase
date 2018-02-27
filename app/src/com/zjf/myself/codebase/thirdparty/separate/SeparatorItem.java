
package com.zjf.myself.codebase.thirdparty.separate;

import android.content.Context;
import android.view.ViewGroup;

import com.zjf.myself.codebase.R;


public class SeparatorItem extends TextItem {
	
    public SeparatorItem() {
        this(null);
    }

    public SeparatorItem(String text) {
        super(text);
        enabled = false;
    }

    @Override
    public ItemView newView(Context context, ViewGroup parent) {
        return createCellFromXml(context, R.layout.item_separator_view, parent);
    }

}
