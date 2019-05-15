package com.jefree.app.ui.splash

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.jefree.app.JefreeApp
import com.jefree.app.R
import com.jefree.app.ui.home.activity.Home
import com.jefree.app.ui.sliderIntro.SliderActivity

class Splash : AppCompatActivity() {

    private var handler: Handler? = null
    private val delay: Long = 2000
    private val runnable: Runnable = Runnable {
        if(!isFinishing) {
            if(JefreeApp.sp.intro) {
                startActivity(Intent(this, SliderActivity::class.java))
            }else {
                startActivity(Intent(this, Home::class.java))
            }
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        initView()
    }

    private fun initView() {
        openActivity()
    }

    private fun openActivity() {
        handler = Handler()
        handler!!.postDelayed(runnable, delay)
    }

    override fun onDestroy() {
        if(handler != null) {
            handler!!.removeCallbacks(runnable)
        }
        super.onDestroy()
    }
}
