package com.example.listafacturasv2.domain

import com.example.listafacturasv2.data.repository.FacturaRepository
import com.example.listafacturasv2.data.model.Factura

class GetFacturasUseCase {

    suspend operator fun invoke(): List<Factura> {

        val list = FacturaRepository.getAllFacturas()

        return if(list.isNotEmpty()) {
            FacturaRepository.deleteAllRoom()
            FacturaRepository.insertAllRoom(list)
            FacturaRepository.importeMaximo = list.stream().max(Comparator.comparing(Factura::importeOrdenacion)).get().importeOrdenacion+1

            list
        } else {
            FacturaRepository.getAllFacturasRoom()
        }
    }
}