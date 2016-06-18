package com.lqc.designsupportlibrarydemo.app.data.db;

import android.content.Context;
import com.j256.ormlite.dao.Dao;
import com.lqc.designsupportlibrarydemo.app.data.bean.Notes;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

/**
 * Created by albert on 16-6-18.
 */
public class NotesDao {
    private Context context;
    private Dao<Notes, Integer> notesDaoOpe;
    private DatabaseHelper helper;

    @SuppressWarnings("unchecked")
    public NotesDao(Context context){
        this.context = context;
        try {
            helper = DatabaseHelper.getHelper(context);
            notesDaoOpe = helper.getDao(Notes.class);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    // 增加 =========================================================

    /**
     * 构造对象增加
     * @param note
     */
    public void add(Notes note){
        try {
            notesDaoOpe.create(note);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    // 查询 =========================================================

    /**
     * 通过id查询
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    public Notes get(int id){
        Notes note = null;
        try {
            note = notesDaoOpe.queryForId(id);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return note;
    }

    /**
     * 通过自己规定字段和字段的值来查找
     * @param fieldName
     * @param value
     * @return
     */
    public List<Notes> get(String fieldName, Object value){
        try{
            return notesDaoOpe.queryForEq(fieldName, value);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查找所有
     * @return
     */
    public List<Notes> getAll(){
        try {
            return notesDaoOpe.queryForAll();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 外键查找相同tagId的note列表
     * @param tagId
     * @return
     */
    public List<Notes> getByTagsId(int tagId){
        try{
            return notesDaoOpe.queryBuilder().where().eq(Notes.TAG_FIELD_NAME, tagId).query();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    // 修改 =========================================================

    /**
     * 修改指定对象
     * @param notes
     */
    public void update(Notes notes){
        try{
            notesDaoOpe.update(notes);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    // 删除 =========================================================

    /**
     * 通过id删除
     * @param id
     */
    public void delete(int id){
        try {
            notesDaoOpe.deleteById(id);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * 删除指定对象
     * @param notes
     */
    public void delete(Notes notes){
        try {
            notesDaoOpe.delete(notes);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
