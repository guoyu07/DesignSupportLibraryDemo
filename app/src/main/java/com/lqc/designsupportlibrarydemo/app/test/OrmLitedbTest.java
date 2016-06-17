package com.lqc.designsupportlibrarydemo.app.test;

import android.test.AndroidTestCase;
import android.util.Log;
import com.j256.ormlite.dao.Dao;
import com.lqc.designsupportlibrarydemo.app.data.bean.Article;
import com.lqc.designsupportlibrarydemo.app.data.bean.Student;
import com.lqc.designsupportlibrarydemo.app.data.bean.User;
import com.lqc.designsupportlibrarydemo.app.data.db.ArticleDao;
import com.lqc.designsupportlibrarydemo.app.data.db.DatabaseHelper;
import com.lqc.designsupportlibrarydemo.app.data.db.UserDao;
import com.lqc.designsupportlibrarydemo.app.data.utils.L;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by albert on 16-6-14.
 */

public class OrmLitedbTest extends AndroidTestCase
{
    public void testAddArticle()
    {
        User u = new User();
        u.setName("张鸿洋");
        new UserDao(getContext()).add(u);
        Article article = new Article();
        article.setTitle("ORMLite的使用");
        article.setUser(u);
        new ArticleDao(getContext()).add(article);

    }

    public void testGetArticleById()
    {
        Article article = new ArticleDao(getContext()).get(1);
        L.e(article.getUser() + " , " + article.getTitle());
    }

    public void testGetArticleWithUser()
    {

        Article article = new ArticleDao(getContext()).getArticleWithUser(1);
        L.e(article.getUser() + " , " + article.getTitle());
    }

    public void testListArticlesByUserId()
    {

        List<Article> articles = new ArticleDao(getContext()).listByUserId(1);
        L.e(articles.toString());
    }

    public void testGetUserById()
    {
        User user = new UserDao(getContext()).get(1);
        L.e(user.getName());
        if (user.getArticles() != null)
            for (Article article : user.getArticles())
            {
                L.e(article.toString());
            }
    }

    public void testAddStudent() throws SQLException
    {
        Dao dao = DatabaseHelper.getHelper(getContext()).getDao(Student.class);
        Student student = new Student();
        student.setDao(dao);
        student.setName("张鸿洋");
        student.create();
    }



}
