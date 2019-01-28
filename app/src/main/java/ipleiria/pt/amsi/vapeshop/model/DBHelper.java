package ipleiria.pt.amsi.vapeshop.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    static String DATABASE_NAME="VapeshopDB";

    public static final String TABLE_NAME="UserTable";
    public static final String ID="id";
    public static final String NAME="name";
    public static final String EMAIL="email";
    public static final String PASSWORD="password";

    //Produtos
    private static final String TABLE_PROD = "Produtos";

    private static final String ID_PROD = "id";
    private static final String NOME_PRODUTO = "nome";
    private static final String DESCRICAO = "descricao";
    private static final String PRECO = "preco";
    private static final String IMG_PROD = "immagem";

    // private ContentValues cValues;
    //private SQLiteDatabase dataBase = null;

    private final SQLiteDatabase database;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.database = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        String CREATE_TABLE="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ("
                +ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +NAME+" VARCHAR, "+EMAIL +" VARCHAR, "
                +PASSWORD +" VARCHAR)";
        database.execSQL(CREATE_TABLE);

        String CREATE_TABLE_PROD="CREATE TABLE IF NOT EXISTS "+TABLE_PROD+" ("
                +ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +NOME_PRODUTO+" TEXT, "+DESCRICAO +" TEXT, "
                +PRECO +" INTEGER, "
                +IMG_PROD + "TEXT);";
        database.execSQL(CREATE_TABLE_PROD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

    public Product adicionarProdutoBD (Product produto)
    {
        ContentValues values = new ContentValues();

        values.put(NOME_PRODUTO, produto.getName());
        values.put(DESCRICAO, produto.getDescription());
        values.put(PRECO, String.valueOf(produto.getPrice()));
        values.put(IMG_PROD, produto.getImage_url());

        long id = this.database.insert(TABLE_NAME,null, values);

        if (id > -1)
        {
            produto.setId(id);
            return produto;
        }

        return null;
    }

    public boolean editarProdutoBD (Product produto)
    {
        ContentValues values = new ContentValues();

        values.put(NOME_PRODUTO, produto.getName());
        values.put(DESCRICAO, produto.getDescription());
        values.put(PRECO, String.valueOf(produto.getPrice()));
        values.put(IMG_PROD, produto.getImage_url());

        return this.database.update(TABLE_PROD, values, "id = ?", new String[]{"" + produto.getId()}) > 0;
    }

    public boolean removerProdutoBD(long idProduto)
    {
        return (this.database.delete(TABLE_PROD,  "id = ?", new String[]{"" +idProduto}) == 1);
    }

    public void removerAllProdutos()
    {
        this.database.delete(TABLE_PROD, null,null);
    }

    public ArrayList<Product> getAllProdutosBD()
    {
        ArrayList<Product> products = new ArrayList<>();

        Cursor cursor = this.database.query(TABLE_PROD, new String[]{"id",
                        NOME_PRODUTO, DESCRICAO, PRECO, IMG_PROD},
                null,null,null,null,null);

        if (cursor.moveToFirst())
        {
            do {
                Product auxProd = new Product(
                        cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getFloat(3),
                        cursor.getString(5));

                auxProd.setId(cursor.getLong(0));
                products.add(auxProd);

            }while (cursor.moveToNext());
        }

        return products;
    }

    public long inserir(String table,ContentValues cv,String whereclm)
    {
        SQLiteDatabase dataBase = getWritableDatabase();
        long a=dataBase.insert(table,whereclm,cv);
        return a;
    }

}