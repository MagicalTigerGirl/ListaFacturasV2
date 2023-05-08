package com.example.listafacturasv2.ui.practica1.fragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.SeekBar
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.NavHostFragment
import com.example.listafacturasv2.R
import com.example.listafacturasv2.databinding.FragmentFiltrosFacturaBinding
import com.example.listafacturasv2.viewmodel.FacturaViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import java.text.SimpleDateFormat
import java.util.*


class FiltrosFacturaFragment : Fragment() {

    private var _binding: FragmentFiltrosFacturaBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModel<FacturaViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFiltrosFacturaBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initMenu()

        initViewModel()

        initDataPicker()

        initSeekBar()

        onAplicarFiltros()

        onEliminarFiltros()
    }

    private fun onAplicarFiltros() {
        binding.btnAplicarFiltros.setOnClickListener {

            viewModel.isFiltered = true

            // Fechas
            val fechaDesde = binding.btnDateDesde.text.toString()
            if (!viewModel.dateInit.equals(fechaDesde))
                viewModel.fechaDesde = fechaDesde

            val fechaHasta = binding.btnDateHasta.text.toString()
            if (!viewModel.dateInit.equals(fechaHasta))
                viewModel.fechaHasta = fechaHasta

            // Importe
            viewModel.importeMaxSelected = binding.sbImporte.progress

            viewModel.isChecked =
                viewModel.bPagadas.value == true || viewModel.bAnuladas.value == true || viewModel.bCuotaFija.value == true || viewModel.bPendientePagos.value == true || viewModel.bPlanPago.value == true

            viewModel.filter()

            NavHostFragment.findNavController(this).navigateUp()
        }
    }

    private fun onEliminarFiltros() {
        binding.btnEliminarFiltros.setOnClickListener(View.OnClickListener {

            // DatePicker
            viewModel.fechaDesde = viewModel.dateInit
            viewModel.fechaHasta = viewModel.dateInit
            binding.btnDateDesde.text = viewModel.fechaDesde
            binding.btnDateHasta.text = viewModel.fechaHasta
            binding.sbImporte.progress = 62

            // Checkbox
            binding.viewmodel?.bPagadas?.value = false
            binding.viewmodel?.bAnuladas?.value = false
            binding.viewmodel?.bCuotaFija?.value = false
            binding.viewmodel?.bPendientePagos?.value = false
            binding.viewmodel?.bPlanPago?.value = false
        })
    }

    private fun initMenu() {
        val menu: MenuHost = requireActivity()

        menu.addMenuProvider(object: MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_navigate_up, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {

                return if (menuItem.itemId == R.id.action_close) {
                    NavHostFragment.findNavController(this@FiltrosFacturaFragment).navigateUp()
                    true
                } else
                    false
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun initSeekBar() {
        val importeMax = 62
        binding.sbImporte.max = importeMax
        binding.tvImporteMax.text = importeMax.toString().plus(" €")

        val importeProgress = viewModel.importeMaxSelected
        binding.sbImporte.progress = importeProgress
        binding.tvImporte.text = getString(R.string.factura_filtros_importe_rango, importeProgress.toString())

        binding.sbImporte.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onStopTrackingTouch(seekBar: SeekBar) {
                    // Cuando se deja de mover el SeekBar
                }
                override fun onStartTrackingTouch(seekBar: SeekBar) {
                    // Cuando se comienza a mover el SeekBar
                }
                override fun onProgressChanged(
                    seekBar: SeekBar, progress: Int,
                    fromUser: Boolean
                ) {
                    binding.tvImporte.text = getString(R.string.factura_filtros_importe_rango, progress.toString())
                }
            }
        )
    }

    private fun initDataPicker() {
        setupDateButton(binding.btnDateDesde)
        setupDateButton(binding.btnDateHasta, binding.btnDateDesde)
    }

    private fun initViewModel() {
        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        // DatePicker
        binding.btnDateDesde.text = viewModel.fechaDesde
        binding.btnDateHasta.text = viewModel.fechaHasta
    }

    private fun setupDateButton(button: Button, minDateButton: Button? = null) {
        button.setOnClickListener {
            val c = Calendar.getInstance()

            val popup = DatePickerDialog(
                requireContext(),
                { _, year, monthOfYear, dayOfMonth ->
                    val days = if (dayOfMonth < 10) "0${dayOfMonth}" else "${dayOfMonth}"
                    val month = monthOfYear + 1
                    val months = if (month < 10) "0${month}" else "${month}"
                    button.text = getString(R.string.factura_filtros_fecha, days, months, year.toString())
                },
                c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH)
            )

            if (minDateButton != null) {
                if (!this.getString(R.string.date_inicial).equals(minDateButton.text.toString()))
                    popup.datePicker.minDate = SimpleDateFormat("dd/MM/yyyy").parse(minDateButton.text.toString()).time
            }

            if (minDateButton == null)
                popup.datePicker.maxDate = Calendar.getInstance().timeInMillis

            popup.show()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}