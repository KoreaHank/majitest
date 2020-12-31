package com.getmaji.majitest

import android.app.Application
open class MajiApp : Application() {

    companion object {
        lateinit open var instance: MajiApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}