package com.oneiros.growapp.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.oneiros.growapp.db.entity.PlantEntity;
import com.oneiros.growapp.db.repository.PlantRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;

import javax.inject.Inject;
import java.util.List;

@HiltViewModel
public class PlantViewModel extends ViewModel {

    private final PlantRepository repository;
    private final LiveData<List<PlantEntity>> plantsWithoutRoom;

    @Inject
    public PlantViewModel(PlantRepository repository) {
        this.repository = repository;
        this.plantsWithoutRoom = repository.getPlantsWithoutRoom();
    }

    public LiveData<List<PlantEntity>> getPlantsWithoutRoom() {
        return plantsWithoutRoom;
    }

    public void addPlant(String plantName){repository.insertPlant(new PlantEntity(plantName));}
}
