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
import com.oneiros.growapp.db.entity.PlantEntity;
import org.jetbrains.annotations.NotNull;

public class PlantAdapter extends ListAdapter<PlantEntity, PlantAdapter.PlantViewHolder> {

    public PlantAdapter() {
        super(new DiffUtil.ItemCallback<PlantEntity>() {
            @Override
            public boolean areItemsTheSame(@NonNull PlantEntity oldItem, @NotNull PlantEntity newItem) {
                return oldItem.plantId() == newItem.plantId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull PlantEntity oldItem, @NotNull PlantEntity newItem) {
                return oldItem.equals(newItem);
            }
        });
    }

    @NonNull
    @Override
    public PlantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_plant, parent, false);
        return new PlantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlantViewHolder holder, int position) { holder.bind(getItem(position));}

    public static class PlantViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameTextView;

        public PlantViewHolder(@NonNull View itemView){
            super(itemView);
            nameTextView = itemView.findViewById(R.id.textViewPlantName);
        }

        public void bind(PlantEntity plant){nameTextView.setText(plant.name());}
    }
}
