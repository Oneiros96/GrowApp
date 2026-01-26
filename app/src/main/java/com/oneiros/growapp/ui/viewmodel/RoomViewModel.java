package com.oneiros.growapp.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Ignore;
import com.oneiros.growapp.db.entity.RoomEntity;
import com.oneiros.growapp.db.repository.RoomRepository;

import java.util.List;
import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class RoomViewModel extends ViewModel {

    private final RoomRepository repository;
    private final LiveData<List<RoomEntity>> allRooms;

    @Inject // Hilt finds the RoomRepository from your DatabaseModule
    public RoomViewModel(RoomRepository repository) {
        this.repository = repository;
        this.allRooms = repository.getAllRooms();
    }

    public LiveData<List<RoomEntity>> getAllRooms() {
        return allRooms;
    }

    public void addRoom(String roomName) {
        repository.insertRoom(new RoomEntity(roomName));
    }
}