package vn.edu.hcmuaf.fit.doanweb.dao.model;

import java.io.Serializable;

public class ScreenPermissions implements Serializable {
    public  int id;
    public int idRights;
    public int idScreen;
    public int read;
    public int add;
    public int delete;
    public int edit;

    public ScreenPermissions(int id, int idRights, int idScreen, int read, int add, int delete, int edit) {

        this.id = id;
        this.idRights = idRights;
        this.idScreen = idScreen;
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

    public int getIdRights() {
        return idRights;
    }

    public void setIdRights(int idRights) {
        this.idRights = idRights;
    }

    public int getIdScreen() {
        return idScreen;
    }

    public void setIdScreen(int idScreen) {
        this.idScreen = idScreen;
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
