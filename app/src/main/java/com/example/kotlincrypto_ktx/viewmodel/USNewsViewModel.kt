package com.example.kotlincrypto_ktx.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.NewsArticle
import com.example.domain.usecase.BaseUseCase
import com.example.domain.usecase.GetUSNewsUseCase
import com.example.domain.util.Failure
import kotlinx.coroutines.launch
import javax.inject.Inject

class USNewsViewModel @Inject constructor(private val getUSNewsUseCase: GetUSNewsUseCase): ViewModel() {

    private val _newsArticles: MutableLiveData<List<NewsArticle>> = MutableLiveData()

    val newsArticles: LiveData<List<NewsArticle>>
        get() = _newsArticles


    fun loadNews(){
        viewModelScope.launch {
            getUSNewsUseCase.execute(BaseUseCase.None()).either(::handleError, ::handleSuccess)
        }
    }

    private fun handleError(failure: Failure){
        Log.d(this::class.simpleName, "$failure")
    }

    private fun handleSuccess(articles: List<NewsArticle>) {
        _newsArticles.postValue(articles)
    }
}