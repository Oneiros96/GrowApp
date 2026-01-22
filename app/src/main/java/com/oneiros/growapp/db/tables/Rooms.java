package com.oneiros.growapp.db.tables;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "rooms")
public record Rooms(
    @PrimaryKey(autoGenerate = true)
    int roomId,
    String name,
    @ColumnInfo(defaultValue = "1")
    boolean indoor,
    Integer length,
    Integer width,
    Integer height,
    @ColumnInfo(defaultValue = "1")
    boolean active
) {
}
