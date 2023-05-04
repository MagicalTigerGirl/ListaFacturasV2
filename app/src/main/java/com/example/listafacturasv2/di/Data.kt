package com.example.listafacturasv2.di

import android.net.sip.SipErrorCode
import android.util.Log
import co.infinum.retromock.Retromock
import com.example.listafacturasv2.core.FacturaClient
import com.example.listafacturasv2.data.repository.FacturaRepository
import com.example.listafacturasv2.domain.GetFacturasUseCase
import com.example.listafacturasv2.utils.Constants
import com.example.listafacturasv2.viewmodel.FacturaViewModel
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.observer.*
import io.ktor.client.request.*
import io.ktor.http.*
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        Retromock.Builder()
            .retrofit(get())
            .build()
    }

    single {
        HttpClient(Android) {
            install(JsonFeature) {
                serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }

            install(JsonFeature) {
                val json = kotlinx.serialization.json.Json { ignoreUnknownKeys = true }
                serializer = KotlinxSerializer(json)
                acceptContentTypes = acceptContentTypes + ContentType.Any
            }

            install(DefaultRequest) {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }
        }
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