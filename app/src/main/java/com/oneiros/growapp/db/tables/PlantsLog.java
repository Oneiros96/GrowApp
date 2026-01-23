package com.oneiros.growapp.db.tables;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.time.ZonedDateTime;

@Entity(
    tableName = "plantsLog",
    foreignKeys = {
        @ForeignKey(
            entity = Rooms.class,
            parentColumns = "roomId",
            childColumns = "roomId",
            onUpdate = ForeignKey.CASCADE),
        @ForeignKey(
            entity = Plants.class,
            parentColumns = "plantId",
            childColumns = "plantId",
            onUpdate = ForeignKey.CASCADE)},
    indices = {
        @Index(value = {"roomId"}),
        @Index(value = {"plantId"})
    }
)
public record PlantsLog(
    @PrimaryKey(autoGenerate = true)
    int plantLogId,
    Integer roomId,
    int plantId,
    ZonedDateTime timestamp,
    Integer lightIntensity,
    String growStage
) {
}
