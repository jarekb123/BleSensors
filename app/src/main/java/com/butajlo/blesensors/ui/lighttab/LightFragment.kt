package com.butajlo.blesensors.ui.lighttab

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.butajlo.blesensors.R
import com.butajlo.blesensors.base.BaseFragment
import com.butajlo.blesensors.di.FRAGMENT_SCOPE
import kotlinx.android.synthetic.main.screen_light.*
import org.koin.android.scope.ext.android.bindScope
import org.koin.android.scope.ext.android.getOrCreateScope
import org.koin.android.viewmodel.ext.android.viewModel

class LightFragment : BaseFragment() {
    override val layoutResId = R.layout.screen_light

    private val lightViewModel by viewModel<LightViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindScope(getOrCreateScope(FRAGMENT_SCOPE))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lightViewModel.lightData.observe(this, Observer {
            tv_lux.text = it.lux.toString()
        })
    }

}