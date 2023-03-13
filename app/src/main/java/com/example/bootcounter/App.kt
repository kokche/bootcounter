package com.example.bootcounter

import android.app.Application
import com.example.bootcounter.di.localModule
import com.example.bootcounter.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                localModule,
                viewModelModule
            )
        }
    }
}