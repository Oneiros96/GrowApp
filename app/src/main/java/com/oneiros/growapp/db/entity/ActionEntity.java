package com.oneiros.growapp.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "actions")
public record ActionEntity(
    @PrimaryKey(autoGenerate = true)
    int actionId,
    String actionName
) {
}
