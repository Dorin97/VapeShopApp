package ipleiria.pt.amsi.vapeshop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;


// AImplementamos a interface BottomNavigationView.OnNavigationItemSelectedListener
// para transformar a Activity numa Listener de item de menu
public class HomePage extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "HomePage";
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    //enviar o email do user
    private BottomNavigationView navigationView;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;


    public static final String DADOS_EMAIL = "amsi.dei.estg.ipleiria.pt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        //inicialização do bottom menu
        navigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);

        //chamamos o método setOnNavigationItemSelectedListener para a Activity
        // notificar e escutar quando um item da Bottom Navigation for selecionado.
        navigationView.setOnNavigationItemSelectedListener(this);
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
