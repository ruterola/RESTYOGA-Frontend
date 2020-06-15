package pt.ismt.yogago.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import pt.ismt.yogago.R;
import pt.ismt.yogago.model.Atividades;


public class PerfilAdapter extends RecyclerView.Adapter<PerfilAdapter.TemplateViewHolder> {
	private final Context mContext;
	 private List<Atividades> atividades;

	 /**
		* Instatiate a new TemplateAdapter.
		*
		* @param context This is the acticity context. If you instatiate this object in a fragment, use getContext. If you instatiate this object in an activity, use this.
		*/
	 public PerfilAdapter(Context context) {
			this.mContext = context;
			this.atividades = new ArrayList<>();
	 }


	 @NonNull
	 @Override
	 public TemplateViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
			View view = LayoutInflater.from(mContext).inflate(R.layout.atividades_row, viewGroup, false); //todo: LAYOUT LINHA-REGISTO-ATIVIDADE
			return new TemplateViewHolder(view);
	 }

	 @Override
	 public void onBindViewHolder(@NonNull TemplateViewHolder viewHolder, int i) {
			viewHolder.bindView(atividades.get(i));
	 }

	@Override
	public int getItemCount() {
		return atividades.size();
	}

	class TemplateViewHolder extends RecyclerView.ViewHolder {

			/**
			 * Instatiates a new ViewHolder and binds the view with ButterKnife.
			 *
			 * @param itemView The layout of each row.
			 */
			private TextView nomePlano;
			private TextView dataStart;
			private TextView dataFin;
			TemplateViewHolder(@NonNull View itemView) {
				 super(itemView);
				 nomePlano = itemView.findViewById(R.id.nomePlano);
				 dataStart = itemView.findViewById(R.id.data_start);
				 dataFin = itemView.findViewById(R.id.data_fin);
			}

			/**
			 * Binds the views using the object of the list.
			 *
			 * @param atividades One object of the list, to populate a row.
			 */

			//PREENCHE
			void bindView(Atividades atividades) {
			nomePlano.setText(String.valueOf(atividades.getPlanos().getNome()));
				Date date = atividades.getDataStart();

				Calendar calendar = Calendar.getInstance();
				if (date != null) {
					calendar.setTime(date);
				}

				Date datefin = atividades.getDataFin();

				Calendar calendarfin = Calendar.getInstance();
				if (datefin != null) {
					calendarfin.setTime(datefin);
				}

			DateFormat df = new DateFormat();
			if (atividades.getDataFin() == null){
				dataFin.setText("a decorrer");
			} else {
				dataFin.setText(stringFormat(calendarfin));
			}
			dataStart.setText(stringFormat(calendar));
			}

	 }

	 private String stringFormat(Calendar calendar){
		 String dateString = String.format(Locale.getDefault(),"%d:%d %d/%d/%d",
				 calendar.get(Calendar.HOUR_OF_DAY),
				 calendar.get(Calendar.MINUTE), calendar.get(Calendar.DAY_OF_MONTH),
				 calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR));
		 return dateString;

	 }

	 /**
		* Refreshes the objectList and notifies the adapter that the dataset has changed.
		*
		* @param objectList The updated list to send to this adapter.
		*/

	 public void refreshList(List<Atividades> objectList) {
			this.atividades = objectList;
			notifyDataSetChanged();
	 }

}