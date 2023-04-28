package com.example.listafacturasv2.data.model

class Practica(
    val name: String,
    val destination: Class<*>
) : Comparable<Practica> {
    override fun compareTo(other: Practica): Int {
        return this.name.compareTo(other.name)
    }
}