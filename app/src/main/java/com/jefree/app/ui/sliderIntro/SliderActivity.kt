package com.jefree.app.ui.sliderIntro

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import com.jefree.app.JefreeApp
import com.jefree.app.R
import com.jefree.app.ui.home.activity.Home
import kotlinx.android.synthetic.main.activity_slider.*

class SliderActivity : AppCompatActivity() {

    private val fragment1 = SliderFragment()
    private val fragment2 = SliderFragment()
    private val fragment3 = SliderFragment()

    lateinit var adapter: myPegerAdapter
    lateinit var preference: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slider)
        initView()
    }

    private fun initView() {
        fragment1.setTitle("Kumpulkan Jelantah")
        fragment2.setTitle("Gunakan JEFREE")
        fragment3.setTitle("Tidak Perlu Repot")

        fragment1.setDescription("Jangan langsung dibuang ya, kumpulkan dulu di sebuah tempat.")
        fragment2.setDescription("Gunakan aplikasi JEFREE untuk menjual minyak jelantah anda.")
        fragment3.setDescription("Jangan khawatir, minyak anda akan kami jemput tepat dilokasi anda berada.")

        fragment1.setImage(R.drawable.ic_onboarding1)
        fragment2.setImage(R.drawable.ic_onboarding2)
        fragment3.setImage(R.drawable.ic_onboarding3)

        adapter = myPegerAdapter(supportFragmentManager)
        adapter.list.add(fragment1)
        adapter.list.add(fragment2)
        adapter.list.add(fragment3)

        view_pager.adapter = adapter

        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {

            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

            }

            override fun onPageSelected(p0: Int) {


                when (view_pager.currentItem) {
                    0 -> {
                        indicator1.setTextColor(resources.getColor(R.color.colorPrimaryDark))
                        indicator2.setTextColor(Color.GRAY)
                        indicator3.setTextColor(Color.GRAY)
                        indicator1.text = "-"
                        indicator2.text = "-"
                        indicator3.text = "-"
                        btn_lewati.visibility = View.VISIBLE
                        btn_lanjut.visibility = View.VISIBLE
                        btn_masuk.visibility = View.GONE

                    }
                    1 -> {
//
                        indicator1.setTextColor(Color.GRAY)
                        indicator2.setTextColor(resources.getColor(R.color.colorPrimaryDark))
                        indicator3.setTextColor(Color.GRAY)
                        indicator1.text = "-"
                        indicator2.text = "-"
                        indicator3.text = "-"
                        btn_lewati.visibility = View.VISIBLE
                        btn_lanjut.visibility = View.VISIBLE
                        btn_masuk.visibility = View.GONE

                    }
                    2 -> {

                        indicator1.text = ""
                        indicator2.text = ""
                        indicator3.text = ""
                        btn_lewati.visibility = View.GONE
                        btn_lanjut.visibility = View.GONE
                        btn_masuk.visibility = View.VISIBLE

                    }
                }
            }

        })

        btn_lewati.setOnClickListener {
            startActivity(Intent(this, Home::class.java))
            finish()
        }

        btn_lanjut.setOnClickListener {
            if(view_pager.currentItem == 0)
                view_pager.currentItem = 1
            else
                view_pager.currentItem = 2
        }

        btn_masuk.setOnClickListener {
            JefreeApp.sp.intro = false
            startActivity(Intent(this, Home::class.java))
            finish()
        }
    }


    class myPegerAdapter(maneger: FragmentManager) : FragmentPagerAdapter(maneger) {
        val list: MutableList<Fragment> = ArrayList()
        override fun getItem(posision: Int): Fragment {
            return list[posision]
        }

        override fun getCount(): Int {
            return list.size
        }

    }
}
