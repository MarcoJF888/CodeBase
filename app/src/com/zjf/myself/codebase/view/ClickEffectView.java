package com.zjf.myself.codebase.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/***
 *
 * @author 帽檐遮不住阳光
 *
 * @DATE 2016/5/6
 *
 */
public class ClickEffectView extends View {

	private List<MyBean> list = null;
	private int MaxAlpha = 255;// 最大透明度
	private boolean START = true;// 如果不设置这个START进行判断,每次点击屏幕后,屏幕只允许出现一个圆

	public ClickEffectView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public ClickEffectView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		list = new ArrayList<MyBean>();
	}

	/**
	 * MyView大小
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		for (int i = 0; i < list.size(); i++) {
			MyBean wave = list.get(i);
			canvas.drawCircle(wave.X, wave.Y, wave.radius, wave.paint);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		super.onTouchEvent(event);

		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				// 点击屏幕后 半径设为0,alpha设置为255
				MyBean bean = new MyBean();
				bean.radius = 0; // 点击后 半径先设为0
				bean.alpha = MaxAlpha; // alpha设为最大值 255
				bean.width = bean.radius / 8; // 描边宽度 这个随意
				bean.X = (int) event.getX(); // 所绘制的圆的X坐标
				bean.Y = (int) event.getY(); // 所绘制的圆的Y坐标
				bean.paint = initPaint(bean.alpha, bean.width);

				if (list.size() == 0) {
					// 如果不设置这个START进行判断,每次点击屏幕后,屏幕只允许出现一个圆
					START = true;
				}
				list.add(bean);
				invalidate();
				if (START) {
					handler.sendEmptyMessage(0);
				}

				break;
		}
		return true;
	}

	/**
	 * 初始化paint
	 */
	private Paint initPaint(int alpha, float width) {
		Paint paint = new Paint();
		paint.setAntiAlias(true);// 抗锯齿
		paint.setStrokeWidth(width);// 描边宽度
		paint.setStyle(Paint.Style.STROKE);// 圆环
		paint.setAlpha(alpha);// 透明度
		paint.setColor(Color.BLUE);// 颜色
		return paint;
	}

	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
				case 0:
					Refresh();
					invalidate();
					if (list != null && list.size() > 0) {
						handler.sendEmptyMessageDelayed(0, 50);// 每50毫秒发送
					}
					break;

				default:
					break;
			}
		}

	};

	/***
	 * 刷新
	 */
	private void Refresh() {
		for (int i = 0; i < list.size(); i++) {
			MyBean bean = list.get(i);
			if (START == false && bean.alpha == 0) {
				list.remove(i);
				bean.paint = null;
				bean = null;
				continue;
			} else if (START == true) {
				START = false;
			}
			bean.radius += 5;// 半径每次+5
			bean.alpha -= 10;// 透明度每次减10
			if (bean.alpha < 0) {
				// 透明度小雨0的时候 赋为0
				bean.alpha = 0;
			}
			bean.width = bean.radius / 8; // 描边宽度设置为半径的1/4
			bean.paint.setAlpha(bean.alpha);
			bean.paint.setStrokeWidth(bean.width);
		}
	}


	public class MyBean {

		int alpha; // 透明度
		int X; // X坐标
		int Y; // Y坐标
		float width; // 描边宽度
		float radius; // 半径
		Paint paint; // 画笔

	}

}
