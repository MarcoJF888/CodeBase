package com.zjf.myself.codebase.activity.ExerciseLibrary;

import android.os.Bundle;
import android.widget.ImageView;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.activity.BaseAct;
import com.zjf.myself.codebase.util.ViewUtils;

/**
 * <pre>
 *     author : ZouJianFeng
 *     e-mail :
 *     time   : 2017/05/19
 *     desc   :Glide加载网络图片，并缓存，可加圆角
 *     version: 1.0
 * </pre>
 */

public class GlideImgTestAct extends BaseAct {
    private ImageView imgTest1,imgTest2,imgTest3,imgTest4,imgTest5,imgTest6;
    private String url1 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1495191618104&di=b794954ba24bd648b41aca5d37fc3873&imgtype=0&src=http%3A%2F%2Fwww.jf258.com%2Fuploads%2F2014-09-16%2F082350610.jpg";
    private String url2 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1495191852014&di=440eafd7c1cee51d735fa155cb61466c&imgtype=0&src=http%3A%2F%2Fwww.wto168.net%2Fuploads%2Fuserup%2F10895%2F13W244943-c08.jpg";
    private String url3 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1495191918903&di=0685ae37d683353180814c93836b6b58&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F0140fd58635b04a8012060c8cf648e.jpg%40900w_1l_2o_100sh.jpg";
    private String url4 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1495191985902&di=86b8ed9f951ff10e5298b0bbac47c4f4&imgtype=0&src=http%3A%2F%2Fimg.juimg.com%2Ftuku%2Fyulantu%2F140305%2F330857-14030513022947.jpg";
    private String url5 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1495192045280&di=802ee3329479ff0bfb880646e2bd241d&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F0179a355d14ce632f875202facfdc0.jpg";
    private String url6 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1495192067382&di=b74e80fc3e43d6847d7164b03512865c&imgtype=0&src=http%3A%2F%2Fimg4.duitang.com%2Fuploads%2Fitem%2F201312%2F05%2F20131205172250_QxQhe.thumb.600_0.jpeg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_glide_img);
        imgTest1= (ImageView)findViewById(R.id.imgTest1);
        imgTest2= (ImageView)findViewById(R.id.imgTest2);
        imgTest3= (ImageView)findViewById(R.id.imgTest3);
        imgTest4= (ImageView)findViewById(R.id.imgTest4);
        imgTest5= (ImageView)findViewById(R.id.imgTest5);
        imgTest6= (ImageView)findViewById(R.id.imgTest6);

        ViewUtils.setImageByUrl(imgTest1,url1,R.mipmap.ic_launcher,ViewUtils.TYPE_ROUND_RECT,5);
        ViewUtils.setImageByUrl(imgTest2,url2,R.mipmap.ic_launcher,ViewUtils.TYPE_CIRCLE,0);
        ViewUtils.setImageByUrl(imgTest3,url3,R.mipmap.ic_launcher,ViewUtils.TYPE_ROUND_RECT,5);
        ViewUtils.setImageByUrl(imgTest4,url4,R.mipmap.ic_launcher,ViewUtils.TYPE_CIRCLE,5);
        ViewUtils.setImageByUrl(imgTest5,url5,R.mipmap.ic_launcher,ViewUtils.TYPE_ROUND_RECT,5);
        ViewUtils.setImageByUrl(imgTest6,url6,R.mipmap.ic_launcher,ViewUtils.TYPE_ROUND_RECT,5);
    }
}
