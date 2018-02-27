package com.zjf.myself.codebase.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.util.StringUtil;
import com.zjf.myself.codebase.view.LoadingView;


public class WebviewFragment extends BaseFragment {
	private WebView webView;
	private LoadingView loadingView;


	public static final int TITLE_TYPE_MAIN=1;

	public static final int TITLE_TYPE_SECOND=2;

	public static final int TITLE_TYPE_NONE=0;

	String url,title;
	int titleType;


    public static WebviewFragment newInstance(String title,int titleType,String url) {
        WebviewFragment f = new WebviewFragment();
        Bundle bundle=new Bundle();
		bundle.putString("title", title);
        bundle.putString("url", url);
		bundle.putInt("titleType",titleType);
        f.setArguments(bundle);
        return f;
    }





    @Override
    protected void initView(Bundle bundle) {

		url = bundle.getString("url");
		if (StringUtil.isNull(url)) {
			return;
		}


		title = bundle.getString("title");
		if (StringUtil.isNull(title)) {
			return;
		}

		titleType = bundle.getInt("titleType", TITLE_TYPE_NONE);
		loadingView= (LoadingView) findViewById(R.id.loadingView);
		ViewStub vstubTitle = (ViewStub) findViewById(R.id.vstubTitle);

		if (titleType == TITLE_TYPE_MAIN) {
			vstubTitle.setLayoutResource(R.layout.layout_main_title);
			View v = vstubTitle.inflate();
			v.setId(R.id.layoutTitle);
			TextView txtTitle = (TextView) findViewById(R.id.txtTitle);
			txtTitle.setText(title);
			int mainTileHeight = (int) getActivity().getResources().getDimension(R.dimen.main_title_height);
			RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, mainTileHeight);
			v.setLayoutParams(layoutParams);
		} else if (titleType == TITLE_TYPE_SECOND) {
			vstubTitle.setLayoutResource(R.layout.layout_second_title);
			View v = vstubTitle.inflate();
			v.setId(R.id.layoutTitle);
			int tileHeight = (int) getActivity().getResources().getDimension(R.dimen.second_title_height);
			RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, tileHeight);
			v.setLayoutParams(layoutParams);
			TextView txtTitle = (TextView) findViewById(R.id.txtTitle);
			txtTitle.setText(title);
			findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					getActivity().finish();
				}
			});

		} else {
//			vstubTitle.setLayoutResource(R.layout.layout_main_title);
//
//			View v=vstubTitle.inflate();

		}

		webView = (WebView) findViewById(R.id.web_view);
		RelativeLayout.LayoutParams webViewlayoutParams = new RelativeLayout.LayoutParams(-1, -1);
		webViewlayoutParams.addRule(RelativeLayout.BELOW,R.id.layoutTitle);
		WebSettings settings = webView.getSettings();

		settings.setAppCacheEnabled(true);
		settings.setDatabaseEnabled(true);
		settings.setDomStorageEnabled(true);//开启DOM缓存，关闭的话H5自身的一些操作是无效的
		settings.setCacheMode(WebSettings.LOAD_DEFAULT);
		webView.getSettings().setJavaScriptEnabled(true);


//
//	     webView.loadUrl(url);
//	        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
//	        webView.setWebViewClient(new WebViewClient(){
////	            @Override
////	            public boolean shouldOverrideUrlLoading(WebView view, String url) {
////	                // TODO Auto-generated method stub
////	                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
////	                view.loadUrl(url);
////	                return true;
////	            }
//
//				@Override
//				public void onPageStarted(WebView view, String url, Bitmap favicon)
//				{
//					super.onPageStarted(view, url, favicon);
////					new LoadingDialog().show();
//				}
//
//				@Override
//				public void onPageFinished(WebView view, String url)
//					{
//						super.onPageFinished(view, url);
////						new LoadingDialog().dismiss();
//					}
//
//	        });
//    }
//
		webView.setWebViewClient(new WebViewClient() {

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
				loadingView.startLoading("正在加载,请稍后");
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				loadingView.onLoadingComplete();
			}

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);//当打开新的连接时,使用当前的webview,不使用系统其他浏览器
				return true;
			}
		});
		webView.loadUrl(url);
	}


	private void setTitle(String title){
		if(titleType==TITLE_TYPE_SECOND){
			findViewById(R.id.btnBack).setVisibility(View.GONE);
		}
		TextView txtTitle= (TextView) findViewById(R.id.txtTitle);
		txtTitle.setText(title);
	}

	@Override
	protected void initView() {

	}

	
	@Override
	protected int layoutId() {
		// TODO Auto-generated method stub
		return R.layout.webview;
	}

}