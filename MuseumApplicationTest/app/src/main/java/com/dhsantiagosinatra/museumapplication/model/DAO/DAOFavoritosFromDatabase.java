package com.dhsantiagosinatra.museumapplication.model.DAO;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.dhsantiagosinatra.museumapplication.controller.PaintController;
import com.dhsantiagosinatra.museumapplication.model.POJO.Paint;
import com.dhsantiagosinatra.museumapplication.model.POJO.PaintContainer;
import com.dhsantiagosinatra.museumapplication.util.ResultListener;
import com.dhsantiagosinatra.museumapplication.view.AdapterPaints;
import com.dhsantiagosinatra.museumapplication.view.FragmentListaPaints;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DAOFavoritosFromDatabase {

    private List<Paint> listaDeTodasLasPaint = new ArrayList<>();
    private List<Paint> listaDeFavoriteadas = new ArrayList<>();

    public DAOFavoritosFromDatabase(List<Paint> listaDeTodasLasPaint) {
        this.listaDeTodasLasPaint = listaDeTodasLasPaint;
    }

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    public List<Paint> getListaDeTodasLasPaint() {
        return listaDeTodasLasPaint;
    }

    DatabaseReference referenciaABaseDeDatos = database.getReference();


    public void agregarAFavoritos(Paint paint) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        String userID = user.getUid();

        DatabaseReference referenceDatabase = database.getReference();
        DatabaseReference referenceUsers = referenceDatabase.child("users");
        DatabaseReference referenceUserLoggedIn = referenceUsers.child(userID);
        DatabaseReference referenceFavs = referenceUserLoggedIn.child("favourites");
        DatabaseReference ref = referenceFavs.child(paint.getName());

        ref.setValue(paint);

    }

    public void removerDeFavoritos(Paint paint) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        String userID = user.getUid();

        DatabaseReference referenceDatabase = database.getReference();
        DatabaseReference referenceUsers = referenceDatabase.child("users");
        DatabaseReference referenceUserLoggedIn = referenceUsers.child(userID);
        DatabaseReference referenceFavs = referenceUserLoggedIn.child("favourites");
        DatabaseReference ref = referenceFavs.child(paint.getName());

        ref.removeValue();
    }

    public List<Paint> getListaDeFavoriteadas() {
        return listaDeFavoriteadas;
    }

    public void leerFavoritos(){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        String userID = user.getUid();

        DatabaseReference referenceDatabase = database.getReference();
        DatabaseReference referenceUsers = referenceDatabase.child("users");
        DatabaseReference referenceUserLoggedIn = referenceUsers.child(userID);
        DatabaseReference ref = referenceUserLoggedIn.child("favourites");


        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Paint unaPaint = dataSnapshot.getValue(Paint.class);
                for (Paint paintLocal:listaDeTodasLasPaint){
                    if (unaPaint.getName().equals(paintLocal.getName()) && unaPaint.getArtistId().equals(paintLocal.getArtistId())){
                        listaDeFavoriteadas.add(paintLocal);
                        break;
                    }
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
