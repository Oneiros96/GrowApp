package com.oneiros.growapp.ui.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.oneiros.growapp.db.entity.PlantEntity;
import com.oneiros.growapp.db.entity.RoomEntity;
import com.oneiros.growapp.db.repository.PlantRepository;
import com.oneiros.growapp.db.repository.RoomRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;

import javax.inject.Inject;
import java.util.List;

@HiltViewModel
public class GrowViewModel extends AndroidViewModel {

    private final RoomRepository roomRepository;
    private final PlantRepository plantRepository;

    private final LiveData<List<RoomEntity>> allRooms;
    private final LiveData<List<PlantEntity>> plantsWithoutRoom;

    @Inject
    public GrowViewModel(@NonNull Application application, RoomRepository roomRepository, PlantRepository plantRepository) {
        super(application);
        // Initialize repositories
        this.roomRepository = roomRepository;
        this.plantRepository = plantRepository;

        // Pre-fetch LiveData to avoid re-triggering queries on every fragment swap
        this.allRooms = roomRepository.getAllRooms();
        this.plantsWithoutRoom = plantRepository.getPlantsWithoutRoom();
    }

    // --- Room Accessors ---
    public LiveData<List<RoomEntity>> getAllRooms() {
        return allRooms;
    }

    public LiveData<RoomEntity> getRoomById(int roomId) {
        return roomRepository.getRoomById(roomId);
    }
    public void insertRoom(RoomEntity room) {
        roomRepository.insertRoom(room);
    }

    // --- Plant Accessors ---
    public LiveData<List<PlantEntity>> getPlantsWithoutRoom() {
        return plantsWithoutRoom;
    }

    public LiveData<List<PlantEntity>> getPlantsByRoom(int roomId) {
        return plantRepository.getPlantsByRoom(roomId);
    }

    public void insertPlant(PlantEntity plant) {
        plantRepository.insertPlant(plant);
    }
}
