package vn.edu.hcmuaf.fit.doanweb.dao.model;

public class ExportOrders {
    private int id;
    private  int productId;
    private  String productName;
    private  String image;
    private  double salePrice;
    private  int quantity;

    public ExportOrders(int id, int productId, String productName, String image, double salePrice, int quantity) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.image = image;
        this.salePrice = salePrice;
        this.quantity = quantity;
    }

    public ExportOrders() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
