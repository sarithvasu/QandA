package com.heptagon.qanda.exam.network.apiservice

import com.heptagon.qanda.exam.network.response.TestResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface TestApiService {
    @GET(value = "get/cpOAeecWGa")
    fun getTestAsync(@QueryMap queryParameterMap: Map<String, String>): Deferred<TestResponse>
}