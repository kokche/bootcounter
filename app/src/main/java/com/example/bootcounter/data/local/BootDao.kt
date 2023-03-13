package com.example.bootcounter.data.local

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bootcounter.data.local.model.BootEntity
import kotlinx.coroutines.flow.Flow

interface BootDao {

    @Insert(onConflict = OnConflictStrategy.ABORT) // fail fast
    suspend fun insertNewBootTime(bootEntity: BootEntity)

    @Query("SELECT * FROM `boots`")
    fun getAllBootTime(): Flow<List<BootEntity>>

    @Query("SELECT * FROM `boots` ORDER BY timestamp DESC LIMIT 1")
    suspend fun getLatestBootEvent(): BootEntity?
}