package com.heptagon.qanda.exam.network.datasource

import androidx.lifecycle.LiveData
import com.heptagon.qanda.exam.network.response.TestResponse

interface TestDataSource {


    val downloadedTestInfo : LiveData<TestResponse>
    suspend fun getTest(queryParameterMap:  Map<String, String>)





}