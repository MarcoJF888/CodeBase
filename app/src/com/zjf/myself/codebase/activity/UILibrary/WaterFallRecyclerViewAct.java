package com.zjf.myself.codebase.activity.UILibrary;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.adapter.WaterFallAdapter;
import com.zjf.myself.codebase.util.ImageUtil;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/26.
 */

public class WaterFallRecyclerViewAct extends AppCompatActivity {
    private RecyclerView recyclerView;
    private WaterFallAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.win_water_fall);
        recyclerView = (RecyclerView) findViewById(R.id.recylerview);
        recyclerView.setHasFixedSize(true);


        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter = new WaterFallAdapter());
        adapter.replaceAll(getData());
    }

    public ArrayList<String> getData() {
        ArrayList<String> list = new ArrayList<>();
        for (String url : ImageUtil.imageUrls) {
            list.add(url);
        }
        return list;
    }
}