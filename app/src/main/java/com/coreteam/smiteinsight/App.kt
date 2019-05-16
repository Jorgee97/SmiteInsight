package com.coreteam.smiteinsight

import android.app.Application
import com.coreteam.smiteinsight.di.DaggerMainComponent
import com.coreteam.smiteinsight.di.MainComponent
import com.coreteam.smiteinsight.di.modules.AppModule
import com.coreteam.smiteinsight.di.modules.NetworkModule

class App: Application() {

    lateinit var mainComponent: MainComponent

    companion object {
        lateinit var instance: App private set
    }

    override fun onCreate() {
        super.onCreate()

        instance = this

        mainComponent = DaggerMainComponent.builder()
            .appModule(AppModule(applicationContext))
            .networkModule(NetworkModule())
            .build()
    }

}