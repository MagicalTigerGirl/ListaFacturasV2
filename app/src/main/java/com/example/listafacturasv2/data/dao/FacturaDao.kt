package com.example.listafacturasv2.data.dao

import androidx.room.*
import com.example.listafacturasv2.data.model.Factura
import com.example.listafacturasv2.data.model.FacturaRoom

@Dao
interface FacturaDao {
    @Insert
    fun insert(facturas: List<FacturaRoom>)

    @Update
    fun update(factura: FacturaRoom?)

    @Delete
    fun delete(factura: FacturaRoom?)

    @Query("DELETE FROM factura")
    fun deleteAll()

    @Query("SELECT * FROM factura")
    fun select(): List<FacturaRoom>

    @Query("SELECT * FROM factura WHERE importeOrdenacion < :importe AND substr(fecha, 7, 4) || '-' || substr(fecha, 4, 2) || '-' || substr(fecha, 1, 2)" +
            " BETWEEN substr(:desde, 7, 4) || '-' || substr(:desde, 4, 2) || '-' || substr(:desde, 1, 2)" +
            " AND substr(:hasta, 7, 4) || '-' || substr(:hasta, 4, 2) || '-' || substr(:hasta, 1, 2)")
    fun selectFilteredAll(importe: Int, desde: String, hasta: String): List<FacturaRoom>

    @Query("SELECT * FROM factura WHERE importeOrdenacion < :importe AND date(substr(fecha, 7, 4) || '-' || substr(fecha, 4, 2) || '-' || substr(fecha, 1, 2))" +
            " < substr(:hasta, 7, 4) || '-' || substr(:hasta, 4, 2) || '-' || substr(:hasta, 1, 2)")
    fun selectFilteredAllHasta(importe: Int, hasta: String): List<FacturaRoom>

    @Query("SELECT * FROM factura WHERE importeOrdenacion < :importe")
    fun selectFilteredImporte(importe: Int): List<FacturaRoom>
}