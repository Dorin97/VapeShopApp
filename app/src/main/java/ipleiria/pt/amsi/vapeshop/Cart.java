package ipleiria.pt.amsi.vapeshop;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import ipleiria.pt.amsi.vapeshop.adapter.CartItemAdapter;

import com.android.tonyvu.sc.model.Saleable;
import com.android.tonyvu.sc.util.CartHelper;

import ipleiria.pt.amsi.vapeshop.adapter.ProductAdapter;
import ipleiria.pt.amsi.vapeshop.model.CartItem;
import ipleiria.pt.amsi.vapeshop.model.Product;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// AImplementamos a interface BottomNavigationView.OnNavigationItemSelectedListener
// para transformar a Activity numa Listener de item de menu
public class Cart extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationView navigationView;

    private static final String TAG = "ShoppingCartActivity";
    ListView lvCartItems;
    Button btnApagar;
    Button btnLoja;
    TextView tvPrecoTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        //inicialização do bottom menu
        navigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);
        //chamamos o método setOnNavigationItemSelectedListener para a Activity
        // notificar e escutar quando um item da Bottom Navigation for selecionado.
        navigationView.setOnNavigationItemSelectedListener(this);

        lvCartItems = (ListView) findViewById(R.id.lvCartItems);
        LayoutInflater layoutInflater = getLayoutInflater();

        final com.android.tonyvu.sc.model.Cart cart = CartHelper.getCart();
        final TextView tvTotalPrice = (TextView) findViewById(R.id.tvTotalPrice);
        tvTotalPrice.setText(ProductAdapter.CURRENCY+String.valueOf(cart.getTotalPrice().setScale(2, BigDecimal.ROUND_HALF_UP)));

        lvCartItems.addHeaderView(layoutInflater.inflate(R.layout.cart_header, lvCartItems, false));

        final CartItemAdapter cartItemAdapter = new CartItemAdapter(this);
        cartItemAdapter.updateCartItems(getCartItems(cart));

        lvCartItems.setAdapter(cartItemAdapter);

        btnApagar = (Button) findViewById(R.id.btnApagar);
        btnLoja = (Button) findViewById(R.id.btnLoja);

        btnApagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Clearing the shopping cart");
                cart.clear();
                cartItemAdapter.updateCartItems(getCartItems(cart));
                cartItemAdapter.notifyDataSetChanged();
                tvTotalPrice.setText(ProductAdapter.CURRENCY+String.valueOf(cart.getTotalPrice().setScale(2, BigDecimal.ROUND_HALF_UP)));
            }
        });

        btnLoja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cart.this, HomePage.class);
                startActivity(intent);
            }
        });

        lvCartItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(Cart.this)
                        .setTitle(getResources().getString(R.string.delete_item))
                        .setMessage(getResources().getString(R.string.delete_item_message))
                        .setPositiveButton(getResources().getString(R.string.sim), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                List<CartItem> cartItems = getCartItems(cart);
                                cart.remove(cartItems.get(position-1).getProduto());
                                cartItems.remove(position-1);
                                cartItemAdapter.updateCartItems(cartItems);
                                cartItemAdapter.notifyDataSetChanged();
                                tvTotalPrice.setText(Product.CURRENCY+String.valueOf(cart.getTotalPrice().setScale(2, BigDecimal.ROUND_HALF_UP)));
                            }
                        })
                        .setNegativeButton(getResources().getString(R.string.nao), null)
                        .show();
                return false;
            }
        });

        lvCartItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                List<CartItem> cartItems = getCartItems(cart);
                Product product = (Product) cartItems.get(position-1).getProduto();
                Log.d(TAG, "Viewing product: " + product.getName());
                bundle.putSerializable("product", (Serializable) product);
                Intent intent = new Intent(Cart.this, Produto.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private List<CartItem> getCartItems(com.android.tonyvu.sc.model.Cart cart) {
        List<CartItem> cartItems = new ArrayList<CartItem>();
        Log.d(TAG, "Current shopping cart: " + cart);

        Map<Saleable, Integer> itemMap = cart.getItemWithQuantity();

        for (Map.Entry<Saleable, Integer> entry : itemMap.entrySet()) {
            CartItem cartItem = new CartItem();
            cartItem.setProduto((Product) entry.getKey());
            cartItem.setQuantidade(entry.getValue());
            cartItems.add(cartItem);
        }

        Log.d(TAG, "Cart item list: " + cartItems);
        return cartItems;
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
        Intent intentCustoms = new Intent(this, Customizables.class);
        startActivity(intentCustoms);
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
