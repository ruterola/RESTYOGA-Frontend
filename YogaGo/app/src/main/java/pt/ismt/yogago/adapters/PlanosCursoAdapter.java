package pt.ismt.yogago.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import pt.ismt.yogago.R;
import pt.ismt.yogago.fragmento_Cursos.Categorias.Avancado;
import pt.ismt.yogago.fragmento_Cursos.Categorias.Iniciante;
import pt.ismt.yogago.fragmento_Cursos.Categorias.Intermedio;
import pt.ismt.yogago.fragmento_Recomendacoes.Categorias.Dormirmelhor;
import pt.ismt.yogago.fragmento_Recomendacoes.Categorias.Manhafeliz;
import pt.ismt.yogago.fragmento_Recomendacoes.Categorias.Queimargordura;
import pt.ismt.yogago.fragmento_Recomendacoes.Categorias.Saudacaosol;
import pt.ismt.yogago.model.Exercicios;
import pt.ismt.yogago.model.Planos;

import static pt.ismt.yogago.data.Datasource.BASE_URL;

public class PlanosCursoAdapter extends RecyclerView.Adapter<PlanosCursoAdapter.TemplateViewHolder> {
    private final Context mContext;
    private List<Planos> planosList;
    private Activity activity;

    /**
     * Instatiate a new TemplateAdapter.
     *
     * @param context This is the acticity context. If you instatiate this object in a fragment, use getContext. If you instatiate this object in an activity, use this.
     */
    public PlanosCursoAdapter(Context context, Activity activity) {
        this.mContext = context;
        this.planosList = new ArrayList<>();
        this.activity = activity;
    }


    @NonNull
    @Override
    public TemplateViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.cursos_row, viewGroup, false); //todo: LAYOUT LINHA-REGISTO-ATIVIDADE
        return new TemplateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TemplateViewHolder viewHolder, int i) {
        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (planosList.get(i).getId()){
                    case 1: activity.startActivity(new Intent(activity, Iniciante.class));
                        break;
                    case 2: activity.startActivity(new Intent(activity, Intermedio.class));
                        break;
                    case 3: activity.startActivity(new Intent(activity, Avancado.class));
                        break;

                        //COMECA RECOMENDACOES
                    case 4: activity.startActivity(new Intent(activity, Dormirmelhor.class));
                        break;
                    case 5: activity.startActivity(new Intent(activity, Manhafeliz.class));
                        break;
                    case 6: activity.startActivity(new Intent(activity, Saudacaosol.class));
                        break;
                    case 7: activity.startActivity(new Intent(activity, Queimargordura.class));
                        break;

                }
            }
        });
        viewHolder.bindView(planosList.get(i));
    }

    @Override
    public int getItemCount() {
        return planosList.size();
    }

    class TemplateViewHolder extends RecyclerView.ViewHolder {

        /**
         * Instatiates a new ViewHolder and binds the view with ButterKnife.
         *
         * @param itemView The layout of each row.
         */
        private TextView titleCursos;
        private TextView descricaoCursos;
        private ImageView buttonCursos;
        public View view;

        TemplateViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            titleCursos = itemView.findViewById(R.id.titleCursos);
            descricaoCursos = itemView.findViewById(R.id.descricaoCursos);
            buttonCursos = itemView.findViewById(R.id.buttonCursos);
        }

        //PREENCHE
        void bindView(Planos planos) {
            titleCursos.setText(planos.getNome());
            descricaoCursos.setText(planos.getDescricao());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Bitmap bitmap = getBitmapFromURL(BASE_URL + "imagem/plano/" + planos.getId());
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            buttonCursos.setImageBitmap(bitmap);
                        }
                    });
                }
            }).start();

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


    }
    /**
     * Refreshes the objectList and notifies the adapter that the dataset has changed.
     *
     * @param objectList The updated list to send to this adapter.
     */

    public void refreshList(List<Planos> objectList) {
        planosList = objectList;
        notifyDataSetChanged();
    }


}
