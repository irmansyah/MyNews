package com.irmansyah.training.mynews.ui.article

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.irmansyah.training.mynews.BaseActivity
import com.irmansyah.training.mynews.R
import com.irmansyah.training.mynews.data.model.Article
import com.irmansyah.training.mynews.ui.webview.WebActivity
import com.irmansyah.training.mynews.utils.invisible
import com.irmansyah.training.mynews.utils.visible
import com.miguelcatalan.materialsearchview.MaterialSearchView
import kotlinx.android.synthetic.main.activity_article.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.startActivity
import org.koin.android.ext.android.inject

class ArticleActivity : BaseActivity(), ArticleView {

    companion object {
        const val TAG = "ArticleActivity"
        const val SOURCE_ID = "SOURCE_ID"
    }

    val presenter : ArticlePresenter<ArticleView> by inject()
    val mAdapter: ArticleAdapter by inject()

    var article = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)
        setSupportActionBar(toolbar)
        presenter.onAttach(this)

        article_list_rv.layoutManager = LinearLayoutManager(this)
        article_list_rv.adapter = mAdapter

        article = intent.getStringExtra(SOURCE_ID)
        presenter.getArticleList(article)

        mAdapter.setOnItemCLickListener {
            ctx.startActivity<WebActivity>(WebActivity.URL to it.url)
        }
    }

    override fun showArticleList(datas: List<Article>?) {
        mAdapter.addArticleToList(datas)
    }

    override fun showProgress() {
        progress.visible()
    }

    override fun hideProgress() {
        progress.invisible()
    }

    private fun setSearchView() {
        presenter.compositeDisposable.add(article_search_view.createObservable()
                .compose(presenter.scProvider.ioToMainObservableScheduler())
                .subscribe {
                    presenter.setSearchMatch(it)
                })

        article_search_view.setOnSearchViewListener(object : MaterialSearchView.SearchViewListener {
            override fun onSearchViewClosed() {
                presenter.getArticleList(article)
            }

            override fun onSearchViewShown() {}
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater?.inflate(R.menu.menu_team, menu)
        val item = menu?.findItem(R.id.action_search)
        article_search_view.setMenuItem(item)
        setSearchView()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId) {
            R.id.action_search -> {
                article_search_view.showSearch()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }
}
