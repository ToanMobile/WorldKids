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

import androidx.recyclerview.widget.RecyclerView
import com.app.worldkids.databinding.AdapterItemGridBinding
import com.app.worldkids.ui.widgets.common.ItemAnimator
import com.rubensousa.dpadrecyclerview.DpadViewHolder

class MainItemViewHolder(
    private val binding: AdapterItemGridBinding
) : RecyclerView.ViewHolder(binding.root), DpadViewHolder {

    private var clickListener: ItemClickListener? = null
    private val animator = ItemAnimator(binding.root)

    init {
        itemView.setOnClickListener {
            clickListener?.onViewHolderClicked()
        }
        itemView.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                animator.startFocusGainAnimation()
            } else {
                animator.startFocusLossAnimation()
            }
        }
    }

    fun bind(item: Int, listener: ItemClickListener?) {
        binding.textView.text = item.toString()
        clickListener = listener
    }

    fun recycle() {
        animator.cancel()
        clickListener = null
    }

    interface ItemClickListener {
        fun onViewHolderClicked()
    }

}
