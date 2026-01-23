package com.oneiros.growapp.db.tables;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;


import java.time.ZonedDateTime;

@Entity(
    tableName = "roomsLog",
    foreignKeys =
        @ForeignKey(
            entity = Rooms.class,
            parentColumns = "roomId",
            childColumns = "roomId",
            onUpdate = ForeignKey.CASCADE
    ),
    indices = @Index(value = "roomId")
)
public record RoomsLog(
    @PrimaryKey(autoGenerate = true)
    int roomLogId,
    int roomId,
    Integer lightCycle,
    ZonedDateTime timestamp,
    Float temperature,
    Float humidity
) {
}
