package com.oneiros.growapp.db.tables;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "plants")
public record Plants(
    @PrimaryKey(autoGenerate = true)
    int plantId,
    String name,
    String strain
) {
}
