package ipleiria.pt.amsi.vapeshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CompanyRegister extends AppCompatActivity {

    EditText Email, Password, Name ;
    Button Register;
    String NameHolder, EmailHolder, PasswordHolder;
    Boolean EditTextEmptyHolder;
    SQLiteDatabase sQLiteDatabase;
    DBHelper dbHelper;
    Cursor cursor;
    String FinalResult = "Not_Found";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_register);

        Register = (Button)findViewById(R.id.buttonRegister);

        Email = (EditText)findViewById(R.id.editEmail);
        Password = (EditText)findViewById(R.id.editPassword);
        Name = (EditText)findViewById(R.id.editName);

        dbHelper = new DBHelper(this);

        //  Adicionar um click listener no register button.

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Criar bd se nao existir.
                SQLiteDataBaseBuild();

                // Criar table se nao existir.
                SQLiteTableBuild();

                // Verificar se esta vazio ou não.
                CheckEditTextStatus();

                // Verificar se o email ja existe ou nao.
                CheckingEmailAlreadyExistsOrNot();

                // Limpar o EditText depois de inserir
                CleanEditTextAfterInsert();


            }
        });

    }

    // Construir BD
    public void SQLiteDataBaseBuild(){

        sQLiteDatabase = openOrCreateDatabase(DBHelper.DATABASE_NAME, Context.MODE_PRIVATE, null);

    }

    // Construir Table da BD
    public void SQLiteTableBuild() {

        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + DBHelper.TABLE_NAME + "(" +
                DBHelper.ID + " PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                DBHelper.NAME + " VARCHAR, " +
                DBHelper.EMAIL + " VARCHAR, " +
                DBHelper.PASSWORD + " VARCHAR);");
    }

    // Inserir informacao na bd
    public void InsertInfoIntoSQLiteDatabase() {
        long a;

        if (EditTextEmptyHolder == true) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", NameHolder);
            contentValues.put("email", EmailHolder);
            contentValues.put("password", PasswordHolder);
            a = dbHelper.inserir(DBHelper.TABLE_NAME, contentValues, DBHelper.ID);
            if (a > 0) {
                Toast.makeText(this, "You have registered successfully.", Toast.LENGTH_SHORT).show();
            }
            sQLiteDatabase.close();

        }
    }



    // Apagar o EditText depois de inserir.
    public void CleanEditTextAfterInsert(){

        Name.getText().clear();

        Email.getText().clear();

        Password.getText().clear();

    }

    // Verificar se o EditText esta vazio ou nao.
    public void CheckEditTextStatus(){

        // Guardar a info em variaveis string.
        NameHolder = Name.getText().toString() ;
        EmailHolder = Email.getText().toString();
        PasswordHolder = Password.getText().toString();

        if(TextUtils.isEmpty(NameHolder) || TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder)){

            EditTextEmptyHolder = false ;

        }
        else {

            EditTextEmptyHolder = true ;
        }
    }

    // Verificar se o email ja existe ou nao.
    public void CheckingEmailAlreadyExistsOrNot(){

        // Abrir permissão para escrever na bd
        sQLiteDatabase = dbHelper.getWritableDatabase();

        // adicionar uma query para procurar o email
        cursor = sQLiteDatabase.query(DBHelper.TABLE_NAME, null, " " +
                DBHelper.EMAIL + "=?", new String[]{EmailHolder}, null, null, null);

        while (cursor.moveToNext()) {

            if (cursor.isFirst()) {

                cursor.moveToFirst();

                FinalResult = "Email Found";

                cursor.close();
            }
        }


        // Chamar o metodo para verificar o resultado final e inserir na bd

        CheckFinalResult();

    }


    // Verificar resultado
    public void CheckFinalResult(){

        // verificar se o email ja existe ou nao..
        if(FinalResult.equalsIgnoreCase("Email Found"))
        {

            // se existir mandar uma tosta a dizer que ja existe.
            Toast.makeText(CompanyRegister.this,"Email Already Exists",Toast.LENGTH_LONG).show();

        }
        else {

            //Se não existir , insere na bd
            InsertInfoIntoSQLiteDatabase();

        }

        FinalResult = "Not_Found" ;

    }

}