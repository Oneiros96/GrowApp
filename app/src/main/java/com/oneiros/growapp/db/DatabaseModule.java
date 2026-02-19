package com.oneiros.growapp.db;

import android.content.Context;
import androidx.room.Room;
import com.oneiros.growapp.AppConstants;
import com.oneiros.growapp.db.dao.PlantDao;
import com.oneiros.growapp.db.dao.RoomDao;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

import javax.inject.Singleton;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Module
@InstallIn(SingletonComponent.class)
public class DatabaseModule {
    @Provides
    @Singleton
    public Database provideDatabase(@ApplicationContext Context context) {
        return Room.inMemoryDatabaseBuilder(context, Database.class).build();

        /* return Room.databaseBuilder(
            context,
            Database.class,
            AppConstants.dbName
        ).build(); */
    }

    @Provides
    public RoomDao provideRoomDao(Database database) {
        return database.roomDao();
    }

    @Provides
    public PlantDao providePlantDao(Database database){return database.plantDao();}

    @Provides
    @Singleton
    public ExecutorService provideExecutorService() {
        return Executors.newFixedThreadPool(4);
    }
}
