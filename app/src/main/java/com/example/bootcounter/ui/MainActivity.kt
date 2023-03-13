package com.example.bootcounter.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.work.Data
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.bootcounter.R
import com.example.bootcounter.data.local.BootDao
import com.example.bootcounter.workmanager.ScheduleNotificationWorkManager
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

private const val WORK_TAG = "work_tag"
private const val WORK_NAME = "work_name"
private const val INTERVAL_MINUTES = 15L

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUiState()

        setContentView(R.layout.activity_main)
        schedulePeriodicNotifications()
    }

    private fun initUiState() {
        lifecycleScope.launch {
            viewModel.getAllBoots().flowWithLifecycle(lifecycle, Lifecycle.State.RESUMED)
                .onEach {
                    findViewById<TextView>(R.id.bootEvent).text =
                        it //todo change to compose or viewBinding
                }
                .collect()
        }
    }

    private fun schedulePeriodicNotifications() {
        val periodicWork = PeriodicWorkRequestBuilder<ScheduleNotificationWorkManager>(
            INTERVAL_MINUTES, TimeUnit.MINUTES
        ).addTag(WORK_TAG).build()

        WorkManager.getInstance(this)
            .enqueueUniquePeriodicWork(
                WORK_NAME,
                ExistingPeriodicWorkPolicy.KEEP,
                periodicWork
            )
    }
}