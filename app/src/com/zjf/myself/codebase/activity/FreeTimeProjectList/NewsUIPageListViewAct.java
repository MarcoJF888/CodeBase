package com.zjf.myself.codebase.activity.FreeTimeProjectList;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.activity.BaseAct;
import com.zjf.myself.codebase.thirdparty.asynchronousloadnews.NewsAdapter;
import com.zjf.myself.codebase.thirdparty.asynchronousloadnews.NewsPageInfo;
import com.zjf.myself.codebase.util.ViewUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     author : ZouJianFeng
 *     e-mail :
 *     time   : 2017/04/28
 *     desc   :
 *     version: 1.0
 * </pre>
 */
//item_news_page
    //http://www.imooc.com/api/teacher?type=4&num=30
public class NewsUIPageListViewAct extends BaseAct{
    private ListView lvNewsPage;
    private static String JsonURL = "http://www.imooc.com/api/teacher?type=4&num=30";
     @Override
         protected void onCreate(Bundle savedInstanceState) {
             super.onCreate(savedInstanceState);
             setContentView(R.layout.act_news_page);

             lvNewsPage = (ListView) findViewById(R.id.lvNewsPage);
             //listview点击事件
             lvNewsPage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                 @Override
                 public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    //				ListView listView = (ListView) parent;//parent代表你点击的那个listview
    //				HashMap<String, String> hashMap = (HashMap<String, String>) listView.getItemAtPosition(position);
    //				String name = hashMap.get("name");
    //				String idd=hashMap.get("id");

                     TextView txtTitle = (TextView) view.findViewById(R.id.txtTitle);
                     String title = txtTitle.getText().toString().trim();
                     ViewUtils.showToast(title);
                 }
             });

             new NewsAsyncTask().execute(JsonURL);
      }

    /**
     * 将URL网址上的json数据转化为我们所需的newsbean对象
     * @return
     */
    private List<NewsPageInfo> getJsonData(String url){
        List<NewsPageInfo> newsBeanList = new ArrayList<NewsPageInfo>();//保存解析出来的所有的数据
        try {
            //获取到json字符串
            String jsonString = readStream(new URL(url).openStream());//和url.openConnection().getInputStream()一样
            //Log.d("MainActivity", jsonString);
            //将获取到的json字符串变为jsonObject对象，打开网址可以看出data节点是一个jsonArray,array里面存放了一个个的jsonObject
            NewsPageInfo newsBean;
            JSONObject jsonObject;
            String newsUrl = null;
            String newsTitle = null;
            String newsContent = null;
            jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                newsUrl = jsonObject.getString("picSmall");//图片网址
                newsTitle = jsonObject.getString("name");//title
                newsContent = jsonObject.getString("description");//content
                newsBean = new NewsPageInfo(newsUrl, newsTitle, newsContent);
                newsBeanList.add(newsBean);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return newsBeanList;
    }

    /**
     * 解析网络返回的数据
     */
    private String readStream(InputStream is) {
        InputStreamReader isReader;
        String result = "";
        String line = "";
        try {
            isReader = new InputStreamReader(is, "utf-8");//将字节流转化为字符流
            BufferedReader buffReader = new BufferedReader(isReader);//从字符输入流中读取文本，缓冲各个字符，从而实现字符、数组和行的高效读取
            while ((line = buffReader.readLine()) != null) {
                result += line;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;
    }
    /**
     * 构造一个AsyncTask，传入String类型的URL，返回一个NewsBean对象，每一个对象就是
     * listview中的每一行数据，包括一个icon,title,content
     */
    class NewsAsyncTask extends AsyncTask<String, Void, List<NewsPageInfo>> {

        @Override
        protected List<NewsPageInfo> doInBackground(String... params) {
            return getJsonData(params[0]);
        }

        @Override
        protected void onPostExecute(List<NewsPageInfo> result) {
            super.onPostExecute(result);
            // 访问网络并解析json成功后返回结果，即我们设置的List<NewsBean>
            NewsAdapter adapter = new NewsAdapter(NewsUIPageListViewAct.this, result, lvNewsPage);
            lvNewsPage.setAdapter(adapter);
        }

    }

}
