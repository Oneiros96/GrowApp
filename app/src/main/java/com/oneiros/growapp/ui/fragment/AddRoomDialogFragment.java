package com.oneiros.growapp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.oneiros.growapp.db.entity.RoomEntity;
import com.oneiros.growapp.databinding.AddRoomBinding;
import com.oneiros.growapp.misc.Helpers;
import com.oneiros.growapp.ui.viewmodel.GrowViewModel;

import static com.oneiros.growapp.misc.Helpers.parseInputInt;
import static java.lang.Integer.parseInt;

public class AddRoomDialogFragment extends BottomSheetDialogFragment {

    private AddRoomBinding binding;
    private GrowViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = AddRoomBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(GrowViewModel.class);

        binding.btnSaveRoom.setOnClickListener(v -> saveRoom());
    }

    private void saveRoom() {
        String name = binding.etRoomName.getText().toString().trim();

        if (name.isEmpty()) {
            binding.tilRoomName.setError("Name is required");
            return;
        }

        // Parse integers with fallback to 0
        int length = parseInputInt(binding.etLength.getText().toString());
        int width = parseInputInt(binding.etWidth.getText().toString());
        int height = parseInputInt(binding.etHeight.getText().toString());
        boolean isIndoor = binding.cbIndoor.isChecked();

        // Create the entity (Assuming constructor: id, name, length, width, height, isIndoor)
        // Adjust the constructor call to match your specific RoomEntity class
        RoomEntity newRoom = new RoomEntity(name, length, width, height, isIndoor);

        viewModel.insertRoom(newRoom);
        dismiss();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
