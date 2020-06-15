package pt.ismt.yogago.fragmento_Recomendacoes;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONException;

import pt.ismt.yogago.adapters.PlanosCursoAdapter;
import pt.ismt.yogago.data.Repository;
import pt.ismt.yogago.R;
import pt.ismt.yogago.model.PlanoResponse;

import static pt.ismt.yogago.data.Datasource.BASE_URL;

public class Recomendacoes_fragment extends Fragment {
    private RecyclerView recyclerViewRecomendacoes;


    public Recomendacoes_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate( R.layout.fragment_recomendacoes_fragment, container, false );
        recyclerViewRecomendacoes = view.findViewById(R.id.recyclerViewRecomendacoes);
        getCursos();
        return view;

    }
    private void getCursos(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    PlanoResponse planoResponse = Repository.getINSTANCE(getActivity().getApplication()).allPlanos(BASE_URL, false);
                    if (getActivity() != null){
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                recyclerViewRecomendacoes.setLayoutManager(new LinearLayoutManager(getContext()));
                                PlanosCursoAdapter planosCursoAdapter = new PlanosCursoAdapter (getContext(), getActivity());
                                recyclerViewRecomendacoes.setAdapter(planosCursoAdapter);
                                planosCursoAdapter.refreshList(planoResponse.getPlanosList());

                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}