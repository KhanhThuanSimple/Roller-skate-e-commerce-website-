package vn.edu.hcmuaf.fit.doanweb.dao.model;

public class CartProduct {
    private int id;
    private String name;
    private String img;
    private double price;
    private String title;
    private String description;
    private String offer;
    private int quantity;

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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public CartProduct(int id, String name, String img, double price, String title, String description, String offer, int quantity) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.price = price;
        this.title = title;
        this.description = description;
        this.offer = offer;
        this.quantity = quantity;
    }

    public CartProduct() {
    }

    @Override
    public String toString() {
        return "CartProduct{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", img='" + img + '\'' +
                ", price=" + price +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", offer='" + offer + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
