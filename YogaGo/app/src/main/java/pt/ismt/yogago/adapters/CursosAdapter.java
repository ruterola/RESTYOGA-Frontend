package pt.ismt.yogago.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import pt.ismt.yogago.R;
import pt.ismt.yogago.model.Exercicios;

import static pt.ismt.yogago.data.Datasource.BASE_URL;

public class CursosAdapter extends RecyclerView.Adapter<CursosAdapter.TemplateViewHolder> {
    private final Context mContext;
    private List<Exercicios> exerciciosList;
    private Activity activity;

    /**
     * Instatiate a new TemplateAdapter.
     *
     * @param context This is the acticity context. If you instatiate this object in a fragment, use getContext. If you instatiate this object in an activity, use this.
     */
    public CursosAdapter(Context context, Activity activity) {
        this.mContext = context;
        this.exerciciosList = new ArrayList<>();
        this.activity = activity;
    }


    @NonNull
    @Override
    public TemplateViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.exercicios_row, viewGroup, false); //todo: LAYOUT LINHA-REGISTO-ATIVIDADE
        return new TemplateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TemplateViewHolder viewHolder, int i) {
        viewHolder.bindView(exerciciosList.get(i));
    }

    @Override
    public int getItemCount() {
        return exerciciosList.size();
    }

    class TemplateViewHolder extends RecyclerView.ViewHolder {

        /**
         * Instatiates a new ViewHolder and binds the view with ButterKnife.
         *
         * @param itemView The layout of each row.
         */
        private TextView textViewTituloExercicio;
        private TextView textViewDescricaoExercicio;
        private ImageView imageViewExercicio;

        TemplateViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTituloExercicio = itemView.findViewById(R.id.textViewTituloExercicio);
            textViewDescricaoExercicio = itemView.findViewById(R.id.textViewDescricaoExercicio);
            imageViewExercicio = itemView.findViewById(R.id.imageViewExercicio);
        }

        /**
         * Binds the views using the object of the list.
         *
         * @param exercicios One object of the list, to populate a row.
         */

        //PREENCHE
        void bindView(Exercicios exercicios) {
            textViewTituloExercicio.setText(exercicios.getNome());
            textViewDescricaoExercicio.setText(exercicios.getDescricao());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Bitmap bitmap = getBitmapFromURL(BASE_URL + "imagem/exercicio/" + exercicios.getId());
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            imageViewExercicio.setImageBitmap(bitmap);
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

    public void refreshList(List<Exercicios> objectList) {
        exerciciosList = objectList;
        notifyDataSetChanged();
    }
}