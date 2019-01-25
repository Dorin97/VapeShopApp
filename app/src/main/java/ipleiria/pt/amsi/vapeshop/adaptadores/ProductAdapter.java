package ipleiria.pt.amsi.vapeshop.adaptadores;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ipleiria.pt.amsi.vapeshop.ProductList;
import ipleiria.pt.amsi.vapeshop.R;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    public static final String KEY_NOME = "nome";
    public static final String KEY_IMAGEM = "imagem";
    public static final String KEY_PRECO = "preco";


    private List<ProductList> productLists;
    private Context context;

    public ProductAdapter(List<ProductList> productLists, Context context){
        this.productLists = productLists;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final ProductList productList = productLists.get(position);
        holder.txtProductName.setText(productList.getProductName());

        Picasso.with(context)
                .load(productList.getImageUrl())
                .into(holder.imageUrl);
        /*
        holder.linearLayout.setOnClickListener((v){
                ProductList productList1 = productLists.get(position);
        Intent infoIntent = new Intent(v.getContext(), Produto.class);
        infoIntent.putExtra(KEY_NOME, productLists.getProductName);
        infoIntent.putExtra(KEY_IMAGEM, productLists.getImageUrl);
        infoIntent.putExtra(KEY_PRECO, productLists.getPrice);
        v.getContext().startAtivity(infoIntent);
        });
        /*

        /*
        ProductItem currentItem = mProductList.get(position);

        String imageUrl = currentItem.getmImageUrl();
        String ProductName = currentItem.getmProductName();
        int price = currentItem.getmPrice();

        holder.mTextViewProductName.setText(ProductName);
        holder.mTextViewPrice.setText("Price: " + price);
        Picasso.with(mContext).load(imageUrl).fit().centerInside().into(holder.mImageView);
        */
    }

    @Override
    public int getItemCount() {
        return productLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txtProductName;
        public ImageView imageUrl;
        public TextView txtPrice;
        public LinearLayout linearLayout;

        public ViewHolder( View itemView) {
            super(itemView);
            txtProductName = itemView.findViewById(R.id.nome_produto);
            imageUrl = itemView.findViewById(R.id.imageView);
            txtPrice = itemView.findViewById(R.id.txtPreco);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
        }
    }
}
