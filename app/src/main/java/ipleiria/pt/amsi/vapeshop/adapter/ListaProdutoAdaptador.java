package ipleiria.pt.amsi.vapeshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import ipleiria.pt.amsi.vapeshop.R;
import ipleiria.pt.amsi.vapeshop.model.Product;

public class ListaProdutoAdaptador extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Product> produtos;

    public ListaProdutoAdaptador(Context context, ArrayList<Product> produtos) {
        this.context = context;
        this.produtos = produtos;
    }

    @Override
    public int getCount() {
       return produtos.size();
    }

    @Override
    public Object getItem(int position) {
        return produtos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater == null)
        {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.activity_product_items, null);
        }

        ViewHolderLista viewHolderLista = (ViewHolderLista) convertView.getTag();

        if(viewHolderLista == null)
        {
            viewHolderLista = new ViewHolderLista(convertView);
            convertView.setTag(viewHolderLista);
        }

        viewHolderLista.update(produtos.get(position));

        return convertView;
    }

    public void refresh(ArrayList<Product> livros)
    {
        this.produtos = produtos;
        notifyDataSetChanged();
    }

    public class ViewHolderLista
    {
        private TextView product_name;
        private TextView price;
        private ImageView imgProduto;

        public ViewHolderLista(View convertView)
        {
            product_name = convertView.findViewById(R.id.product_name);
            price = convertView.findViewById(R.id.price);
            imgProduto = convertView.findViewById(R.id.thumbnail);
        }

        public void update(Product produto)
        {
            product_name.setText(produto.getName());
            price.setText(String.valueOf(produto.getPrice()));
            Glide.with(context)
                    .load(produto.getImage_url())
                    .placeholder(R.drawable.ipl_semfundo)
                    .thumbnail(0f)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgProduto);
        }
    }
}
