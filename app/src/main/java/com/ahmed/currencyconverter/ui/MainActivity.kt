package com.ahmed.currencyconverter.ui

import android.view.LayoutInflater
import com.ahmed.currencyconverter.R
import com.ahmed.currencyconverter.databinding.ActivityMainBinding
import com.ahmed.currencyconverter.ui.base.BaseActivity
import com.ahmed.currencyconverter.ui.home.HomeFragment

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val inflate: (LayoutInflater) -> ActivityMainBinding = ActivityMainBinding::inflate

    override fun setup() {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container_view_all, HomeFragment()).commit()
    }
}