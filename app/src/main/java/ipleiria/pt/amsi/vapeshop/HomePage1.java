package ipleiria.pt.amsi.vapeshop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ipleiria.pt.amsi.vapeshop.adapter.RecyclerViewAdapter;
import ipleiria.pt.amsi.vapeshop.model.Product;


public class HomePage1 extends AppCompatActivity {

    private final String JSON_URL = "http://192.168.1.77:8888/produtos" ;
    private JsonArrayRequest request ;
    private RequestQueue requestQueue ;
    private List<Product> lstProduct ;
    private RecyclerView recyclerView ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page2);

        lstProduct = new ArrayList<>() ;
        recyclerView = findViewById(R.id.rv);
        jsonrequest();



    }

    private void jsonrequest() {

        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject  = null ;

                for (int i = 0 ; i < response.length(); i++ ) {


                    try {
                        jsonObject = response.getJSONObject(i) ;
                        Product product = new Product() ;
                        product.setName(jsonObject.getString("nome"));
                        product.setDescription(jsonObject.getString("descricao"));

                        product.setPrice(jsonObject.getInt("preco"));

                        product.setImage_url(jsonObject.getString("imagem"));
                        lstProduct.add(product);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

                setuprecyclerview(lstProduct);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        requestQueue = Volley.newRequestQueue(HomePage1.this);
        requestQueue.add(request) ;


    }

    private void setuprecyclerview(List<Product> lstAnime) {


        RecyclerViewAdapter myadapter = new RecyclerViewAdapter(this,lstAnime) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myadapter);

    }
}