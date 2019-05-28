package com.plugow.exchangerateapp.di

import com.plugow.exchangerateapp.di.scope.ActivityScoped
import com.plugow.exchangerateapp.ui.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector
    internal abstract fun mainActivity() : MainActivity

}