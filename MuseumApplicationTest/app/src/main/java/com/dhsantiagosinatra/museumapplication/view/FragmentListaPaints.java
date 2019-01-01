package com.dhsantiagosinatra.museumapplication.view;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dhsantiagosinatra.museumapplication.R;
import com.dhsantiagosinatra.museumapplication.controller.PaintController;
import com.dhsantiagosinatra.museumapplication.model.DAO.DAOFavoritosFromDatabase;
import com.dhsantiagosinatra.museumapplication.model.DAO.DAOPaints;
import com.dhsantiagosinatra.museumapplication.model.POJO.Paint;
import com.dhsantiagosinatra.museumapplication.util.ResultListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentListaPaints extends Fragment implements AdapterPaints.ListenerAdapterPaints {

    private AdapterPaints adapterPaints;
    private ListenerFragmentLista listenerFragmentLista;


    public FragmentListaPaints() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_lista_paints, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_paints);
        recyclerView.setHasFixedSize(true);
        adapterPaints = new AdapterPaints(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterPaints);
        getPaints();

        FloatingActionButton floatingActionButton = view.findViewById(R.id.fab_fav);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenerFragmentLista.cambiarAFragmentFavoritos();
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        listenerFragmentLista = (ListenerFragmentLista) context;
    }


    public void getPaints(){
        PaintController paintController = new PaintController();
        paintController.getPaints(new ResultListener<List<Paint>>() {
            @Override
            public void finish(List<Paint> result) {
                for(Paint paint: result) {
                    getPaintFromStorage(paint);


                }
                adapterPaints.setPaints(result);
                DAOFavoritosFromDatabase daoFavoritosFromDatabase = new DAOFavoritosFromDatabase(result);
                daoFavoritosFromDatabase.leerFavoritos();

            }
        }, getActivity().getApplicationContext());
    }

    public void getPaintFromStorage(final Paint paint){
        DAOPaints daoPaints = new DAOPaints();
        daoPaints.getImageFromFirebase(new ResultListener<Uri>() {
            @Override
            public void finish(Uri result) {
                paint.setDownloadUrl(result.toString());
                //adapterPaints.agregarPaint(paint);
            }
        }, paint.getImage());
    }


    @Override
    public void celdaPaintSeleccionada(Paint paint) {
        listenerFragmentLista.paintseleccionada(paint);
    }

    @Override
    public void botonFavoritoSeleccionado(Paint paint) {
        //DAOFavoritosFromDatabase daoFavoritosFromDatabase = new DAOFavoritosFromDatabase();
        //daoFavoritosFromDatabase.agregarAFavoritos(paint);

    }

    public interface ListenerFragmentLista {
        public void paintseleccionada (Paint paint);
        public void cambiarAFragmentFavoritos();
    }
}
