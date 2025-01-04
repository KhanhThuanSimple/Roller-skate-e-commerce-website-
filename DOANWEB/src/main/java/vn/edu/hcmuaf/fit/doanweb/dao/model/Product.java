package vn.edu.hcmuaf.fit.doanweb.dao.model;

public class Product implements java.io.Serializable {



        private int id;
        private String name;
        private double price;
        private String description;
        private String img;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
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

    public Product(int id, String name, double price, String description, String img) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.img = img;
    }

    @Override
        public String toString() {
            return
                    id +";"+name+";"+price+";"+img+";"+description;

        }

        public Product() {}
    }
