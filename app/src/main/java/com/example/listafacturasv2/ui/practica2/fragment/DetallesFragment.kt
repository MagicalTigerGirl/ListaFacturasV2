package com.example.listafacturasv2.ui.practica2.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.listafacturasv2.R
import com.example.listafacturasv2.databinding.FragmentDetallesBinding


class DetallesFragment : Fragment() {

    private var _binding: FragmentDetallesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetallesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tilEstadoSolicitud.setEndIconOnClickListener {
            showPopup()
        }
    }

    private fun showPopup() {
        val layoutInflater = layoutInflater

        val customView: View = layoutInflater.inflate(R.layout.popup_estado, null)

        val button = customView.findViewById(R.id.btnAceptar) as Button

        val dialog = AlertDialog.Builder(requireContext())
            .setView(customView)
            .create()

        button.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}