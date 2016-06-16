package com.lqc.designsupportlibrarydemo.app.data.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.lqc.designsupportlibrarydemo.app.data.model.EditList;

import java.sql.SQLException;

/**
 * Created by albert on 16-6-8.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String TABLE_NAME = "sqlite-test.db";

    private Dao<EditList, Integer> editlistDao;

    private DatabaseHelper(Context context){
        super(context, TABLE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try{
            TableUtils.createTable(connectionSource, EditList.class);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try{
            TableUtils.dropTable(connectionSource, EditList.class, true);
            onCreate(sqLiteDatabase, connectionSource);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //单例获取Helper
    private static DatabaseHelper instance;

    public static synchronized DatabaseHelper getHelper(Context context){
        if (instance==null){
            synchronized (DatabaseHelper.class){
                if (instance==null)
                    instance = new DatabaseHelper(context);
            }
        }
        return instance;
    }

    public Dao<EditList, Integer> getEditlistDao() throws SQLException{
        if (editlistDao == null){
            editlistDao = getDao(EditList.class);
        }
        return editlistDao;
    }

    //释放资源


    @Override
    public void close() {
        super.close();
        editlistDao = null;
    }
}
