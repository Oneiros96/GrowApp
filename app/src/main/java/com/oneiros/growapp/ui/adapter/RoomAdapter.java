package com.oneiros.growapp.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.oneiros.growapp.R;
import com.oneiros.growapp.databinding.ItemRoomBinding;
import com.oneiros.growapp.db.entity.RoomEntity;


import java.util.function.Consumer;

public class RoomAdapter extends ListAdapter<RoomEntity, RoomAdapter.RoomViewHolder> {

    private final Consumer<RoomEntity> onRoomClickListener;

    public RoomAdapter(Consumer<RoomEntity> listener) {
        super(new DiffUtil.ItemCallback<RoomEntity>() {
            @Override
            public boolean areItemsTheSame(@NonNull RoomEntity oldItem, @NonNull RoomEntity newItem) {
                // Using record style accessors if RoomEntity is a record, or .getRoomId()
                return oldItem.roomId() == newItem.roomId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull RoomEntity oldItem, @NonNull RoomEntity newItem) {
                return oldItem.equals(newItem);
            }
        });
        this.onRoomClickListener = listener;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRoomBinding binding = ItemRoomBinding.inflate(
            LayoutInflater.from(parent.getContext()), parent, false);
        return new RoomViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        RoomEntity room = getItem(position);
        holder.bind(room, onRoomClickListener);
    }

    public static class RoomViewHolder extends RecyclerView.ViewHolder {
        private final ItemRoomBinding binding;

        public RoomViewHolder(@NonNull ItemRoomBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(RoomEntity room, Consumer<RoomEntity> listener) {
            binding.tvRoomsHeader.setText(room.name());

            // Set unique transition name for the expanding animation
            binding.getRoot().setTransitionName("room_card_" + room.roomId());

            binding.getRoot().setOnClickListener(v -> {
                if (listener != null) {
                    listener.accept(room);
                }
            });
        }
    }
}