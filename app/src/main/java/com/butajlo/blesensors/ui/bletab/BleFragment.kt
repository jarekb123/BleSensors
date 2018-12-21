package com.butajlo.blesensors.ui.bletab

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.butajlo.blesensors.R
import com.butajlo.blesensors.base.BaseFragment
import kotlinx.android.synthetic.main.screen_ble.*
import org.koin.android.viewmodel.ext.android.viewModel

class BleFragment : BaseFragment() {
    override val layoutResId = R.layout.screen_ble

    private val viewModel by viewModel<BleViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.infoRes.observe(this, Observer {
            tv_ble_info.setText(it)
        })
    }

}