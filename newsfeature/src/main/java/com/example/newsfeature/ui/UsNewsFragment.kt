package com.example.newsfeature.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.ItemSpacing
import com.example.android.injector
import com.example.android.viewModel
import com.example.newsfeature.R
import com.example.newsfeature.di.NewsFeatureComponent
import kotlinx.android.synthetic.main.fragment_news.*

class UsNewsFragment : Fragment() {

    private val recyclerView: RecyclerView by lazy { news_recycler }
    private val usNewsViewModel by viewModel { injector<NewsFeatureComponent>().newsViewModel }
    private val newsAdapter: NewsAdapter by lazy { NewsAdapter() }

    //https://stackoverflow.com/questions/54581071/fragments-destroyed-recreated-with-jetpacks-android-navigation-components
    private var _view: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        if(_view == null) {
            _view = inflater.inflate(R.layout.fragment_news, container, false)
        }

        return _view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.adapter = newsAdapter
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.addItemDecoration(ItemSpacing(10))

        lifecycleScope.launchWhenStarted{

            usNewsViewModel.newsArticles.observe(this@UsNewsFragment){
                newsAdapter.articles = it
                newsAdapter.notifyDataSetChanged()
            }

            usNewsViewModel.loadNews()
        }
    }

}