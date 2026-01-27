package com.oneiros.growapp.ui.activity;

import android.os.Bundle;

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
import com.oneiros.growapp.ui.adapter.RoomAdapter;
import com.oneiros.growapp.ui.viewmodel.RoomViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private RoomViewModel roomViewModel;
    private RoomAdapter adapter;
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
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView roomRecyclerView = findViewById(R.id.recyclerViewRooms);
        adapter = new RoomAdapter();
        roomRecyclerView.setAdapter(adapter);

        roomViewModel = new ViewModelProvider(this).get(RoomViewModel.class);

        roomViewModel.getAllRooms().observe(this, rooms -> {
            adapter.submitList(rooms);
        });

        findViewById(R.id.fabAddRoom).setOnClickListener(v -> showAddRoomDialog());
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
}