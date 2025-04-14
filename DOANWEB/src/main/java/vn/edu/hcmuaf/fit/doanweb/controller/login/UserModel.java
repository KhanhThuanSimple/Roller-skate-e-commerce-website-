package vn.edu.hcmuaf.fit.doanweb.controller.login;

import java.sql.Timestamp;

public class UserModel {
    private long id;
    private String username;
    private String password;
    private String name;
    private int type;
    private String phoneNumber;
    private String address;
    private String resetToken;
    private Timestamp tokenExpiry;

    public UserModel() {
    }

    public UserModel(long id, String username, String password, String name, int type,
                     String phoneNumber, String address, String resetToken, Timestamp tokenExpiry) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.type = type;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.resetToken = resetToken;
        this.tokenExpiry = tokenExpiry;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    public Timestamp getTokenExpiry() {
        return tokenExpiry;
    }

    public void setTokenExpiry(Timestamp tokenExpiry) {
        this.tokenExpiry = tokenExpiry;
    }
}
