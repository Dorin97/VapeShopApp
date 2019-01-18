package ipleiria.pt.amsi.vapeshop.adaptadores;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.TextView;


class RecyclerViewHolders {
    public TextView produtos;
    private SparseBooleanArray selectedItems = new SparseBooleanArray();
    public RecyclerViewHolders(View itemView) {
        super(itemView);
        view.setOnClickListener(this);
        produtos = (TextView)itemView.findViewById(R.id.nome_produto);
    }
    @Override
    public void onClick(View view) {
    }
}
