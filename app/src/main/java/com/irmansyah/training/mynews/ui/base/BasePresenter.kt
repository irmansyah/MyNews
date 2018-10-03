package com.irmansyah.training.mynews

import android.arch.lifecycle.ViewModel
import com.irmansyah.training.mynews.data.DataManager
import com.irmansyah.training.mynews.utils.ScProvider
import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<V : MVPView> internal constructor(var dataManager: DataManager, var scProvider: ScProvider) : ViewModel() {

    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private var view: V? = null
    private val isViewAttached: Boolean get() = view != null

    fun onAttach(view: V?) {
        this.view = view
    }

    fun getView(): V? = view

    fun onDetach() {
        compositeDisposable.dispose()
        view = null
    }

    override fun onCleared() {
        super.onCleared()
        onDetach()
    }
}