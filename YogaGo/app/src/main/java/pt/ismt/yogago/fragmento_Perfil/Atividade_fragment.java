package pt.ismt.yogago.fragmento_Perfil;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;

import java.util.List;

import pt.ismt.yogago.R;
import pt.ismt.yogago.adapters.PerfilAdapter;
import pt.ismt.yogago.data.Repository;
import pt.ismt.yogago.local.SharedPreferencesManager;
import pt.ismt.yogago.model.Atividades;
import pt.ismt.yogago.model.AtividadesResponse;
import pt.ismt.yogago.model.LoginResponse;
import pt.ismt.yogago.model.Utilizadores;

import static pt.ismt.yogago.data.Datasource.BASE_URL;

public class Atividade_fragment extends Fragment {


    public Atividade_fragment() {
        // Required empty public constructor
    }

    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_atividade_fragment, container, false );
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        getInfo();
        return view;

    }

    private void getInfo(){
        LoginResponse loginResponse = SharedPreferencesManager.getINSTANCE(getActivity().getApplication()).getUser();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    AtividadesResponse atividadesResponse = Repository.getINSTANCE(getActivity().getApplication())
                            .atividades(BASE_URL, loginResponse.getToken(), loginResponse.getUtilizador().getId());
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Snackbar.make(getView(), "Atividades obtidas com sucesso!", Snackbar.LENGTH_LONG).show();
                            recycleView(atividadesResponse.getAtividades());
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void recycleView(List<Atividades> atividades) {
        PerfilAdapter perfilAdapter = new PerfilAdapter(getContext());
        recyclerView.setAdapter(perfilAdapter);
        perfilAdapter.refreshList(atividades);
    }
}