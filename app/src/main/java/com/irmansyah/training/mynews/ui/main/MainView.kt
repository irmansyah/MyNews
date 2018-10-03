package com.irmansyah.training.mynews.ui.main

import com.irmansyah.training.mynews.MVPView
import com.irmansyah.training.mynews.data.model.Source

interface MainView : MVPView {

    fun showSourceList(datas: List<Source>?)
}