package ipleiria.pt.amsi.vapeshop.model;

import com.android.tonyvu.sc.model.Saleable;

public class CartItem {
    private Product produto;
    private int quantidade;

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Saleable getProduto() {
        return produto;
    }

    public void setProduto(Product produto) {
        this.produto = produto;
    }

}