package ipleiria.pt.amsi.vapeshop.model;

public class Product {

    private String name ;
    private String Description;
    private int price;
    private String image_url;

    public Product() {
    }

    public Product(String name, String description, int price, String image_url) {
        this.name = name;
        Description = description;
        this.price = price;
        this.image_url = image_url;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return Description;
    }

    public int getPrice() {
        return price;
    }

    public String getImage_url() {
        return image_url;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setPrice(int nb_episode) {
        this.price = nb_episode;
    }


    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}