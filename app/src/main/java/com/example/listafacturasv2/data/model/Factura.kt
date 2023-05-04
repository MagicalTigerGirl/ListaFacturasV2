package com.example.listafacturasv2.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Factura (
    val descEstado: String,
    val importeOrdenacion: Double,
    val fecha: String
)