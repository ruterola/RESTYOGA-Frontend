package pt.ismt.yogago.menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import pt.ismt.yogago.HomeFragment;
import pt.ismt.yogago.fragmento_BodyMind.Bodymind_fragment;
import pt.ismt.yogago.fragmento_Cursos.Cursos_fragment;
import pt.ismt.yogago.fragmento_Perfil.Perfil_fragment;
import pt.ismt.yogago.R;
import pt.ismt.yogago.fragmento_Recomendacoes.Recomendacoes_fragment;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView btm_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main2 );

        getFragment(new HomeFragment());

        //MENU
        btm_view = findViewById( R.id.bottom_view );
        btm_view.setOnNavigationItemSelectedListener( new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.home){
                    getFragment( new HomeFragment() );
                } else if(item.getItemId() == R.id.planos){
                    getFragment( new Recomendacoes_fragment() );
                }else if(item.getItemId() == R.id.cursos){
                    getFragment( new Cursos_fragment());
                }else if(item.getItemId() == R.id.ler){
                    getFragment( new Bodymind_fragment());
                }else if(item.getItemId() == R.id.perfil){
                    getFragment( new Perfil_fragment() );
                }
                return true;
            }
        } );
    }

    private void getFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.container,fragment);
        fragmentTransaction.commit();
    }
}