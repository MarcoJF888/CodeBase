package com.zjf.myself.codebase.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.EditText;

import java.lang.reflect.Field;

/**
 * Created by JakeYang on 2016/11/24.
 */

public class MyEditText extends EditText implements Animation.AnimationListener{

    /**
     * 画线
     */
    private Paint linePaint;

    /**
     * 画圆
     */
    private Paint cPaint;

    /**
     * 控件宽度
     */
    private int width;

    /**
     * 控件高度
     */
    private int height;

    /**
     * 绘制框内填充色
     */
    private Paint kPaintColor;

    /**
     * 记录框内颜色矩形大小
     */
    private RectF krectF;

    /**
     * 记录外轮框矩形大小
     */
    private RectF rectF;

    /**
     * 获取最大可以输入的长度
     */
    private int maxLength;

    /**
     * 给边距值，因为线粗
     */
    private float padding = 1f;

    /**
     * 四周倒角半径
     */
    private float chamferRadius = 16f;

    /**
     * 圆半径
     */
    private float radius = 22;

    /**
     * 百分比，用于计算圆的大小
     */
    private float percent = 0f;

    /**
     * 控制框内背景色
     */
    private String backgroundColor = "#eeeeee";

    /**
     * 动画类
     */
    private MyAnimation animationy;
    /**
     * 运行一次
     */
    private boolean isFirst = false;

    /**
     * 用于记录圆个数是增加还是减少
     */
    private boolean isAdd = true;

    /**
     * 记录上次输入内容的长度
     */
    private int length = 0;

    /**
     * 记录当前输入内容的长度
     */
    private int currentLength = 0;

    /**
     * 记录当前输入的内容
     */
    private CharSequence texts;

    public MyEditText(Context context) {
        this(context,null);
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public MyEditText(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    /**
     * 初始化数据
     */
    private void init() {
        //隐藏光标
        setCursorVisible(false);
        //获取焦点
        requestFocus();
        setFocusableInTouchMode(true);
        setFocusable(true);

        //绘制外边边框和中间线条
        linePaint = new Paint();
        linePaint.setAntiAlias(true);
        linePaint.setStyle(Paint.Style.STROKE);

        //绘制框内填充色
        kPaintColor = new Paint();
        kPaintColor.setAntiAlias(true);
        //设置框内颜色
        kPaintColor.setColor(Color.parseColor(backgroundColor));
        //设置填充
        kPaintColor.setStyle(Paint.Style.FILL);

        //绘制圆
        cPaint = new Paint();
        cPaint.setStyle(Paint.Style.FILL);
        cPaint.setAntiAlias(true);

        animationy = new MyAnimation();
        animationy.setDuration(200);
        animationy.setAnimationListener(this);
    }

    private boolean isFirstTest = true;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制框内颜色
        canvas.drawRoundRect(krectF,chamferRadius,chamferRadius,kPaintColor);
        //绘制外轮廓
        canvas.drawRoundRect(rectF,chamferRadius,chamferRadius,linePaint);
        //计算每个输入框宽
        float x = width / maxLength;
        //计算每个输入框高
        float y = height;
        //画线
        for(int i = 1; i < maxLength; i++) {
            //画框内竖线
            canvas.drawLine(x*i,padding,x*i,y - padding,linePaint);
        }

        for(int i = 0; i < currentLength; i++) {

            if(isAdd) {
                //增加圆isAdd = true;
                if(i < currentLength-1) {
                    //画圆，不为当前圆时，不需要产生动画效果
                    canvas.drawCircle(x/2+x*i,y/2,radius,cPaint);
                }else if(i == currentLength-1) {
                    //画圆，当画当前圆时，需要产生扩大效果
                    canvas.drawCircle(x/2+x*i,y/2,radius*percent,cPaint);
                }
            }else {
                //减少圆isAdd = false;
                if(i < currentLength - 1) {
                    //画圆，不为当前圆时，不需要产生动画效果
                    canvas.drawCircle(x/2+x*i,y/2,radius,cPaint);
                }else if(i == currentLength - 1) {
                    //画圆，当画当前圆时，需要产生缩小效果
                    canvas.drawCircle(x/2+x*i,y/2,radius-radius*percent,cPaint);
                }
            }
        }
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        //记录内容
        texts = text;
        //获取当前文本输入的长度
        currentLength = text.length();
        if(currentLength >= length) {
            //增加圆
            isAdd = true;
        }else {
            //减少圆
            isAdd = false;
            //防止当为减少时，圆瞬间减少两个
            currentLength = currentLength + 1;
        }

        if(listener != null) {
            if(currentLength == 1 && !isAdd) {
                //防止输入框内没有值时，调用者仍可获取到第一个输入值
                listener.onTextChanged("");
            }else {
                listener.onTextChanged(text);
            }
        }

        if(currentLength <= getMaxLength()) {
            if(animationy != null) {
                //每次进来清除上次动画
                clearAnimation();
                //开启新动画
                startAnimation(animationy);
            }else {
                invalidate();
            }
        }

        //记录上次输入内容的长度
        length = currentLength;

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        //执行一次
        if(!isFirst && changed) {
            //记录控件当前宽度
            width = getWidth();
            //记录控件当前高度
            height = getHeight();
            //绘制框内颜色
            krectF = new RectF(padding,padding,width-padding,height-padding);
            //绘制外轮廓
            rectF = new RectF(padding,padding,width-padding,height-padding);
            maxLength = getMaxLength();
            //只允许执行一次
            isFirst = true;
        }
    }

    /**
     * 获取EditText输入框内允许输入的最大个数，通过属性必须设置值
     * @return
     */
    private int getMaxLength() {
        //系统提供的方法,查看源码可知字段mMax保存在LengthFilter类内,LengthFilter类是InputFilter内部实现类
        InputFilter[] filters = getFilters();
        for(InputFilter filter : filters) {
            if("android.text.InputFilter.LengthFilter".equals(filter.getClass().getCanonicalName())) {
                try {
                    Field mMax = filter.getClass().getDeclaredField("mMax");
                    mMax.setAccessible(true);
                    //获取字段值
                    int num = (int)mMax.get(filter);
                    return num;
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        return -1;
    }

    private boolean isFirstComplete = false;

    /**
     * 动画开始时，回调此方法
     * @param animation
     */
    @Override
    public void onAnimationStart(Animation animation) {

    }

    /**
     * 动画结束时回调次方法
     * @param animation
     */
    @Override
    public void onAnimationEnd(Animation animation) {
        //动画完成执行
        if(isAdd && currentLength == getMaxLength() && listener != null /*&& !isFirstComplete*/) {
            //最后一个圆执行完动画后，执行此代码，为了保证只允许执行一次，添加个isFirstComplete标识用于判断
            isFirstComplete = true;
            listener.onComplete(texts);
        }
    }

    /**
     * 动画重复执行时，回调此方法
     * @param animation
     */
    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    /**
     * 执行动画
     */
    private class MyAnimation extends Animation {
        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            //记录百分比值
            percent = interpolatedTime;
            //百分比变化，需要更新页面
            postInvalidate();
        }
    }

    public EditTextContentListener listener;

    /**
     * 设置输入内容监听接口
     * @param listener
     */
    public void setOnEditTextContentListener(EditTextContentListener listener) {
        this.listener = listener;
    }

    /**
     * 输入内容监听接口,提供给调用者
     */
    public interface EditTextContentListener {
        /**
         * 完成时，回调此方法
         * @param text
         */
        public void onComplete(CharSequence text);

        /**
         * 输入框内容改变时，回调次方法
         * @param text
         */
        public void onTextChanged(CharSequence text);
    }

}
