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

        fun isNetworkAvailable(): Boolean {
            val connectivity = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            // Si la red es nula devuelve false
            val network = connectivity.activeNetwork ?: return false

            // Si las capacidades de red son nulas devuelve false
            val actNetwork = connectivity.getNetworkCapabilities(network) ?: return false

            return when {
                // Si está conectado a través de Wifi devuelve true
                actNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    true
                }
                // Si está conectado a través de datos móviles devuelve true
                actNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    true
                }
                else -> {
                    false
                }
            }
        }
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