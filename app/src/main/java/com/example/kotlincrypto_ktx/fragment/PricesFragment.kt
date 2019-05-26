package com.example.kotlincrypto_ktx.fragment

import android.graphics.Rect
import android.os.Bundle
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.text.style.URLSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlincrypto_ktx.R
import com.example.kotlincrypto_ktx.adapter.PriceAdapter
import com.example.kotlincrypto_ktx.model.CurrencyModel
import com.example.kotlincrypto_ktx.viewmodel.PricesViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.viewModel

class PricesFragment : Fragment(), PriceAdapter.ClickListener {

    private lateinit var recyclerView : RecyclerView
    private val pricesAdapter : PriceAdapter = PriceAdapter(this)
    private val pricesViewModel: PricesViewModel by viewModel()
    private lateinit var attributionText: AppCompatTextView
    private lateinit var liveDataToggle: FloatingActionButton

    //https://stackoverflow.com/questions/54581071/fragments-destroyed-recreated-with-jetpacks-android-navigation-components
    private var _view: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        if(_view == null) {
            _view = LayoutInflater.from(this.context).inflate(R.layout.fragment_prices, container, false)

            recyclerView = _view!!.findViewById(R.id.prices_recycler_view)
            recyclerView.adapter = pricesAdapter
            recyclerView.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
            attributionText = _view!!.findViewById(R.id.attribution_text)
            liveDataToggle = _view!!.findViewById(R.id.live_data_toggle)
            liveDataToggle.setOnClickListener {
                pricesViewModel.togglePolling()
                it.isSelected = !it.isSelected
            }
            val itemDecoration = ItemSpacing()
            recyclerView.addItemDecoration(itemDecoration)
        } else {
            //we already have state lets make sure we set the right state from the viewModel
            liveDataToggle.isSelected = pricesViewModel.getPollingState()
        }

        return _view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenStarted{
            pricesViewModel.currencyModels.observe(this@PricesFragment){
                pricesAdapter.currencies = it
                pricesAdapter.notifyDataSetChanged()
                Log.d(this::class.java.canonicalName, "updating data set on ui adapter")
            }
        }

        var stringBuilder = SpannableStringBuilder(attributionText.text)
        val startIndex = stringBuilder.indexOf("Cryptocurrency")
        stringBuilder.setSpan(URLSpan("https://p.nomics.com/cryptocurrency-bitcoin-api"), startIndex, stringBuilder.length, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        attributionText.text = stringBuilder
        attributionText.movementMethod = LinkMovementMethod.getInstance()
    }

    override fun onCurrencyClicked(currencyModel: CurrencyModel) {
        Log.d("Clicked:", currencyModel.name)
        val action = PricesFragmentDirections.actionPricesNonLiveFragmentToDashboardFragmentMain(currencyModel.name)
        view!!.findNavController().navigate(action)
    }

    class ItemSpacing : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            outRect.bottom = 10
        }
    }
}