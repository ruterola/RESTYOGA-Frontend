package pt.ismt.yogago.fragmento_Recomendacoes.Categorias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import pt.ismt.yogago.fragmento_Recomendacoes.Recomendacoes_fragment;
import pt.ismt.yogago.R;

public class Manhafeliz extends AppCompatActivity {

    private Button btnSeta;
    Button buttonplano;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_manhafeliz );

        //BOTAO VOLTAR PARA RECOMENDAÇÕES
        btnSeta=findViewById(R.id.buttonseta);
        btnSeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );

        //ALTERNAR ENTRE O BOTAO "INICIAR PLANO" E "TERMINAR PLANO"
        buttonplano = findViewById(R.id.buttonplano);

        buttonplano.setOnClickListener( new View.OnClickListener() {

            Drawable background = buttonplano.getBackground();
            @Override
            public void onClick(View v) {
                if (buttonplano.getText().equals("Iniciar plano")) //BOTAO PARA INICIAR PLANO
                {
                    buttonplano.setText("Terminar plano");
                    buttonplano.setBackgroundResource(R.drawable.arredondado2);
                }
                else if (buttonplano.getText().equals("Terminar plano")) //BOTAO PARA TERMINAR PLANO
                {
                    buttonplano.setText("Iniciar plano");
                    buttonplano.setBackground(background);
                }
            }
        } );

    }
}
