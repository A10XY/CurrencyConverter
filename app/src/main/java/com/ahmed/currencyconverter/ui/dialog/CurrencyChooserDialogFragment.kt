package com.ahmed.currencyconverter.ui.dialog

import com.ahmed.currencyconverter.data.CurrencyType
import com.ahmed.currencyconverter.data.model.CurrencyInfo
import com.ahmed.currencyconverter.databinding.FragmentCurrencyChooserDialogBinding
import com.ahmed.currencyconverter.presenter.DialogPresenter
import com.ahmed.currencyconverter.ui.base.BaseDialogFragment

class CurrencyChooserDialogFragment(
    private val currencyChooserContract: CurrencyChooserContract,
    private val currencyType: CurrencyType,
    private val currencyCodesListToExclude: List<String>
    ) : BaseDialogFragment<FragmentCurrencyChooserDialogBinding>(), DialogView, CurrencyDialogClicksListener {

    private lateinit var currencyChooserDialogAdapter: CurrencyChooserDialogAdapter

    private val dialogPresenter = DialogPresenter(this)

    override fun bindingInflater(): FragmentCurrencyChooserDialogBinding = FragmentCurrencyChooserDialogBinding.inflate(layoutInflater)

    override fun setUp() {
        dialogPresenter.getCurrenciesList(requireContext(), currencyCodesListToExclude)
    }

    override fun onCurrenciesListProvided(currenciesList: List<CurrencyInfo>) {
        currencyChooserDialogAdapter = CurrencyChooserDialogAdapter(currenciesList, this)
        binding.currenciesRecyclerView.adapter = currencyChooserDialogAdapter
    }

    override fun onCurrencyItemClickListener(currencyInfo: CurrencyInfo) {
        if (currencyType == CurrencyType.FROM_CURRENCY) currencyChooserContract.onFromCurrencyChosen(currencyInfo)
        else currencyChooserContract.onToCurrencyChosen(currencyInfo)
        this.dialog?.hide()
    }
}