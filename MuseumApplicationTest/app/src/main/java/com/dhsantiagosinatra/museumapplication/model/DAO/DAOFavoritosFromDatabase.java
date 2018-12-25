package com.dhsantiagosinatra.museumapplication.model.DAO;

import com.dhsantiagosinatra.museumapplication.model.POJO.Paint;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOFavoritosFromDatabase {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference referenciaABaseDeDatos = database.getReference();


    public void agregarAFavoritos(Paint paint){

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

    public void removerDeFavoritos(Paint paint){

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
}
