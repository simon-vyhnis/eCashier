package com.simcom.ecashier.model.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Person.class, Group.class, PersonToGroup.class, Collection.class, CollectionLog.class}, version = 3)
public abstract class CashierDatabase extends RoomDatabase {
    private static CashierDatabase instance;

    public abstract PersonDao personDao();
    public abstract GroupDao groupDao();
    public abstract PersonToGroupDao personToGroupDao();
    public abstract CollectionDao collectionDao();
    public abstract CollectionLogDao collectionLogDao();

    public final static ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(4);

    public static synchronized CashierDatabase getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),CashierDatabase.class,"cashier_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
