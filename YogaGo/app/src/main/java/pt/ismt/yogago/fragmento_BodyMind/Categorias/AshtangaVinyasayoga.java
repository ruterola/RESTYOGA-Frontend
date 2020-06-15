package pt.ismt.yogago.fragmento_BodyMind.Categorias;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import pt.ismt.yogago.adapters.CursosAdapter;
import pt.ismt.yogago.data.Repository;
import pt.ismt.yogago.fragmento_BodyMind.Bodymind_fragment;
import pt.ismt.yogago.R;
import pt.ismt.yogago.fragmento_Cursos.Categorias.Iniciante;
import pt.ismt.yogago.model.ArtigoResponse;
import pt.ismt.yogago.model.PlanoResponse;

import static pt.ismt.yogago.data.Datasource.BASE_URL;

public class AshtangaVinyasayoga extends AppCompatActivity {

    private Button btnSeta;
    private ImageView imageViewBody;
    private TextView tituloBody;
    private TextView descricaoBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_ashtanga_vinyasayoga );
        imageViewBody = findViewById(R.id.imageViewBody);
        tituloBody = findViewById(R.id.tituloBody);
        descricaoBody = findViewById(R.id.descricaoBody);

        //BOTAO VOLTAR PARA FRAGMENT BODY&MIND
        btnSeta=findViewById(R.id.buttonseta);
        btnSeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );
        getArtigo();
    }

    public Bitmap getBitmapFromURL(String src) {
        try {
            java.net.URL url = new java.net.URL(src);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void getArtigo(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ArtigoResponse artigoResponse = Repository.getINSTANCE(getApplication()).getArtigo(BASE_URL,"2");
                    Bitmap bitmap = getBitmapFromURL(BASE_URL + "imagem/artigo/" + artigoResponse.getArtigoList().get(0).getId());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                           tituloBody.setText(artigoResponse.getArtigoList().get(0).getNome());
                            descricaoBody.setText(artigoResponse.getArtigoList().get(0).getDescricao());
                           imageViewBody.setImageBitmap(bitmap);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}