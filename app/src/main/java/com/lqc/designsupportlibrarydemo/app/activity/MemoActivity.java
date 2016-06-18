package com.lqc.designsupportlibrarydemo.app.activity;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.lqc.designsupportlibrarydemo.app.R;
import com.lqc.designsupportlibrarydemo.app.adapter.MemoRecyclerViewAdapter;
import com.lqc.designsupportlibrarydemo.app.data.bean.Todos;
import com.lqc.designsupportlibrarydemo.app.data.db.TodoDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by albert on 16-6-18.
 */
public class MemoActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private MemoRecyclerViewAdapter mAdapter;
    private List<String> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);

        mDrawerLayout = (DrawerLayout)findViewById(R.id.memo_drawer);
        mToolbar = (Toolbar)findViewById(R.id.memo_toolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        // recyclerView 配置===============================

        //从数据库获取数据
        mDatas = new ArrayList<String>();
        for(Todos todo: new TodoDao(MemoActivity.this).getAll()){
            mDatas.add(todo.getTodoCon());
        }
        mRecyclerView = (RecyclerView)findViewById(R.id.memo_recyclerView);
        mAdapter = new MemoRecyclerViewAdapter(this, mDatas);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        initEvent();

    }

    private void initEvent()
    {
        mAdapter.setOnItemClickListener(new MemoRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int positon) {
                Toast.makeText(MemoActivity.this,
                        "点击事件",
                        Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(MemoActivity.this,
                        "长点击事件",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_memo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
