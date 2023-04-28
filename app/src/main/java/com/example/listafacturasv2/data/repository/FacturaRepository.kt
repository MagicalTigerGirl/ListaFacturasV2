package com.example.listafacturasv2.data.repository

import com.example.listafacturas.data.FacturasDatabase
import com.example.listafacturasv2.core.FacturaClient
import com.example.listafacturasv2.data.dao.FacturaDao
import com.example.listafacturasv2.data.model.Factura
import com.example.listafacturasv2.ui.application.MainApplication
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


object FacturaRepository {

    private var facturaDao: FacturaDao? = null
    var importeMaximo: Double = 0.0
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO

    init {
        facturaDao = FacturasDatabase.dataBase?.facturaDao()
    }

    suspend fun getAllFacturas(): List<Factura> {

        return withContext(dispatcher) {
            val result: List<Factura>
            result = if (MainApplication.isNetworkAvailable()) {
                FacturaClient.service.listFacturas().body()?.facturas ?: emptyList()
            } else
                FacturaClient.serviceMock.listFacturas().body()?.facturas ?: emptyList()

            result
        }

    }

    fun getAllFacturasRoom(): List<Factura> {
        return facturaDao!!.select()
    }

    suspend fun insertAllRoom(list: List<Factura>) {
        withContext(dispatcher) {
            FacturasDatabase.dataBase!!.facturaDao()?.insert(list)
        }
    }

    suspend fun deleteAllRoom() {
        withContext(dispatcher) {
            FacturasDatabase.dataBase!!.facturaDao()?.deleteAll()
        }
    }

    suspend fun getListFilteredAll(importe: Int, desde: String, hasta: String): List<Factura> {
        return withContext(dispatcher) {
            val list: List<Factura>? =
                FacturasDatabase.dataBase!!.facturaDao()?.selectFilteredAll(importe, desde, hasta)
            list ?: emptyList()
        }
    }

    suspend fun getListFilteredAllHasta(importe: Int, hasta: String): List<Factura> {
        return withContext(dispatcher) {
            val list: List<Factura>? =
                FacturasDatabase.dataBase!!.facturaDao()?.selectFilteredAllHasta(importe, hasta)
            list ?: emptyList()
        }
    }

    suspend fun getListFilteredImporte(importe: Int): List<Factura> {
        return withContext(dispatcher) {
            val list: List<Factura>? =
                FacturasDatabase.dataBase!!.facturaDao()?.selectFilteredImporte(importe)
            list ?: emptyList()
        }
    }
}