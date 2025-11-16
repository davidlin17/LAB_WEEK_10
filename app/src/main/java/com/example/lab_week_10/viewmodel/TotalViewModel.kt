package com.example.lab_week_10

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TotalViewModel : ViewModel() {

    val total = MutableLiveData<Int>(0)

    fun setTotal(v: Int) {
        total.value = v
    }
}
