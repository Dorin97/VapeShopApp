package ipleiria.pt.amsi.vapeshop;

public class ProductItem {
    private String mImageUrl;
    private String mProductName;
    private int mPrice;

    public ProductItem(String ImageUrl, String ProductName, int Price){
        mImageUrl = ImageUrl;
        mProductName = ProductName;
        mPrice = Price;
    }

    public String getmImageUrl(){
        return mImageUrl;
    }

    public String getmProductName(){
        return mProductName;
    }

    public int getmPrice(){
        return mPrice;
    }

}
