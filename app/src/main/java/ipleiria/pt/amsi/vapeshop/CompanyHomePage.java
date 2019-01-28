package ipleiria.pt.amsi.vapeshop;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ipleiria.pt.amsi.vapeshop.model.DBHelper;

public class CompanyHomePage extends AppCompatActivity {

    Button LogInButton, RegisterButton;
    EditText Email, Password ;
    String EmailHolder, PasswordHolder;
    Boolean EditTextEmptyHolder;
    SQLiteDatabase sqLiteDatabaseObj;
    DBHelper dBHelper;
    Cursor cursor;
    String TempPassword = "NOT_FOUND" ;
    public static final String UserEmail = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_login);

        LogInButton = (Button)findViewById(R.id.buttonLogin);

        RegisterButton = (Button)findViewById(R.id.buttonRegister);

        Email = (EditText)findViewById(R.id.editEmail);
        Password = (EditText)findViewById(R.id.editPassword);

        dBHelper = new DBHelper(this);

        //Adicionar um click listener no login button.
        LogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Chamar a função para ver se o EditText está vazio ou nao.
                CheckEditTextStatus();

                // Chamar a função login.
                LoginFunction();


            }
        });

        // Adicionar um click listener no register button.
        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Abrir a atividade register
                Intent intent = new Intent(CompanyHomePage.this, CompanyRegister.class);
                startActivity(intent);

            }
        });
    }

    //Função login

    public void LoginFunction(){

        if(EditTextEmptyHolder) {

            // Abrir permissão para escrever na database.

            sqLiteDatabaseObj = dBHelper.getWritableDatabase();

            // Adicionar uma query para procurar emails no cursor.
            cursor = sqLiteDatabaseObj.query(DBHelper.TABLE_NAME, null, " " + DBHelper.EMAIL + "=?", new String[]{EmailHolder}, null, null, null);

            while (cursor.moveToNext()) {

                if (cursor.isFirst()) {

                    cursor.moveToFirst();

                    // Guardar a password com o respetivo email.
                    TempPassword = cursor.getString(cursor.getColumnIndex(DBHelper.PASSWORD));

                    cursor.close();
                }
            }

            // Verificar o resultado
            CheckFinalResult();

        }
        else {

            Toast.makeText(CompanyHomePage.this,"Please Enter UserName or Password.",Toast.LENGTH_LONG).show();

        }

    }

    // Função para ver se o EditText está vazio ou nao.
    public void CheckEditTextStatus(){


        EmailHolder = Email.getText().toString();
        PasswordHolder = Password.getText().toString();

        // Usar o TextUtils para fazer a verificação.
        if( TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder)){

            EditTextEmptyHolder = false ;

        }
        else {

            EditTextEmptyHolder = true ;
        }
    }

    // Verificar a password inserida com a password que esta associada ao email na bd
    public void CheckFinalResult(){

        if(TempPassword.equalsIgnoreCase(PasswordHolder))
        {

            Toast.makeText(CompanyHomePage.this,"Login Successfully",Toast.LENGTH_LONG).show();

            // Ir para o menu se o login
            Intent intent = new Intent(CompanyHomePage.this, CompanyMenu.class);

            // Enviar o email da companhia para o menu
            intent.putExtra(UserEmail, EmailHolder);

            startActivity(intent);


        }
        else {

            Toast.makeText(CompanyHomePage.this,"Username or Password is Wrong, Please Try Again.",Toast.LENGTH_LONG).show();

        }
        TempPassword = "NOT_FOUND" ;

    }

}