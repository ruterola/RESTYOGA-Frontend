package pt.ismt.yogago.inicio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import pt.ismt.yogago.R;
import pt.ismt.yogago.Registo;

public class pagina3 extends AppCompatActivity {

    private Button btnVamosComecar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_pagina3 );

        btnVamosComecar=findViewById(R.id.button5);
        btnVamosComecar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(pagina3.this, Registo.class)  );
                finish();
            }
        } );
    }
}
