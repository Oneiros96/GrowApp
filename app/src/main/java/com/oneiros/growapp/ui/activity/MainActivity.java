package com.oneiros.growapp.ui.activity;

import android.os.Bundle;

import android.util.Log;
import android.widget.EditText;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.oneiros.growapp.R;
import com.oneiros.growapp.ui.adapter.PlantAdapter;
import com.oneiros.growapp.ui.adapter.RoomAdapter;
import com.oneiros.growapp.ui.viewmodel.PlantViewModel;
import com.oneiros.growapp.ui.viewmodel.RoomViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private RoomViewModel roomViewModel;
    private PlantViewModel plantViewModel;

    /**
     * Overrides the parent onCreate Method to: <br>
     *  - link the activity and its XML <br>
     *  - enable Edge-to-Edge drawing of the app (introduced android 10, mandatory since android 15) <br>
     *      ->bevor apps had a "safe box" between the sys bars <br>
     *  - add a listener which adds padding corresponding to the sys bars <br>
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupLayout();

        initRoomViewModel();
        findViewById(R.id.fabAddRoom).setOnClickListener(v -> showAddRoomDialog());

        initPlantViewModel();
        findViewById(R.id.fabAddPlant).setOnClickListener((v -> showAddPlantDialog()));
    }

    private void setupLayout(){
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void initRoomViewModel() {
        RecyclerView roomRecyclerView = findViewById(R.id.recyclerViewRooms);
        RoomAdapter roomAdapter = new RoomAdapter();
        roomRecyclerView.setAdapter(roomAdapter);

        roomViewModel = new ViewModelProvider(this).get(RoomViewModel.class);

        roomViewModel.getAllRooms().observe(this, rooms -> {
            roomAdapter.submitList(rooms);
            Log.d("MainActivity", "Rooms loaded: " + rooms.size());
        });
    }
    private void initPlantViewModel() {
        RecyclerView plantRecyclerView = findViewById(R.id.recyclerViewPlants);
        PlantAdapter plantAdapter = new PlantAdapter();
        plantRecyclerView.setAdapter(plantAdapter);

        plantViewModel = new ViewModelProvider(this).get(PlantViewModel.class);

        plantViewModel.getPlantsWithoutRoom().observe(this, plants -> {
            plantAdapter.submitList(plants);
            Log.d("MainActivity", "Plants loaded: " + plants.size());
        });
    }
    private void showAddRoomDialog() {
        EditText input = new EditText(this);
        new MaterialAlertDialogBuilder(this)
            .setTitle("New Room")
            .setMessage("Enter the name of the grow room")
            .setView(input)
            .setPositiveButton("Create", (dialog, which) -> {
                String name = input.getText().toString().trim();
                if (!name.isEmpty()) {
                    roomViewModel.addRoom(name);
                }
            })
            .setNegativeButton("Cancel", null)
            .show();
    }
    private void showAddPlantDialog(){
        EditText input = new EditText(this);
        new MaterialAlertDialogBuilder(this)
            .setTitle("New Plant")
            .setMessage("Enter the name of the plant")
            .setView(input)
            .setPositiveButton("Create", (dialog, which) -> {
                String name = input.getText().toString().trim();
                if (!name.isEmpty()) {
                    plantViewModel.addPlant(name);
                }
            })
            .setNegativeButton("Cancel", null)
            .show();
    }
}