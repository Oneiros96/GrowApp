package com.oneiros.growapp.db.tables;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
    tableName = "plantLogActions",
    foreignKeys = {
        @ForeignKey(
            entity = PlantsLog.class,
            parentColumns = "plantLogId",
            childColumns = "plantLogId",
            onUpdate = ForeignKey.CASCADE
        ),
        @ForeignKey(
            entity = Actions.class,
            parentColumns = "actionId",
            childColumns = "actionId",
            onUpdate = ForeignKey.CASCADE
        )
    }
)
public record PlantLogActions(
    @PrimaryKey(autoGenerate = true)
    int plantLogActionId,
    int plantLogId,
    int actionId,
    String actionDescription
) {
}
