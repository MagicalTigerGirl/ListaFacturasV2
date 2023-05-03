package com.example.listafacturasv2.di

import co.infinum.retromock.Retromock
import com.example.listafacturasv2.core.FacturaClient
import com.example.listafacturasv2.data.repository.FacturaRepository
import com.example.listafacturasv2.domain.GetFacturasUseCase
import com.example.listafacturasv2.viewmodel.FacturaViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://viewnextandroid.mocklab.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        Retromock.Builder()
            .retrofit(get())
            .build()
    }

    single {

    }

    factoryOf(::FacturaClient)
    factoryOf(::GetFacturasUseCase)
    factoryOf(::FacturaRepository)
}

val viewModelModule = module {
    viewModelOf(::FacturaViewModel)
}

enum class Qualifier {
    ApiEndpoint
}