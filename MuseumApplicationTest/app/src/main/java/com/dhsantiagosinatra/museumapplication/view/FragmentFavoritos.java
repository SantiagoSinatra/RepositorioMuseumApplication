package com.dhsantiagosinatra.museumapplication.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dhsantiagosinatra.museumapplication.R;
import com.dhsantiagosinatra.museumapplication.controller.PaintController;
import com.dhsantiagosinatra.museumapplication.model.DAO.DAOFavoritosFromDatabase;
import com.dhsantiagosinatra.museumapplication.model.POJO.Paint;
import com.dhsantiagosinatra.museumapplication.util.ResultListener;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentFavoritos extends Fragment implements AdapterPaints.ListenerAdapterPaints{

    private AdapterPaints adapterPaints;

    public FragmentFavoritos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favoritos, container, false);

        RecyclerView recyclerViewFavoritos = view.findViewById(R.id.recyclerview_favoritos);

        adapterPaints = new AdapterPaints(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        recyclerViewFavoritos.setAdapter(adapterPaints);

        recyclerViewFavoritos.setLayoutManager(linearLayoutManager);

        //getFavoritos();

        return view;
    }


    @Override
    public void celdaPaintSeleccionada(Paint paint) {

    }

    @Override
    public void botonFavoritoSeleccionado(Paint paint) {

    }

    public void getFavoritos(){
        //adapterPaints.getFavoritos();
    }
}
