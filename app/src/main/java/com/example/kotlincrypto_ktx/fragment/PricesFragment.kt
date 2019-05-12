package com.example.kotlincrypto_ktx.fragment

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlincrypto_ktx.R
import com.example.kotlincrypto_ktx.adapter.PriceAdapter
import com.example.kotlincrypto_ktx.viewmodel.CurrenciesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PricesFragment : Fragment() {

    companion object {
        fun newInstance() : PricesFragment {
            return PricesFragment()
        }
    }

    private val pricesAdapter : PriceAdapter = PriceAdapter()
    private lateinit var recyclerView : RecyclerView

    private val currenciesViewModel: CurrenciesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currenciesViewModel.loadCurrencies().observe(this) {
            pricesAdapter.currencies = it
            pricesAdapter.notifyDataSetChanged()
            Log.d(this::class.qualifiedName, "updating data set")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = LayoutInflater.from(this.context).inflate(R.layout.fragment_prices, container, false)

        recyclerView = view.findViewById(R.id.prices_recycler_view)
        recyclerView.adapter = pricesAdapter
        recyclerView.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

        val itemDecoration = ItemSpacing()
        recyclerView.addItemDecoration(itemDecoration)

        return view
    }

    class ItemSpacing : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            outRect.bottom = 10
        }
    }

}