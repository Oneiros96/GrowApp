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
import com.oneiros.growapp.R;
import com.oneiros.growapp.databinding.FragmentPlantListBinding;
import com.oneiros.growapp.ui.adapter.PlantAdapter;
import com.oneiros.growapp.ui.viewmodel.GrowViewModel;



public class PlantListFragment extends Fragment {
    private FragmentPlantListBinding binding;
    private GrowViewModel viewModel;
    private PlantAdapter plantAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPlantListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Access the shared ViewModel (Scoped to Activity)
        viewModel = new ViewModelProvider(requireActivity()).get(GrowViewModel.class);

        setupRecyclerView();
        observeData();

        binding.fabAddPlant.setOnClickListener(v -> {
            AddPlantDialogFragment dialogFragment = new AddPlantDialogFragment();
            dialogFragment.show(getChildFragmentManager(), "AddPlantDialog");
        });
    }

    private void setupRecyclerView() {
        plantAdapter = new PlantAdapter(plant -> {
            Bundle args = new Bundle();
            args.putLong("plantId", plant.plantId());
            Navigation.findNavController(requireView())
                .navigate(R.id.action_plantList_to_plantDetail, args);
        });
        binding.recyclerViewAllPlants.setAdapter(plantAdapter);
    }

    private void observeData() {
        viewModel.getPlantsWithoutRoom().observe(getViewLifecycleOwner(), plants -> {
            if (plants != null) {
                plantAdapter.submitList(plants);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
