package com.kotlincoroutinesbasics.viewmodel_livedata_fragmet_communication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class A99_ViewModel : ViewModel() {

    private val data = MutableLiveData<String>()
    fun setData(setData: String) {
        data.value = setData
    }

    fun getData() : LiveData<String>{
        return data
    }


}