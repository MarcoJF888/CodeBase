package com.zjf.myself.codebase.activity.UILibrary;

    import android.app.Activity;
    import android.os.Bundle;
    import android.util.Log;
    import android.view.KeyEvent;
    import android.view.View;
    import android.view.View.OnClickListener;
    import android.widget.ImageView;
    import android.widget.RelativeLayout;

    import com.zjf.myself.codebase.R;
    import com.zjf.myself.codebase.util.AnimUtil;

public class CircularMenuAct extends Activity implements View.OnClickListener {
    private String tag = CircularMenuAct.class.getSimpleName();
    private RelativeLayout level1, level2, level3;
    private ImageView iv_home;
    private ImageView iv_menu;
    private boolean isShowLevel2 = true;
    private boolean isShowLevel3 = true;
    private boolean isShowMenu = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initListener();
    }

    private void initView() {
        setContentView(R.layout.win_circular_menu);
        iv_home = (ImageView) findViewById(R.id.iv_home);
        iv_menu = (ImageView) findViewById(R.id.iv_menu);
        level1 = (RelativeLayout) findViewById(R.id.level1);
        level2 = (RelativeLayout) findViewById(R.id.level2);
        level3 = (RelativeLayout) findViewById(R.id.level3);
    }

    private void initListener() {
        iv_home.setOnClickListener(this);
        iv_menu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_home:
                if(AnimUtil.animCount!=0){
                    //说明有动画在执行
                    return;
                }
                if (isShowLevel2) {
                    // 需要隐藏
                    // Log.e(tag, "隐藏");
                    int startOffset = 0;
                    if (isShowLevel3) {
                        AnimUtil.closeMenu(level3, startOffset);
                        isShowLevel3 = false;
                        startOffset += 200;
                    }
                    AnimUtil.closeMenu(level2, startOffset);
                } else {
                    // 显示
                    // Log.e(tag, "显示");
                    AnimUtil.showMenu(level2,0);
                }
                isShowLevel2 = !isShowLevel2;
                break;
            			case R.id.iv_menu:
            				if(AnimUtil.animCount!=0){
            					//说明有动画在执行
            					return;
            				}
            				if (isShowLevel3) {
            					AnimUtil.closeMenu(level3, 0);
            				} else {
            					AnimUtil.showMenu(level3,0);
            				}
            				isShowLevel3 = !isShowLevel3;
            				break;
            			default:
            				break;
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            if (isShowMenu) {
                // 显示，变成隐藏
                int startOffset = 0;
                if (isShowLevel3) {
                    AnimUtil.closeMenu(level3, startOffset);
                    isShowLevel3 = false;
                    startOffset += 200;
                }
                if (isShowLevel2) {
                    AnimUtil.closeMenu(level2, startOffset);
                    isShowLevel2 = false;
                    startOffset += 200;
                }
                AnimUtil.closeMenu(level1, startOffset);
            } else {
                // 隐藏变成显示
                AnimUtil.showMenu(level1, 0);
                AnimUtil.showMenu(level2, 200);
                isShowLevel2 = true;
                AnimUtil.showMenu(level3, 400);
                isShowLevel3 = true;

            }
            isShowMenu = !isShowMenu;
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
