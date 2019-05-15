package com.jefree.app.ui.aboutUs.list_more

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Toast
import com.jefree.app.R
import com.jefree.app.config.GeneralKey
import com.jefree.app.ui.adapter.GeneralAdapter
import com.jefree.app.ui.base.BaseActivity
import com.jefree.app.util.Shortcut
import kotlinx.android.synthetic.main.activity_general.*
import kotlinx.android.synthetic.main.appbar.*

class GeneralActivity : BaseActivity() {

    var mcontent = ""
    var mkey = ""
    var mtitle = ""


    override fun setLayoutView(): Int {
        return R.layout.activity_general
    }

    override fun setTitleToolbar(): String {
        mtitle = getString(R.string.app_name)
        if (intent.getStringExtra(GeneralKey.key) != null) {
            mkey = intent.getStringExtra(GeneralKey.key)
            when (mkey) {
                GeneralKey.tentang_kami -> {
                    mtitle = getString(R.string.tentang_kami)
                    mcontent = getString(R.string.tentang_kami_desc)
                }
                GeneralKey.tanya_jawab -> mtitle = getString(R.string.tanya_jawab)
                GeneralKey.syarat_dan_ketentuan -> mtitle = getString(R.string.syarat_dan_ketentuan)
                GeneralKey.kontak_kami -> mtitle = getString(R.string.kontak_kami)
            }
        }

        mtitle = Shortcut.ucFirst(mtitle)
        return mtitle
    }

    override fun setTitleColor(): Int {
        return ContextCompat.getColor(applicationContext, R.color.colorBlack)
    }

    override fun hasTitleToolbar(): Boolean {
        return true
    }

    override fun setToolbatIconColor(): Int {
        return ContextCompat.getColor(applicationContext, R.color.colorBlack)
    }

    override fun hasToolbarIcon(): Boolean {
        return true
    }

    override fun setToolbarColor(): Int {
        return ContextCompat.getColor(applicationContext, R.color.colorWhite)
    }

    override fun setToolbar(): Toolbar {
        return custom_toolbar
    }

    override fun onViewReady(savedInstanceState: Bundle?) {
        when (mkey) {
            GeneralKey.tentang_kami -> {
                content.text = mcontent
                layout_content_dinamis.visibility = View.VISIBLE
            }
            GeneralKey.kontak_kami -> {
                setupContactUs()
                layout_content_kontak.visibility = View.VISIBLE
            }
            GeneralKey.tanya_jawab -> {
                card_view_list.visibility = View.VISIBLE
                setupRecycylerview()
            }
            GeneralKey.syarat_dan_ketentuan -> {
                card_view_list.visibility = View.VISIBLE
                setupRecycylerview()
            }

        }
    }

    fun setupContactUs(){
        img_1.setOnClickListener {
            sendEmail()
        }

        img_2.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("tel:+62"+contact_us.text.subSequence(1,contact_us.text.length))))
        }
    }

    fun sendEmail(){
        var i = Intent(Intent.ACTION_SEND)
        i.setType("message/rfc822")
        i.putExtra(Intent.EXTRA_EMAIL, arrayOf(email_us.text))
        try{
            startActivity(Intent.createChooser(i,"Chose email client"))
        }catch (ex: Exception){
            ex.printStackTrace()
            Toast.makeText(applicationContext,ex.message,Toast.LENGTH_SHORT).show()
        }

    }

    fun setupRecycylerview() {
        var list = emptyArray<String>()
        when (mkey) {
            GeneralKey.tanya_jawab -> {
                list = resources.getStringArray(R.array.tanya_jawab_titles)
            }
            GeneralKey.syarat_dan_ketentuan -> {
                list = resources.getStringArray(R.array.syarat_dan_ketentuan_title)
            }
        }

        var madapter = GeneralAdapter(applicationContext, list.asList(),mkey)
        list_view.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = madapter
        }
    }

    /*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_general)

        getDataIntent()
    }

    fun getDataIntent() {
        if (intent.getStringExtra(GeneralKey.key) != null) {
            mkey = intent.getStringExtra(GeneralKey.key)

            if (mkey == GeneralKey.tentang_kami) {
                mtitle = getString(R.string.tentang_kami)
                mcontent = getString(R.string.tentang_kami_desc)
                content.text = mcontent
                layout_content_dinamis.visibility = View.VISIBLE
            } else if (mkey == GeneralKey.kontak_kami) {
                mtitle = getString(R.string.kontak_kami)
                layout_content_kontak.visibility = View.VISIBLE

            }

        }
    }

    fun setupTextContent() {

        content.text = ""
    }
    */
}
