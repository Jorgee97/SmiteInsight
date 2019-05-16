package com.coreteam.smiteinsight.di.modules

import com.coreteam.smiteinsight.api.SmiteApi
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    private val BASE_URL = "http://api.smitegame.com/smiteapi.svc/"

    @Provides
    fun provideSmiteApi(retrofit: Retrofit): SmiteApi {
        return retrofit.create(SmiteApi::class.java)
    }

    @Provides
    fun provideRetrofit() : Retrofit {
        val clientBuilder = OkHttpClient.Builder()

        return Retrofit.Builder()
            .client(clientBuilder.build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

}