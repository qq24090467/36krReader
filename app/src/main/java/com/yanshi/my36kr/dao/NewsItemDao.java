package com.yanshi.my36kr.dao;

import android.content.Context;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.table.TableUtils;
import com.yanshi.my36kr.bean.NewsItem;
import com.yanshi.my36kr.utils.DatabaseHelper;

import java.sql.SQLException;
import java.util.List;

/**
 * 新闻项目dao
 * 作者：yanshi
 * 时间：2014-11-03 15:01
 */
public class NewsItemDao {

    private Dao<NewsItem, Integer> newsItemDao;

    public NewsItemDao(Context context) {
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            newsItemDao = helper.getDao(NewsItem.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean add(NewsItem newsItem) {
        try {
            newsItemDao.createOrUpdate(newsItem);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteById(int id) {

        try {
            newsItemDao.deleteById(id);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteByItem(NewsItem newsItem) {
        try {
            newsItemDao.delete(newsItem);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteBatch(List<NewsItem> list) {
        try {
            newsItemDao.delete(list);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void update(NewsItem newsItem) {

        try {
            newsItemDao.update(newsItem);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<NewsItem> getAll() {

        try {
            return newsItemDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    //根据主键查询
    public boolean findItemById(int id) {
        try {
            return null != (newsItemDao.queryForId(id));
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //条件查询
    public NewsItem findItemByTitle(String title) {
        try {
            List<NewsItem> list = newsItemDao.queryBuilder().where().eq("title", title).query();
            if (null != list && !list.isEmpty()) {
                return list.get(0);
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //清空表
    public boolean clearAll() {
        List<NewsItem> news = getAll();
        return deleteBatch(news);
    }
}
