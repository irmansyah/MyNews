package com.irmansyah.training.mynews.ui.article

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.irmansyah.training.mynews.BaseViewHolder
import com.irmansyah.training.mynews.R
import com.irmansyah.training.mynews.data.model.Article
import kotlinx.android.synthetic.main.item_article.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class ArticleAdapter(private val articles: ArrayList<Article>) : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    private lateinit var mListener: (Article) -> Unit

    fun setOnItemCLickListener(listener: (Article) -> Unit) {
        this.mListener = listener
    }

    fun addArticleToList(articles: List<Article>?) {
        this.articles.clear()
        articles?.let { this.articles.addAll(it) }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = articles.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleAdapter.ArticleViewHolder =
            ArticleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false))

    override fun onBindViewHolder(holder: ArticleAdapter.ArticleViewHolder, position: Int) {
        holder.clear()
        holder.onBind(position)
    }

    inner class ArticleViewHolder(view: View) : BaseViewHolder(view) {

        override fun clear() {
            itemView.article_title_tv.text = ""
        }

        override fun onBind(position: Int) {
            articles[position].let {
                itemView.article_title_tv.text = it.title
            }
            itemView.onClick { mListener(articles[position]) }
        }
    }
}