package com.team.currencyconverter.di

import com.team.currencyconverter.presentation.activity.MainActivity
import dagger.Component

@Component(modules = [DataModule::class, DomainModule::class, PresentationModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)
}