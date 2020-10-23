package com.heptagon.qanda.internal

import com.heptagon.qanda.internal.interceptor.ConnectivityInterceptorImpl
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class AbstractRetrofit {

    companion object {
        @Volatile
        private var instance: Retrofit? = null

        private const val BASE_URL="http://www.json-generator.com/api/json/"


        private val LOCK = Any()

        operator fun invoke() = instance ?: synchronized(LOCK) {
            instance ?: buildRetrofit().also { instance = it }
        }

        private fun buildRetrofit(): Retrofit {
            val headerInterceptor = Interceptor { chain ->
                val request = chain.request()
                    .newBuilder()
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .header("token", "pwd1234$!!")
                    .build()
                return@Interceptor chain.proceed(request)
            }



             val httpLoggingInterceptor= HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(ConnectivityInterceptorImpl())
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(headerInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        fun <T> buildService(serviceType: Class<T>): T {
            return invoke().create(serviceType)
        }
    }
}