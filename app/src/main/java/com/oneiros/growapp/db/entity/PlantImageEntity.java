package com.oneiros.growapp.db.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
    tableName = "plantImages",
    foreignKeys = {
        @ForeignKey(
            entity = PlantLogEntity.class,
            parentColumns = "plantLogId",
            childColumns = "plantLogId",
            onUpdate = ForeignKey.CASCADE
        ),
        @ForeignKey(
            entity = PlantEntity.class,
            parentColumns = "plantId",
            childColumns = "plantId",
            onUpdate = ForeignKey.CASCADE
        )
    },
    indices = {
        @Index(value = {"plantLogId"}),
        @Index(value = {"plantId"})
    }
)
public record PlantImageEntity(
    @PrimaryKey(autoGenerate = true)
    int imageId,
    int plantLogId,
    int plantId,
    @NonNull
    String imageName,
    @NonNull
    String imagePath
) {
}
