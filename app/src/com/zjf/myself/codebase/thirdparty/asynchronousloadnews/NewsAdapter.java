package com.zjf.myself.codebase.thirdparty.asynchronousloadnews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zjf.myself.codebase.R;

import java.util.List;

/**
 * listview的适配器，包括上下文对象和数据源
 * 提高listview的效率：当listview滚动时不去加载可见项图片，停止滚动后再开始加载
 */
public class NewsAdapter extends BaseAdapter implements AbsListView.OnScrollListener{

	private LayoutInflater mInflater;
	private List<NewsPageInfo> mList;
	private ImageLoader mImageLoader;
	private int mStart, mEnd;//listview屏幕可视范围内的第一条item和最后一个item
	public static String URLS[];//设置一个数组，用来保存所有图片的URL
	private boolean mFirstIn;//判断是否是第一次启动程序

	public NewsAdapter(Context context, List<NewsPageInfo> data, ListView listView) {
		mInflater = LayoutInflater.from(context);
		this.mList = data;
		mImageLoader = new ImageLoader(listView);//在这里初始化，能够保证只有一个imageloader的实例，即只有一个LruCache的实例
		URLS = new String[data.size()];
		for (int i = 0; i < data.size(); i++) {
			URLS[i] = data.get(i).getNewsIconUrl();//将data中的每一个URL赋值给数组
		}
		listView.setOnScrollListener(this);
		mFirstIn = true;//写在构造函数中，第一次调用newsAdapter时表示第一次启动程序，显示listview
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_news_page, null);
			holder = new ViewHolder();
			holder.ivIcon = (ImageView) convertView.findViewById(R.id.imgHeadIcon);
			holder.tvTitle = (TextView) convertView.findViewById(R.id.txtTitle);
			holder.tvContent = (TextView) convertView.findViewById(R.id.txtContent);
			convertView.setTag(holder);
		}else {
			holder = (ViewHolder) convertView.getTag();
		}
		//holder.ivIcon.setImageResource(R.drawable.ic_launcher);
		String url = mList.get(position).getNewsIconUrl();
		holder.ivIcon.setTag(url);//给ImageView设置标志，即对应的图片网址
		//利用thread类实现异步加载图片，我们这里将其注释，使用AsyncTask的方式
		//new ImageLoader().showImageByThread(holder.ivIcon, mList.get(position).getNewsIconUrl());
		mImageLoader.showImages(holder.ivIcon, url);

		holder.tvTitle.setText(mList.get(position).getNewsTitle());
		holder.tvContent.setText(mList.get(position).getNewsContent());
		return convertView;
	}


	class ViewHolder {
		public ImageView ivIcon;
		public TextView tvTitle, tvContent;
	}
	/*
	 * 当listview滑动的时候调用
	 */
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		mStart = firstVisibleItem;
		mEnd = mStart + visibleItemCount;
		//只在第一次加载的时候调用
		if (mFirstIn && visibleItemCount >0) {//表示第一次加载listview并且已经绘制了可见范围内的item
			mImageLoader.loadImages(mStart, mEnd);
			mFirstIn = false;//加载图片后即设为false
		}
	}

	/*
	 * 当listview滑动状态变化时调用
	 */
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		if (scrollState == SCROLL_STATE_IDLE) {//listview停止滚动
			//加载可见项
			mImageLoader.loadImages(mStart, mEnd);

		}else {
			//停止加载任务
			mImageLoader.cancelAllTasks();
		}

	}

}
