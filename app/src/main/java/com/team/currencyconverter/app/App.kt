package com.team.currencyconverter.app

import android.app.Application
import com.team.currencyconverter.di.AppComponent
import com.team.currencyconverter.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .create()
    }
}