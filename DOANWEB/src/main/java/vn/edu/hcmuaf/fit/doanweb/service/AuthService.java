package vn.edu.hcmuaf.fit.doanweb.service;

import vn.edu.hcmuaf.fit.doanweb.dao.CategoryDao;
import vn.edu.hcmuaf.fit.doanweb.dao.ExportOrdersDao;
import vn.edu.hcmuaf.fit.doanweb.dao.ImportDao;
import vn.edu.hcmuaf.fit.doanweb.dao.UserDao;
import vn.edu.hcmuaf.fit.doanweb.dao.model.Category;
import vn.edu.hcmuaf.fit.doanweb.dao.model.ExportOrders;
import vn.edu.hcmuaf.fit.doanweb.dao.model.ImportOrders;
import vn.edu.hcmuaf.fit.doanweb.dao.model.User;

import java.sql.SQLException;
import java.util.ArrayList;

public class AuthService {

    public User findByUsername(String username) throws SQLException {
        UserDao userDao = new UserDao();
        User user = userDao.findUserByUserName(username);
        if (user == null) {
            return null;
        }
        return user;
    }

    public ArrayList<User> getList(int page, int type) throws SQLException {
        UserDao userDao = new UserDao();
        ArrayList<User> users = userDao.getList(page, type);
        return users;
    }
    public int getPage( int type) throws SQLException {
        UserDao userDao = new UserDao();
        return userDao.getPage(type);
    }
    public int getPageExport( ) throws SQLException {
        ExportOrdersDao exportOrdersDao = new ExportOrdersDao();
        return exportOrdersDao.getPageExport();
    }

    public int getPageImport( ) throws SQLException {
        ImportDao importDao = new ImportDao();
        return importDao.getPageImport();
    }

    public int getPageCategory( ) throws SQLException {
        CategoryDao categoryDao = new CategoryDao();
        return categoryDao.getPageCategory();
    }


    public boolean insert(String name, String email, String pass,String address,String phone, int type) throws SQLException {
        UserDao userDao = new UserDao();
        return userDao.insertUser(name,email,pass,address,phone,type);
    }

    public boolean update(String name, String email,String address,String phone, int type, int id) throws SQLException {
        UserDao userDao = new UserDao();
        return userDao.updateUser(name,email,address,phone,type, id);
    }

    public boolean delete(int id) throws SQLException {
        UserDao userDao = new UserDao();
        return userDao.deleteUser(id);
    }

    public User findById(int id) throws SQLException {
        UserDao userDao = new UserDao();
        return userDao.getUserByID(id);
    }

    public ArrayList<ExportOrders> getListExport(int page) throws SQLException {
        ExportOrdersDao exportDao = new ExportOrdersDao();
        ArrayList<ExportOrders> exports = exportDao.getListExport(page);
        return exports;
    }

    public ArrayList<ImportOrders> getListImport(int page) throws SQLException {
        ImportDao importDao = new ImportDao();
        ArrayList<ImportOrders> imports = importDao.getListImport(page);
        return imports;
    }
    public ArrayList<Category> getListCategory(int page) throws SQLException {
        CategoryDao categoryDao = new CategoryDao();
        ArrayList<Category> categorys = categoryDao.getListCategory(page);
        return categorys;
    }

    public boolean insertImport(int product_id, String product_name, String image,double purchase_price,int quantity) throws SQLException {
       ImportDao importDao = new ImportDao();
        return importDao.insertImport(product_id,product_name,image,purchase_price,quantity);
    }
    public boolean updateImport(int product_id, String product_name,String image,double purchase_price, int quantity, int id) throws SQLException {
        ImportDao importDao = new ImportDao();
        return importDao.updateImport(product_id,product_name,image,purchase_price,quantity,id);
    }
    public boolean deleteImport(int id) throws SQLException {
        ImportDao importDao = new ImportDao();
        return importDao.deleteImport(id);
    }

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








}
