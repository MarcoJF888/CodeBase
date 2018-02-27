package com.zjf.myself.codebase.thirdparty.asynchronousloadnews;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.LruCache;
import android.widget.ImageView;
import android.widget.ListView;

import com.zjf.myself.codebase.R;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class ImageLoader {
	private ImageView mImageView;
	private String url;
	//当图片加载过后就将图片缓存到本地，下次便不用重新联网获取，直接从本地缓存获取即可,一个图片即string url --> bitmap
	private LruCache<String, Bitmap> mCache;
	private ListView mListView;
	private Set<NewsAsyncTask> mTasks;//从start到end范围每次执行加载图片任务的集合

	public ImageLoader(ListView listView) {
		mListView = listView;
		mTasks = new HashSet<NewsAsyncTask>();
		int maxMemory = (int) Runtime.getRuntime().maxMemory();//获取最大可用内存
		int cacheSize = maxMemory/4;//设置缓存的大小
		mCache = new LruCache<String, Bitmap>(cacheSize){
			@Override
			protected int sizeOf(String key, Bitmap value) {
				// 每次将图片存入缓存时返回图片的大小
				return value.getByteCount();
			}
		};
	}
	/**
	 * 将已联网获取成功的图片加入到缓存中
	 * @param bitmap
	 */
	public void addBitmapToCache(String url, Bitmap bitmap) {
		//在将图片缓存到本地之前要判断这个图片是否已经缓存过了
		if (getBitmapFromCache(url) == null) {
			mCache.put(url, bitmap);
		}
	}
	/**
	 * 通过URL从缓存中取出相应的图片
	 */
	public Bitmap getBitmapFromCache(String url)
	{
		return mCache.get(url);
	}

	private Handler mHandler = new Handler(){
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			//通过tag使得ImageView和它对应的URL绑定，这样在上下滑动listview时ImageView显示的图片就始终是正确的
			//否则，由于listview的缓存机制，ImageView会先显示出上次加载成功时的图片，然后再显示正确的图片
			if (mImageView.getTag().equals(url)) {
				mImageView.setImageBitmap((Bitmap) msg.obj);//使用handler在主线程中更新UI，并将URL对应的图片设置给控件imageview
			}

		}
	};

	/**
	 * 通过使用Thread的方式从网络上获取图片
	 */
	public void showImageByThread(ImageView imageView, final String iconUrl) {
		mImageView = imageView;
		url = iconUrl;
		new Thread(){
			@Override
			public void run() {
				// 在新的进程中实现图片的加载
				super.run();
				//从url中获得bitmap，将bitmap发送给主线程
				Bitmap bitmap = getBitmapFromUrl(iconUrl);
				Message message = Message.obtain();
				message.obj = bitmap;
				mHandler.sendMessage(message);
			}
		}.start();
	}

	public Bitmap getBitmapFromUrl(String urlString) {
		InputStream is = null;
		Bitmap bitmap;
		try {
			URL url = new URL(urlString);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			is = new BufferedInputStream(connection.getInputStream());
			bitmap = BitmapFactory.decodeStream(is);
			connection.disconnect();
			//Thread.sleep(1000);
			return bitmap;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 加载listview可见范围内的所有图片
	 *
	 */
	public void loadImages(int start, int end) {
		for (int i = start; i < end; i++) {
			String url = NewsAdapter.URLS[i];
			//看是否能从缓存中取出对应的图片
			Bitmap bitmap = getBitmapFromCache(url);
			//如果缓存中没有，就要对每个url执行异步加载任务去获取图片
			if (bitmap == null) {
				NewsAsyncTask task = new NewsAsyncTask(url);
				task.execute(url);
				mTasks.add(task);

			}else {
				//如果缓存中存在此图片，直接将其设置给对应的imageview即可
				//因为我们之前给imageview设置的tag就是URL,可以利用findViewWithTag直接在这里获取到
				ImageView imageView = (ImageView) mListView.findViewWithTag(url);
				imageView.setImageBitmap(bitmap);
			}
		}
	}
	/**
	 * 取消所有正在进行的图片加载任务
	 */
	public void cancelAllTasks(){
		if (mTasks != null) {
			for(NewsAsyncTask task : mTasks)
			{
				task.cancel(false);
			}
		}
	}

	public void showImages(ImageView imageView, String iconUrl){
		//是否能从缓存中取出对应的图片
		Bitmap bitmap = getBitmapFromCache(iconUrl);
		if (bitmap == null) {
			imageView.setImageResource(R.mipmap.ic_launcher);//显示默认图片
		}else {
			//如果缓存中存在此图片，直接将其设置给对应的imageview即可
			imageView.setImageBitmap(bitmap);
		}

	}
	/**
	 * 使用AsyncTask实现图片的异步加载
	 */
	class NewsAsyncTask extends AsyncTask<String, Void, Bitmap> {
		//private ImageView mImageView;
		private String mUrl;
		public NewsAsyncTask(String url) {
			//	mImageView = image;
			mUrl = url;
		}
		@Override
		protected Bitmap doInBackground(String... params) {
			String url = params[0];
			Bitmap bitmap = getBitmapFromUrl(url);//从网络上得到图片
			if (bitmap != null) {
				addBitmapToCache(url, bitmap);//获取图片成功将图片存入缓存中
			}
			return bitmap;
		}
		@Override
		protected void onPostExecute(Bitmap bitmap) {
			// 后台获取图片的任务完成时调用此方法
			super.onPostExecute(bitmap);
			//给imageview设置图片
			ImageView imageView = (ImageView) mListView.findViewWithTag(mUrl);
			if (imageView != null && bitmap != null) {
				imageView.setImageBitmap(bitmap);
			}
			mTasks.remove(this);
		}
	}

}
