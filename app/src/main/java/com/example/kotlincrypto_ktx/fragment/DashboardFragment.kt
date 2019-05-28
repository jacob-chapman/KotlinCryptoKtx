package com.example.kotlincrypto_ktx.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.example.kotlincrypto_ktx.databinding.FragmentDashboardBinding
import com.example.kotlincrypto_ktx.model.DashboardModel
import com.example.kotlincrypto_ktx.utils.injector
import com.example.kotlincrypto_ktx.utils.viewModel
import com.example.kotlincrypto_ktx.viewmodel.DashboardViewModel

class DashboardFragment : Fragment() {

    companion object {

        const val currency_name_key = "currency_name"

        fun newInstance(currencyName: String) : DashboardFragment {
            var bundle = Bundle()
            bundle.putString(currency_name_key, currencyName)
            val frag = DashboardFragment()
            frag.arguments = bundle
            return frag
        }
    }

    private val args: DashboardFragmentArgs by navArgs()
    private val dashboardViewModel: DashboardViewModel by viewModel { injector.dashboardViewModel }
    private lateinit var binding: FragmentDashboardBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layoutInflater = LayoutInflater.from(this.context)
        binding = FragmentDashboardBinding.inflate(layoutInflater, container, false)
        binding.context = this.context
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val name = args.currencyName

        lifecycleScope.launchWhenStarted {
            dashboardViewModel.loadDashboard(name).observe(this@DashboardFragment){
                if(it != null){
                    binding.dashboard = DashboardModel.create(it)
                }
            }
        }
    }

}