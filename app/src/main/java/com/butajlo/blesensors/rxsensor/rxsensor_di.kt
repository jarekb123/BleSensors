package com.butajlo.blesensors.rxsensor

import android.content.Context
import android.hardware.SensorManager
import com.butajlo.blesensors.di.fragmentScope
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val rxSensorModule = module {
    fragmentScope { androidContext().getSystemService(Context.SENSOR_SERVICE) as SensorManager }
    fragmentScope { RxSensorListenerFactory() }
    fragmentScope { RxSensor(get(), get()) }
}