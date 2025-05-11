package vn.edu.hcmuaf.fit.doanweb.dao.model;

import java.io.Serializable;

public class ScreenPermissions implements Serializable {
    public int id;

    public int idScreen;
    public  String nameScreen;
    public String codeScreen;



    public int idRights;
    public String nameRights;

    public int read;
    public int add;
    public int delete;
    public int edit;

    public ScreenPermissions(int id, int idScreen, String nameScreen, String codeScreen, int idRights, String nameRights, int read, int add, int delete, int edit) {
        this.id = id;
        this.idScreen = idScreen;
        this.nameScreen = nameScreen;
        this.codeScreen = codeScreen;
        this.idRights = idRights;
        this.nameRights = nameRights;
        this.read = read;
        this.add = add;
        this.delete = delete;
        this.edit = edit;
    }

    public ScreenPermissions() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdScreen() {
        return idScreen;
    }

    public void setIdScreen(int idScreen) {
        this.idScreen = idScreen;
    }

    public String getNameScreen() {
        return nameScreen;
    }

    public void setNameScreen(String nameScreen) {
        this.nameScreen = nameScreen;
    }

    public String getCodeScreen() {
        return codeScreen;
    }

    public void setCodeScreen(String codeScreen) {
        this.codeScreen = codeScreen;
    }

    public int getIdRights() {
        return idRights;
    }

    public void setIdRights(int idRights) {
        this.idRights = idRights;
    }

    public String getNameRights() {
        return nameRights;
    }

    public void setNameRights(String nameRights) {
        this.nameRights = nameRights;
    }

    public int getRead() {
        return read;
    }

    public void setRead(int read) {
        this.read = read;
    }

    public int getAdd() {
        return add;
    }

    public void setAdd(int add) {
        this.add = add;
    }

    public int getDelete() {
        return delete;
    }

    public void setDelete(int delete) {
        this.delete = delete;
    }

    public int getEdit() {
        return edit;
    }

    public void setEdit(int edit) {
        this.edit = edit;
    }
}