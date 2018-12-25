package com.dhsantiagosinatra.museumapplication.model.DAO;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.dhsantiagosinatra.museumapplication.model.POJO.Paint;

@Database(entities = {Paint.class}, version = 1, exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {

    private static final String DB_NAME = "database_pinturas";
    private static MyDatabase database;
    public abstract PaintDatabaseDAO paintDatabaseDAO();

    public static MyDatabase getDatabase(Context context){
        if (database == null){
            database = Room.databaseBuilder(context.getApplicationContext(), MyDatabase.class, DB_NAME).allowMainThreadQueries().build();
        }
        return database;
    }
}
