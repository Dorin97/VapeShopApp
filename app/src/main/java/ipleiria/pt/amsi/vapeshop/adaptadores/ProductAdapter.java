package ipleiria.pt.amsi.vapeshop.adaptadores;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ipleiria.pt.amsi.vapeshop.ProductItem;
import ipleiria.pt.amsi.vapeshop.R;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private Context mContext;
    private ArrayList<ProductItem> mProductList;

    public ProductAdapter(Context context, ArrayList<ProductItem> productList){
       mContext = context;
       mProductList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.product_item, parent, false);
        return new ProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        ProductItem currentItem = mProductList.get(position);

        String imageUrl = currentItem.getmImageUrl();
        String ProductName = currentItem.getmProductName();
        int price = currentItem.getmPrice();

        holder.mTextViewProductName.setText(ProductName);
        holder.mTextViewPrice.setText("Price: " + price);
        Picasso.with(mContext).load(imageUrl).fit().centerInside().into(holder.mImageView);


    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public TextView mTextViewProductName;
        public TextView mTextViewPrice;



        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view);
            mTextViewProductName = itemView.findViewById(R.id.text_view_product_name);
            mTextViewPrice = itemView.findViewById(R.id.text_view_price);
        }
    }
}
