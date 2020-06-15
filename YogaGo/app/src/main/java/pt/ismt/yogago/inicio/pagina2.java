package pt.ismt.yogago.inicio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import pt.ismt.yogago.R;

public class pagina2 extends AppCompatActivity {

    private Button btnSeguinte2;
    private Button btnAnterior2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_pagina2 );

        btnSeguinte2=findViewById(R.id.button2);
        btnSeguinte2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(pagina2.this, pagina3.class)  );
                finish();

            }
        } );

        btnAnterior2=findViewById(R.id.button);
        btnAnterior2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(pagina2.this, pagina1.class)  );
                finish();
            }
        } );
    }
}
