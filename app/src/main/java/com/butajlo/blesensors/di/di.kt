package com.butajlo.blesensors.di

import com.butajlo.blesensors.ble.bleModule
import com.butajlo.blesensors.rxsensor.rxSensorModule
import com.butajlo.blesensors.ui.bletab.bleTabModule
import com.butajlo.blesensors.ui.lighttab.lightTabModule
import org.koin.dsl.context.ModuleDefinition
import org.koin.dsl.definition.Definition

val diModules = listOf(bleModule, bleTabModule, lightTabModule, rxSensorModule)

const val FRAGMENT_SCOPE = "fragment"

inline fun <reified T : Any> ModuleDefinition.fragmentScope(
    name: String = "",
    override: Boolean = false,
    noinline definition: Definition<T>
) = scope(FRAGMENT_SCOPE, name, override, definition)