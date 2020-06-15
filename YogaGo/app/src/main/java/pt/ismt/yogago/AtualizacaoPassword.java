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

public class AtualizacaoPassword extends AppCompatActivity {

    private Button btnRecuoerarPassword;
    private EditText email;
    private EditText password;
    private EditText confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_atualizacao_password );
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);

        btnRecuoerarPassword=findViewById(R.id.button3);
        btnRecuoerarPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if(email.getText().toString().isEmpty() || password.getText().toString().isEmpty() || confirmPassword.getText().toString().isEmpty()){
                            Snackbar.make(findViewById(android.R.id.content), "Preencha todos os campos!", Snackbar.LENGTH_LONG).show();
                        } else {
                            try{
                                SimpleResponse utilizadores = reset(email.getText().toString(), password.getText().toString(), confirmPassword.getText().toString());
                                //SharedPreferencesManager.getINSTANCE(getApplication()).setUser(utilizadores);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (utilizadores.isSuccess()){
                                            Snackbar.make(findViewById(android.R.id.content), "Password efectuada com sucesso!", Snackbar.LENGTH_LONG).show();
                                            new Handler().postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    startActivity(new Intent(AtualizacaoPassword.this, Login.class));
                                                    finish();
                                                }
                                            }, 500);

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
    }

    private SimpleResponse reset (String email, String password, String confirmPassword) throws JSONException {
        Repository repository = Repository.getINSTANCE(getApplication());
        return repository.reset(BASE_URL + "auth/resetpassword", email, password, confirmPassword);
    }
}
