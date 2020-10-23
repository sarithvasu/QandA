package com.heptagon.qanda.exam.repository

import androidx.lifecycle.LiveData
import com.heptagon.qanda.exam.network.datasource.TestDataSource
import com.heptagon.qanda.exam.network.response.TestResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TestRepositoryImpl (private val testDataSource: TestDataSource) : TestRepository {

    override suspend fun getTest(queryParameterMap: Map<String, String>): LiveData<out TestResponse> {
        return withContext(Dispatchers.IO) {
            testDataSource.getTest(queryParameterMap)
            return@withContext testDataSource.downloadedTestInfo
        }
    }


}