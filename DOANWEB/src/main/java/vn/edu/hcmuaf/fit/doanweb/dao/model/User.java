package vn.edu.hcmuaf.fit.doanweb.dao.model;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String username;

    private String password;

    private String name;


    private int type;
    private String phone;
    private String address;

    public User(int id, String username, String password, String name, int type) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.type = type;
        this.phone = phone;
        this.address = address;
    }

    public User() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
