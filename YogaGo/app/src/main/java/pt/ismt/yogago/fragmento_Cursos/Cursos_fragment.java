package pt.ismt.yogago.fragmento_Cursos;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import pt.ismt.yogago.adapters.CursosAdapter;
import pt.ismt.yogago.adapters.PlanosCursoAdapter;
import pt.ismt.yogago.data.Repository;
import pt.ismt.yogago.fragmento_Cursos.Categorias.Avancado;
import pt.ismt.yogago.fragmento_Cursos.Categorias.Iniciante;
import pt.ismt.yogago.fragmento_Cursos.Categorias.Intermedio;
import pt.ismt.yogago.R;
import pt.ismt.yogago.model.PlanoResponse;
import pt.ismt.yogago.model.Planos;

import static pt.ismt.yogago.data.Datasource.BASE_URL;

public class Cursos_fragment extends Fragment {
    private RecyclerView recyclerViewCursos;
    public Cursos_fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate( R.layout.fragment_cursos_fragment, container, false );
        recyclerViewCursos = view.findViewById(R.id.recyclerViewCursos);
        getCursos();

        return view;
    }

    private void getCursos(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    PlanoResponse planoResponse = Repository.getINSTANCE(getActivity().getApplication()).allPlanos(BASE_URL, true);
                    List<Planos> planosList = new ArrayList<>();
                    for (int i = 0; i < 3; i++) {
                        planosList.add(planoResponse.getPlanosList().get(i));

                    }
                    if (getActivity() != null){
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                recyclerViewCursos.setLayoutManager(new LinearLayoutManager(getContext()));
                                PlanosCursoAdapter planosCursoAdapter = new PlanosCursoAdapter (getContext(), getActivity());
                                recyclerViewCursos.setAdapter(planosCursoAdapter);
                                planosCursoAdapter.refreshList(planosList);

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