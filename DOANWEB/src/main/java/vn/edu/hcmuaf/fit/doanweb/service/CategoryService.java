package vn.edu.hcmuaf.fit.doanweb.service;

import vn.edu.hcmuaf.fit.doanweb.dao.CategoryDao;
import vn.edu.hcmuaf.fit.doanweb.dao.model.Category;

import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryService {
    public boolean insertCategory(String name) throws SQLException {
        CategoryDao categoryDao = new CategoryDao();
        return categoryDao.insertCategory(name);
    }
    public boolean updateCategory(String name, int id) throws SQLException {
        CategoryDao categoryDao = new CategoryDao();
        return categoryDao.updateCategory(name,id);
    }
    public boolean deleteCategory(int id) throws SQLException {
        CategoryDao categoryDao = new CategoryDao();
        return categoryDao.deleteCategory(id);
    }
    public int getPageCategory( ) throws SQLException {
        CategoryDao categoryDao = new CategoryDao();
        return categoryDao.getPageCategory();
    }
    public ArrayList<Category> getListCategory(int page) throws SQLException {
        CategoryDao categoryDao = new CategoryDao();
        ArrayList<Category> categorys = categoryDao.getListCategory(page);
        return categorys;
    }
}
