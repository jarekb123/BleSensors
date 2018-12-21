package com.butajlo.blesensors.ble

import io.reactivex.Observable

interface BleManager {

    fun observeState(): Observable<State>


    enum class State {
        OFF, ON, DISCOVERING
    }
}