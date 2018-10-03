package com.irmansyah.training.mynews.ui.main

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.irmansyah.training.mynews.BaseActivity
import com.irmansyah.training.mynews.R
import com.irmansyah.training.mynews.data.model.Source
import com.irmansyah.training.mynews.ui.article.ArticleActivity
import com.irmansyah.training.mynews.utils.gone
import com.irmansyah.training.mynews.utils.invisible
import com.irmansyah.training.mynews.utils.visible
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.startActivity
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity(), MainView {

    companion object {
        const val TAG = "MainActivity"
    }

    val presenter : MainPresenter<MainView> by inject()
    val mAdapter: SourceAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.onAttach(this)
        title = getString(R.string.source_news)

        source_list_rv.layoutManager = LinearLayoutManager(this)
        source_list_rv.adapter = mAdapter

        presenter.getSourceList()

        mAdapter.setOnItemCLickListener {
            ctx.startActivity<ArticleActivity>(ArticleActivity.SOURCE_ID to it.id)
        }
    }

    override fun showSourceList(datas: List<Source>?) {
        mAdapter.addSourceToList(datas)
    }

    override fun showProgress() {
        progress.visible()
    }

    override fun hideProgress() {
        progress.invisible()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }
}
