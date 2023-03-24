package com.app.worldkids.ui.screen.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.worldkids.data.pre.DataStoreUtils
import com.app.worldkids.data.repository.NetworkRepository
import com.app.worldkids.model.CheckInStatus
import com.app.worldkids.model.response.ListUser
import com.app.worldkids.model.response.Register
import com.app.worldkids.model.response.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainViewModel(private val networkRepository: NetworkRepository, dataStoreUtils: DataStoreUtils) : ViewModel() {
    private val listDataCheckIn = MutableLiveData<ListUser>()
    val listData: LiveData<ListUser> = listDataCheckIn

    private val _schoolName = MutableLiveData<String>()
    val schoolName: LiveData<String> = _schoolName

    private val _managerName = MutableLiveData<String>()
    val managerName: LiveData<String> = _managerName

    private val _countCheckIn = MutableLiveData<CheckInStatus>()
    val countCheckIn: LiveData<CheckInStatus> = _countCheckIn

    private val _changeStatus = MutableLiveData<String?>()
    val changeStatus: LiveData<String?> = _changeStatus

    val currentHours = MutableLiveData<String>()
    val currentTime = MutableLiveData<String>()

    init {
        viewModelScope.launch {
            val user = dataStoreUtils.getUserPreferences()
            val classId = user?.data?.classX?.id
            if (classId == null || user.auth?.token == null) {
                networkRepository.register()
            }
            initNameClass(user = user)
            initDataClass(classId = classId ?: "")
            initStatusClass(classId = classId ?: "")
        }
        viewModelScope.launch(Dispatchers.IO) {
            val localTime = LocalDateTime.now()
            currentHours.postValue(localTime.format(DateTimeFormatter.ofPattern("HH:mm")))
            currentTime.postValue(localTime.format(DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy")))
        }
    }

    private fun initNameClass(user: Register?) {
        _schoolName.postValue("${user?.data?.classX?.name} - ${user?.data?.school?.name}")
        val listManager = mutableListOf<String>()
        user?.data?.classX?.user?.map {
            it.fullname?.let { name ->
                listManager.add(name)
            }
        }
        _managerName.postValue(listManager.joinToString(", "))
    }

    private suspend fun initDataClass(classId: String?) {
        networkRepository.getListCheckIn(classId = classId ?: "").onSuccess {
            Timber.e("onSuccess::$it")
            listDataCheckIn.postValue(it)
        }.onFailure {
            Timber.e(it)
        }
    }

    private suspend fun initStatusClass(classId: String?) {
        networkRepository.statusReport(classId = classId ?: "").onSuccess {
            Timber.e("onSuccess::$it")
            _countCheckIn.postValue(it)
        }.onFailure {
            Timber.e(it)
        }
    }

    fun updateStatus(clientId: String?, status: String) {
        viewModelScope.launch {
            networkRepository.changeStatus(clientId = clientId ?: "", status = status).onSuccess {
                Timber.e("onSuccess::$it")
                _changeStatus.postValue(null)
            }.onFailure {
                _changeStatus.postValue(it.message)
                Timber.e(it)
            }
        }
    }
}
