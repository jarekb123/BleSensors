package com.butajlo.blesensors.ble

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.butajlo.blesensors.ext.addTo
import com.butajlo.blesensors.ext.registerReceiver
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observable

class AndroidBleManager(
    private val bluetoothManager: BluetoothManager
) : BleManager {

    private val receivers = mutableListOf<BroadcastReceiver>()

    private val bluetoothStateRelay: BehaviorRelay<BleManager.State> = BehaviorRelay.create()

    fun init(context: Context) {
        with(context) {
            registerReceiver(
                BluetoothAdapter.ACTION_STATE_CHANGED,
                ::onBluetoothStateChanged
            ).addTo(receivers)

            registerReceiver(
                BluetoothAdapter.ACTION_DISCOVERY_STARTED,
                BluetoothAdapter.ACTION_DISCOVERY_FINISHED,
                onReceive = ::onDiscoveryStateChange
            ).addTo(receivers)
        }
    }

    fun clear(context: Context) {
        receivers.forEach { context.unregisterReceiver(it) }
        receivers.clear()
    }

    override fun observeState(): Observable<BleManager.State> {
        val currentState = if(!bluetoothManager.adapter.isEnabled) {
            BleManager.State.OFF
        } else if(bluetoothManager.adapter.isDiscovering) {
            BleManager.State.DISCOVERING
        } else {
            BleManager.State.ON
        }

        return bluetoothStateRelay
            .startWith(currentState)
    }

    private fun onBluetoothStateChanged(intent: Intent) {
        val state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR)
        when(state) {
            BluetoothAdapter.STATE_ON -> bluetoothStateRelay.accept(BleManager.State.ON)
            BluetoothAdapter.STATE_OFF -> bluetoothStateRelay.accept(BleManager.State.OFF)
        }
    }

    private fun onDiscoveryStateChange(intent: Intent) {
        when(intent.action) {
            BluetoothAdapter.ACTION_DISCOVERY_STARTED -> bluetoothStateRelay.accept(BleManager.State.DISCOVERING)
        }
    }
}