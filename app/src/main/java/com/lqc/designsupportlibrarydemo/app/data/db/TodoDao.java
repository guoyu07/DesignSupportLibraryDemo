package com.lqc.designsupportlibrarydemo.app.data.db;

import android.content.Context;
import com.j256.ormlite.dao.Dao;
import com.lqc.designsupportlibrarydemo.app.data.bean.Todos;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by albert on 16-6-18.
 */
public class TodoDao {
    private Context context;
    private DatabaseHelper helper;
    private Dao<Todos, Integer> todoDaoOpe;

    public TodoDao(Context context){
        this.context = context;
        try {
            helper = DatabaseHelper.getHelper(context);
            todoDaoOpe = helper.getDao(Todos.class);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    // 增加 =========================================================

    /**
     * 增加一个数据项
     * @param ：
     */
    public void add(Todos todo){
        try {
            todoDaoOpe.create(todo);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    // 查找 =========================================================

    /**
     * 通过id查找
     * @param id
     * @return
     */
    public Todos get(int id){
        try {
            return todoDaoOpe.queryForId(id);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过自己规定字段和字段的值来查找
     * @param fieldName
     * @param value
     * @return
     */
    public List<Todos> get(String fieldName, Object value){
        try {
            return todoDaoOpe.queryForEq(fieldName, value);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 返回所有todo内容
     * @return
     */
    public List<Todos> getAll(){
        try {
            return todoDaoOpe.queryForAll();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    // 修改 =========================================================

    /**
     * 指定对象删除
     * @param
     */
    public void update(Todos todo){
        try {
            todoDaoOpe.update(todo);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    // 删除 =========================================================

    /**
     * 指定对象id删除
     * @param id
     */
    public void delete(int id){
        try {
            todoDaoOpe.deleteById(id);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * 指定对象删除
     * @param
     */
    public void delete(Todos todo){
        try {
            todoDaoOpe.delete(todo);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
