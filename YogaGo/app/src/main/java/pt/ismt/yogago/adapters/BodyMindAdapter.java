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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import pt.ismt.yogago.R;
import pt.ismt.yogago.fragmento_BodyMind.Categorias.AshtangaVinyasayoga;
import pt.ismt.yogago.fragmento_BodyMind.Categorias.Hathayoga;
import pt.ismt.yogago.fragmento_BodyMind.Categorias.Vinyasaflow;
import pt.ismt.yogago.model.Artigo;

import static pt.ismt.yogago.data.Datasource.BASE_URL;

public class BodyMindAdapter extends RecyclerView.Adapter<BodyMindAdapter.TemplateViewHolder> {
    private final Context mContext;
    private List<Artigo> artigoList;
    private Activity activity;

    /**
     * Instatiate a new TemplateAdapter.
     *
     * @param context This is the acticity context. If you instatiate this object in a fragment, use getContext. If you instatiate this object in an activity, use this.
     */
    public BodyMindAdapter(Context context, Activity activity) {
        this.mContext = context;
        this.artigoList = new ArrayList<>();
        this.activity = activity;
    }


    @NonNull
    @Override
    public TemplateViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.bodymind_row, viewGroup, false); //todo: LAYOUT LINHA-REGISTO-ATIVIDADE
        return new TemplateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TemplateViewHolder viewHolder, int i) {
        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (artigoList.get(i).getId()){
                    case 1: activity.startActivity(new Intent(activity, Hathayoga.class));
                        break;
                    case 2: activity.startActivity(new Intent(activity, AshtangaVinyasayoga.class));
                        break;
                    case 3: activity.startActivity(new Intent(activity, Vinyasaflow.class));
                        break;
                }
            }
        });
        viewHolder.bindView(artigoList.get(i));
    }

    @Override
    public int getItemCount() {
        return artigoList.size();
    }

    class TemplateViewHolder extends RecyclerView.ViewHolder {

        /**
         * Instatiates a new ViewHolder and binds the view with ButterKnife.
         *
         * @param itemView The layout of each row.
         */
        private TextView titleHY;
        private TextView descHY;
        private ImageView imageHY;
        public View view;

        TemplateViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            titleHY= itemView.findViewById(R.id.titleHY);
            descHY = itemView.findViewById(R.id.descHY);
            imageHY = itemView.findViewById(R.id.imageHY);
        }

        //PREENCHE
        void bindView(Artigo artigo) {
            titleHY.setText(artigo.getNome());
            descHY.setText(artigo.getDescricao());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Bitmap bitmap = getBitmapFromURL(BASE_URL + "imagem/artigo/" + artigo.getId());
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            imageHY.setImageBitmap(bitmap);
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

    public void refreshList(List<Artigo> objectList) {
        artigoList = objectList;
        notifyDataSetChanged();
    }


}
