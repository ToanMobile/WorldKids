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
import com.app.worldkids.R
import com.app.worldkids.databinding.LayoutMainBinding
import com.app.worldkids.ui.model.ListMode
import com.app.worldkids.ui.viewBinding
import com.idanatz.oneadapter.OneAdapter


class MainFragment : Fragment(R.layout.layout_main) {
    private val binding by viewBinding(LayoutMainBinding::bind)
    private val viewModel by viewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTime()
        setupRecyclerViewNoCheckIn()
        setupRecyclerViewCheckIn()
    }

    private fun setupTime() {
        viewModel.currentTime.observe(viewLifecycleOwner) {
            binding.txtDatetime.text = it
        }
        viewModel.currentHours.observe(viewLifecycleOwner) {
            binding.txtTime.text = it
        }
    }

    private fun setupRecyclerViewNoCheckIn() {
        val oneAdapter = OneAdapter(binding.rcvNoCheckIn) {
            itemModules += MainModule()
        }
        oneAdapter.setItems(listOf(
            ListMode(0, "Features Examples", "Title", "https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/salinas_grandes-argentina-25.jpg"),
        ))
    }

    private fun setupRecyclerViewCheckIn() {
        val oneAdapter = OneAdapter(binding.rcvCheckIn) {
            itemModules += MainModule()
        }
        oneAdapter.setItems(listOf(
            ListMode(0, "Features Examples", "Title", "https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/salinas_grandes-argentina-25.jpg"),
        ))
    }

}
