package com.butajlo.blesensors.ui.bletab

data class BleDevice(
    val mac: String,
    val name: String,
    val signalStrength: Float
)