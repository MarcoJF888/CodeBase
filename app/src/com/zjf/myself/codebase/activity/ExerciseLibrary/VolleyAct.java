package com.zjf.myself.codebase.activity.ExerciseLibrary;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.activity.BaseAct;
import com.zjf.myself.codebase.application.AppController;
import com.zjf.myself.codebase.model.ExpressInfo;
import com.zjf.myself.codebase.util.AppLog;
import com.zjf.myself.codebase.util.GsonParser;
import com.zjf.myself.codebase.view.LoadingView;

import org.json.JSONObject;

import java.util.List;

/**
 * <pre>
 *     author : ZouJianFeng
 *     e-mail : 1434690833@qq.com
 *     time   : 2017/05/27
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class VolleyAct extends BaseAct{
    private TextView txtResult;
    private LoadingView loadingView;
    private static String URL = "http://blog.csdn.net/telenewbie/article/details/45667603";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_volley);
        txtResult = (TextView) findViewById(R.id.txtResult);
        loadingView = (LoadingView)findViewById(R.id.loadingView);
    }

    public void btnGet(View view){
        txtResult.setText("");
        requestStart();

    }


    private void requestStart(){
        loadingView.startLoading("正在加载请稍后...");

        StringRequest stringRequest = new StringRequest(Request.Method.GET,URL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        String trim = response.toString().trim();
                        txtResult.setText(trim);
                        AppLog.d("请求成功："+response.toString().trim());
                        loadingView.onLoadingComplete();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AppLog.d("请求失败：："+error.getMessage(), error);
            }
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 1, 1.0f));
        AppController.getRequestQueue().add(stringRequest);
    }



}
