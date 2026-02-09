package com.oneiros.growapp.db.repository;

import androidx.lifecycle.LiveData;
import com.oneiros.growapp.db.dao.PlantDao;
import com.oneiros.growapp.db.entity.PlantEntity;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.concurrent.ExecutorService;

@Singleton
public class PlantRepository {
    private final PlantDao plantDao;
    private final ExecutorService executorService;

    @Inject
    public PlantRepository(PlantDao plantDao, ExecutorService executorService) {
        this.plantDao = plantDao;
        this.executorService = executorService;
    }

    public LiveData<List<PlantEntity>> getPlantsWithoutRoom() {
        return plantDao.getPlantsWithoutRoom();
    }

    public LiveData<List<PlantEntity>> getPlantsByRoom(int roomId) {
        return plantDao.getPlantsByRoom(roomId);
    }

    public void insertPlant(PlantEntity plant) {
        executorService.execute(() -> plantDao.insert(plant));
    }
}
