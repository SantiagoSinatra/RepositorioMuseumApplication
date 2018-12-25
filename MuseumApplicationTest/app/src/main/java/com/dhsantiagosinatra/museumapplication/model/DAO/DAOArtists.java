package com.dhsantiagosinatra.museumapplication.model.DAO;


import android.support.annotation.NonNull;

import com.dhsantiagosinatra.museumapplication.model.POJO.Artist;
import com.dhsantiagosinatra.museumapplication.model.POJO.ArtistContainer;
import com.dhsantiagosinatra.museumapplication.util.ResultListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DAOArtists {

    //Instancia de DB:
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    //Nodo PPAL a DB:
    DatabaseReference referenciaABaseDeDatos = database.getReference();


    public void getArtists(final ResultListener<Artist>listener, final String idArtista){

    //Metodo para escuchar cambios en la DB:
    referenciaABaseDeDatos.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            ArtistContainer artistContainer = dataSnapshot.getValue(ArtistContainer.class);


            for (Artist artist  : artistContainer.getArtists()) {

                if (artist.getArtistId().equals(idArtista)){
                    listener.finish(artist);
                }

            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            databaseError.getCode();
        }
    });
}
}


