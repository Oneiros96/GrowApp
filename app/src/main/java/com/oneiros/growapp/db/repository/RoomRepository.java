package com.oneiros.growapp.db.repository;

import androidx.lifecycle.LiveData;
import com.oneiros.growapp.db.dao.RoomDao;
import com.oneiros.growapp.db.entity.RoomEntity;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.concurrent.ExecutorService;

@Singleton
public class RoomRepository {
    private final RoomDao roomDao;
    private final ExecutorService executor;

    @Inject
    public RoomRepository(RoomDao roomDao, ExecutorService executor) {
        this.roomDao = roomDao;
        this.executor = executor;
    }

    public LiveData<List<RoomEntity>> getAllRooms() {
        return roomDao.getAllRooms();
    }

    public void insertRoom(RoomEntity room) {
        executor.execute(() -> roomDao.insert(room));
    }

    public LiveData<RoomEntity> getRoomById(int id) {
        return roomDao.getRoomById(id);
    }
}
