package com.masum.demo.di

import android.app.Application
import com.masum.demo.common.MyApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,AppModule::class,ActivityBindingModule::class])
open interface AppComponent :AndroidInjector<MyApplication> {

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun bindApplicationInstance(application: Application) :Builder

        fun build(): AppComponent
    }
}