package com.oneiros.growapp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.oneiros.growapp.R;
import com.oneiros.growapp.databinding.FragmentRoomDetailBinding;
import com.oneiros.growapp.ui.adapter.PlantAdapter;
import com.oneiros.growapp.ui.viewmodel.GrowViewModel;

public class RoomDetailFragment extends Fragment {

    private FragmentRoomDetailBinding binding;
    private GrowViewModel viewModel;
    private PlantAdapter plantAdapter;
    private int roomId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            roomId = (int) getArguments().getLong("roomId");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRoomDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(GrowViewModel.class);

        setupRecyclerView();
        observeRoomDetails();
        observePlantsInRoom();
    }

    private void setupRecyclerView() {
        // REUSE: Using the same PlantAdapter from the Home dashboard
        plantAdapter = new PlantAdapter(plant -> {
            Bundle args = new Bundle();
            args.putLong("plantId", plant.plantId());
            Navigation.findNavController(requireView()).navigate(R.id.action_home_to_plantDetail, args);
        });
        binding.rvRoomPlants.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvRoomPlants.setAdapter(plantAdapter);
    }

    private void observeRoomDetails() {
        viewModel.getRoomById(roomId).observe(getViewLifecycleOwner(), room -> {
            if (room != null) {
                binding.tvDetailRoomName.setText(room.name());
                binding.tvValLen.setText(room.length() + "m");
                binding.tvValWid.setText(room.width() + "m");
                binding.tvValHei.setText(room.height() + "m");
                binding.chipIndoor.setVisibility(room.indoor() ? View.VISIBLE : View.GONE);
            }
        });
    }

    private void observePlantsInRoom() {
        // You'll need a method in GrowViewModel: getPlantsByRoom(long id)
        viewModel.getPlantsByRoom(roomId).observe(getViewLifecycleOwner(), plants -> {
            plantAdapter.submitList(plants);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
