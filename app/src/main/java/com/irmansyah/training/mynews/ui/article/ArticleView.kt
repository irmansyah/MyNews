package com.irmansyah.training.mynews.ui.article

import com.irmansyah.training.mynews.MVPView
import com.irmansyah.training.mynews.data.model.Article

interface ArticleView : MVPView {

    fun showArticleList(datas: List<Article>?)
}