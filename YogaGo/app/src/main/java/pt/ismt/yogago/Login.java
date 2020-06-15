package pt.ismt.yogago;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;

import pt.ismt.yogago.data.Repository;
import pt.ismt.yogago.local.SharedPreferencesManager;
import pt.ismt.yogago.menu.MainActivity;
import pt.ismt.yogago.model.LoginResponse;

import static pt.ismt.yogago.data.Datasource.BASE_URL;

public class Login extends AppCompatActivity {

    private Button btnEntrarLogin;
    private Button btnRecuperar;
    private EditText email;
    private EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btnEntrarLogin=findViewById(R.id.button3);
        btnEntrarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if(email.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
                            Snackbar.make(findViewById(android.R.id.content), "Preencha todos os campos!", Snackbar.LENGTH_LONG).show();
                        } else {
                            try{
                                LoginResponse utilizadores = login(email.getText().toString(), password.getText().toString());
                                SharedPreferencesManager.getINSTANCE(getApplication()).setUser(utilizadores);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        startActivity(new Intent(Login.this, MainActivity.class));
                                        finish();
                                    }
                                });
                            } catch (Exception e){
                                Log.d("login error", e.getMessage());
                            }
                        }
                    }
                }).start();
            }
        } );

        btnRecuperar=findViewById(R.id.button4);
        btnRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, AtualizacaoPassword.class)  );
            }
        } );
    }
    private LoginResponse login(String email, String password) throws JSONException {
        Repository repository = Repository.getINSTANCE(getApplication());
        return repository.login(BASE_URL + "auth/login", email, password);
    }
}
