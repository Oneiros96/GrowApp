package com.oneiros.growapp.db.entity;

import android.health.connect.datatypes.units.Length;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "rooms")
public record RoomEntity(
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
    @Ignore
    public RoomEntity(String name) {
        this(0, name, true, null, null, null, true);
    }

    @Ignore
    public RoomEntity(String name, int length, int width, int height, boolean indoor) {
        this(0, name, indoor, length, width, height,true);
    }
}
