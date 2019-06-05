package com.example.android

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.Exception


fun <T> T?.or(default: T): T = this ?: default
fun <T> T?.or(compute: () -> T): T = this ?: compute()

inline fun <reified T> Activity.injector () : T {
    return try {
        (application as ComponentProvider).components.find { item -> item is T } as T
    } catch(exception: Exception) {
       throw Throwable("Component Provider must implement given type")
    }
}

inline fun <reified T> Fragment.injector () : T {
    return try {
        (context?.applicationContext as ComponentProvider).components.find { item -> item is T } as T
    } catch(exception: Exception) {
        throw Throwable("Component Provider must implement given type")
    }
}

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