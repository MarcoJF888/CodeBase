package com.zjf.myself.codebase.thirdparty.NewsCompatibleTest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zjf.myself.codebase.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <pre>
 *     author : ZouJianFeng
 *     e-mail : 1434690833@qq.com
 *     time   : 2017/06/15
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class NewsTitleFragment extends Fragment{
    private boolean isTwoPage;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container, Bundle saveInstanceState){
       view = inflater.inflate(R.layout.new_title_fragment,container,false);

        RecyclerView newsTitleRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewNewsTitle);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        newsTitleRecyclerView.setLayoutManager(layoutManager);
        NewsAdapter adapter = new NewsAdapter(getNews());
        newsTitleRecyclerView.setAdapter(adapter);

        return view;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity().findViewById(R.id.news_content_layout) != null) {
            isTwoPage = true; // 可以找到news_content_layout布局时，为双页模式
        } else {
            isTwoPage = false; // 找不到news_content_layout布局时，为单页模式
        }
    }

    private List<NewsCompatibleInfo> getNews() {
        List<NewsCompatibleInfo> newsList = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            NewsCompatibleInfo newsInfo = new NewsCompatibleInfo();
            newsInfo.setTitle("新闻标题 " + i);
            newsInfo.setContent(getRandomLengthContent("新闻内容 " + i + ". "));
            newsList.add(newsInfo);
        }
        return newsList;
    }

    private String getRandomLengthContent(String content) {
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(content);
        }
        return builder.toString();
    }

    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

        private List<NewsCompatibleInfo> mNewsList;

        class ViewHolder extends RecyclerView.ViewHolder {

            TextView newsTitleText;

            public ViewHolder(View view) {
                super(view);
                newsTitleText = (TextView) view.findViewById(R.id.news_title);
            }

        }

        public NewsAdapter(List<NewsCompatibleInfo> newsList) {
            mNewsList = newsList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
            final ViewHolder holder = new ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NewsCompatibleInfo newsInfo = mNewsList.get(holder.getAdapterPosition());
                    if (isTwoPage) {
                        NewsContentFragment newsContentFragment = (NewsContentFragment)
                                getFragmentManager().findFragmentById(R.id.news_content_fragment);
                        newsContentFragment.refresh(newsInfo.getTitle(), newsInfo.getContent());
                    } else {
                        NewsCompatibleAct.actionStart(getActivity(), newsInfo.getTitle(), newsInfo.getContent());
                    }
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            NewsCompatibleInfo newsInfo = mNewsList.get(position);
            holder.newsTitleText.setText(newsInfo.getTitle());
        }

        @Override
        public int getItemCount() {
            return mNewsList.size();
        }

    }

}
