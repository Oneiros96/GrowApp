package com.oneiros.growapp.db.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;


@Entity(
        tableName = "plants",
        foreignKeys = {
                @ForeignKey(
                        entity = RoomEntity.class,
                        parentColumns = "roomId",
                        childColumns = "roomId",
                        onUpdate = ForeignKey.CASCADE
                )
        },
        indices = {
                @Index(value = {"roomId"})
        }
)
public record PlantEntity(
        @PrimaryKey(autoGenerate = true)
        int plantId,
        Integer roomId,
        String name,
        String strain
) {
}
