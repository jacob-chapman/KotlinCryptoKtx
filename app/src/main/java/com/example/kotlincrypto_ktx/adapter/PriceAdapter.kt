package com.example.kotlincrypto_ktx.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.data.model.Currency
import com.example.kotlincrypto_ktx.databinding.CurrencyItemBinding
import com.example.kotlincrypto_ktx.model.CurrencyModel

class PriceAdapter : Adapter<PriceAdapter.PricesViewHolder>(){

    var currencies : List<CurrencyModel> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PricesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        //extremely important to add all params to the inflate call
        val binding = CurrencyItemBinding.inflate(layoutInflater, parent, false)
        return PricesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return currencies.count()
    }

    override fun onBindViewHolder(holder: PricesViewHolder, position: Int) = holder.bind(currencies[position])

    class PricesViewHolder(private val binding: CurrencyItemBinding) : ViewHolder(binding.root) {
        fun bind(item: CurrencyModel) {
            binding.currency = item
            binding.executePendingBindings()
        }
    }

}