package com.app.worldkids.ui.screen.main

import coil.load
import coil.transform.CircleCropTransformation
import com.app.worldkids.R
import com.app.worldkids.databinding.AdapterItemGridBinding
import com.app.worldkids.ui.model.ListMode
import com.idanatz.oneadapter.external.modules.ItemModule

class MainModule : ItemModule<ListMode>() {
    init {
        config {
            layoutResource = R.layout.adapter_item_grid
        }
        onBind { model, viewBinder, _ ->
            viewBinder.bindings(AdapterItemGridBinding::bind).run {
                txtTitle.text = model.title
                txtDes.text = model.des
                imgAvatar.load(model.image) {
                    crossfade(true)
                    placeholder(R.drawable.baseline_person_pin_24)
                    transformations(CircleCropTransformation())
                }
            }
        }
    }
}
