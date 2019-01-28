package ipleiria.pt.amsi.vapeshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CompanyMenu extends AppCompatActivity {

    String EmailHolder;
    TextView Email;
    Button Logout ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_home);

        Email = (TextView)findViewById(R.id.textViewLogout);
        Logout = (Button)findViewById(R.id.buttonLogout);

        Intent intent = getIntent();

        // Receber o email
        EmailHolder = intent.getStringExtra(CompanyHomePage.UserEmail);

        Email.setText(Email.getText().toString()+ EmailHolder);

        //  Adicionar um click listener no Logout button.
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

                Toast.makeText(CompanyMenu.this,"Log Out Successfull", Toast.LENGTH_LONG).show();

            }
        });

    }
}