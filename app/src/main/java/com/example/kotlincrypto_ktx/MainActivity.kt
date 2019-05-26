package com.example.kotlincrypto_ktx

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.kotlincrypto_ktx.fragment.DashboardFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val navigationBar : BottomNavigationView by lazy { bottom_navigation_bar }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //set content view
        setContentView(R.layout.activity_main)

        val hostFragment = supportFragmentManager.findFragmentById(R.id.fragment_main_nav_host) as NavHostFragment
        val navController = hostFragment.navController
        navController.addOnDestinationChangedListener { controller, destination, _ ->
            Log.d("Destination Changed", destination.label.toString())
            when(destination.id){
                 R.id.dashboardFragmentMain -> {
                     navigationBar.visibility = View.GONE
                 }
                else -> {
                    navigationBar.visibility = View.VISIBLE
                }
            }
        }
        navigationBar.setupWithNavController(navController)

        hostFragment.fragmentManager?.addOnBackStackChangedListener {
            var layout = findViewById<CoordinatorLayout>(R.id.host_layout)
            Snackbar.make(layout, supportFragmentManager.backStackEntryCount, Snackbar.LENGTH_LONG).show()
        }
    }
}