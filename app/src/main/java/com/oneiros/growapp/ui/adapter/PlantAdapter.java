package com.oneiros.growapp.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.oneiros.growapp.db.entity.PlantEntity;
import com.oneiros.growapp.databinding.ItemPlantBinding;

import java.util.function.Consumer;

public class PlantAdapter extends ListAdapter<PlantEntity, PlantAdapter.PlantViewHolder> {

    private final Consumer<PlantEntity> onPlantClickListener;

    public PlantAdapter(Consumer<PlantEntity> listener) {
        super(new DiffUtil.ItemCallback<PlantEntity>() {
            @Override
            public boolean areItemsTheSame(@NonNull PlantEntity oldItem, @NonNull PlantEntity newItem) {
                // Assuming plantId() is your accessor (Record style)
                return oldItem.plantId() == newItem.plantId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull PlantEntity oldItem, @NonNull PlantEntity newItem) {
                return oldItem.equals(newItem);
            }
        });
        this.onPlantClickListener = listener;
    }

    @NonNull
    @Override
    public PlantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPlantBinding binding = ItemPlantBinding.inflate(
            LayoutInflater.from(parent.getContext()), parent, false);
        return new PlantViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PlantViewHolder holder, int position) {
        holder.bind(getItem(position), onPlantClickListener);
    }

    public static class PlantViewHolder extends RecyclerView.ViewHolder {
        private final ItemPlantBinding binding;

        public PlantViewHolder(@NonNull ItemPlantBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(PlantEntity plant, Consumer<PlantEntity> listener) {
            binding.tvPlantsHeader.setText(plant.name());

            // Prepare for Material Container Transform (Expansion)
            binding.getRoot().setTransitionName("plant_card_" + plant.plantId());

            binding.getRoot().setOnClickListener(v -> {
                if (listener != null) {
                    listener.accept(plant);
                }
            });
        }
    }
}