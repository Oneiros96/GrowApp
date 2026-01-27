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
import com.oneiros.growapp.db.entity.RoomEntity;

public class RoomAdapter extends ListAdapter<RoomEntity, RoomAdapter.RoomViewHolder> {

    public RoomAdapter() {
        super(new DiffUtil.ItemCallback<RoomEntity>() {
            @Override
            public boolean areItemsTheSame(@NonNull RoomEntity oldItem, @NonNull RoomEntity newItem) {
                return oldItem.roomId() == newItem.roomId();
            }
            @Override
            public boolean areContentsTheSame(@NonNull RoomEntity oldItem, @NonNull RoomEntity newItem) {
                return oldItem.equals(newItem); // Records have perfect equals() built-in!
            }
        });
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_room, parent, false);
        return new RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public static class RoomViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameTextView;

        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.textViewRoomName);
        }

        public void bind(RoomEntity room) {
            nameTextView.setText(room.name());
        }
    }
}
