package com.example.listafacturasv2.ui.practica2.activity.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.listafacturasv2.R
import com.example.listafacturasv2.ui.practica2.fragment.DetallesFragment
import com.example.listafacturasv2.ui.practica2.fragment.EnergiaFragment
import com.example.listafacturasv2.ui.practica2.fragment.MiInstalacionFragment

private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2,
    R.string.tab_text_3
)

class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                MiInstalacionFragment()
            }
            1 -> {
                EnergiaFragment()
            }
            2 -> {
                DetallesFragment()
            }
            else -> {PlaceholderFragment.newInstance(position + 1)}
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return TAB_TITLES.size
    }
}