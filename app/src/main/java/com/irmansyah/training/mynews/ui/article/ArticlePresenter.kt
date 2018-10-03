package com.irmansyah.training.mynews.ui.article

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.irmansyah.training.mynews.BasePresenter
import com.irmansyah.training.mynews.data.DataManager
import com.irmansyah.training.mynews.utils.ScProvider
import java.util.concurrent.TimeUnit

class ArticlePresenter<V : ArticleView> constructor(dataManager: DataManager, scProvider: ScProvider) : BasePresenter<V>(dataManager = dataManager, scProvider = scProvider) {

    fun getArticleList(sourceId: String) {
        getView()?.showProgress()
        compositeDisposable.add(dataManager.performArticleList(sourceId)
                .compose(scProvider.ioToMainSingleScheduler())
                .subscribe({

                    it.articles?.let {
                        getView()?.showArticleList(it)
                        getView()?.hideProgress()

                    }

                }, {

                    Log.e(ArticleActivity.TAG, it.message)

                }))
    }

    fun setSearchMatch(query: String) {
        getView()?.showProgress()
        compositeDisposable.add(dataManager.performSearchArticleList(query)
                .toObservable()
                .debounce(1000, TimeUnit.MILLISECONDS)
                .compose(scProvider.ioToMainObservableScheduler())
                .subscribe({
                    it.articles?.let{
                        getView()?.showArticleList(it)
                        getView()?.hideProgress()
                    }
                }, {

                    Log.e(ArticleActivity.TAG, "Error Search :: ${it.message}")

                }))
    }
}