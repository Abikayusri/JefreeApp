package com.jefree.app.ui.aboutUs.list_more

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.jefree.app.R
import com.jefree.app.config.GeneralKey
import com.jefree.app.ui.base.BaseActivity
import com.jefree.app.util.Shortcut
import kotlinx.android.synthetic.main.activity_gdetail.*
import kotlinx.android.synthetic.main.appbar.*



class GdetailActivity : BaseActivity() {
    var mkey = ""
    var mtitle = ""
    var mpos = 0
    var pathUrl = ""

    override fun setLayoutView(): Int {
        return R.layout.activity_gdetail
    }

    override fun setToolbar(): Toolbar {
        return custom_toolbar
    }

    override fun setTitleToolbar(): String {
        mtitle = getString(R.string.app_name)
        if (intent.getStringExtra(GeneralKey.key) != null) {
            mkey = intent.getStringExtra(GeneralKey.key)
            when (mkey) {
                GeneralKey.tanya_jawab -> mtitle = getString(R.string.tanya_jawab)
                GeneralKey.syarat_dan_ketentuan -> mtitle = getString(R.string.syarat_dan_ketentuan)
            }
        }

        mtitle = Shortcut.ucFirst(mtitle)
        return mtitle
    }

    override fun setTitleColor(): Int {
       return ContextCompat.getColor(applicationContext,R.color.colorBlack)
    }

    override fun hasTitleToolbar(): Boolean {
       return true
    }

    override fun setToolbatIconColor(): Int {
        return ContextCompat.getColor(applicationContext,R.color.colorBlack)
    }

    override fun hasToolbarIcon(): Boolean {
        return true
    }

    override fun setToolbarColor(): Int {
        return ContextCompat.getColor(applicationContext,R.color.colorWhite)
    }

    override fun onViewReady(savedInstanceState: Bundle?) {
        if(mkey==GeneralKey.tanya_jawab){
            lay_content.visibility = View.VISIBLE
            setupContent()
        }
        else if(mkey==GeneralKey.syarat_dan_ketentuan){
            lay_webview.visibility = View.VISIBLE
            setupWebView()
        }
    }

    fun setupContent(){
        mpos = intent.getIntExtra(GeneralKey.pos,0)
        val mtitle = resources.getStringArray(R.array.tanya_jawab_titles)[mpos]
        val mContent = resources.getStringArray(R.array.tanya_jawab_content)[mpos]
        title_sub.text = mtitle
        content_sub.text = mContent
    }


    fun setupWebView(){
        mpos = intent.getIntExtra(GeneralKey.pos,0)
        pathUrl = resources.getStringArray(R.array.syarat_dan_ketentuan_url)[mpos]
Log.e("pathUrl",pathUrl)
        var websetting = webview.settings
        websetting.javaScriptEnabled =true
        websetting.displayZoomControls = false
        webview.webViewClient = MyWebClient()

        webview.loadUrl("file:///android_asset/html/"+pathUrl+".html")
    }

    class MyWebClient:WebViewClient(){
        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            return super.shouldOverrideUrlLoading(view, request)
        }
    }


}
