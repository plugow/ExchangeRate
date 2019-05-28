package com.plugow.exchangerateapp.di

import android.app.Application
import com.plugow.exchangerateapp.ExchangeRateApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [ApiModule::class, AndroidSupportInjectionModule::class, AppModule::class,
    ActivityBindingModule::class, ViewModelModule::class])
interface AppComponent : AndroidInjector<ExchangeRateApp> {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}