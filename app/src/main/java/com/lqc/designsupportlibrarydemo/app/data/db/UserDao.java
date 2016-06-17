package com.lqc.designsupportlibrarydemo.app.data.db;

import android.content.Context;
import com.j256.ormlite.dao.Dao;
import com.lqc.designsupportlibrarydemo.app.data.bean.User;

import java.sql.SQLException;

/**
 * Created by albert on 16-6-17.
 */
public class UserDao
{
    private Context context;
    private Dao<User, Integer> userDaoOpe;
    private DatabaseHelper helper;

    public UserDao(Context context)
    {
        this.context = context;
        try
        {
            helper = DatabaseHelper.getHelper(context);
            userDaoOpe = helper.getDao(User.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 增加一个用户
     *
     * @param user
     * @throws SQLException
     */
    public void add(User user)
    {
		/*//事务操作
		TransactionManager.callInTransaction(helper.getConnectionSource(),
				new Callable<Void>()
				{

					@Override
					public Void call() throws Exception
					{
						return null;
					}
				});*/
        try
        {
            userDaoOpe.create(user);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    public User get(int id)
    {
        try
        {
            return userDaoOpe.queryForId(id);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

}
