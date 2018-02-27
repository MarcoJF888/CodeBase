
package com.zjf.myself.codebase.thirdparty.separate;

import android.content.Context;
import android.view.ViewGroup;

public class TextItem extends Item {
	
    public String text;

    public TextItem() {
    }
    
    public TextItem(String text) {
        this.text = text;
    }

	@Override
	public ItemView newView(Context context, ViewGroup parent) {
		return null;
	}

}
