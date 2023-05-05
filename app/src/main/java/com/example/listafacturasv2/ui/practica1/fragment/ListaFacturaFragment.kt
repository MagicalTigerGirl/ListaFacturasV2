package com.example.listafacturasv2.ui.practica1.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.NavHostFragment
import com.example.listafacturas.viewmodel.StateDataList
import com.example.listafacturasv2.R
import com.example.listafacturasv2.data.adapter.FacturaAdapter
import com.example.listafacturasv2.data.model.Factura
import com.example.listafacturasv2.databinding.FragmentListaFacturaBinding
import com.example.listafacturasv2.ui.basedialog.BaseDialogFragment
import com.example.listafacturasv2.viewmodel.FacturaViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel


class ListaFacturaFragment : Fragment(), FacturaAdapter.OnManageFacturaListener {

    private var _binding: FragmentListaFacturaBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: FacturaAdapter
    private val viewModel by activityViewModel<FacturaViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentListaFacturaBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initMenu()

        initRecyclerView()

        initViewModel()
    }

    private fun initMenu() {
        val menu: MenuHost = requireActivity()

        menu.addMenuProvider(object: MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_main, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return if (menuItem.itemId == R.id.action_filter) {
                    NavHostFragment.findNavController(this@ListaFacturaFragment).navigate(R.id.action_ListaFacturaFragment_to_FiltrosFacturaFragment)
                    true
                } else
                    false
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun initRecyclerView() {
        adapter = FacturaAdapter(this)
        binding.rvFacturas.adapter = adapter
    }

    private fun initViewModel() {
        viewModel.liveDataList.observe(viewLifecycleOwner) {
            when (it.state) {
                StateDataList.DataState.LOADING -> {
                    binding.prbloading.visibility = View.VISIBLE
                }
                StateDataList.DataState.SUCCESS -> {
                    adapter.update(it.data)
                    binding.prbloading.visibility = View.INVISIBLE
                    binding.tvNoData.visibility = View.INVISIBLE
                }
                StateDataList.DataState.NODATA -> {
                    binding.tvNoData.visibility = View.VISIBLE
                }
                else -> {
                    StateDataList.DataState.ERROR
                }
            }
        }

        if (viewModel.isFiltered)
            viewModel.getDataList()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onShowFactura(factura: Factura) {
        val bundle = Bundle()
        bundle.putString(BaseDialogFragment.TITLE, "Información (${factura.fecha})")
        bundle.putString(BaseDialogFragment.MESSAGE, "Esta funcionalidad aún no está disponible")

        NavHostFragment.findNavController(this).navigate(R.id.action_ListaFacturaFragment_to_baseDialogFragment, bundle)
    }
}