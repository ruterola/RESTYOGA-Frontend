package pt.ismt.yogago.fragmento_Perfil;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pt.ismt.yogago.R;
import pt.ismt.yogago.local.SharedPreferencesManager;
import pt.ismt.yogago.model.LoginResponse;
import pt.ismt.yogago.model.Utilizadores;


public class Eu_fragment extends Fragment {
    private TextView name;
    private TextView email;


    public Eu_fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_eu_fragment, container, false );
        name = view.findViewById(R.id.name);
        email = view.findViewById(R.id.email);
        getProfileInfo();
        return view;
    }
    private void getProfileInfo(){
        Utilizadores utilizadores = SharedPreferencesManager.getINSTANCE(getActivity().getApplication()).getUser().getUtilizador();
        name.setText(utilizadores.getName());
        email.setText(utilizadores.getEmail());

    }
}