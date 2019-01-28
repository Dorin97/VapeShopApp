package ipleiria.pt.amsi.vapeshop.listeners;

import java.util.ArrayList;

import ipleiria.pt.amsi.vapeshop.model.Product;

public interface ProdutosListener {

    void onRefreshListaProdutos(ArrayList<Product> listaProdutos);
    void onUpdateListaProdutos(Product produto, int operacao);

}
