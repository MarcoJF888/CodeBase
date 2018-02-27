package com.zjf.myself.codebase.thirdparty.separate;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import com.zjf.myself.codebase.thirdparty.separate.Item;
import com.zjf.myself.codebase.thirdparty.separate.ItemView;

import java.util.HashMap;
import java.util.List;

public class SeparateItemAdapter extends BaseAdapter {

	private static final int DEFAULT_MAX_VIEW_TYPE_COUNT = 10;

	private static class TypeInfo {
		int count;
		int type;
	}

	private List<Item> mItems;
	private HashMap<Class<? extends Item>, TypeInfo> mTypes;
	private Context mContext;
	private int mMaxViewTypeCount;

	public SeparateItemAdapter(Context context, List<Item> items) {
		this(context, items, DEFAULT_MAX_VIEW_TYPE_COUNT);
	}

	public SeparateItemAdapter(Context context, List<Item> items, int maxViewTypeCount) {
		mContext = context;
		mItems = items;
		mTypes = new HashMap<Class<? extends Item>, TypeInfo>();
		mMaxViewTypeCount = Integer.MAX_VALUE;

		for (Item item : mItems) {
			addItem(item);
		}

		mMaxViewTypeCount = Math.max(1,
				Math.max(mTypes.size(), maxViewTypeCount));
	}

	private void addItem(Item item) {
		final Class<? extends Item> klass = item.getClass();
		TypeInfo info = mTypes.get(klass);

		if (info == null) {
			final int type = mTypes.size();
			if (type >= mMaxViewTypeCount) {
				throw new RuntimeException("This ItemAdapter may handle only "
						+ mMaxViewTypeCount + " different view types.");
			}
			final TypeInfo newInfo = new TypeInfo();
			newInfo.count = 1;
			newInfo.type = type;
			mTypes.put(klass, newInfo);
		} else {
			//记录某种类型的view有几个
			info.count++;
		}
	}

	@Override
	public int getCount() {
		return mItems.size();
	}

	@Override
	public Object getItem(int position) {
		return mItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public int getItemViewType(int position) {
//		System.out.println("===>>>"+mTypes.get(getItem(position).getClass()).type);
		System.out.println("getItemViewType=============");
		return mTypes.get(getItem(position).getClass()).type;
	}

	@Override
	public boolean isEnabled(int position) {
		return ((Item) getItem(position)).enabled;
	}

	@Override
    public int getViewTypeCount() {
        return mMaxViewTypeCount;
    }

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		System.out.println("getView===========");
		final Item item = (Item) getItem(position);
        ItemView cell = (ItemView) convertView;

        if (cell == null) {
            cell = item.newView(mContext, null);
            cell.prepareItemView();
//            System.out.println("======================"+cell);
        }

//        System.out.println("----------------------"+cell);

        cell.setObject(item);

        return (View) cell;
	}

}
