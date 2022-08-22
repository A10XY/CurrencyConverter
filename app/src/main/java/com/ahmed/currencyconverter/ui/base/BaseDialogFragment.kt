package com.ahmed.currencyconverter.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding

abstract class BaseDialogFragment<VB: ViewBinding> : DialogFragment() {
    private lateinit var _binding: VB
    protected val binding get() = _binding
    abstract fun bindingInflater(): VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater()
        setUp()
        return _binding.root
    }

    abstract fun setUp()
}