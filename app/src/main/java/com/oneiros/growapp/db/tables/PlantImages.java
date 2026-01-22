package com.oneiros.growapp.db.tables;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
    tableName = "plantImages",
    foreignKeys = {
        @ForeignKey(
            entity = PlantsLog.class,
            parentColumns = "plantLogId",
            childColumns = "plantLogId",
            onUpdate = ForeignKey.CASCADE
        ),
        @ForeignKey(
            entity = Plants.class,
            parentColumns = "plantId",
            childColumns = "plantId",
            onUpdate = ForeignKey.CASCADE
        )
    }
)
public record PlantImages(
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
