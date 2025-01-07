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

    public ArrayList<User> getList(int page, int type) throws SQLException {
        UserDao userDao = new UserDao();
        ArrayList<User> users = userDao.getList(page, type);
        return users;

    }
    public ArrayList<User> getListC(int page, int type) throws SQLException {
        UserDao userDao = new UserDao();
        ArrayList<User> customers = userDao.getListC(page, type);
        return customers;

    }

//    public boolean register(String name, String uname, String pass, String rePass, String phone, String address) throws SQLException {
//        UserDao userDao = new UserDao();
//
//        // Kiểm tra xem tên người dùng đã tồn tại chưa
//        User existingUser = userDao.findUserByUserName(uname);
//        if (existingUser != null) {
//            return false; // Tên người dùng đã tồn tại
//        }
//
//        // Kiểm tra xem mật khẩu có trùng khớp không
//        if (!pass.equals(rePass)) {
//            return false; // Mật khẩu không khớp
//        }
//
//        // Tạo đối tượng User mới
//        User newUser = new User();
//        newUser.setName(name); // Giả sử bạn có phương thức setName trong User
//        newUser.setUsername(uname);
//        newUser.setPassword(pass); // Mật khẩu nên được mã hóa trước khi lưu vào CSDL
//        newUser.setPhone(phone); // Giả sử bạn có phương thức setPhone trong User
//        newUser.setAddress(address); // Giả sử bạn có phương thức setAddress trong User
//
//        // Thêm người dùng mới vào cơ sở dữ liệu
//        return userDao.addUser(newUser);
//    }
}
