package ipleiria.pt.amsi.vapeshop.modelo;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.android.volley.RequestQueue;

import java.util.ArrayList;

import ipleiria.pt.amsi.vapeshop.ProductItem;
import ipleiria.pt.amsi.vapeshop.R;
import ipleiria.pt.amsi.vapeshop.adaptadores.ProductAdapter;

public class HomePage1 {
    private RecyclerView mRecyclerView;
    private ProductAdapter mProductAdapter;
    private ArrayList<ProductItem> mProductList;
    private RequestQueue mRequestQueue;

}
