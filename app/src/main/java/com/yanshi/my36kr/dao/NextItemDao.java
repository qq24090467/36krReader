package com.yanshi.my36kr.dao;

import android.content.Context;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.table.TableUtils;
import com.yanshi.my36kr.bean.NewsItem;
import com.yanshi.my36kr.bean.NextItem;
import com.yanshi.my36kr.utils.DatabaseHelper;

import java.sql.SQLException;
import java.util.List;

/**
 * NEXT项目dao
 * 作者：yanshi
 * 时间：2014-11-04 11:44
 */
public class NextItemDao {

    private Dao<NextItem, Integer> nextItemDao;

    public NextItemDao(Context context) {
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(context);
            nextItemDao = helper.getDao(NextItem.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean add(NextItem nextItem) {
        try {
            nextItemDao.createOrUpdate(nextItem);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteById(int id) {

        try {
            nextItemDao.deleteById(id);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteByItem(NextItem nextItem) {
        try {
            nextItemDao.delete(nextItem);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteBatch(List<NextItem> list) {
        try {
            nextItemDao.delete(list);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void update(NextItem nextItem) {

        try {
            nextItemDao.update(nextItem);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<NextItem> getAll() {

        try {
            return nextItemDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    //根据主键查询
    public boolean findItemById(int id) {
        try {
            return null != (nextItemDao.queryForId(id));
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //条件查询
    public NextItem findItemByTitle(String title) {
        try {
            List<NextItem> list = nextItemDao.queryBuilder().where().eq("title", title).query();
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
        List<NextItem> next = getAll();
        return deleteBatch(next);
    }

}
