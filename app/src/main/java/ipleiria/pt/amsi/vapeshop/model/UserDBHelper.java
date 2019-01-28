package ipleiria.pt.amsi.vapeshop.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDBHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    public static String DATABASE_NAME="VapeshopDB";

    public static final String TABLE_NAME="UserTable";

    public static final String ID="id";

    public static final String NAME="name";

    public static final String EMAIL="email";

    public static final String PASSWORD="password";

    // private ContentValues cValues;
    //private SQLiteDatabase dataBase = null;

    public UserDBHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        String CREATE_TABLE="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ("
                +ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +NAME+" VARCHAR, "+EMAIL +" VARCHAR, "
                +PASSWORD +" VARCHAR)";
        database.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }


    public long inserir(String table,ContentValues cv,String whereclm)
    {
        SQLiteDatabase dataBase = getWritableDatabase();
        long a=dataBase.insert(table,whereclm,cv);
        return a;
    }

}