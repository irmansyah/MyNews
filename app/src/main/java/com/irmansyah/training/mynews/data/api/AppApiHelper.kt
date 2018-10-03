package com.irmansyah.training.mynews.data.api

import com.irmansyah.training.mynews.BuildConfig
import com.irmansyah.training.mynews.data.model.ArticleResponse
import com.irmansyah.training.mynews.data.model.SearchArticleResponse
import com.irmansyah.training.mynews.data.model.SourceResponse
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Single

class AppApiHelper : ApiHelper {

    override fun performSourceList(): Single<SourceResponse> =
            Rx2AndroidNetworking.get(NewsApi.SOURCE)
                    .addQueryParameter("apiKey", BuildConfig.API_KEY)
                    .build()
                    .getObjectSingle(SourceResponse::class.java)

    override fun performArticleList(sourceId: String): Single<ArticleResponse> =
            Rx2AndroidNetworking.get(NewsApi.ARTICLE)
                    .addQueryParameter("sources", sourceId)
                    .addQueryParameter("apiKey", BuildConfig.API_KEY)
                    .build()
                    .getObjectSingle(ArticleResponse::class.java)

    override fun performSearchArticleList(query: String): Single<SearchArticleResponse> =
            Rx2AndroidNetworking.get(NewsApi.ARTICLE)
                    .addQueryParameter("q", query)
                    .addQueryParameter("apiKey", BuildConfig.API_KEY)
                    .build()
                    .getObjectSingle(SearchArticleResponse::class.java)

}