package com.lqc.designsupportlibrarydemo.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
    private FloatingActionButton fb;
    private String sourceCon;
    private String result; //编辑窗口返回的数据

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);

        //这里是如果是编辑已有的memo返回的数据
        Intent intent = getIntent();
        try{
            sourceCon = intent.getExtras().getString("sourceCon");
            result = intent.getExtras().getString("result");
            Todos todo = new TodoDao(MemoActivity.this).get(Todos.CON_FIELD_NAME, sourceCon).get(0);
            todo.setTodoCon(result);    //更新数据
            new TodoDao(MemoActivity.this).update(todo);    //保存到数据库
        }catch (NullPointerException e){
            e.printStackTrace();
        }


        mDrawerLayout = (DrawerLayout)findViewById(R.id.memo_drawer);
        mToolbar = (Toolbar)findViewById(R.id.memo_toolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        // recyclerView 配置

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

        fb = (FloatingActionButton)findViewById(R.id.memo_fab);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MemoActivity.this, MemoEditActivity.class);
                intent.putExtra("isNew", true);

                startActivityForResult(new Intent(MemoActivity.this, MemoEditActivity.class), 1);
            }
        });

        initEvent();

    }

    //编辑界面返回的数据的保存
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        result = data.getExtras().getString("result");

        //更新界面
        mAdapter.addMemo(0, result);
        //更新数据库
        new TodoDao(MemoActivity.this).add(new Todos(result));
    }

    ///////这里好像没有效果=============
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
//                Toast.makeText(MemoActivity.this,
//                        "长点击事件",
//                        Toast.LENGTH_SHORT)
//                        .show();
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
                break;
//            case R.id.menu_memo_action_delete:
//                mAdapter.removeData();
//                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
