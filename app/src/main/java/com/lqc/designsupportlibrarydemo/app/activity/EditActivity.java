package com.lqc.designsupportlibrarydemo.app.activity;

import android.content.Context;
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
import android.view.View;
import android.view.ViewTreeObserver;
import com.lqc.designsupportlibrarydemo.app.R;
import com.lqc.designsupportlibrarydemo.app.adapter.BtnRecyclerViewAdapter;
import com.lqc.designsupportlibrarydemo.app.customwidget.richeditor.RichLqcEditor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by albert on 16-6-16.
 */
public class EditActivity extends AppCompatActivity {

    //这是自定义的富文本编辑菜单及所需要的LayoutManager和Adapter
    private RecyclerView btnRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private BtnRecyclerViewAdapter mAdapter;

    //这是富文本编辑组件
    private RichLqcEditor mRichEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Toolbar toolbar = (Toolbar)findViewById(R.id.edit_toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        mRichEditor = (RichLqcEditor)findViewById(R.id.richEditor);
        //设置菜单
        setRichMenu(this);

        //菜单栏隐显设置
        final View activityRootView = findViewById(R.id.edit_layout);
        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int heightDiff = activityRootView.getRootView().getHeight()-activityRootView.getHeight();
                if (heightDiff>100){
                    //显示虚拟键盘的时候
                    btnRecyclerView.setVisibility(View.VISIBLE);
                }else {
                    //虚拟键盘消失
                    btnRecyclerView.setVisibility(View.GONE);
                }
            }
        });


    }

    private void setRichMenu(Context context){
        //开始设置recyclerview,格式编辑菜单
        btnRecyclerView = (RecyclerView)findViewById(R.id.format_btn);
        btnRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(context);
        mLayoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        btnRecyclerView.setLayoutManager(mLayoutManager);

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
        mAdapter = new BtnRecyclerViewAdapter(context, mSrcBtnImgs);
        btnRecyclerView.setAdapter(mAdapter);
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
