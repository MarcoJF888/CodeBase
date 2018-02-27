
package com.zjf.myself.codebase.thirdparty.separate;

import android.content.Context;
import android.view.ViewGroup;

import com.zjf.myself.codebase.R;


public class ContentItem extends TextItem {
	
	public int drawableId;
	public String title;
	
	public ContentItem(int id, String title, String content) {
		this.drawableId = id;
		this.title = title;
		text = content;
	}
	
	@Override
    public ItemView newView(Context context, ViewGroup parent) {
        return createCellFromXml(context, R.layout.item_content_view, parent);
    }

}
