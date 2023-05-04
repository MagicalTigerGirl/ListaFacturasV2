package com.example.listafacturasv2.data.model

import kotlinx.serialization.Serializable
@Serializable
data class FacturaResult(
    val numFacturas: Int,
    val facturas: List<Factura>
)