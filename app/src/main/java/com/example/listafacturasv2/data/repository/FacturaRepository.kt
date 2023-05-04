package com.example.listafacturasv2.data.repository

import com.example.listafacturas.data.FacturasDatabase
import com.example.listafacturasv2.core.FacturaClient
import com.example.listafacturasv2.data.dao.FacturaDao
import com.example.listafacturasv2.data.model.Factura
import com.example.listafacturasv2.data.model.FacturaRoom
import com.example.listafacturasv2.preferences.PreferencesManager
import com.example.listafacturasv2.ui.application.MainApplication
import io.ktor.client.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class FacturaRepository(private val client: FacturaClient, private val httpClient: HttpClient) {

    private var facturaDao: FacturaDao? = null
    var importeMaximo: Double = 0.0
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO

    init {
        facturaDao = FacturasDatabase.dataBase?.facturaDao()
    }

    suspend fun getAllFacturas(): List<Factura> {

        val httpRequest = PreferencesManager(MainApplication.context).getHttpRequest()

        return withContext(dispatcher) {
            val result: List<Factura> = when (httpRequest) {
                "RETROFIT" -> { client.service.listFacturas().body()?.facturas ?: emptyList() }
                "RETROMOCK" -> { client.serviceMock.listFacturas().body()?.facturas ?: emptyList() }
                "KTOR" -> { client.client().facturas }
                else -> {
                    emptyList()
                }
            }
            result
        }

    }

    suspend fun getAllFacturasRoom(): List<Factura> {
        return withContext(dispatcher) {
            facturaDao!!.select().map { Factura(it.descEstado, it.importeOrdenacion, it.fecha) }
        }
    }

    suspend fun insertAllRoom(list: List<Factura>) {
        withContext(dispatcher) {
            FacturasDatabase.dataBase!!.facturaDao()?.insert(list.map { FacturaRoom(0, it.descEstado, it.importeOrdenacion, it.fecha) })
        }
    }

    suspend fun deleteAllRoom() {
        withContext(dispatcher) {
            FacturasDatabase.dataBase!!.facturaDao()?.deleteAll()
        }
    }

    suspend fun getListFilteredAll(importe: Int, desde: String, hasta: String): List<Factura> {
        return withContext(dispatcher) {
            val list: List<Factura>? = FacturasDatabase.dataBase!!.facturaDao()?.selectFilteredAll(importe, desde, hasta)
                    ?.map { Factura(it.descEstado, it.importeOrdenacion, it.fecha) }
            list ?: emptyList()
        }
    }

    suspend fun getListFilteredAllHasta(importe: Int, hasta: String): List<Factura> {
        return withContext(dispatcher) {
            val list: List<Factura>? = FacturasDatabase.dataBase!!.facturaDao()?.selectFilteredAllHasta(importe, hasta)
                    ?.map { Factura(it.descEstado, it.importeOrdenacion, it.fecha) }
            list ?: emptyList()
        }
    }

    suspend fun getListFilteredImporte(importe: Int): List<Factura> {
        return withContext(dispatcher) {
            val list: List<Factura>? = FacturasDatabase.dataBase!!.facturaDao()?.selectFilteredImporte(importe)
                    ?.map { Factura(it.descEstado, it.importeOrdenacion, it.fecha) }
            list ?: emptyList()
        }
    }
}