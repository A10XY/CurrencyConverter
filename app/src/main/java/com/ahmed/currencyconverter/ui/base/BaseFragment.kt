package com.ahmed.currencyconverter.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB: ViewBinding> : Fragment() {
    private lateinit var _binding: VB
    protected val binding get() = _binding
    abstract fun bindingInflater(): VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = bindingInflater()
        setup()
        return _binding.root
    }

    abstract fun setup()
}