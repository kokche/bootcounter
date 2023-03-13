package com.example.bootcounter.notification

import android.Manifest
import android.app.Notification
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlin.random.Random

private const val CHANNEL_ID = "boot"
object NotificationManager {
    fun createNotification(context: Context, content: String) {
       val builder =  NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle("Hi, there. I have a new information about the boot")
            .setContentText(content)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        //todo add requesting of the permission and handle corner cases when user will not give that permission
        with(NotificationManagerCompat.from(context)) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                notify(Random.nextInt(), builder.build())
            }
        }
    }
}