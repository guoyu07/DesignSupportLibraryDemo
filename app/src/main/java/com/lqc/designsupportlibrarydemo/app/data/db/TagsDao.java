package com.lqc.designsupportlibrarydemo.app.data.db;

import android.content.Context;
import com.j256.ormlite.dao.Dao;
import com.lqc.designsupportlibrarydemo.app.data.bean.Tags;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by albert on 16-6-18.
 */
public class TagsDao {
    private Context context;
    private Dao<Tags, Integer> tagsDaoOpe;
    private DatabaseHelper helper;

    public TagsDao(Context context){
        this.context = context;
        try{
            helper = DatabaseHelper.getHelper(context);
            tagsDaoOpe = helper.getDao(Tags.class);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    // 增加 =========================================================

    /**
     * 指定对象添加
     * @param tag
     */
    public void add(Tags tag){
        try {
            tagsDaoOpe.create(tag);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    // 查找 =========================================================

    /**
     * 指定id查找
     * @param id
     * @return
     */
    public Tags get(int id){
        try{
            return tagsDaoOpe.queryForId(id);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 自定义字段与值查找
     * @param fieldName
     * @param value
     * @return
     */
    public List<Tags> get(String fieldName, Object value){
        try{
            return tagsDaoOpe.queryForEq(fieldName, value);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查找所有的tags
     * @return
     */
    public List<Tags> getAll(){
        try {
            return tagsDaoOpe.queryForAll();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    // 修改 =========================================================

    /**
     * 指定对象删除
     * @param tags
     */
    public void update(Tags tags){
        try {
            tagsDaoOpe.update(tags);
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
            tagsDaoOpe.deleteById(id);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * 指定对象删除
     * @param tags
     */
    public void delete(Tags tags){
        try {
            tagsDaoOpe.delete(tags);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
