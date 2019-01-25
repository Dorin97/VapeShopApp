package ipleiria.pt.amsi.vapeshop;

public class ProductList {
    private String ProductName;
    private String ImageUrl;
    private int Price;

    public String getProductName(){
        return ProductName;
    }

    public String getImageUrl(){
        return ImageUrl;
    }

    public int getPrice(){
        return Price;
    }

    public ProductList(String ProductName, String ImageUrl, int Price){
        this.ProductName = ProductName;
        this.ImageUrl = ImageUrl;
        this.Price = Price;
    }

}
