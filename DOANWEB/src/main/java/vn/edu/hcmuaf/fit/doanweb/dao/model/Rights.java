package vn.edu.hcmuaf.fit.doanweb.dao.model;

import java.io.Serializable;

public class Rights implements Serializable {
    public  int id;
    public String name;

    public Rights(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Rights() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
