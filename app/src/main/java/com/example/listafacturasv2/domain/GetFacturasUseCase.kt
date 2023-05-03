package com.example.listafacturasv2.domain

import com.example.listafacturasv2.data.model.Factura
import com.example.listafacturasv2.data.repository.FacturaRepository

class GetFacturasUseCase(private val repository: FacturaRepository) {

    suspend operator fun invoke(): List<Factura> {

        val list = repository.getAllFacturas()

        return if(list.isNotEmpty()) {
            repository.deleteAllRoom()
            repository.insertAllRoom(list)
            repository.importeMaximo = list.stream().max(Comparator.comparing(Factura::importeOrdenacion)).get().importeOrdenacion+1
            list
        } else {
            repository.getAllFacturasRoom()
        }
    }
}