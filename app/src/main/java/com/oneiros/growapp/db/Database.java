package com.oneiros.growapp.db;

import android.content.Context;
import androidx.room.Dao;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.oneiros.growapp.AppConstants;
import com.oneiros.growapp.db.converters.DateConverters;
import com.oneiros.growapp.db.dao.PlantDao;
import com.oneiros.growapp.db.dao.RoomDao;
import com.oneiros.growapp.db.entity.*;

/**
 * This class links {@link androidx.room.Entity} and {@link androidx.room.TypeConverters} to build the db.<br>
 * The singleton pattern implemented with {@link #getInstance(Context)} prevents multiple instances.
 */
@androidx.room.Database(
    entities = {
        ActionEntity.class,
        PlantImageEntity.class,
        PlantLogActionEntity.class,
        PlantEntity.class, PlantLogEntity.class,
        RoomEntity.class,
        RoomLogEntity.class},
    version = 1
)
@TypeConverters({DateConverters.class})
public abstract class Database extends RoomDatabase {

    public abstract RoomDao roomDao();
    public abstract PlantDao plantDao();
    private static Database instance;

    public static synchronized Database getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                context.getApplicationContext(),
                Database.class,
                AppConstants.dbName
            ).build();
        }
        return instance;
    }
}
