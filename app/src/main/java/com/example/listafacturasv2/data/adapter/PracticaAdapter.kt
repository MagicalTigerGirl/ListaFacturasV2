package com.example.listafacturasv2.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listafacturasv2.R
import com.example.listafacturasv2.data.model.Practica
import com.example.listafacturasv2.data.repository.PracticaRepository
import com.example.listafacturasv2.databinding.ItemPracticaBinding

class PracticaAdapter(listener: OnManagePracticaListener): RecyclerView.Adapter<PracticaAdapter.ViewHolder>() {

    var practicas: ArrayList<Practica> = PracticaRepository.list
    var listener: OnManagePracticaListener = listener

    interface OnManagePracticaListener {
        fun onNavigationPractica(practica: Practica)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_practica, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(practicas[position], listener)
    }

    override fun getItemCount(): Int {
        return practicas.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val binding = ItemPracticaBinding.bind(itemView)

        fun bind(practica: Practica, listener: OnManagePracticaListener) {
            binding.tvName.text = practica.name

            binding.imgButton.setOnClickListener { listener.onNavigationPractica(practica) }
        }
    }
}