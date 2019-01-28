package ipleiria.pt.amsi.vapeshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.tonyvu.sc.model.Cart;
import com.android.tonyvu.sc.util.CartHelper;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import ipleiria.pt.amsi.vapeshop.model.Product;
import ipleiria.pt.amsi.vapeshop.model.SingletonGestorVapeshop;


// AImplementamos a interface BottomNavigationView.OnNavigationItemSelectedListener
// para transformar a Activity numa Listener de item de menu
public class DetalhesProduto extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationView navigationView;

    public Product produto;

    public TextView txtNomeProduto;
    public TextView txtDescricao;
    public TextView txtPreco;
    public ImageView imgProduto;
    long idProduto;

    public Button btnAddCart;
    public Spinner spQuantidade;

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


        txtNomeProduto = (TextView) findViewById(R.id.txtNomeProduto);
        txtDescricao = (TextView) findViewById(R.id.txtDescricao);
        txtPreco = (TextView) findViewById(R.id.txtPreco);
        imgProduto = (ImageView) findViewById(R.id.imgProduto);


        if(idProduto == -1)
        {
            setTitle(R.string.AdicionarProduto);
        }
        else
        {
            produto = SingletonGestorVapeshop.getInstance(getApplicationContext()).getProduto(idProduto);
            System.out.println(produto.getName());
            setTitle("Detalhes"+produto.getName());
            preencherDadosProduto();
        }

        idProduto = getIntent().getLongExtra(HomePage.DETALHES_PRODUTO, -1);

        produto = SingletonGestorVapeshop.getInstance(getApplicationContext()).getProduto(idProduto);

        setTitle("Detalhes:"+produto.getName());

        preencherDadosProduto();
        onOrderProduct();

    }

    private void preencherDadosProduto()
    {
        txtNomeProduto.setText(produto.getName());
        txtDescricao.setText(produto.getDescription());
        txtPreco.setText(String.valueOf(produto.getPrice()));
        Glide.with(getApplicationContext())
                .load(produto.getImage_url())
                .placeholder(R.drawable.ipl_semfundo)
                .thumbnail(0f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgProduto);
    }

    private Product criarProduto()
    {
        String img = "http://amsi.dei.estg.ipleiria.pt/img/ipl_semfundo.png";

        Product auxProduto = new Product(
                0,
                txtNomeProduto.getText().toString(),
                txtDescricao.getText().toString(),
                (float) Integer.parseInt(txtPreco.getText().toString()),
                img
        );

        return auxProduto;
    }

    private Product editarProduto()
    {
        produto.setName(txtNomeProduto.getText().toString());
        produto.setDescription(txtDescricao.getText().toString());
        produto.setPrice(Float.valueOf(txtPreco.getText().toString()));

        return produto;
    }
/*
    public void onClickGuardarProdutos(View view) {

        if(idProduto == -1)
        {
            SingletonGestorVapeshop.getInstance(getApplicationContext()).adicionarProdutoBD(criarProduto());
            finish();
        }
        else
        {
            SingletonGestorVapeshop.getInstance(getApplicationContext()).editarProdutoBD(criarProduto());
        }

    }
*/
    private void onOrderProduct() {
        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cart cart = CartHelper.getCart();
                // Log.d(TAG, "Adding product: " + product.getName());
                //cart.add(product, Integer.valueOf(spQuantidade.getSelectedItem().toString()));
                Intent intent = new Intent(DetalhesProduto.this, Cart.class);
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
