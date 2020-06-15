package pt.ismt.yogago.fragmento_Perfil;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;

import java.util.List;

import pt.ismt.yogago.R;

public class Perfil_fragment extends Fragment{

    FragmentTransaction transaction;
    Fragment fragmentEu, fragmentAtividade;
    private Button eu;
    private Button atividades;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
            View view = inflater.inflate( R.layout.fragment_perfil_fragment, container, false );

            fragmentEu = new Eu_fragment();
            eu = view.findViewById(R.id.buttonEu);
            atividades = view.findViewById(R.id.buttonAtividade);
            fragmentAtividade = new Atividade_fragment();
            eu.setOnClickListener(onClickListener);
            atividades.setOnClickListener(onClickListener1);

            getActivity().getSupportFragmentManager().beginTransaction().replace( R.id.fragment_placeholder, fragmentEu).addToBackStack(null).commit();

        return view;
    }
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getActivity().getSupportFragmentManager().beginTransaction().replace( R.id.fragment_placeholder, fragmentEu).addToBackStack(null).commit();


        }
    };

    private View.OnClickListener onClickListener1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getActivity().getSupportFragmentManager().beginTransaction().replace( R.id.fragment_placeholder, fragmentAtividade).addToBackStack(null).commit();


        }
    };

}