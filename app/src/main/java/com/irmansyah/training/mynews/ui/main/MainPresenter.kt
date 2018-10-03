package com.irmansyah.training.mynews.ui.main

import android.util.Log
import com.irmansyah.training.mynews.BasePresenter
import com.irmansyah.training.mynews.data.DataManager
import com.irmansyah.training.mynews.utils.ScProvider


class MainPresenter<V : MainView> constructor(dataManager: DataManager, scProvider: ScProvider) : BasePresenter<V>(dataManager = dataManager, scProvider = scProvider) {

    fun getSourceList() {
        getView()?.showProgress()
        compositeDisposable.add(dataManager.performSourceList()
                .compose(scProvider.ioToMainSingleScheduler())
                .subscribe({

                    it.sources?.let {
                        for (source in it) {
                            Log.i(MainActivity.TAG, source.name)
                        }
                        getView()?.showSourceList(it)
                        getView()?.hideProgress()
                    }


                }, {

                    Log.e(MainActivity.TAG, it.message)

                }))
    }
}