package com.masum.demo.di

import androidx.lifecycle.ViewModel
import com.masum.demo.viewmodel.FactDataViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FactViewmodelModule {

    @Binds
    @IntoMap
    @ViewModelKey(FactDataViewModel::class)
    abstract fun bindAcademyViewmodel(factDataViewModel: FactDataViewModel): ViewModel
}