package com.butajlo.blesensors.ext

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter

fun Context.registerReceiver(
    action: String,
    onReceive: (Intent) -> Unit
): BroadcastReceiver {
    val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val intentAction = intent?.action ?: return
            if(action == intentAction) {
                onReceive(intent)
            }
        }
    }
    registerReceiver(receiver, IntentFilter(action))

    return receiver
}

fun Context.registerReceiver(
    vararg actions: String,
    onReceive: (Intent) -> Unit
): BroadcastReceiver {
    val intentFilter = IntentFilter().apply {
        actions.forEach { addAction(it) }
    }
    val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val action = intent?.action ?: return
            onReceive(intent)
        }
    }
    registerReceiver(receiver, intentFilter)

    return receiver
}