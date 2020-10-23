package com.heptagon.qanda.exam.network.datasource

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.heptagon.qanda.exam.network.apiservice.TestApiService
import com.heptagon.qanda.internal.NoConnectivityException
import com.heptagon.qanda.exam.network.response.TestResponse

class TestDataSourceImpl(private val testApiService: TestApiService) : TestDataSource {



    private val _downloadedTestInfo = MutableLiveData<TestResponse>()
    override val downloadedTestInfo: LiveData<TestResponse>
        get() = _downloadedTestInfo

    override suspend fun getTest(queryParameterMap: Map<String, String>) {
        try {
            val fetchedTestInfo = testApiService.getTestAsync(queryParameterMap).await()
            _downloadedTestInfo.postValue(fetchedTestInfo)
        }catch (ex: NoConnectivityException){
            Log.e("Connectivity", "No internet connection.", ex)
        }
    }


}