package com.app.worldkids.ui.screen.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.worldkids.data.pre.DataStoreUtils
import com.app.worldkids.data.repository.NetworkRepository
import com.app.worldkids.model.response.ListUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainViewModel(networkRepository: NetworkRepository, dataStoreUtils: DataStoreUtils) : ViewModel() {
    private val listDataCheckIn = MutableLiveData<ListUser>()
    private val loadingStateLiveData = MutableLiveData<Boolean>()
    private val pageSize = 50
    val listData: LiveData<ListUser> = listDataCheckIn

    private val _schoolName = MutableLiveData<String>()
    val schoolName: LiveData<String> = _schoolName

    private val _managerName = MutableLiveData<String>()
    val managerName: LiveData<String> = _managerName
    val currentHours = MutableLiveData<String>()
    val currentTime = MutableLiveData<String>()

    init {
        viewModelScope.launch {
            val user = dataStoreUtils.getUserPreferences()
            val classId = user?.data?.classX?.id
            if (classId == null || user.auth?.token == null) {
                networkRepository.register()
            }
            _schoolName.postValue("${user?.data?.classX?.name} - ${user?.data?.school?.name}")
            val listManager = mutableListOf<String>()
            user?.data?.classX?.user?.map {
                it.fullname?.let { name ->
                    listManager.add(name)
                }
            }
            _managerName.postValue(listManager.joinToString(", "))
            networkRepository.getListCheckIn(classId = classId ?: "").onSuccess {
                Timber.e("onSuccess::$it")
                listDataCheckIn.postValue(it)
            }.onFailure {
                Timber.e(it)
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            val localTime = LocalDateTime.now()
            currentHours.postValue(localTime.format(DateTimeFormatter.ofPattern("HH:mm")))
            currentTime.postValue(localTime.format(DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy")))
        }
    }

    /*   fun loadMore(selectedPosition: Int, spanCount: Int) {
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
       }*/

    /* private fun createPage(): List<Int> {
         return List(pageSize) { index -> list.size + index }
     }*/

}
