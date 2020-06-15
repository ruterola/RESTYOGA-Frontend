package pt.ismt.yogago.inicio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import pt.ismt.yogago.R;

public class pagina1 extends AppCompatActivity {

    private Button btnSeguinte1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_pagina1 );

        btnSeguinte1=findViewById(R.id.button2);
        btnSeguinte1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( pagina1.this, pagina2.class)  );
                finish();
            }
        } );
    }
}
