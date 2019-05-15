package com.jefree.app.ui.base

import android.graphics.PorterDuff
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.jefree.app.R

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setLayoutView())

        setUpToolbar(
            setToolbar(),
            setToolbarColor(),
            setToolbatIconColor(),
            setTitleToolbar(),
            setTitleColor(),
            hasTitleToolbar(),
            hasToolbarIcon()
        )

        onViewReady(savedInstanceState)
    }

    fun setUpToolbar(
        toolbar: Toolbar,
        toolbarColor: Int,
        colorIcon: Int,
        toolbarTitle: String,
        toolbarTitleColor: Int,
        hasTitle: Boolean,
        hasToolbarIcon: Boolean
    ) {
        if (toolbar != null) {

            toolbar.setBackgroundColor(toolbarColor)
            if (hasTitle) {
                showToolbarTitle(toolbar, toolbarTitleColor, toolbarTitle)
            }
            setSupportActionBar(toolbar)

            if (hasToolbarIcon) {
                updateToolbarIcon(colorIcon)
                toolbarBackPres(toolbar)
            }
        }
    }

    fun updateToolbarIcon(iconColor: Int) {
        var arrowBack = ContextCompat.getDrawable(applicationContext, R.drawable.ic_arrow_back_black)
        arrowBack?.setColorFilter(iconColor, PorterDuff.Mode.SRC_ATOP)
        supportActionBar?.setHomeAsUpIndicator(arrowBack)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun toolbarBackPres(toolbar: Toolbar) {
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    fun showToolbarTitle(toolbar: Toolbar, toolbarTitleColor: Int, toolbarTitle: String) {
        toolbar.setTitleTextColor(toolbarTitleColor)
        toolbar.title = toolbarTitle
    }

    abstract fun setLayoutView(): Int
    abstract fun setToolbar(): Toolbar
    abstract fun setTitleToolbar(): String
    abstract fun setTitleColor(): Int
    abstract fun hasTitleToolbar(): Boolean
    abstract fun setToolbatIconColor(): Int
    abstract fun hasToolbarIcon(): Boolean
    abstract fun setToolbarColor(): Int
    abstract fun onViewReady(savedInstanceState: Bundle?)



}