package com.jobplanet.test.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jobplanet.test.network.RetrofitModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DataListViewModel(app : Application) : AndroidViewModel(app) {

    var mListData : MutableLiveData<ArrayList<Any>> = MutableLiveData()

    val appContext = app

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                var response = RetrofitModule.getInstance().getTestData().execute()
                if(response.isSuccessful) {
                    mListData.postValue(response.body())
                }

            }
        }
    }
}