package com.lqc.designsupportlibrarydemo.app.test;

import android.test.AndroidTestCase;
import com.lqc.designsupportlibrarydemo.app.data.Helper.DatabaseHelper;
import com.lqc.designsupportlibrarydemo.app.data.model.EditList;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by albert on 16-6-14.
 */
public class OrmLiteTest extends AndroidTestCase {
    public void testAddEditList(){
        EditList el = new EditList("测试1", "这是测试内...", "这是测试内容，没有什么东西的", new Timestamp(System.currentTimeMillis()));
        DatabaseHelper dbHelper = DatabaseHelper.getHelper(getContext());
        try{
            dbHelper.getEditlistDao().create(el);
            el = new EditList("测试2", "这是测试内...", "这是测试内容，没有什么东西的", new Timestamp(System.currentTimeMillis()));
            dbHelper.getEditlistDao().create(el);
            el = new EditList("测试3", "这是测试内...", "这是测试内容，没有什么东西的", new Timestamp(System.currentTimeMillis()));
            dbHelper.getEditlistDao().create(el);
            testEditList_List();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void testDeleteEditList(){
        DatabaseHelper dbHelper = DatabaseHelper.getHelper(getContext());
        try{
            dbHelper.getEditlistDao().deleteById(1);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void testUpdateEditList(){
        DatabaseHelper dbHelper = DatabaseHelper.getHelper(getContext());
        try {
            EditList el = new EditList("测试6", "这是测试内...", "这是测试内容，没有什么东西的", new Timestamp(System.currentTimeMillis()));
            el.setItemId(6);
            dbHelper.getEditlistDao().update(el);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void testEditList_List(){
        DatabaseHelper dbHelper = DatabaseHelper.getHelper(getContext());
        try {
            List<EditList> e_list = dbHelper.getEditlistDao().queryForAll();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
