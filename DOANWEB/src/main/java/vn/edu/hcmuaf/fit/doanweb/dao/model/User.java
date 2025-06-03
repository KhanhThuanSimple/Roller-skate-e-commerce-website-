package vn.edu.hcmuaf.fit.doanweb.dao.model;

import java.sql.Timestamp;

public class User {
    public int id;
    private String username;
    private String password;
    private String name;
    private int type;
    private String phone;
    private String address;
    private String email;
    private String resetToken;
    private Timestamp tokenExpiry;
    private String googleId;
    private int idPer;
    private int role;
    private String namePer;

    public User() {
    }

    public User(int id, String username, String password, String name, int type, String phone, String address, String email, String resetToken, Timestamp tokenExpiry, String googleId, int idPer, int role, String namePer) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.type = type;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.resetToken = resetToken;
        this.tokenExpiry = tokenExpiry;
        this.googleId = googleId;
        this.idPer = idPer;
        this.role = role;
        this.namePer = namePer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public int getIdPer() {
        return idPer;
    }

    public void setIdPer(int idPer) {
        this.idPer = idPer;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getNamePer() {
        return namePer;
    }

    public void setNamePer(String namePer) {
        this.namePer = namePer;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", resetToken='" + resetToken + '\'' +
                ", tokenExpiry=" + tokenExpiry +
                ", googleId='" + googleId + '\'' +
                ", idPer=" + idPer +
                ", role=" + role +
                ", namePer='" + namePer + '\'' +
                '}';
    }
}
