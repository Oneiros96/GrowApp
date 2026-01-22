package com.oneiros.growapp.db;

import android.content.Context;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.oneiros.growapp.AppConstants;
import com.oneiros.growapp.db.converters.DateConverters;
import com.oneiros.growapp.db.tables.*;

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
    private static volatile Database INSTANCE;

    public static Database getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (Database.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.getApplicationContext(),
                        Database.class,
                        AppConstants.dbName
                    ).build();
                }
            }
        }
        return INSTANCE;
    }
}
