package pt.ismt.yogago.fragmento_Cursos.Categorias;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;

import pt.ismt.yogago.adapters.CursosAdapter;
import pt.ismt.yogago.data.Repository;
import pt.ismt.yogago.fragmento_Cursos.Cursos_fragment;
import pt.ismt.yogago.R;
import pt.ismt.yogago.local.SharedPreferencesManager;
import pt.ismt.yogago.model.LoginResponse;
import pt.ismt.yogago.model.PlanoResponse;
import pt.ismt.yogago.model.SimpleResponse;

import static pt.ismt.yogago.data.Datasource.BASE_URL;

public class Avancado extends AppCompatActivity {

    private Button btnSeta;
    Button buttonplano;
    private RecyclerView recyclerViewAvancado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_avancado );
        recyclerViewAvancado = findViewById(R.id.recyclerViewAvancado);
        getExercicios();

        //BOTAO VOLTAR PARA RECOMENDAÇÕES
        btnSeta=findViewById(R.id.buttonseta);
        btnSeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); //fecha a janela
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
                    acaoPlano(Iniciante.ACAO.INICIAR);
                    buttonplano.setBackgroundResource(R.drawable.arredondado2);
                }
                else if (buttonplano.getText().equals("Terminar plano")) //BOTAO PARA TERMINAR PLANO
                {
                    buttonplano.setText("Iniciar plano");
                    acaoPlano(Iniciante.ACAO.TERMINAR);
                    buttonplano.setBackground(background);
                }
            }
        } );
    }
    private void acaoPlano(Iniciante.ACAO acao){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String s = ""; //VAZIO
                switch (acao){
                    case INICIAR: {
                        s = "atividadestart";
                        break;
                    }
                    case TERMINAR:{
                        s = "atividadefin";
                        break;
                    }
                }
                LoginResponse loginResponse = SharedPreferencesManager.getINSTANCE(getApplication()).getUser();
                try {
                    SimpleResponse simpleResponse =
                            Repository.getINSTANCE(getApplication()).
                                    acaoPlanos(BASE_URL, 3, loginResponse.getUtilizador().getEmail(), s, loginResponse.getToken());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String message = "";
                            switch (acao){
                                case INICIAR: {
                                    message = "Iniciou uma atividade";
                                    break;
                                }
                                case TERMINAR:{
                                    message = "Terminou uma atividade";
                                    break;
                                }
                            }
                            Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).show();
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void getExercicios(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    PlanoResponse planoResponse = Repository.getINSTANCE(getApplication()).planoResponse(BASE_URL,3);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            CursosAdapter cursosAdapter = new CursosAdapter(Avancado.this, Avancado.this);
                            recyclerViewAvancado.setLayoutManager(new LinearLayoutManager(Avancado.this)); //RecyclerView
                            recyclerViewAvancado.setAdapter(cursosAdapter);
                            cursosAdapter.refreshList(planoResponse.getPlanosList().get(0).getExerciciosList());
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
