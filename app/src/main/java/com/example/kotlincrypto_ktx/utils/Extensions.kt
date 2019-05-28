package com.example.kotlincrypto_ktx.utils

import android.app.Activity
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlincrypto_ktx.di.ComponentProvider


fun <T> T?.or(default: T): T = this ?: default
fun <T> T?.or(compute: () -> T): T = this ?: compute()

val Activity.injector get() = (application as ComponentProvider).applicationComponent
val Fragment.injector get() = (context?.applicationContext as ComponentProvider).applicationComponent

//https://www.youtube.com/watch?v=9fn5s8_CYJI
inline fun <reified T : ViewModel> Fragment.viewModel(crossinline provider: () -> T) =
        viewModels<T> {
            object : ViewModelProvider.Factory{
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                        provider() as T
            }
        }

inline fun <reified  T : ViewModel> Fragment.activityViewModel(
    crossinline provider: () -> T) = activityViewModels<T> {
        object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                provider() as T
        }
    }