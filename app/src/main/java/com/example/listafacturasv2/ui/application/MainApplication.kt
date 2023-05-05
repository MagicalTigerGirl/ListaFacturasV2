package com.example.listafacturasv2.ui.application

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.listafacturas.data.FacturasDatabase
import com.example.listafacturasv2.di.dataModule
import com.example.listafacturasv2.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class MainApplication(): Application() {

    companion object {
        lateinit var context: MainApplication
    }

    override fun onCreate() {
        super.onCreate()
        context = this

        FacturasDatabase.create(applicationContext)

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(dataModule, viewModelModule)
        }
    }
}