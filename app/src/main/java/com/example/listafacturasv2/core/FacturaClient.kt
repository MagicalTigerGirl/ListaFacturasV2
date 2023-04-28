package com.example.listafacturasv2.core

import co.infinum.retromock.Retromock
import com.example.listafacturasv2.data.network.FacturaService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FacturaClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://viewnextandroid.wiremockapi.cloud/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val retromock = Retromock.Builder()
        .retrofit(retrofit)
        .build()

    val service = retrofit.create(FacturaService::class.java)

    val serviceMock = retromock.create(FacturaService::class.java)
}