package com.zjf.myself.codebase.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * <pre>
 *     author : ZouJianFeng
 *     e-mail :
 *     time   : 2017/04/28
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class MarqueeText extends TextView{

    public MarqueeText(Context context){
        super(context);
    }

    public MarqueeText(Context context, AttributeSet attrs ,int defStyle){
        super(context,attrs,defStyle);
    }
    public MarqueeText(Context context, AttributeSet attrs ){
        super(context,attrs);
    }
    @Override
    public boolean isFocused(){
        return true;
    }

}
