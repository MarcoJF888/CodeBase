package com.zjf.myself.codebase.activity.ExerciseLibrary;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.util.GetData;

/**
 * Created by Administrator on 2017/1/7.
 */

public class HttpURLConnectionTestMeAct extends Activity{

    private final static String PIC_URL = "http://ww2.sinaimg.cn/large/7a8aed7bgw1evshgr5z3oj20hs0qo0vq.jpg";

    private TextView txtMeMenu;
    private ImageView imgMePic;
    private Bitmap bitmap;

    // 用于刷新界面
    private Handler handler =   new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0x001:
                    imgMePic.setVisibility(View.VISIBLE);
                    imgMePic.setImageBitmap(bitmap);
                    Toast.makeText(HttpURLConnectionTestMeAct.this, "图片加载完毕", Toast.LENGTH_SHORT).show();
                    break;

                default:
                    break;
            }
        }

        ;
    };

    private GoogleApiClient client;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.win_http_url_connection_me);

         txtMeMenu = (TextView) findViewById(R.id.txtMeMenu);
         imgMePic =  (ImageView) findViewById(R.id.imgMePic);

         client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

         txtMeMenu.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 new Thread() {
                     public void run() {
                         try {
                             byte[] data = GetData.getImage(PIC_URL);
                             bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                         } catch (Exception e) {
                             e.printStackTrace();
                         }
                         handler.sendEmptyMessage(0x001);
                     };
                 }.start();
             }
         });

      }


    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("HttpURLConnectionTestAct Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

}
