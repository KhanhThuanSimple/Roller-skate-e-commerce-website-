package vn.edu.hcmuaf.fit.doanweb.service;

import vn.edu.hcmuaf.fit.doanweb.dao.UserDao;
import vn.edu.hcmuaf.fit.doanweb.dao.model.User;

import java.sql.SQLException;
import java.util.ArrayList;

public class AuthService {

    public boolean login(String username, String password) throws SQLException {
        UserDao userDao = new UserDao();
        User user = userDao.findUserByUserName(username);
        if (user == null) {
            return false;
        }
        return user.getPassword().equals(password);
    }

    public ArrayList<User> getList(int page, String type) throws SQLException {
        UserDao userDao = new UserDao();
        ArrayList<User> users = userDao.getList(page, type);
        return users;

    }
}
