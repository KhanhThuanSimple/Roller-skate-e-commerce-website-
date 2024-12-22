package vn.edu.hcmuaf.fit.doanweb.dao;

import vn.edu.hcmuaf.fit.doanweb.dao.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserDao {
    static Map<String, User> users = new HashMap<String, User>();

    public User findUserByUserName(String userName) {
        return users.get(userName);
    }
}
