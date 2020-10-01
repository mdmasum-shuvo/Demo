package com.masum.demo.di

import com.masum.demo.view.DetailActivity
import com.masum.demo.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [FactViewmodelModule::class])
    abstract fun MainActivityBinding(): MainActivity

    @ContributesAndroidInjector()
    abstract fun detailActivity(): DetailActivity


}