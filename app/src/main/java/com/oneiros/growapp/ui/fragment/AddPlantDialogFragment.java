package com.oneiros.growapp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.oneiros.growapp.db.entity.PlantEntity;
import com.oneiros.growapp.db.entity.RoomEntity;
import com.oneiros.growapp.databinding.AddPlantBinding;
import com.oneiros.growapp.ui.viewmodel.GrowViewModel;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AddPlantDialogFragment extends BottomSheetDialogFragment {

    private AddPlantBinding binding;
    private GrowViewModel viewModel;
    private List<RoomEntity> availableRooms = new ArrayList<>();
    private Integer selectedRoomId = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = AddPlantBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(GrowViewModel.class);

        setupRoomDropdown();
        binding.btnSavePlant.setOnClickListener(v -> savePlant());
    }

    private void setupRoomDropdown() {
        viewModel.getAllRooms().observe(getViewLifecycleOwner(), rooms -> {
            if (rooms == null) return;
            this.availableRooms = rooms;

            List<String> roomNames = rooms.stream()
                .map(RoomEntity::name)
                .collect(Collectors.toList());

            ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_dropdown_item_1line, roomNames);

            binding.autoCompleteRoom.setAdapter(adapter);

            binding.autoCompleteRoom.setOnItemClickListener((parent, v, position, id) -> {
                selectedRoomId = availableRooms.get(position).roomId();
            });
        });
    }

    private void savePlant() {
        String name = binding.etPlantName.getText().toString().trim();
        String strain = binding.etStrainName.getText().toString().trim();

        if (name.isEmpty()) {
            binding.tilPlantName.setError("Nickname is required");
            return;
        }

        // Construct entity: plantId(0 for auto), name, strain, roomId
        PlantEntity newPlant = new PlantEntity(0, selectedRoomId, name, strain );

        viewModel.insertPlant(newPlant);
        dismiss();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
