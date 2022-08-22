package com.ahmed.currencyconverter.ui.home

import android.view.View
import androidx.lifecycle.lifecycleScope
import com.ahmed.currencyconverter.R
import com.ahmed.currencyconverter.data.CurrencyType
import com.ahmed.currencyconverter.data.model.CurrencyConvertResponse
import com.ahmed.currencyconverter.data.model.CurrencyInfo
import com.ahmed.currencyconverter.databinding.FragmentHomeBinding
import com.ahmed.currencyconverter.presenter.HomePresenter
import com.ahmed.currencyconverter.ui.base.BaseFragment
import com.ahmed.currencyconverter.ui.dialog.CurrencyChooserContract
import com.ahmed.currencyconverter.ui.dialog.CurrencyChooserDialogFragment
import com.ahmed.currencyconverter.util.Constants
import com.ahmed.currencyconverter.util.showShortToast

class HomeFragment : BaseFragment<FragmentHomeBinding>(), HomeView, CurrencyChooserContract {
    private val homePresenter = HomePresenter(this, lifecycleScope)
    override fun bindingInflater(): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)

    private var fromCurrencyCode: String = Constants.DEFAULT_FROM_CURRENCY_CODE
    private var toCurrencyCode: String = Constants.DEFAULT_TO_CURRENCY_CODE

    override fun setup() {
        binding.run {
            setUpFromCurrencyButton(Constants.DEFAULT_FROM_CURRENCY)
            setUpToCurrencyButton(Constants.DEFAULT_TO_CURRENCY)
            convertButton.setOnClickListener {
                convertCurrency()
            }
        }
    }

    private fun convertCurrency() {
        val amount = binding.amountEditText.text.toString()
        homePresenter.convertCurrencyUsingApi(fromCurrencyCode, toCurrencyCode, amount)
    }

    private fun setUpFromCurrencyButton(buttonText: String) {
        binding.fromCurrencyButton.run {
            text = buttonText
            setOnClickListener {
                showCurrencyChooserDialog(CurrencyType.FROM_CURRENCY, listOf(fromCurrencyCode, toCurrencyCode))
            }
        }
    }

    private fun setUpToCurrencyButton(buttonText: String) {
        binding.toCurrencyButton.run {
            text = buttonText
            setOnClickListener {
                showCurrencyChooserDialog(CurrencyType.TO_CURRENCY, listOf(fromCurrencyCode, toCurrencyCode))
            }
        }
    }

    private fun showCurrencyChooserDialog(currencyType: CurrencyType, currencyCodesListToExclude: List<String>) {
        val dialog = CurrencyChooserDialogFragment(this@HomeFragment, currencyType, currencyCodesListToExclude)
        dialog.show(requireActivity().supportFragmentManager, Constants.DIALOG_TAG)
    }

    override fun onCurrencyApiRequestInProgress() {
        binding.run {
            exchangeRateTextview.visibility = View.INVISIBLE
            exchangeRateTextview.visibility = View.INVISIBLE
            progressBar.visibility = View.VISIBLE
        }
    }

    override fun onCurrencyApiResponseFail(message: String) {
        binding.run {
            progressBar.visibility = View.INVISIBLE
            exchangeResultTextview.text = getString(R.string.check_connection)
            exchangeResultTextview.visibility = View.VISIBLE
        }
    }

    override fun onCurrencyApiResponseSuccess(currencyConvertResponse: CurrencyConvertResponse) {
        binding.run {
            progressBar.visibility = View.INVISIBLE
            exchangeResultTextview.text = "${currencyConvertResponse.conversionResult} $toCurrencyCode"
            exchangeRateTextview.text = "1 $fromCurrencyCode = ${currencyConvertResponse.conversionRate} $toCurrencyCode"
            exchangeRateTextview.visibility = View.VISIBLE
            exchangeResultTextview.visibility = View.VISIBLE
        }
    }

    override fun onAmountIsEmpty() {
        showShortToast(requireContext(), getString(R.string.empty_amount))
    }

    override fun onFromCurrencyChosen(currencyInfo: CurrencyInfo) {
        setUpFromCurrencyButton(currencyInfo.currencyName)
        fromCurrencyCode = currencyInfo.currencyCode
    }

    override fun onToCurrencyChosen(currencyInfo: CurrencyInfo) {
        setUpToCurrencyButton(currencyInfo.currencyName)
        toCurrencyCode = currencyInfo.currencyCode
    }
}