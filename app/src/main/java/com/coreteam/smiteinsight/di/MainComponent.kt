package com.coreteam.smiteinsight.di

import com.coreteam.smiteinsight.MainActivity
import com.coreteam.smiteinsight.di.modules.AppModule
import com.coreteam.smiteinsight.di.modules.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,NetworkModule::class])
interface MainComponent {
    fun inject(mainActivity: MainActivity)
}