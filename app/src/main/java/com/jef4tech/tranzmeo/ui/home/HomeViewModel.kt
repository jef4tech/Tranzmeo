package com.jef4tech.tranzmeo.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jef4tech.tranzmeo.models.UsersResponse
import com.jef4tech.tranzmeo.network.RestApiImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
    val loader = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()
    val userList = MutableLiveData<UsersResponse>()

    fun getUserList(limit: Int, skip: Int) {
        loader.value = true
        viewModelScope.launch {
            val response = RestApiImpl.getUserList(limit,skip)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    userList.postValue(response.body())
                    loader.value = false
                } else {
                    onError("Error ${response.message()}")
                }
            }
        }
    }
    private fun onError(message: String) {
        errorMessage.value = message
        loader.value = false
    }
}