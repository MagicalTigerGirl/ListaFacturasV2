package com.example.listafacturasv2.ui.practica1.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.listafacturasv2.R
import com.example.listafacturasv2.databinding.ActivityFacturaBinding

class FacturaActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityFacturaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFacturaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        getSupportActionBar()?.setDisplayShowTitleEnabled(false);

        val topLevelDestination: MutableSet<Int> = HashSet()
        topLevelDestination.add(R.id.FiltrosFacturaFragment)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration.Builder(topLevelDestination).build()
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}