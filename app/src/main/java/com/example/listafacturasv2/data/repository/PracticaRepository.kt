package com.example.listafacturasv2.data.repository

import com.example.listafacturasv2.data.model.Practica
import com.example.listafacturasv2.ui.practica1.activity.FacturaActivity
import com.example.listafacturasv2.ui.practica2.activity.SmartSolarActivity
import java.util.*
import kotlin.collections.ArrayList

class PracticaRepository() {

    companion object {
        var list: ArrayList<Practica>

        init {
            list = ArrayList()
            inicializar()
        }

        private fun inicializar() {
            list.add(Practica("Práctica 1", FacturaActivity::class.java))
            list.add(Practica("Práctica 2", SmartSolarActivity::class.java))
            Collections.sort(list)
        }
    }
}