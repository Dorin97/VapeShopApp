package ipleiria.pt.amsi.vapeshop.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ipleiria.pt.amsi.vapeshop.model.Product;

public class ProdutoJsonParser {

    public static ArrayList<Product> parserJsonProdutos(JSONArray response, Context context)
    {
        ArrayList<Product> tempListaProduto = new ArrayList<Product>();

        try
        {
            for (int i = 0; i < response.length(); i++)
            {
                JSONObject produto = (JSONObject) response.get(i);

                int idProduto = produto.getInt("id");
                String nome = produto.getString("nome");
                String descircao = produto.getString("descricao");
                float preco = Float.parseFloat(produto.getString("preco"));
                String imagem = produto.getString("imagem");

                Product auxLivro = new Product(idProduto,nome,descircao,preco,imagem);
                tempListaProduto.add(auxLivro);
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            Toast.makeText(context,"Error: "+e.getMessage(), Toast.LENGTH_LONG).show();
        }

        return tempListaProduto;
    }

    public static Product parserJsonProdutos(String response, Context context)
    {
        Product auxProduto = null;

        try
        {
            JSONObject produto = new JSONObject(response);

            int idProduto = produto.getInt("id");
            String nome = produto.getString("nome");
            String descircao = produto.getString("descricao");
            float preco = Float.parseFloat(produto.getString("preco"));
            String imagem = produto.getString("imagem");

            auxProduto = new Product(idProduto,nome,descircao,preco,imagem);

        }
        catch (JSONException e)
        {
            e.printStackTrace();
            Toast.makeText(context,"Error: "+e.getMessage(), Toast.LENGTH_LONG).show();
        }

        return auxProduto;
    }

    public static boolean isConnectionInternet(Context context)
    {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }

    public static boolean parserJsonLogin(Context context)
    {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }


}
