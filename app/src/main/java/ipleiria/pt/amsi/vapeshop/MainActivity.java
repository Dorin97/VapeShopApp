package ipleiria.pt.amsi.vapeshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //abre janela dos vapers
        ImageButton btnVapers = (ImageButton) findViewById(R.id.imgBtnVapers);
        btnVapers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Vapers.class));
            }
        });

        //abre janela dos liquidos
        ImageButton btnLiquids = (ImageButton) findViewById(R.id.imgBtnLiquid);
        btnLiquids.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Eliquids.class));
            }
        });

        //abre janela dos costumizables
        ImageButton btnCostums = (ImageButton) findViewById(R.id.imgBtnCostum);
        btnCostums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Customizables.class));
            }
        });
    }
}
