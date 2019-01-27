package ipleiria.pt.amsi.vapeshop.model;

public class Product {

    private String name ;
    private String description;
    private Double price;
    private String image_url;

    public Product() {
    }

    public Product(String name, String description, Double price, String image_url) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.image_url = image_url;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public String getImage_url() {
        return image_url;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}