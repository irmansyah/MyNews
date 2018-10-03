package com.irmansyah.training.mynews.data.api

import com.irmansyah.training.mynews.data.model.ArticleResponse
import com.irmansyah.training.mynews.data.model.SearchArticleResponse
import com.irmansyah.training.mynews.data.model.SourceResponse
import io.reactivex.Single

interface ApiHelper {

    fun performSourceList(): Single<SourceResponse>

    fun performArticleList(sourceId: String): Single<ArticleResponse>

    fun performSearchArticleList(query: String): Single<SearchArticleResponse>
}