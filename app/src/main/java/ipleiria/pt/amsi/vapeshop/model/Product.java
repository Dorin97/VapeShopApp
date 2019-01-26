package ipleiria.pt.amsi.vapeshop.model;

public class Product {

    private String name ;
    private String description;
    private int price;
    private String image_url;
    private String rating;

    public Product() {
    }

    public Product(String name, String description, int price, String image_url) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.image_url = image_url;
        this.rating = rating;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public String getRating() {
        return rating;
    }

    public String getImage_url() {
        return image_url;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}