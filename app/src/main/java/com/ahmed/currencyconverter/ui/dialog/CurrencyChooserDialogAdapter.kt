package com.ahmed.currencyconverter.ui.dialog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahmed.currencyconverter.data.model.CurrencyInfo
import com.ahmed.currencyconverter.databinding.LayoutCurrencyInfoListItemBinding

class CurrencyChooserDialogAdapter(private val list: List<CurrencyInfo>, private val listener: CurrencyDialogClicksListener) : RecyclerView.Adapter<CurrencyChooserDialogAdapter.CurrencyChooserViewHolder>() {
    inner class CurrencyChooserViewHolder(val binding: LayoutCurrencyInfoListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyChooserViewHolder = CurrencyChooserViewHolder(
        LayoutCurrencyInfoListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: CurrencyChooserViewHolder, position: Int) {
        holder.binding.run {
            currencyNameTextview.text = list[position].currencyName
            currencyCodeTextview.text = list[position].currencyCode

            rowLinearLayout.setOnClickListener {
                listener.onCurrencyItemClickListener(list[position])
            }
        }
    }

    override fun getItemCount(): Int = list.size
}