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

package com.app.ui.dpad

import android.util.Log
import android.view.ViewGroup
import coil.util.Logger
import com.rubensousa.dpadrecyclerview.compose.DpadComposeViewHolder
import com.app.ui.dpad.model.ListTypes
import com.app.ui.dpad.common.MutableListAdapter
import com.app.ui.dpad.item.GridItemComposable
import com.app.ui.dpad.item.MutableGridAdapter

class ComposeGridAdapter : MutableListAdapter<Int, DpadComposeViewHolder<Int>>(
    MutableGridAdapter.DIFF_CALLBACK
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DpadComposeViewHolder<Int> {
        return DpadComposeViewHolder(parent,
            composable = { item, isFocused, _ ->
                GridItemComposable(item, isFocused)
            },
            onClick = { item ->
                Log.e("ComposeGridAdapter","Clicked: $item")
            }
        )
    }

    override fun onBindViewHolder(holder: DpadComposeViewHolder<Int>, position: Int) {
        holder.setItemState(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return ListTypes.ITEM
    }
}