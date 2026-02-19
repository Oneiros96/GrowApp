package com.oneiros.growapp.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.oneiros.growapp.db.entity.RoomEntity;

import java.util.List;

@Dao
public interface RoomDao {
    @Query("SELECT * FROM rooms WHERE roomId = :roomId")
    LiveData<RoomEntity> getRoomById(int roomId);
    @Query("SELECT * FROM rooms ORDER BY name ASC")
    LiveData<List<RoomEntity>> getAllRooms();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(RoomEntity room);
}
