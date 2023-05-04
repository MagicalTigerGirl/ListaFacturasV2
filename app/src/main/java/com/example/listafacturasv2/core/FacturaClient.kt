package com.example.listafacturasv2.core

import co.infinum.retromock.Retromock
import com.example.listafacturasv2.data.model.FacturaResult
import com.example.listafacturasv2.data.network.FacturaService
import com.example.listafacturasv2.utils.Constants
import io.ktor.client.*
import io.ktor.client.features.*
import retrofit2.Retrofit
import io.ktor.client.request.*

class FacturaClient(retrofit: Retrofit, retromock: Retromock, private val httpClient: HttpClient) {

    val service = retrofit.create(FacturaService::class.java)

    val serviceMock = retromock.create(FacturaService::class.java)

    suspend fun client(): FacturaResult {
          return httpClient.get {
            url(Constants.URL)
        }
    }
}