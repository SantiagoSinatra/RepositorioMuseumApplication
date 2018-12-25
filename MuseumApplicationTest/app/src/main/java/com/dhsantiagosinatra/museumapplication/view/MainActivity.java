package com.dhsantiagosinatra.museumapplication.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dhsantiagosinatra.museumapplication.R;
import com.dhsantiagosinatra.museumapplication.model.POJO.Paint;

public class MainActivity extends AppCompatActivity implements FragmentListaPaints.ListenerFragmentLista {

    private static final String FRAGMENT_LISTA_PAINTS = "fragmentListaPaints";
    private static final String FRAGMENT_DETALLE_PAINTS = "fragmentDetallePaints";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cambiarFragment(new FragmentListaPaints(), FRAGMENT_LISTA_PAINTS);
    }

    public void cambiarFragment (Fragment fragment, String tag){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentcontainer, fragment, tag);
        transaction.commit();
        if (!tag.equals(FRAGMENT_LISTA_PAINTS)) {
            transaction.addToBackStack(null);
        }
    }

    @Override
    public void paintseleccionada(Paint paint) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(FragmentDetallePaints.KEY_PAINT, paint);
        FragmentDetallePaints fragmentDetallePaints = new FragmentDetallePaints();
        fragmentDetallePaints.setArguments(bundle);
        cambiarFragment(fragmentDetallePaints, FRAGMENT_DETALLE_PAINTS);
    }


}
