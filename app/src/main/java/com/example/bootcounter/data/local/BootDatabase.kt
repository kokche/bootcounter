package com.example.bootcounter.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bootcounter.data.local.model.BootEntity

@Database(entities = [BootEntity::class], version = 1, exportSchema = false)
abstract class BootDatabase : RoomDatabase() {
    abstract fun bootDao(): BootDao
}