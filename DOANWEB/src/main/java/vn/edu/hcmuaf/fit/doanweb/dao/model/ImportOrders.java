package vn.edu.hcmuaf.fit.doanweb.dao.model;

public class ImportOrders {
    private int id;
    private  int product_id;
    private  String product_name;
    private String image;
    private  double purchase_price;
    private  int quantity;

    public ImportOrders(int id, int product_id, String product_name, String image, double purchase_price, int quantity) {
        this.id = id;
        this.product_id = product_id;
        this.product_name = product_name;
        this.image = image;
        this.purchase_price = purchase_price;
        this.quantity = quantity;
    }

    public ImportOrders() {

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPurchase_price() {
        return purchase_price;
    }

    public void setPurchase_price(double purchase_price) {
        this.purchase_price = purchase_price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
