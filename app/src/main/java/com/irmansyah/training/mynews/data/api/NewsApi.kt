package com.irmansyah.training.mynews.data.api

import com.irmansyah.training.mynews.BuildConfig

object NewsApi {

    const val SOURCE = "${BuildConfig.BASE_URL}/sources"

    const val ARTICLE = "${BuildConfig.BASE_URL}/top-headlines"

    const val SEARCH = "${BuildConfig.BASE_URL}/everything"
}