package com.oneiros.growapp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.oneiros.growapp.databinding.AddPlantBinding;
import com.oneiros.growapp.db.entity.PlantEntity;
import com.oneiros.growapp.ui.viewmodel.GrowViewModel;

public class AddPlantDialogFragment extends BottomSheetDialogFragment{
    private AddPlantBinding binding;
    private GrowViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = AddPlantBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Scope to activity so it talks to the same DB source as the HomeFragment
        viewModel = new ViewModelProvider(requireActivity()).get(GrowViewModel.class);

        binding.btnSavePlant.setOnClickListener(v -> {
            String name = binding.etPlantName.getText().toString().trim();

            if (!name.isEmpty()) {
                // RoomEntity(long roomId, String name) - assuming id 0 for auto-gen
                viewModel.insertPlant(new PlantEntity(name));
                dismiss(); // Close the sheet
            } else {
                binding.tilPlantName.setError("Name cannot be empty");
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
