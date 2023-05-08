package com.example.listafacturasv2.domain

import com.example.listafacturasv2.data.model.Factura
import com.example.listafacturasv2.data.repository.FacturaRepository

class GetFacturasUseCase(private val repository: FacturaRepository) {

    suspend operator fun invoke(): List<Factura> {

        var list = repository.getAllFacturas()

        if(list.isNotEmpty()) {
            repository.deleteAllRoom()
            repository.insertAllRoom(list)
        } else {
            list = repository.getAllFacturasRoom()
        }

        if (list.isNotEmpty()) {
            repository.importeMaximo = list.stream().max(Comparator.comparing(Factura::importeOrdenacion)).get().importeOrdenacion+1
        }

        return list
    }
}