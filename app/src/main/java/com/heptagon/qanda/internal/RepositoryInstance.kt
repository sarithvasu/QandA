package com.heptagon.qanda.internal



import com.heptagon.qanda.exam.network.apiservice.TestApiService
import com.heptagon.qanda.exam.network.datasource.TestDataSourceImpl
import com.heptagon.qanda.exam.repository.TestRepositoryImpl

class RepositoryInstance {

    companion object {
        val testRepositoryInstance: TestRepositoryImpl by lazy {
            val apiService= AbstractRetrofit.buildService(TestApiService::class.java)
            val testDataSource = TestDataSourceImpl(apiService)
            TestRepositoryImpl(testDataSource)
        }

    }
}
