package com.example.kotlincrypto_ktx.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.kotlincrypto_ktx.databinding.CurrencyItemBinding
import com.example.kotlincrypto_ktx.model.CurrencyModel

class PriceAdapter(private val clickListener: ClickListener) : Adapter<PriceAdapter.PricesViewHolder>(){

    var currencies : List<CurrencyModel> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PricesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        //extremely important to add all params to the inflate call
        val binding = CurrencyItemBinding.inflate(layoutInflater, parent, false)
        return PricesViewHolder(binding).listen { position, type ->
            val item = currencies[position]
            clickListener.onCurrencyClicked(item)
        }
    }

    interface ClickListener{
        fun onCurrencyClicked(currencyModel: CurrencyModel)
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

    fun <T: RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit) : T {
        itemView.setOnClickListener{
            event.invoke(adapterPosition, itemViewType)
        }
        return this
    }

}