package com.oneiros.growapp.db.entity;

import androidx.room.*;


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
    @Ignore
    public PlantEntity(String name) {
        this(0, null, name, null);
    }
}
