package com.app.worldkids.ui.screen.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.worldkids.data.repository.NetworkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainViewModel(networkRepository: NetworkRepository) : ViewModel() {
    //http://139.180.155.164:3002/api/admin/checkin/6/list-checkin
    private val list = ArrayList<Int>()
    private val listLiveData = MutableLiveData<MutableList<Int>>()
    private val loadingStateLiveData = MutableLiveData<Boolean>()
    private val pageSize = 50

    val loadingState: LiveData<Boolean> = loadingStateLiveData
    val listState: LiveData<MutableList<Int>> = listLiveData
    val currentHours = MutableLiveData<String>()
    val currentTime = MutableLiveData<String>()

    init {
        viewModelScope.launch {
            networkRepository.register()
        }
        list.addAll(createPage())
        listLiveData.postValue(list)
        viewModelScope.launch(Dispatchers.IO) {
            val localTime = LocalDateTime.now()
            currentHours.postValue(localTime.format(DateTimeFormatter.ofPattern("HH:mm")))
            currentTime.postValue(localTime.format(DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy")))
        }
    }

    fun loadMore(selectedPosition: Int, spanCount: Int) {
        if (loadingState.value == true) {
            return
        }
        val diffToEnd = list.size - selectedPosition
        if (diffToEnd > spanCount) {
            return
        }
        loadingStateLiveData.postValue(true)
        viewModelScope.launch(Dispatchers.Default) {
            list.addAll(createPage())
            delay(2500L)
            listLiveData.postValue(ArrayList(list))
        }.invokeOnCompletion { loadingStateLiveData.postValue(false) }
    }

    private fun createPage(): List<Int> {
        return List(pageSize) { index -> list.size + index }
    }

}
