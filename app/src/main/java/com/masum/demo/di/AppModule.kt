package com.masum.demo.di

import com.masum.demo.BuildConfig
import com.masum.demo.network.ApiService

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
open class AppModule {


    @Singleton
    @Provides
    open fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Singleton
    @Provides
    fun provideAuthApi(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

}