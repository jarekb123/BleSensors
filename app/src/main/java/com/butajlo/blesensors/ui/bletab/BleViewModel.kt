package com.butajlo.blesensors.ui.bletab

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.butajlo.blesensors.R
import com.butajlo.blesensors.ble.BleManager
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class BleViewModel(private val bleManager: BleManager) : ViewModel() {

    private val subscriptions = CompositeDisposable()

    private val _devices = MutableLiveData<List<BleDevice>>()
    val devices: LiveData<List<BleDevice>>
        get() = _devices

    private val _infoRes = MutableLiveData<Int>()
    val infoRes: LiveData<Int>
        get() = _infoRes

    init {
        bleManager.observeState()
            .subscribeBy {
                when(it) {
                    BleManager.State.OFF -> _infoRes.value = R.string.ble_off_info
                    BleManager.State.ON -> _infoRes.value = R.string.ble_on_info
                    BleManager.State.DISCOVERING -> _infoRes.value = R.string.ble_discovering_info
                }
            }.addTo(subscriptions)
    }

    override fun onCleared() {
        subscriptions.clear()
    }

}