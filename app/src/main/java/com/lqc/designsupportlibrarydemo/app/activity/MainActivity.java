package com.lqc.designsupportlibrarydemo.app.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;
import com.lqc.designsupportlibrarydemo.app.adapter.FragmentAdapter;
import com.lqc.designsupportlibrarydemo.app.data.bean.Notes;
import com.lqc.designsupportlibrarydemo.app.data.db.NotesDao;
import com.lqc.designsupportlibrarydemo.app.fragment.ListFragment;
import com.lqc.designsupportlibrarydemo.app.R;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);//toolbar取代actionbar

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout)findViewById(R.id.main_drawer);
        NavigationView navigationView =
                (NavigationView)findViewById(R.id.main_nav);
        if(navigationView!=null){
            setupDrawerContent(navigationView);
        }
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "准备进入写日记模式", Snackbar.LENGTH_LONG)
                        .setAction("我知道了", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(MainActivity.this, RichEditActivity.class)
                                        .setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY));
                            }
                        }).show();
            }
        });

        mViewPager = (ViewPager)findViewById(R.id.viewpager);
        setupViewPager();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    private void setupViewPager(){

        //TabLayout设置
        mTabLayout = (TabLayout)findViewById(R.id.tabs);
        List<String> titles = new ArrayList<String>();
        titles.add("游记");
        titles.add("心情");
        titles.add("读后感");
        titles.add("游记");
        titles.add("心情");
        titles.add("读后感");
        titles.add("游记");
        titles.add("心情");
        titles.add("读后感");
        for(int i=0;i<titles.size();i++){
            mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(i)));
        }

        //ViewPager设置
        List<Fragment> fragments = new ArrayList<Fragment>();
        for(int i=0;i<titles.size();i++){
            fragments.add(new ListFragment());
        }

        //viewPager设置adapter
        FragmentAdapter adapter =
                new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
        mViewPager.setAdapter(adapter);

        //将tabLayout与viewPager关联起来
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(adapter);
    }
    private void setupDrawerContent(NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        if (menuItem.getItemId() == R.id.nav_menu_memo){
                            startActivity(new Intent(MainActivity.this, MemoActivity.class));
                        }
                        return true;
                    }
                });
    }
}
