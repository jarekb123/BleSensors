package com.butajlo.blesensors.ble

import android.bluetooth.BluetoothManager
import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val bleModule = module {
    single { androidContext().getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager }
    single { AndroidBleManager(get()) } bind BleManager::class
}