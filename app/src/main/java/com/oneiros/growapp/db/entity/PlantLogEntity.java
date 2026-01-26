package com.oneiros.growapp.db.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.time.ZonedDateTime;

@Entity(
    tableName = "plantsLog",
    foreignKeys = {
        @ForeignKey(
            entity = RoomEntity.class,
            parentColumns = "roomId",
            childColumns = "roomId",
            onUpdate = ForeignKey.CASCADE),
        @ForeignKey(
            entity = PlantEntity.class,
            parentColumns = "plantId",
            childColumns = "plantId",
            onUpdate = ForeignKey.CASCADE)},
    indices = {
        @Index(value = {"roomId"}),
        @Index(value = {"plantId"})
    }
)
public record PlantLogEntity(
    @PrimaryKey(autoGenerate = true)
    int plantLogId,
    Integer roomId,
    int plantId,
    ZonedDateTime timestamp,
    Integer lightIntensity,
    String growStage
) {
}
