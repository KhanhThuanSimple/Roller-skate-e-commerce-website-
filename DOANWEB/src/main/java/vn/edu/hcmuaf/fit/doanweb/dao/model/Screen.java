package vn.edu.hcmuaf.fit.doanweb.dao.model;

import java.io.Serializable;

public class Screen implements Serializable {
    public  int id;
    public String idScreen;
    public String nameScreen;

    public Screen(int id, String idScreen, String nameScreen) {
        this.id = id;
        this.idScreen = idScreen;
        this.nameScreen = nameScreen;
    }

    public Screen() {

    }

    public String getIdScreen() {
        return idScreen;
    }

    public void setIdScreen(String idScreen) {
        this.idScreen = idScreen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameScreen() {
        return nameScreen;
    }

    public void setNameScreen(String nameScreen) {
        this.nameScreen = nameScreen;
    }
}
