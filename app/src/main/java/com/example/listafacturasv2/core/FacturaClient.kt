package com.example.listafacturasv2.core

import co.infinum.retromock.Retromock
import com.example.listafacturasv2.data.network.FacturaService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FacturaClient(retrofit: Retrofit, retromock: Retromock) {

    val service = retrofit.create(FacturaService::class.java)

    val serviceMock = retromock.create(FacturaService::class.java)
}