package com.irmansyah.training.mynews.di

import android.support.v7.widget.LinearLayoutManager
import com.irmansyah.training.mynews.data.AppDataManager
import com.irmansyah.training.mynews.data.DataManager
import com.irmansyah.training.mynews.data.api.ApiHelper
import com.irmansyah.training.mynews.data.api.AppApiHelper
import com.irmansyah.training.mynews.ui.article.ArticleAdapter
import com.irmansyah.training.mynews.ui.article.ArticlePresenter
import com.irmansyah.training.mynews.ui.article.ArticleView
import com.irmansyah.training.mynews.ui.main.MainPresenter
import com.irmansyah.training.mynews.ui.main.MainView
import com.irmansyah.training.mynews.ui.main.SourceAdapter
import com.irmansyah.training.mynews.utils.ScProvider
import com.irmansyah.training.mynews.utils.SchedulerProvider
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext

object AppModule {

    fun getModule(): Module = applicationContext {

        factory { MainPresenter<MainView>(get(), get()) }
        factory { ArticlePresenter<ArticleView>(get(), get()) }

        bean { SourceAdapter(ArrayList()) }
        bean { ArticleAdapter(ArrayList()) }

        bean { AppDataManager(get()) as DataManager }
        bean { AppApiHelper() as ApiHelper }
        bean { SchedulerProvider() as ScProvider }
//        bean { LinearLayoutManager(get()) }
    }
}