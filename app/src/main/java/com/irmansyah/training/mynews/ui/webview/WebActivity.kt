package com.irmansyah.training.mynews.ui.webview

import android.os.Bundle
import android.view.View
import com.irmansyah.training.mynews.BaseActivity
import com.irmansyah.training.mynews.R
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : BaseActivity() {

    companion object {
        const val TAG = "WebActivity"
        const val URL = "URL"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        webview.loadUrl(intent.getStringExtra(URL))
        webview.settings.javaScriptEnabled = true
    }
}
