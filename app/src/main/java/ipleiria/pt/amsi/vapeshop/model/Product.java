package ipleiria.pt.amsi.vapeshop.model;

import com.android.tonyvu.sc.model.Saleable;

import java.math.BigDecimal;

public class Product implements Saleable {
    public static final String CURRENCY = "$";

    private  long id;
    private String name ;
    private String description;
    private Float price;
    private String image_url;

    public Product() {
        super();
    }

    public Product(long id, String name, String description, Float price, String image_url) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image_url = image_url;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Float getPrice() {
        return price;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}