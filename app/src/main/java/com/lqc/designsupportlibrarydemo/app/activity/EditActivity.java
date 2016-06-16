package com.lqc.designsupportlibrarydemo.app.activity;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.lqc.designsupportlibrarydemo.app.R;
import com.lqc.designsupportlibrarydemo.app.adapter.BtnRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by albert on 16-6-16.
 */
public class EditActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager mLayoutManager;
    private BtnRecyclerViewAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Toolbar toolbar = (Toolbar)findViewById(R.id.edit_toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        //开始设置recyclerview,格式编辑菜单
        recyclerView = (RecyclerView)findViewById(R.id.format_btn);
        recyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        recyclerView.setLayoutManager(mLayoutManager);

        List<Integer> mSrcBtnImgs = new ArrayList<Integer>();
        mSrcBtnImgs.add(R.drawable.ic_format_img);
        mSrcBtnImgs.add(R.drawable.ic_format_bold);
        mSrcBtnImgs.add(R.drawable.ic_format_quote);
        mSrcBtnImgs.add(R.drawable.ic_format_bullet);
        mSrcBtnImgs.add(R.drawable.ic_insert_link);
        mSrcBtnImgs.add(R.drawable.ic_insert_link);
        mSrcBtnImgs.add(R.drawable.ic_format_strikethrough);
        mSrcBtnImgs.add(R.drawable.ic_format_bold);
        mSrcBtnImgs.add(R.drawable.ic_format_italic);
        mSrcBtnImgs.add(R.drawable.ic_format_underline);
        mAdapter = new BtnRecyclerViewAdapter(this, mSrcBtnImgs);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_overaction, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
