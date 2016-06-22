package com.lqc.designsupportlibrarydemo.app.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
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
import android.widget.Toast;
import com.lqc.designsupportlibrarydemo.app.R;
import com.lqc.designsupportlibrarydemo.app.adapter.BtnRecyclerViewAdapter;
import com.lqc.designsupportlibrarydemo.app.customwidget.richeditor.RichLqcEditor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by albert on 16-6-16.
 */
public class RichEditActivity extends AppCompatActivity {

    //这是自定义的富文本编辑菜单及所需要的LayoutManager和Adapter
    private RecyclerView btnRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private BtnRecyclerViewAdapter mAdapter;
    private DrawerLayout dLayout;
    private NavigationView navigationView;

    //这是富文本编辑组件
    private RichLqcEditor mRichEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        dLayout = (DrawerLayout)findViewById(R.id.edit_drawer);

        Toolbar toolbar = (Toolbar)findViewById(R.id.edit_toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        mRichEditor = (RichLqcEditor)findViewById(R.id.richEditor);
        //设置菜单
        setRichMenu(this);

        //监听左滑菜单行为
        setNavMenu(this);
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
        mAdapter.setOnBtnRecyclerViewClickListener(new BtnRecyclerViewAdapter.OnBtnRecyclerViewClickListener() {
            @Override
            public void OnItemClick(View v, int position) {
                //TODO 接口调用写好，然后进行富文本调试
                Toast.makeText(RichEditActivity.this,
                        "点击了第"+position+"个按钮",
                        Toast.LENGTH_SHORT)
                        .show();
                switch (position){
                    case 0:
                        mRichEditor.insertImage();
                    case 1:
                        mRichEditor.setTextSize(18);
                    case 2:
                        mRichEditor.setQuote(true);
                    case 3:
                        mRichEditor.setUlist(true);
                    case 4:
//                        mRichEditor.link();
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                }
            }

            @Override
            public void OnIntemLongClick(View v, int flags) {

            }
        });
        btnRecyclerView.setAdapter(mAdapter);

        //菜单栏隐显设置
        final View activityRootView = findViewById(R.id.edit_layout);
        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int heightDiff = activityRootView.getRootView().getHeight() - activityRootView.getHeight();
                if (heightDiff > 100) {
                    //显示虚拟键盘的时候
                    btnRecyclerView.setVisibility(View.VISIBLE);
                    dLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                    //禁止左滑
                } else {
                    //虚拟键盘消失
                    btnRecyclerView.setVisibility(View.GONE);
                    //允许左滑
                    dLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);

                }
            }
        });
    }

    private void setNavMenu(final Context context){
        navigationView = (NavigationView)findViewById(R.id.main_nav);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                dLayout.closeDrawers();
                if (menuItem.getItemId() == R.id.nav_menu_all){
                    context.startActivity(new Intent(context, MainActivity.class)
                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
