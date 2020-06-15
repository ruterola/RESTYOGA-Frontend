package pt.ismt.yogago;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;

import pt.ismt.yogago.data.Repository;
import pt.ismt.yogago.model.SimpleResponse;

import static pt.ismt.yogago.data.Datasource.BASE_URL;

public class Registo extends AppCompatActivity {

    private Button btnRegistar;
    private Button btnEntrar;
    private EditText name;
    private EditText email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_registo );
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        btnRegistar=findViewById(R.id.button3);
        btnRegistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if(name.getText().toString().isEmpty() || email.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
                            Snackbar.make(findViewById(android.R.id.content), "Preencha todos os campos!", Snackbar.LENGTH_LONG).show();
                        } else {
                            try{
                                SimpleResponse utilizadores = registo(name.getText().toString(), email.getText().toString(), password.getText().toString());
                                //SharedPreferencesManager.getINSTANCE(getApplication()).setUser(utilizadores);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (utilizadores.isSuccess()){
                                            Snackbar.make(findViewById(android.R.id.content), "Registo efectuado com sucesso!", Snackbar.LENGTH_LONG).show();
                                            new Handler().postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    startActivity(new Intent(Registo.this, Login.class));
                                                    finish();
                                                }
                                            }, 1500);

                                        } else {
                                            Snackbar.make(findViewById(android.R.id.content), utilizadores.getMessage(), Snackbar.LENGTH_LONG).show();
                                        }

                                    }
                                });
                            } catch (Exception e){
                                Log.d("registo error", e.getMessage());
                            }
                        }
                    }
                }).start();
            }
        } );

        btnEntrar=findViewById(R.id.button4);
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registo.this, Login.class));
                finish();
            }
        } );
    }
    private SimpleResponse registo(String name, String email, String password) throws JSONException {
        Repository repository = Repository.getINSTANCE(getApplication());
        return repository.registo(BASE_URL + "auth/registo", name, email, password);
    }
}
