package com.butajlo.blesensors.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.butajlo.blesensors.R
import com.butajlo.blesensors.ble.AndroidBleManager
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val bleManager by inject<AndroidBleManager>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        NavigationUI.setupWithNavController(
            bottom_navigation,
            findNavController(R.id.nav_host_fragment)
        )
    }

    override fun onResume() {
        super.onResume()
        bleManager.init(this)
    }

    override fun onPause() {
        super.onPause()
        bleManager.clear(this)
    }
}
