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
import android.os.Handler
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.app.worldkids.R
import com.app.worldkids.databinding.LayoutMainBinding
import com.app.worldkids.ui.viewBinding
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter


class MainFragment : Fragment(R.layout.layout_main) {
    private val binding by viewBinding(LayoutMainBinding::bind)
    private val viewModel by viewModels<MainViewModel>()

    val simpleImageItems: List<MainItem>
        get() = toList(
            MainItem().withIdentifier(1L).withName("Yang Zhuo Yong Cuo, Tibet China").withDescription("#100063").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/yang_zhuo_yong_cuo,_tibet-china-63.jpg"),
            MainItem().withIdentifier(2L).withName("Yellowstone United States").withDescription("#100017").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/yellowstone-united_states-17.jpg"),
            MainItem().withIdentifier(3L).withName("Victoria Australia").withDescription("#100031").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/victoria-australia-31.jpg"),
            MainItem().withIdentifier(4L).withName("Valencia Spain").withDescription("#100082").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/valencia-spain-82.jpg"),
            MainItem().withIdentifier(5L).withName("Xigaze, Tibet China").withDescription("#100030").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/xigaze,_tibet-china-30.jpg"),
            MainItem().withIdentifier(6L).withName("Utah United States").withDescription("#100096").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/utah-united_states-96.jpg"),
            MainItem().withIdentifier(7L).withName("Utah United States").withDescription("#100015").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/utah-united_states-15.jpg"),
            MainItem().withIdentifier(8L).withName("Utah United States").withDescription("#100088").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/utah-united_states-88.jpg"),
            MainItem().withIdentifier(9L).withName("Umm Al Quwain United Arab Emirates").withDescription("#100013").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/umm_al_quwain-united_arab_emirates-13.jpg"),
            MainItem().withIdentifier(10L).withName("Texas United States").withDescription("#100026").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/texas-united_states-26.jpg"),
            MainItem().withIdentifier(11L).withName("Siuslaw National Forest United States").withDescription("#100092").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/siuslaw_national_forest-united_states-92.jpg"),
            MainItem().withIdentifier(12L).withName("The Minquiers Channel Islands").withDescription("#100069").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/the_minquiers-channel_islands-69.jpg"),
            MainItem().withIdentifier(13L).withName("Texas United States").withDescription("#100084").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/texas-united_states-84.jpg"),
            MainItem().withIdentifier(14L).withName("Tabuaeran Kiribati").withDescription("#100050").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/tabuaeran-kiribati-50.jpg"),
            MainItem().withIdentifier(15L).withName("Stanislaus River United States").withDescription("#100061").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/stanislaus_river-united_states-61.jpg"),
            MainItem().withIdentifier(16L).withName("Salinas Grandes Argentina").withDescription("#100025").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/salinas_grandes-argentina-25.jpg"),
            MainItem().withIdentifier(17L).withName("Shadegan Refuge Iran").withDescription("#100012").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/shadegan_refuge-iran-12.jpg"),
            MainItem().withIdentifier(18L).withName("San Pedro De Atacama Chile").withDescription("#100043").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/san_pedro_de_atacama-chile-43.jpg"),
            MainItem().withIdentifier(19L).withName("Ragged Island The Bahamas").withDescription("#100064").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/ragged_island-the_bahamas-64.jpg"),
            MainItem().withIdentifier(20L).withName("Qinghai Lake China").withDescription("#100080").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/qinghai_lake-china-80.jpg"),
            MainItem().withIdentifier(21L).withName("Qesm Al Wahat Ad Dakhlah Egypt").withDescription("#100056").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/qesm_al_wahat_ad_dakhlah-egypt-56.jpg"),
            MainItem().withIdentifier(22L).withName("Riedstadt Germany").withDescription("#100042").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/riedstadt-germany-42.jpg"),
            MainItem().withIdentifier(23L).withName("Redwood City United States").withDescription("#100048").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/redwood_city-united_states-48.jpg"),
            MainItem().withIdentifier(24L).withName("Nyingchi, Tibet China").withDescription("#100098").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/nyingchi,_tibet-china-98.jpg"),
            MainItem().withIdentifier(25L).withName("Ngari, Tibet China").withDescription("#100057").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/ngari,_tibet-china-57.jpg"),
            MainItem().withIdentifier(26L).withName("Pozoantiguo Spain").withDescription("#100099").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/pozoantiguo-spain-99.jpg"),
            MainItem().withIdentifier(27L).withName("Ningaloo Australia").withDescription("#100073").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/ningaloo-australia-73.jpg"),
            MainItem().withIdentifier(28L).withName("Niederzier Germany").withDescription("#100079").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/niederzier-germany-79.jpg"),
            MainItem().withIdentifier(29L).withName("Olympic Dam Australia").withDescription("#100065").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/olympic_dam-australia-65.jpg"),
            MainItem().withIdentifier(30L).withName("Peedamulla Australia").withDescription("#100040").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/peedamulla-australia-40.jpg"),
            MainItem().withIdentifier(31L).withName("Nevado Tres Cruces Park Chile").withDescription("#100089").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/nevado_tres_cruces_park-chile-89.jpg")
        )
    private fun toList(vararg imageItems: MainItem): List<MainItem> {
        return imageItems.toList()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupTime()
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
        val itemAdapter = ItemAdapter<MainItem>()
        val fastAdapter = FastAdapter.with(itemAdapter)
        Handler().postDelayed({
            //add some dummy data
            itemAdapter.add(simpleImageItems)
        }, 50)
        binding.rcvNoCheckIn.adapter = fastAdapter
    }

}
