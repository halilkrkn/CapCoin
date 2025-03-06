package com.halilkrkn.capcoin

import android.app.Application
import com.halilkrkn.capcoin.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CapCoinApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CapCoinApp)
            androidLogger()
            modules(appModule)
        }
    }
}