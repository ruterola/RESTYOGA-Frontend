package pt.ismt.yogago;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pt.ismt.yogago.local.SharedPreferencesManager;
public class HomeFragment extends Fragment {

    private TextView user;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate( R.layout.fragment_home_fragment, container, false );
        user = view.findViewById(R.id.user);
        user.setText("Olá " + SharedPreferencesManager.getINSTANCE(getActivity().getApplication()).getUser().getUtilizador().getName() + ",");
        return view;
    }

}