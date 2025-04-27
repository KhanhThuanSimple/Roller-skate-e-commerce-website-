package vn.edu.hcmuaf.fit.doanweb.dao.model;

import java.io.Serializable;

public class Stock implements Serializable {
    public  int id;
    public int product_id;
    public String product_name;
    public int quantity_stock;

    public Stock(int id, int product_id, String product_name, int quantity_stock) {
        this.id = id;
        this.product_id = product_id;
        this.product_name = product_name;
        this.quantity_stock = quantity_stock;
    }

    public Stock() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getQuantity_stock() {
        return quantity_stock;
    }

    public void setQuantity_stock(int quantityStock) {
        this.quantity_stock = quantity_stock;
    }
}
