package com.app.worldkids.ui.screen.main

import coil.load
import coil.transform.CircleCropTransformation
import com.app.worldkids.R
import com.app.worldkids.databinding.AdapterItemGridBinding
import com.app.worldkids.model.response.CheckIn
import com.idanatz.oneadapter.external.modules.ItemModule
import timber.log.Timber

class MainModule : ItemModule<CheckIn>() {
    init {
        config {
            layoutResource = R.layout.adapter_item_grid
        }
        onBind { model, viewBinder, _ ->
            Timber.e("MainModule::$model")
            viewBinder.bindings(AdapterItemGridBinding::bind).run {
                txtTitle.text = model.client?.fullname
                txtDes.text = model.status
                imgAvatar.load(model.client?.avatar) {
                    crossfade(true)
                    placeholder(com.google.android.material.R.drawable.mtrl_ic_error)
                    transformations(CircleCropTransformation())
                }
                itemRoot.setOnFocusChangeListener { v, hasFocus ->
                    if (hasFocus) {
                        v.setBackgroundResource(R.drawable.item_focus)
                    } else {
                        v.setBackgroundResource(R.drawable.item_no_focus)
                    }
                }
            }
        }
    }
}
