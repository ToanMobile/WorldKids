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

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.worldkids.R
import com.app.worldkids.databinding.ScreenRecyclerviewBinding
import com.app.worldkids.ui.viewBinding
import com.app.worldkids.ui.widgets.common.PlaceholderAdapter
import com.rubensousa.dpadrecyclerview.DpadRecyclerView
import com.rubensousa.dpadrecyclerview.DpadSpanSizeLookup
import com.rubensousa.dpadrecyclerview.OnViewHolderSelectedListener
import com.rubensousa.dpadrecyclerview.spacing.DpadGridSpacingDecoration
import com.rubensousa.dpadrecyclerview.spacing.DpadSpacingLookup
import timber.log.Timber

class MainFragment : Fragment(R.layout.screen_recyclerview) {

    private val spanCount = 5
    private val binding by viewBinding(ScreenRecyclerviewBinding::bind)
    private val viewModel by viewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView(binding.recyclerView)
        val placeholderAdapter = PlaceholderAdapter(
            items = spanCount,
            layoutId = R.layout.adapter_grid_placeholder
        )
        val itemAdapter = MainItemAdapter(object : MainItemViewHolder.ItemClickListener {
            override fun onViewHolderClicked() {
                Timber.i("Item clicked")
            }
        })
        val concatAdapter = ConcatAdapter(
            ConcatAdapter.Config.Builder()
                .setIsolateViewTypes(true)
                .build()
        )
        concatAdapter.addAdapter(itemAdapter)
        concatAdapter.addAdapter(placeholderAdapter)

        viewModel.loadingState.observe(viewLifecycleOwner) { isLoading ->
            placeholderAdapter.show(isLoading)
        }

        viewModel.listState.observe(viewLifecycleOwner) { list ->
            itemAdapter.submitList(list)
        }
        binding.recyclerView.requestFocus()
        binding.recyclerView.adapter = concatAdapter
    }

    private fun setupRecyclerView(recyclerView: DpadRecyclerView) {
        recyclerView.apply {
            setSpanCount(spanCount)
            //setReverseLayout(args.reverseLayout)
            addOnViewHolderSelectedListener(object : OnViewHolderSelectedListener {
                override fun onViewHolderSelected(
                    parent: RecyclerView,
                    child: RecyclerView.ViewHolder?,
                    position: Int,
                    subPosition: Int
                ) {
                    viewModel.loadMore(position, spanCount)
                }
            })

            addItemDecoration(
                DpadGridSpacingDecoration.create(
                    itemSpacing = resources.getDimensionPixelOffset(R.dimen.grid_item_spacing)
                ).also {
                    it.setSpacingLookup(object : DpadSpacingLookup {
                        override fun shouldApplySpacing(
                            viewHolder: RecyclerView.ViewHolder,
                            itemCount: Int
                        ): Boolean {
                            return viewHolder.absoluteAdapterPosition > 0
                        }
                    })
                }
            )

            addItemDecoration(
                DpadGridSpacingDecoration.create(
                    itemSpacing = 0,
                    perpendicularItemSpacing = resources.getDimensionPixelOffset(R.dimen.grid_item_spacing)
                ).also {
                    it.setSpacingLookup(object : DpadSpacingLookup {
                        override fun shouldApplySpacing(
                            viewHolder: RecyclerView.ViewHolder,
                            itemCount: Int
                        ): Boolean {
                            return viewHolder.absoluteAdapterPosition == 0
                        }
                    })
                }
            )
            setSpanSizeLookup(object : DpadSpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if (position == 0) {
                        recyclerView.getSpanCount()
                    } else {
                        1
                    }
                }
            })
        }
    }

}
