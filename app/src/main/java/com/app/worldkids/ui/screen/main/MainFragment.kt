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

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.app.worldkids.R
import com.app.worldkids.databinding.LayoutMainBinding
import com.app.worldkids.ui.viewBinding
import com.app.worldkids.utils.withDialogItems
import com.google.android.material.snackbar.Snackbar
import com.idanatz.oneadapter.OneAdapter
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.activityViewModel


class MainFragment : Fragment(R.layout.layout_main) {
    private val binding by viewBinding(LayoutMainBinding::bind)
    private val viewModel by activityViewModel<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupData()
        setupTime()
        setupRecyclerView()
    }

    private fun setupData() {
        viewModel.schoolName.observe(viewLifecycleOwner) {
            binding.txtClassName.text = it
        }
        viewModel.managerName.observe(viewLifecycleOwner) {
            binding.txtManager.text = it
        }
        viewModel.countCheckIn.observe(viewLifecycleOwner) {
            binding.txtPRESENT.text = it.totalCheckin.toString()
            binding.txtABSENT.text = it.totalAbsent.toString()
            binding.txtLATE.text = it.totalLate.toString()
            binding.txtOFFWITHLETTER.text = it.totalOff.toString()
            binding.txtONLEAVE.text = it.totalConfirmOff.toString()
        }
        viewModel.changeStatus.observe(viewLifecycleOwner) {
            if (it == null) {
                binding.imgCheck.isVisible = true
                val snackBar = Snackbar.make(
                    binding.root,
                    getString(R.string.verify_success),
                    Snackbar.LENGTH_LONG
                )
                val view: View = snackBar.view
                val tv =
                    view.findViewById<View>(com.google.android.material.R.id.snackbar_text) as TextView
                tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, resources.getDimension(R.dimen.text10sp))
                snackBar.show()
            }
        }
        binding.layoutVerify.setOnClickListener {
            viewModel.verify()
        }
        binding.btnSetting.setOnClickListener {
            val i = Intent(Settings.ACTION_SETTINGS)
            startActivity(i)
        }
    }

    private fun setupTime() {
        viewModel.currentTime.observe(viewLifecycleOwner) {
            binding.txtDatetime.text = it
        }
        viewModel.currentHours.observe(viewLifecycleOwner) {
            binding.txtTime.text = it
        }
    }

    private fun setupRecyclerView() {
        val oneNoCheckInAdapter = OneAdapter(binding.rcvNoCheckIn) {
            itemModules += MainModule { item, view ->
                withDialogItems(view = view, context = this@MainFragment.requireContext()) {
                    viewModel.updateStatus(clientId = item.client?.id, status = "IN")
                }
            }
        }
        val oneCheckInAdapter = OneAdapter(binding.rcvCheckIn) {
            itemModules += MainModule { item, view ->
                /*withDialogItems(view = view, context = this@MainFragment.requireContext()) {
                    viewModel.updateStatus(clientId = item.client?.id, status = "CONFIRM_OFF")
                }*/
            }
        }
        viewModel.listData.observe(viewLifecycleOwner) {
            it.listNotCheckin?.let { it1 -> oneNoCheckInAdapter.setItems(it1) }
            it.listCheckin?.let { it1 -> oneCheckInAdapter.setItems(it1) }
        }
        MainScope().launch {
            delay(1000)
            try {
                if (oneNoCheckInAdapter.itemCount > 0) {
                    binding.rcvNoCheckIn.getChildAt(0).requestFocus()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
