package com.example.kotlincrypto_ktx.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.NewsArticle
import com.example.kotlincrypto_ktx.R
import com.example.kotlincrypto_ktx.databinding.NewsItemBinding

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>(){

    var articles: List<NewsArticle> = emptyList()

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
       holder.bind(articles[position])
    }

    override fun getItemCount(): Int = articles.count()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        val binding = NewsItemBinding.bind(view)
        return ArticleViewHolder(binding)
    }


    class ArticleViewHolder(private val binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(newsArticle: NewsArticle){
            binding.article = newsArticle
            binding.executePendingBindings()
        }

    }
}