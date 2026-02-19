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
import com.oneiros.growapp.databinding.FragmentHomeBinding;
import com.oneiros.growapp.ui.adapter.PlantAdapter;
import com.oneiros.growapp.ui.adapter.RoomAdapter;
import com.oneiros.growapp.ui.viewmodel.GrowViewModel;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private GrowViewModel viewModel;
    private RoomAdapter roomAdapter;
    private PlantAdapter plantAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(GrowViewModel.class);

        setupNavigation();
        setupRecyclerViews();
        observeData();
    }

    private void setupNavigation() {
        // Header clicks for Overviews
        binding.tvRoomsHeader.setOnClickListener(v ->
            Navigation.findNavController(v).navigate(R.id.action_home_to_roomList));

        binding.tvPlantsHeader.setOnClickListener(v ->
            Navigation.findNavController(v).navigate(R.id.action_home_to_plantList));

        // FAB clicks (you can add specific actions in nav_graph)
        binding.fabAddRoom.setOnClickListener(v -> {
            AddRoomDialogFragment dialogFragment = new AddRoomDialogFragment();
            dialogFragment.show(getChildFragmentManager(), "AddRoomDialog");
        });

        binding.fabAddPlant.setOnClickListener(v -> {
            AddPlantDialogFragment dialogFragment = new AddPlantDialogFragment();
            dialogFragment.show(getChildFragmentManager(), "AddPlantDialog");
        });
    }

    private void setupRecyclerViews() {
        binding.recyclerViewRooms.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewPlants.setLayoutManager(new LinearLayoutManager(getContext()));

        roomAdapter = new RoomAdapter(room -> {
            // Create the argument bundle
            Bundle args = new Bundle();
            args.putLong("roomId", room.roomId());

            // Navigate using the action ID from nav_graph.xml
            Navigation.findNavController(requireView())
                .navigate(R.id.action_home_to_roomDetail, args);
        });
        binding.recyclerViewRooms.setAdapter(roomAdapter);

        // 2. Setup Plant Adapter
        plantAdapter = new PlantAdapter(plant -> {
            Bundle args = new Bundle();
            args.putLong("plantId", plant.plantId());
            Navigation.findNavController(requireView())
                .navigate(R.id.action_home_to_plantDetail, args);
        });
        binding.recyclerViewPlants.setAdapter(plantAdapter);
    }

    private void navigateToRoomDetail(long roomId) {
        Bundle args = new Bundle();
        args.putLong("roomId", roomId);
        Navigation.findNavController(requireView()).navigate(R.id.action_home_to_roomDetail, args);
    }

    private void observeData() {
        viewModel.getAllRooms().observe(getViewLifecycleOwner(), rooms -> {
            if (rooms != null) {
                roomAdapter.submitList(rooms);
            }
        });

        viewModel.getPlantsWithoutRoom().observe(getViewLifecycleOwner(), plants -> {
                if (plants != null) {
                    plantAdapter.submitList(plants);
                }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // Prevent memory leaks (SonarQube requirement)
    }
}
