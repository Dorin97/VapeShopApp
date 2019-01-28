package ipleiria.pt.amsi.vapeshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.tonyvu.sc.util.CartHelper;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.android.tonyvu.sc.model.Cart;

import ipleiria.pt.amsi.vapeshop.model.Product;


// AImplementamos a interface BottomNavigationView.OnNavigationItemSelectedListener
// para transformar a Activity numa Listener de item de menu
public class Produto extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationView navigationView;
    private static final String TAG = "ProductActivity";

    Button btnAddCart;
    Spinner spQuantidade;
    Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);
        btnAddCart = (Button) findViewById(R.id.btnAddCart);

        //inicialização do bottom menu
        navigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);

        //chamamos o método setOnNavigationItemSelectedListener para a Activity
        // notificar e escutar quando um item da Bottom Navigation for selecionado.
        navigationView.setOnNavigationItemSelectedListener(this);

        // Recieve data
        String name  = getIntent().getExtras().getString("nome");
        String description = getIntent().getExtras().getString("descricao");
        Double price = getIntent().getExtras().getDouble("preco") ;
        String image_url = getIntent().getExtras().getString("imagem") ;

        // ini views

        TextView tv_name = findViewById(R.id.txtNomeProduto);
        TextView tv_description = findViewById(R.id.txtDescricao);
        TextView tv_price = findViewById(R.id.txtPreco);
        ImageView img = findViewById(R.id.imgProduto);

        // setting values to each view

        tv_name.setText(name);
        tv_description.setText(description);
        tv_price.setText(String.valueOf(price) + "€");

        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

        // set image using Glide
        Glide.with(this).load(image_url).apply(requestOptions).into(img);

        onOrderProduct();
    }

    private void onOrderProduct() {
        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cart cart = CartHelper.getCart();
               // Log.d(TAG, "Adding product: " + product.getName());
                //cart.add(product, Integer.valueOf(spQuantidade.getSelectedItem().toString()));
                Intent intent = new Intent(Produto.this, Cart.class);
                startActivity(intent);
            }
        });
    }

    private void initializeQuantity() {
        ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spQuantidade.setAdapter(dataAdapter);
    }

    //abre janela dos vapers
    public void onClickVapers(View view) {
        Intent intentVapers = new Intent(this, Vapers.class);
        startActivity(intentVapers);
    }

    //abre janela dos liquidos
    public void onClickLiquids(View view) {
        Intent intentLiquids = new Intent(this, Eliquids.class);
        startActivity(intentLiquids);
    }

    //abre janela dos costumizables
    public void onClickCostums(View view) {
        Intent intentConstums = new Intent(this, Customizables.class);
        startActivity(intentConstums);
    }

    //navigation menu
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuHome: {
                getSupportActionBar().setTitle("Home");
                Intent intentHomePage = new Intent(this, HomePage.class);
                startActivity(intentHomePage);
                // Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.menuProfile: {
                getSupportActionBar().setTitle("Profile");
                Intent intentProfile = new Intent(this, Profile.class);
                startActivity(intentProfile);
                // Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.menuCart: {
                getSupportActionBar().setTitle("Cart");
                Intent intentCart = new Intent(this, Cart.class);
                startActivity(intentCart);
                // Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            }
        }
        return true;
    }
}
