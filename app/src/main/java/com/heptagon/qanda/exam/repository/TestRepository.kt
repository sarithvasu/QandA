package com.heptagon.qanda.exam.repository

import androidx.lifecycle.LiveData
import com.heptagon.qanda.exam.network.response.TestResponse


interface TestRepository {
    suspend fun getTest(queryParameterMap: Map<String, String>): LiveData<out TestResponse>
}