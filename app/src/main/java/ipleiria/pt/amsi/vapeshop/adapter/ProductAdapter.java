package ipleiria.pt.amsi.vapeshop.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import ipleiria.pt.amsi.vapeshop.Produto;
import ipleiria.pt.amsi.vapeshop.R;
import ipleiria.pt.amsi.vapeshop.model.Product;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    public static final String CURRENCY = "€";
    private Context mContext ;
    private List<Product> mData ;
    RequestOptions option;


    public ProductAdapter(Context mContext, List<Product> mData) {
        this.mContext = mContext;
        this.mData = mData;

        // Request option for Glide
        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view ;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.activity_product_items,parent,false) ;

        final MyViewHolder viewHolder = new MyViewHolder(view) ;
        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(mContext, Produto.class);
                i.putExtra("nome",mData.get(viewHolder.getAdapterPosition()).getName());
                i.putExtra("descricao",mData.get(viewHolder.getAdapterPosition()).getDescription());
                i.putExtra("preco",mData.get(viewHolder.getAdapterPosition()).getPrice());
                i.putExtra("imagem",mData.get(viewHolder.getAdapterPosition()).getImage_url());

                mContext.startActivity(i);

            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.pr_name.setText(mData.get(position).getName());
        //holder.description.setText(mData.get(position).getDescription());
        holder.price.setText(String.valueOf(mData.get(position).getPrice()) + "€");

        // Load Image from the internet and set it into Imageview using Glide
        Glide.with(mContext).load(mData.get(position).getImage_url()).apply(option).into(holder.img_thumbnail);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView pr_name ;
        TextView price;
        ImageView img_thumbnail;
        LinearLayout view_container;

        public MyViewHolder(View itemView) {
            super(itemView);
            view_container = itemView.findViewById(R.id.container);
            pr_name = itemView.findViewById(R.id.product_name);
            img_thumbnail = itemView.findViewById(R.id.thumbnail);
            price = itemView.findViewById(R.id.price);
        }
    }

}
