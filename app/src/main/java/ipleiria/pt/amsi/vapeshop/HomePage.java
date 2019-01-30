package ipleiria.pt.amsi.vapeshop;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import ipleiria.pt.amsi.vapeshop.adapter.ListaProdutoAdaptador;
import ipleiria.pt.amsi.vapeshop.listeners.ProdutosListener;
import ipleiria.pt.amsi.vapeshop.model.Product;
import ipleiria.pt.amsi.vapeshop.model.SingletonGestorVapeshop;
import ipleiria.pt.amsi.vapeshop.utils.ProdutoJsonParser;

//import ipleiria.pt.amsi.vapeshop.adapter.ProductAdapter;


// AImplementamos a interface BottomNavigationView.OnNavigationItemSelectedListener
// para transformar a Activity numa Listener de item de menu
public class HomePage extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, ProdutosListener {

    public static final String DETALHES_PRODUTO = "PRODUTO";
    public static final String DADOS_EMAIL = "http://192.168.1.77:8888/produtos";

    private ListView lvListaProduto;
    private ArrayList<Product> listaProdutos;
    private String mEmail;
    private ListaProdutoAdaptador listaProdutoAdapter;


    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        sharedPref = getPreferences(Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        if(mEmail == null) {
            mEmail = sharedPref.getString("email","Não existe");
        }
        else {
            editor.putString("email",mEmail);
            editor.commit();
        }
        mEmail = getIntent().getStringExtra(DADOS_EMAIL);

        SingletonGestorVapeshop.getInstance(getApplicationContext()).setProdutosListener(this);
        SingletonGestorVapeshop.getInstance(getApplicationContext()).getAllProdutosAPI(getApplicationContext(),
                ProdutoJsonParser.isConnectionInternet(getApplicationContext()));

        lvListaProduto = (ListView) findViewById(R.id.list_produtos);

        lvListaProduto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Product tempProduto = (Product) parent.getItemAtPosition(position);

                Intent intent = new Intent(getApplicationContext(), DetalhesProduto.class);

                intent.putExtra(DETALHES_PRODUTO, tempProduto.getId());

                startActivity(intent);
                Toast.makeText(HomePage.this, "-->"+position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onClickAdicionarProdutos(View view) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        SingletonGestorVapeshop.getInstance(getApplicationContext()).getAllProdutosAPI(getApplicationContext(),
                ProdutoJsonParser.isConnectionInternet(getApplicationContext()));
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

    @Override
    public void onRefreshListaProdutos(ArrayList<Product> listaProdutos) {
        if (!listaProdutos.isEmpty()) {

            listaProdutoAdapter = new ListaProdutoAdaptador(this, listaProdutos);
            lvListaProduto.setAdapter(listaProdutoAdapter);

            listaProdutoAdapter.refresh(listaProdutos);
        }
    }

    @Override
    public void onUpdateListaProdutos(Product produto, int operacao) {

    }
}
