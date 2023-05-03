package com.example.listafacturas.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.example.listafacturasv2.data.dao.FacturaDao
import com.example.listafacturasv2.data.model.Factura

@Database(entities = [Factura::class], version = 6)
abstract class FacturasDatabase : RoomDatabase() {

    abstract fun facturaDao(): FacturaDao?

    companion object {
        @Volatile
        var dataBase: FacturasDatabase? = null

        fun create(context: Context) {
            if (dataBase == null) {
                synchronized(FacturasDatabase::class.java) {
                    if (dataBase == null) {
                        dataBase = databaseBuilder(
                            context.applicationContext,
                            FacturasDatabase::class.java, "facturasdatabase"
                        )
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
        }
    }
}