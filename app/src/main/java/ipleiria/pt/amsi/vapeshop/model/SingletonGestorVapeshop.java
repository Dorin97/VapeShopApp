package ipleiria.pt.amsi.vapeshop.model;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.util.ArrayList;

import ipleiria.pt.amsi.vapeshop.HomePage;
import ipleiria.pt.amsi.vapeshop.listeners.ProdutosListener;
import ipleiria.pt.amsi.vapeshop.utils.ProdutoJsonParser;

public class SingletonGestorVapeshop implements ProdutosListener{

    private static SingletonGestorVapeshop INSTANCE = null;

    private ArrayList<Product> produtos;

    private static RequestQueue volleyQueue = null;

    private ProdDBHelper produtoProdDBHelper = null;

    private ProdutosListener produtosListener;

    private String mUrlAPIProdutos =  "http://192.168.1.70:8888/produtos";
    private String mUrlAPILogin =  "http://192.168.1.70:8888/auth/login";

    public static synchronized SingletonGestorVapeshop getInstance(Context context) {
        if (INSTANCE == null)
        {
            INSTANCE = new SingletonGestorVapeshop(context);
            volleyQueue = Volley.newRequestQueue(context);
        }
        return INSTANCE;
    }
    private SingletonGestorVapeshop(Context context) {
        produtos = new ArrayList<>();
        //gerarFakeData();

        produtoProdDBHelper = new ProdDBHelper(context);
    }
    public ArrayList<Product>getProdutos()
    {
        produtos = produtoProdDBHelper.getAllProdutosBD();
        return new ArrayList<>(produtos);
    }

    public Product getProduto(long idProduto)
    {
        for (Product produto : produtos)
        {
            if(produto.getId() == idProduto)
            {
                return produto;
            }
        }
        return null;
    }

    public void adicionarProdutoBD(Product produto)
    {
        produtoProdDBHelper.adicionarProdutoBD(produto);
    }

    public void adicionarProdutosBD(ArrayList<Product> listaProdutos)
    {
        produtoProdDBHelper.removerAllProdutos();

        for(Product produto : listaProdutos)
        {
            adicionarProdutoBD(produto);
        }
    }

    public void adicionarProdutoAPI(final Product produto, final Context context)
    {

    }

    public void removerProdutoBD(long idProduto)
    {
        Product auxProduto = getProduto(idProduto);

        if(auxProduto != null)
        {
            if (produtoProdDBHelper.removerProdutoBD(auxProduto.getId()))
            {
                produtos.remove(auxProduto);
            }
        }
    }

    public void editarProdutoBD(Product produto) {
        if (!produtos.contains(produto))
        {
            return;
        }

        Product auxProduto = getProduto(produto.getId());

        auxProduto.setName(produto.getName());
        auxProduto.setDescription(produto.getDescription());
        auxProduto.setPrice(produto.getPrice());
        auxProduto.setImage_url(produto.getImage_url());

        if (produtoProdDBHelper.editarProdutoBD(auxProduto))
        {
            System.out.println("Produto Alterado");
        }
    }

    public void getAllProdutosAPI(final Context context, boolean isConnected)
    {
        Toast.makeText(context, "Network connection: "+ isConnected, Toast.LENGTH_LONG).show();

        if(!isConnected)
        {
            produtos = produtoProdDBHelper.getAllProdutosBD();
        }
        else
        {
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlAPIProdutos,null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    System.out.println("--> RESPOSTA: " + response);

                    produtos = ProdutoJsonParser.parserJsonProdutos(response,context);
                    adicionarProdutosBD(produtos);

                    if (produtosListener != null)
                    {
                        produtosListener.onRefreshListaProdutos(produtos);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("-->Erro: GETALLPODUTOAPI");
                }
            });

            volleyQueue.add(req);
        }
    }
    public void setProdutosListener(HomePage produtosListener)
    {
        this.produtosListener = produtosListener;

    }

    @Override
    public void onRefreshListaProdutos(ArrayList<Product> listaProdutos) {

    }

    @Override
    public void onUpdateListaProdutos(Product produto, int operacao) {
        switch (operacao)
        {
            case 1: adicionarProdutoBD(produto);
                break;

            case 2: editarProdutoBD(produto);
                break;

            case 3: removerProdutoBD(produto.getId());
                break;
        }
    }

    public void loginAPI(final String email, final String password, final Context context){

    }
}
