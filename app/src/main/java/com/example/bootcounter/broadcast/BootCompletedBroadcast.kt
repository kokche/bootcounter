package com.example.bootcounter.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.bootcounter.data.local.BootDao
import com.example.bootcounter.data.local.model.BootEntity
import org.koin.java.KoinJavaComponent.inject
import java.util.Calendar

//todo add coroutine impl
//todo add koin injection

class BootCompletedBroadcast : BroadcastReceiver() {

    val dao: BootDao by inject()

    override fun onReceive(p0: Context?, p1: Intent?) {
        dao.insertNewBootTime(BootEntity(timestamp = Calendar.getInstance().timeInMillis))

    }
}