package com.dhsantiagosinatra.museumapplication.controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.dhsantiagosinatra.museumapplication.model.DAO.DAOArtists;
import com.dhsantiagosinatra.museumapplication.model.DAO.DAOFavoritosFromDatabase;
import com.dhsantiagosinatra.museumapplication.model.DAO.DAOPaints;
import com.dhsantiagosinatra.museumapplication.model.DAO.MyDatabase;
import com.dhsantiagosinatra.museumapplication.model.POJO.Artist;
import com.dhsantiagosinatra.museumapplication.model.POJO.Paint;
import com.dhsantiagosinatra.museumapplication.model.POJO.PaintContainer;
import com.dhsantiagosinatra.museumapplication.util.ResultListener;

import java.util.ArrayList;
import java.util.List;

public class PaintController {

    private List<Paint> listaDeTodasLasPaints = new ArrayList<>();


    public void getPaints(final ResultListener<List<Paint>> listenerDeLaView, final Context context) {
        if (isConnected(context)) {
            DAOPaints daoPaints = new DAOPaints();
            daoPaints.getPaintSet1(new ResultListener<PaintContainer>() {
                @Override
                public void finish(PaintContainer result) {
                    listenerDeLaView.finish(result.getPaints());
                    for (Paint paint : result.getPaints()) {
                        MyDatabase.getDatabase(context).paintDatabaseDAO().insertarPaints(paint);
                    }
                }
            });
        } else {
            listenerDeLaView.finish(MyDatabase.getDatabase(context).paintDatabaseDAO().getAllPaints());
        }
    }

    public void getArtista(final ResultListener<Artist> listener, String idArtist){

        DAOArtists daoArtists = new DAOArtists();
        daoArtists.getArtists(new ResultListener<Artist>() {
            @Override
            public void finish(Artist result) {
                listener.finish(result);
            }
        }, idArtist);
    }

    private Boolean isConnected(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public List BuscarFavoriteadasPorElUsuario(List<Paint> listaDeTodasLasPaints){
        DAOFavoritosFromDatabase daoFavoritosFromDatabase = new DAOFavoritosFromDatabase(listaDeTodasLasPaints);
        return daoFavoritosFromDatabase.leerFavoritos();
    }

}
