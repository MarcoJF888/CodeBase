package com.zjf.myself.codebase.activity.FreeTimeProjectList;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.reflect.TypeToken;
import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.activity.BaseAct;
import com.zjf.myself.codebase.activity.ZxingCaptureAct;
import com.zjf.myself.codebase.adapter.CommonAdaper;
import com.zjf.myself.codebase.adapter.ViewHolder;
import com.zjf.myself.codebase.application.AppController;
import com.zjf.myself.codebase.dialog.SitCheckIntervalSettingsDialog;
import com.zjf.myself.codebase.model.ExpressInfo;
import com.zjf.myself.codebase.util.AppLog;
import com.zjf.myself.codebase.util.CallBack;
import com.zjf.myself.codebase.util.GsonParser;
import com.zjf.myself.codebase.util.StringUtil;
import com.zjf.myself.codebase.util.ViewUtils;
import com.zjf.myself.codebase.view.LoadingView;


import org.json.JSONObject;

import java.util.List;

/**
 * <pre>
 *     author : ZouJianFeng
 *     e-mail :
 *     time   : 2017/04/05
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class VolleyAndJsonTestAct extends BaseAct implements View.OnClickListener{

    public List<ExpressInfo> expressInfoList;
    private ListView lvExpressInfo;
    private Button btnStart;
    private ExpressInfoAdapter adaper;
    private EditText edtNum;
    private String expressNum;
    private String company = "";
    private LoadingView loadingView;
    private TextView btnCompany,txtRight,btnBack;
    SitCheckIntervalSettingsDialog companyDialog;
     @Override
         protected void onCreate(Bundle savedInstanceState) {
             super.onCreate(savedInstanceState);
             setContentView(R.layout.act_volley_json_test);
            ((TextView)findViewById(R.id.txtRight)).setOnClickListener(this);
            ((ImageView)findViewById(R.id.btnBack)).setOnClickListener(this);

             loadingView = (LoadingView)findViewById(R.id.loadingView);

             edtNum = (EditText)findViewById(R.id.edtNum);
             edtNum.setText("");//3986240276222
             lvExpressInfo = (ListView) findViewById(R.id.lvExpressInfo);
             adaper = new ExpressInfoAdapter(this, expressInfoList, R.layout.item_express_info);

             btnCompany = (TextView)findViewById(R.id.btnCompany);
             btnCompany.setOnClickListener(this);

             btnStart = (Button) findViewById(R.id.btnStart);
             btnStart.setOnClickListener(this);

            companyDialog=new SitCheckIntervalSettingsDialog(new CallBack() {
                 @Override
                 public void onCall(Object data) {
                     String interval=(String) data;
                     companyDialog.setSelectTime("");
                     btnCompany.setText(interval);
                     company = companyHelper(interval);
                 }
             });
     }

     //正则优化数据显示的算法待优化
//     public static String removeSurplusString(String string ,String s1 ,String s2){
//         String targe = string;//原始字符串
//         String matter1 = s1;//目标字符串1
//         String matter2 = s2;//目标字符串2
//         int a = targe.indexOf(matter1);//第一个字符串的起始位置
//         int b = targe.indexOf(matter2);//第二个字符串的起始位置
//         String result = targe.substring(0, a+matter1.length())+targe.substring(b, targe.length());//利用substring进行字符串截取
//         String result2=result.replace("[","").replace("]"," ");
//         return result2;
//     }


    private void requestStart(){
        if(dataIsReady()) {
            loadingView.startLoading("正在加载请稍后...");
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("http://www.kuaidi100.com/query?type=" + company + "&postid=" + expressNum, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            try {
                                String modeData = response.optString("data");
                                String com = response.optString("com");
                                String message = response.optString("message");

                                TypeToken<List<ExpressInfo>> token = new TypeToken<List<ExpressInfo>>(){};
                                expressInfoList = GsonParser.getInstance().fromJson(modeData, token);

                                AppLog.d("请求成功，所有数据====" + response.toString());
                                AppLog.d("请求成功，物流公司====" + com);
                                AppLog.d("请求成功，物流数据====" + modeData);

                                loadingView.onLoadingComplete();

                                adaper.clearAll(false);
                                adaper.addList(expressInfoList, true);
                                lvExpressInfo.setAdapter(adaper);
                                ViewUtils.showToast(message);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    AppLog.d("请求失败：：" + error.getMessage(), error);
                    ViewUtils.showToast("请求失败,请检查您的网络");
                }
            });

            AppController.getRequestQueue().add(jsonObjectRequest);
        }
    }



    static class ExpressInfoAdapter extends CommonAdaper<ExpressInfo> {
        public ExpressInfoAdapter(Context context, List<ExpressInfo> list, int itemLayoutId){
            super(context,list,itemLayoutId);
        }
        @Override
        public void convert(ViewHolder holder, ExpressInfo item , int position){
            holder.setText(R.id.txtTime, item.getTime());
//            holder.setText(R.id.txtLoc,item.getLocation());

            holder.setText(R.id.txtState, item.getContext());
//            holder.setText(R.id.txtState, removeSurplusString(item.getContext(),"[","]"));
        }
    }

    @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.btnCompany:
                    companyDialog.show();
                    break;

                case R.id.btnStart:
                    requestStart();
                    break;
                case R.id.btnBack:
                      finish();
                    break;
                case R.id.txtRight:
                    // 扫描功能
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        //申请CAMERA权限
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 3);
                    } else {
                        Intent intent = new Intent(VolleyAndJsonTestAct.this,ZxingCaptureAct.class);
                        startActivityForResult(intent,0);
                    }

//                    ZxingCaptureAct.start(null);
                    break;
                default:
                    break;
            }
        }


    public static final String SHEN_TONG="申通快递";
    public static final String EMS="EMS";
    public static final String SHUN_FENG="顺丰快递";
    public static final String YUAHN_TONG="圆通快递";
    public static final String ZHONG_TONG="中通快递";
    public static final String YUN_DA="韵达快递";
    public static final String TIAN_TIAN="天天快递";
    public static final String HUI_TONG="汇通快递";
    public static final String QUAN_FENG="全峰快递";
    public static final String DE_BANG="德邦快递";
    public static final String ZHAI_JI_SONG="宅急送";

    private String companyHelper(String company){
            switch (company){
                case SHEN_TONG:
                    company = "shentong";
                    break;
                case EMS:
                    company = "ems";
                    break;
                case SHUN_FENG:
                    company = "shunfeng";
                    break;
                case YUAHN_TONG:
                    company = "yuantong";
                    break;
                case ZHONG_TONG:
                    company = "zhongtong";
                    break;
                case YUN_DA:
                    company = "yunda";
                    break;
                case TIAN_TIAN:
                    company = "tiantian";
                    break;
                case HUI_TONG:
                    company = "huitongkuaidi";
                    break;
                case QUAN_FENG:
                    company = "quanfengkuaidi";
                    break;
                case DE_BANG:
                    company = "debangwuliu";
                    break;
                case ZHAI_JI_SONG:
                    company = "zhaijisong";
                    break;
            }

            return company;
        }

    private boolean dataIsReady(){
        expressNum = edtNum.getText().toString().trim();
        String company=btnCompany.getText().toString().trim();
        if(StringUtil.isNull(expressNum)||company.equals("请选择快递公司")){
            ViewUtils.showToast("请填写单号和选择快递公司！");
            return false;
        }
                return true;
    }

//扫描回调
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case 0:
                if (!(data==null)){
                    String result = data.getStringExtra("result");
                    AppLog.d(result);
                    edtNum.setText(result);
                }
                break;
            default:
                break;
        }
    }

//获取权限回调
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (3 == requestCode) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 授权
                Intent openCameraIntent = new Intent(this, ZxingCaptureAct.class);
                startActivityForResult(openCameraIntent, 0);
            } else {
                // 未授权
            }
        }
    }
}
