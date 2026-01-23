package com.oneiros.growapp.db;

import android.content.Context;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.oneiros.growapp.AppConstants;
import com.oneiros.growapp.db.converters.DateConverters;
import com.oneiros.growapp.db.tables.*;

/**
 * This class links {@link androidx.room.Entity} and {@link androidx.room.TypeConverters} to build the db.<br>
 * The singleton pattern implemented with {@link #getInstance(Context)} prevents multiple instances.
 */
@androidx.room.Database(
    entities = {
        Actions.class,
        PlantImages.class,
        PlantLogActions.class,
        Plants.class, PlantsLog.class,
        Rooms.class,
        RoomsLog.class},
    version = 1
)
@TypeConverters({DateConverters.class})
public abstract class Database extends RoomDatabase {
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
