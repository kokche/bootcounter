package com.example.bootcounter.di

import androidx.room.Room
import com.example.bootcounter.data.local.BootDao
import com.example.bootcounter.data.local.BootDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localModule = module {
    single {
        Room.databaseBuilder(androidContext(), BootDatabase::class.java, "BootDatabase")
            .build()
    }
    single { get<BootDatabase>().bootDao() }
}