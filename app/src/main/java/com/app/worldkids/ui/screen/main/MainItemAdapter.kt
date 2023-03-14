/*
 * Copyright 2023 Rúben Sousa
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.app.worldkids.ui.screen.main

import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.worldkids.R
import com.app.worldkids.databinding.AdapterItemGridBinding
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class MainItemAdapter : AbstractBindingItem<AdapterItemGridBinding>() {
    private var mImageUrl: String? = null
    private var mName: String? = null
    private var mDescription: String? = null
    private var isPlaceholder: Boolean =
        false // True when used as placeholderInterceptor by PagedModelAdapter

    override val type: Int
        get() = R.id.fastadapter_simple_image_item_id

    override fun bindView(binding: AdapterItemGridBinding, payloads: List<Any>) {
        //binding.name.text = name
    }

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): AdapterItemGridBinding {
        return AdapterItemGridBinding.inflate(inflater, parent, false)
    }
}