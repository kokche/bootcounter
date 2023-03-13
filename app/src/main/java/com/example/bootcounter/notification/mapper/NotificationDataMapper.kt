package com.example.bootcounter.notification.mapper

import com.example.bootcounter.data.local.model.BootEntity
import java.util.Calendar
import java.util.Date

class NotificationDataMapper : (BootEntity?) -> String {

    override fun invoke(bootEntity: BootEntity?): String {
        return when {
            bootEntity == null -> "No boots detected"
            bootEntity.id == 1 -> "The boot was detected with the timestamp = ${bootEntity.timestamp}"
            else -> {
                "Last boots time delta = ${Calendar.getInstance().timeInMillis - bootEntity.timestamp}"
            }
        }
    }
}