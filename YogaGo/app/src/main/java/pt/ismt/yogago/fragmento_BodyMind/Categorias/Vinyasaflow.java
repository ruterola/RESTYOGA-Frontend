package pt.ismt.yogago.fragmento_BodyMind.Categorias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import pt.ismt.yogago.fragmento_BodyMind.Bodymind_fragment;
import pt.ismt.yogago.R;

public class Vinyasaflow extends AppCompatActivity {

    private Button btnSeta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_vinyasaflow );

        //BOTAO VOLTAR PARA FRAGMENT BODY&MIND
        btnSeta=findViewById(R.id.buttonseta);
        btnSeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); //fecha a janela
            }
        } );
    }
}