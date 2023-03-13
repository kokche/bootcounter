package com.example.bootcounter.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.WorkManager
import com.example.bootcounter.data.local.BootDao
import com.example.bootcounter.workmanager.ScheduleNotificationWorkManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn

// todo add viewModel interface for testing
// todo discuss whether we would add some api for adding pattern Repository
// todo discuss the changing of the project logic for the adding domain
class MainViewModel(private val bootDao: BootDao) : ViewModel() {

    fun getAllBoots(): Flow<String> = bootDao.getAllBootTime().map { entities ->
        entities.joinToString(separator = "\n") { bootEntity -> "${bootEntity.id} - ${bootEntity.timestamp}" }
    }.shareIn(viewModelScope, SharingStarted.WhileSubscribed())
}