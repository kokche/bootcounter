package com.example.bootcounter.workmanager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.bootcounter.data.local.BootDao
import com.example.bootcounter.notification.NotificationManager
import com.example.bootcounter.notification.mapper.NotificationDataMapper
import org.koin.java.KoinJavaComponent.inject

// todo create work manager factory for providing dependencies and  testing
class ScheduleNotificationWorkManager(
    appContext: Context, workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    val notificationWorkManager = NotificationDataMapper()
    val dao: BootDao by inject() //todo factory. It doesn't work without that


    override suspend fun doWork(): Result {
        return runCatching {
            NotificationManager.createNotification(
                applicationContext,
                notificationWorkManager(dao.getLatestBootEvent())
            )
        }.fold(onSuccess = {
            Result.success()
        }, onFailure = {
            Result.failure()
        })
    }
}