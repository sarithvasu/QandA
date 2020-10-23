package com.heptagon.qanda.internal

import android.app.Application
import android.content.Context


class QandAApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        qandAApplication = applicationContext

    }

    companion object {
        var qandAApplication: Context? = null
    }
}