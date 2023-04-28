package com.example.listafacturasv2.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listafacturasv2.data.adapter.FacturaAdapter
import com.example.listafacturasv2.data.adapter.PracticaAdapter
import com.example.listafacturasv2.data.model.Practica
import com.example.listafacturasv2.databinding.ActivityDashboardBinding
import com.example.listafacturasv2.ui.practica1.activity.FacturaActivity

class DashboardActivity : AppCompatActivity(), PracticaAdapter.OnManagePracticaListener {

    private lateinit var binding: ActivityDashboardBinding
    private lateinit var adapter: PracticaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.rvPracticas.setHasFixedSize(true)
        binding.rvPracticas.layoutManager = LinearLayoutManager(this)
        adapter = PracticaAdapter(this)
        binding.rvPracticas.adapter = adapter
    }

    override fun onNavigationPractica(practica: Practica) {
        val intent = Intent(this@DashboardActivity, practica.destination)
        startActivity(intent)
    }
}