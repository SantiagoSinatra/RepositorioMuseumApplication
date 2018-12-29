package com.dhsantiagosinatra.museumapplication.model.DAO;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.dhsantiagosinatra.museumapplication.controller.PaintController;
import com.dhsantiagosinatra.museumapplication.model.POJO.Paint;
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

import java.util.List;

public class DAOFavoritosFromDatabase {

    protected List<Paint> listaDeFavoriteadas;
    private String nombreDeLaPaintLocal;
    private String nombreDeLaPaintEnFirebase;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
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

    public void leerFavoritos(final List<Paint> listaDeTodasLasPaint){

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
                    if (paintLocal.getName().equals(unaPaint.getName())){
                        break;
                    } else {
                        FragmentListaPaints fragmentListaPaints = new FragmentListaPaints();
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




























//    public void chequearSiEstaFavoriteada(List<Paint> listaDeTodasLasPaint){
//
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//
//        String userID = user.getUid();
//
//        DatabaseReference referenceDatabase = database.getReference();
//        DatabaseReference referenceUsers = referenceDatabase.child("users");
//        DatabaseReference referenceUserLoggedIn = referenceUsers.child(userID);
//        DatabaseReference referenceFavs = referenceUserLoggedIn.child("favourites");
//        DatabaseReference ref = referenceFavs.child(paint.getName());
//
//        for (Paint paintLocal : listaDeTodasLasPaint){
//
//
//            ref.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    Paint paintLocal = dataSnapshot.getValue(Paint.class);
//                    paintLocal.getName();
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                }
//            });
//
//
//
//            if (ref.equals(paintLocal.getName())){
//                FragmentListaPaints fragmentListaPaints = new FragmentListaPaints();
//                fragmentListaPaints.getPaintsFavoriteadas().add(paintLocal);
//                //Toast.makeText(fragmentListaPaints.getContext(), "Lo encontre gato", Toast.LENGTH_SHORT).show();
//            } else {
//                FragmentListaPaints fragmentListaPaints = new FragmentListaPaints();
//                //Toast.makeText(fragmentListaPaints.getContext(), "No se pudo perro", Toast.LENGTH_SHORT).show();
//            }
//        }
//
//    }
//
//    public void chequearSiEstaFavoriteada(){
//
//    }



}
