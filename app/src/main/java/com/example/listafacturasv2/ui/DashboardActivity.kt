package com.example.listafacturasv2.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listafacturasv2.R
import com.example.listafacturasv2.data.adapter.PracticaAdapter
import com.example.listafacturasv2.data.model.Practica
import com.example.listafacturasv2.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity(), PracticaAdapter.OnManagePracticaListener {

    private lateinit var binding: ActivityDashboardBinding
    private lateinit var adapter: PracticaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        getSupportActionBar()?.setDisplayShowTitleEnabled(false);

        initRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_dashboard, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.action_settings) {
            startActivity(Intent(this@DashboardActivity, SettingsActivity::class.java))
            true
        } else {
            false
        }
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