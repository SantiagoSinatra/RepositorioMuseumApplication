package com.dhsantiagosinatra.museumapplication.model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.dhsantiagosinatra.museumapplication.model.POJO.Artist;
import com.dhsantiagosinatra.museumapplication.model.POJO.Paint;

import java.util.List;

@Dao
public interface PaintDatabaseDAO {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insertarPaints(Paint paintFromDatabase);

    @Query("SELECT * FROM tabla_de_paints")
    List<Paint> getAllPaints();


    //Falta el insert de artist que lo voy a hacer cuando funcione lo de paints.
    //Faltan las querys (por lo que me fui a hacer las tablas a cada clase)

}
