package com.app.worldkids.ui.screen.main

import android.view.View
import coil.load
import coil.transform.CircleCropTransformation
import com.app.worldkids.R
import com.app.worldkids.databinding.AdapterItemGridBinding
import com.app.worldkids.model.StatusType
import com.app.worldkids.model.response.CheckIn
import com.app.worldkids.utils.tint
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
                isStatus = model.status
                txtTitle.text = model.client?.fullname
                txtDes.text = model.status
                imgAvatar.load(model.client?.avatar) {
                    crossfade(true)
                    placeholder(com.google.android.material.R.drawable.mtrl_ic_error)
                    transformations(CircleCropTransformation())
                }
                itemRoot.setOnFocusChangeListener { v, hasFocus ->
                    if (hasFocus) {
                        v.setBackgroundResource(R.drawable.item_focus_absent)
                        initStatusBackground(itemRoot = itemRoot, model.status)
                    } else {
                        v.setBackgroundResource(R.drawable.item_no_focus)
                    }
                }
            }
        }
    }

    private fun initStatusBackground(itemRoot: View, status: String?){
        when(status){
            StatusType.IN.name -> itemRoot.background.tint(itemRoot.context, R.color.color3E9346)
            StatusType.ABSENT.name -> itemRoot.background.tint(itemRoot.context, R.color.colorEA1911)
            StatusType.LATE.name -> itemRoot.background.tint(itemRoot.context, R.color.color8939DA)
            StatusType.OFF.name -> itemRoot.background.tint(itemRoot.context, R.color.colorF7AD1A)
            StatusType.CONFIRM_OFF.name -> itemRoot.background.tint(itemRoot.context, R.color.colorF27F0C)
            else -> itemRoot.background.tint(itemRoot.context, R.color.color3E9346)
        }

    }

}
