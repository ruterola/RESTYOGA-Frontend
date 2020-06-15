package pt.ismt.yogago.fragmento_BodyMind;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONException;

import pt.ismt.yogago.adapters.BodyMindAdapter;
import pt.ismt.yogago.data.Repository;
import pt.ismt.yogago.R;
import pt.ismt.yogago.model.ArtigoResponse;

import static pt.ismt.yogago.data.Datasource.BASE_URL;

public class Bodymind_fragment extends Fragment {
    private RecyclerView recyclerViewBody;


    public Bodymind_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate( R.layout.fragment_bodymind_fragment, container, false );
        recyclerViewBody = view.findViewById(R.id.recyclerViewBody);
        getArtigos();
        return view;
    }

    private void getArtigos(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ArtigoResponse artigoResponse = Repository.getINSTANCE(getActivity().getApplication()).allArtigos(BASE_URL);
                    if (getActivity() != null){
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                recyclerViewBody.setLayoutManager(new LinearLayoutManager(getContext()));
                                BodyMindAdapter bodyMindAdapter = new BodyMindAdapter (getContext(), getActivity());
                                recyclerViewBody.setAdapter(bodyMindAdapter);
                                bodyMindAdapter.refreshList(artigoResponse.getArtigoList());

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