package com.example.kotlincrypto_ktx.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlincrypto_ktx.R
import com.example.kotlincrypto_ktx.adapter.PriceAdapter
import com.example.kotlincrypto_ktx.model.CurrencyModel
import com.example.kotlincrypto_ktx.viewmodel.CurrenciesNonLiveViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PricesNonLiveFragment : Fragment(), PriceAdapter.ClickListener {

    private lateinit var recyclerView : RecyclerView
    private val pricesAdapter : PriceAdapter = PriceAdapter(this)
    private val currenciesViewModel: CurrenciesNonLiveViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = LayoutInflater.from(this.context).inflate(R.layout.fragment_prices, container, false)

        recyclerView = view.findViewById(R.id.prices_recycler_view)
        recyclerView.adapter = pricesAdapter
        recyclerView.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

        val itemDecoration = PricesFragment.ItemSpacing()
        recyclerView.addItemDecoration(itemDecoration)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenStarted{
            currenciesViewModel.load()
            currenciesViewModel.currencyModels.observe(this@PricesNonLiveFragment){
                pricesAdapter.currencies = it
                pricesAdapter.notifyDataSetChanged()
                Log.d(this::class.qualifiedName, "updating data set on ui adapter")
            }
        }
    }

    override fun onCurrencyClicked(currencyModel: CurrencyModel) {
        Log.d("Clicked:", currencyModel.name)
        val currencyName = currencyModel.name
        val action = PricesFragmentDirections.pricesToDashboardTransaction(currencyName)
        view!!.findNavController().navigate(action)
    }
}