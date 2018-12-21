package com.butajlo.blesensors.ui.lighttab

import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val lightTabModule = module {
    viewModel { LightViewModel(get()) }
}