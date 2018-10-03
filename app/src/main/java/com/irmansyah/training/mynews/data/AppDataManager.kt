package com.irmansyah.training.mynews.data

import com.irmansyah.training.mynews.data.api.ApiHelper
import com.irmansyah.training.mynews.data.model.ArticleResponse
import com.irmansyah.training.mynews.data.model.SearchArticleResponse
import com.irmansyah.training.mynews.data.model.SourceResponse
import io.reactivex.Single

class AppDataManager(private val apiHelper: ApiHelper) : DataManager {

    override fun performSourceList(): Single<SourceResponse> = apiHelper.performSourceList()

    override fun performArticleList(sourceId: String): Single<ArticleResponse> = apiHelper.performArticleList(sourceId)

    override fun performSearchArticleList(query: String): Single<SearchArticleResponse> = apiHelper.performSearchArticleList(query)
}