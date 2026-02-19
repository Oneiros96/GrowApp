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
import com.oneiros.growapp.databinding.FragmentRoomListBinding;
import com.oneiros.growapp.ui.adapter.RoomAdapter;
import com.oneiros.growapp.ui.viewmodel.GrowViewModel;

public class RoomListFragment extends Fragment {

    private FragmentRoomListBinding binding;
    private GrowViewModel viewModel;
    private RoomAdapter roomAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRoomListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Access the shared ViewModel (Scoped to Activity)
        viewModel = new ViewModelProvider(requireActivity()).get(GrowViewModel.class);

        setupRecyclerView();
        observeData();

        binding.fabAddRoom.setOnClickListener(v -> {
            AddRoomDialogFragment dialogFragment = new AddRoomDialogFragment();
            dialogFragment.show(getChildFragmentManager(), "AddRoomDialog");
        });
    }

    private void setupRecyclerView() {
        roomAdapter = new RoomAdapter(room -> {
             Bundle args = new Bundle();
            args.putLong("roomId", room.roomId());
            Navigation.findNavController(requireView())
                      .navigate(R.id.action_roomList_to_roomDetail, args);
         });
         binding.recyclerViewAllRooms.setAdapter(roomAdapter);
    }

    private void observeData() {
        viewModel.getAllRooms().observe(getViewLifecycleOwner(), rooms -> {
            if (rooms != null) {
                roomAdapter.submitList(rooms);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
