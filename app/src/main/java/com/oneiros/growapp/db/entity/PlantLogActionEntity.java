package com.oneiros.growapp.db.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
    tableName = "plantLogActions",
    foreignKeys = {
        @ForeignKey(
            entity = PlantLogEntity.class,
            parentColumns = "plantLogId",
            childColumns = "plantLogId",
            onUpdate = ForeignKey.CASCADE
        ),
        @ForeignKey(
            entity = ActionEntity.class,
            parentColumns = "actionId",
            childColumns = "actionId",
            onUpdate = ForeignKey.CASCADE
        )
    },
    indices = {
        @Index(value = {"plantLogId"}),
        @Index(value = {"actionId"})
    }
)
public record PlantLogActionEntity(
    @PrimaryKey(autoGenerate = true)
    int plantLogActionId,
    int plantLogId,
    int actionId,
    String actionDescription
) {
}
