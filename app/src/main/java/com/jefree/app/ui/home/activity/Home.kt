package com.jefree.app.ui.home.activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.jefree.app.JefreeApp
import com.jefree.app.R
import com.jefree.app.ui.home.fragment.HomeFragment
import com.jefree.app.ui.home.fragment.ProfileFragment
import com.jefree.app.ui.home.fragment.RiwayatFragment
import com.jefree.app.ui.login.Login
import kotlinx.android.synthetic.main.activity_home.*

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setupView()
//        setSupportActionBar(toolbar)
    }

    private fun setupView() {

        val menuItem = bottomNav.menu
        if(!JefreeApp.sp.login) {
            menuItem.getItem(1).isCheckable = false
            menuItem.getItem(2).isCheckable = false
        }else{
            menuItem.getItem(1).isCheckable = true
            menuItem.getItem(2).isCheckable = true
        }

        bottomNav.itemIconTintList = null

        setFragment(HomeFragment.newInstance(), getString(R.string.home))
        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navHome -> setFragment(HomeFragment.newInstance(), getString(R.string.home))
                R.id.navRiwayat -> {
                    if(JefreeApp.sp.login) {
                        setFragment(RiwayatFragment.newInstance(), getString(R.string.riwayat))
                    } else {
                      startActivity(Intent(this, Login::class.java))
                    }
                }

                R.id.navProfile ->
                    if(JefreeApp.sp.login) {
                        setFragment(ProfileFragment.newInstance(), getString(R.string.profile))
                   } else {
                       startActivity(Intent(this, Login::class.java))
                    }

            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    private fun setFragment(fragment: Fragment, title: String) {
        this.title = title
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

    override fun onPause() {
        super.onPause()
        if(!JefreeApp.sp.login) {
            bottomNav.selectedItemId = R.id.navHome
        }
    }
}
