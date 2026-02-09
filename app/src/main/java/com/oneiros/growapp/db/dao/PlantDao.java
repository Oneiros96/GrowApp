package com.oneiros.growapp.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.oneiros.growapp.db.entity.PlantEntity;

import java.util.List;

@Dao
public interface PlantDao {
    @Query("SELECT * FROM plants WHERE roomId IS NULL")
    LiveData<List<PlantEntity>> getPlantsWithoutRoom();
    @Query("SELECT * FROM plants WHERE roomId = :roomId")
    LiveData<List<PlantEntity>> getPlantsByRoom(int roomId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(PlantEntity plant);
}
