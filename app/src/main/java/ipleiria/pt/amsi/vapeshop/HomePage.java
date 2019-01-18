package ipleiria.pt.amsi.vapeshop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import ipleiria.pt.amsi.vapeshop.adaptadores.RecyclerViewAdapter;


// AImplementamos a interface BottomNavigationView.OnNavigationItemSelectedListener
// para transformar a Activity numa Listener de item de menu
public class HomePage extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "HomePage";
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private List<itemObject> countryList;
    private RecyclerViewAdapter adapter;
    private static final String API_SERVER_PATH ="http://localhost:8888/produtos";
    //enviar o email do user
    private BottomNavigationView navigationView;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    private String mEmail;

    public static final String DADOS_EMAIL = "amsi.dei.estg.ipleiria.pt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        recyclerView = (RecyclerView)findViewById(R.id.list_produtos);
        layoutManager = new LinearLayoutManager(HomePage.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setVisibility(View.GONE);
        requestRemoteDatabase();
/*
        //enviar o email do user
        sharedPref = getPreferences(Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        if(mEmail == null)
        {
            mEmail = sharedPref.getString("email","Não existe");
        }
        else
        {
            editor.putString("email",mEmail);
            editor.commit();
        }
*/

        mEmail = getIntent().getStringExtra(DADOS_EMAIL);

        //inicialização do bottom menu
        navigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);

        //chamamos o método setOnNavigationItemSelectedListener para a Activity
        // notificar e escutar quando um item da Bottom Navigation for selecionado.
        navigationView.setOnNavigationItemSelectedListener(this);
    }

    private void requestRemoteDatabase() {
        Log.d(TAG, "Response " + response);
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        countryList = Arrays.asList(gson.fromJson(response, ItemObject[].class));
        //display first question to the user
        if (null == countryList) {
            Toast.makeText(MainActivity.this, getResources().getString(R.string.no_country), Toast.LENGTH_LONG).show();
            return;
        }
        recyclerView.setVisibility(View.VISIBLE);
        adapter = new RecyclerViewAdapter(HomePage.this, countryList);
        recyclerView.setAdapter(adapter);
    }
    },new Response.ErrorListener(){
@Override
public void onErrorResponse(VolleyError error){
        Log.d(TAG,"Error "+error.getMessage());
        }
        });
        queue.add(stringRequest);
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
