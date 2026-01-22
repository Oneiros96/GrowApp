package com.oneiros.growapp.db.tables;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "actions")
public record Actions(
    @PrimaryKey(autoGenerate = true)
    int actionId,
    String actionName
) {
}
