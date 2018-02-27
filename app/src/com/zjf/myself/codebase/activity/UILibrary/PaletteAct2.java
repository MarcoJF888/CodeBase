package com.zjf.myself.codebase.activity.UILibrary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.adapter.PaletteGridAdapter;


public class PaletteAct2 extends AppCompatActivity {

    private RecyclerView mRvPalette;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_palette2);
        mRvPalette = (RecyclerView) findViewById(R.id.rvPalette);
        mRvPalette.setLayoutManager(new GridLayoutManager(this, 2));
        mRvPalette.setAdapter(new PaletteGridAdapter());
    }

}
