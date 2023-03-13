package com.example.bootcounter.data.local.model

import android.media.AudioTimestamp
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "boots")
data class BootEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 1,
    val timestamp: Long
)