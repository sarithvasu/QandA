package com.heptagon.qanda.internal.interceptor

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

import com.heptagon.qanda.internal.QandAApplication
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException


@Suppress("DEPRECATION")
class ConnectivityInterceptorImpl :
    ConnectivityInterceptor {

    private val appContext = QandAApplication.qandAApplication

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isOnline())
            throw IOException("no internet")
        return chain.proceed(chain.request())
    }


    private fun isOnline(): Boolean {
        var result = false
        val cm = appContext!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cm?.run {
                cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                    result = when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                        else -> false
                    }
                }
            }
        } else {
            cm?.run {
                cm.activeNetworkInfo?.run {
                    if (type == ConnectivityManager.TYPE_WIFI) {
                        result = true
                    } else if (type == ConnectivityManager.TYPE_MOBILE) {
                        result = true
                    }
                }
            }
        }
        return result
    }
}


