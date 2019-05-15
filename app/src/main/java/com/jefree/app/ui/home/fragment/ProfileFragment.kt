package com.jefree.app.ui.home.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.PopupMenu
import android.util.Log
import android.view.*
import com.bumptech.glide.Glide
import com.jefree.app.JefreeApp
import com.jefree.app.R
import com.jefree.app.common.Constant
import com.jefree.app.common.toast
import com.jefree.app.config.GeneralKey
import com.jefree.app.model.Profile.Data
import com.jefree.app.ui.aboutUs.list_more.GeneralActivity
import com.jefree.app.ui.home.activity.EditProfile
import com.jefree.app.ui.home.activity.Home
import com.jefree.app.ui.home.presenter.ProfilePresenter
import com.jefree.app.ui.home.view.ProfileView
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*

class ProfileFragment : Fragment(), ProfileView {
    override fun proggress(boolean: Boolean) {
        if (boolean) {
            progress?.visibility = View.VISIBLE
        } else
            progress?.visibility = View.GONE
    }

    private lateinit var data: Data

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private val presenter = ProfilePresenter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onResume() {
        super.onResume()
        presenter.showUser(JefreeApp.sp.id)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

/*    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.ubahProfile -> {
                editProfile(data)
            }
            R.id.keluar -> {
                JefreeApp.sp.logOut()
                JefreeApp.sp.login = false
                val intent = Intent(context, Home::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }*/

    private fun editProfile(data: Data) {
        Log.d("dbg", "$data")
        Log.d("_debug", data.toString())
        val intent = Intent(context, EditProfile::class.java).apply {
            putExtra(Constant.USERS, data)
        }
        startActivity(intent)
    }

    private fun setupView() {
        if (activity is AppCompatActivity) {
            (activity as AppCompatActivity).setSupportActionBar(view?.toolbar2)
        }

        (activity as AppCompatActivity).supportActionBar?.title = "Profile"
        setHasOptionsMenu(true)

        ivMenuProfile.setOnClickListener { view ->
            val popup = PopupMenu(context!!, view)
            val inflater: MenuInflater = popup.menuInflater
            inflater.inflate(R.menu.menu2, popup.menu)
            popup.show()

            popup.setOnMenuItemClickListener { p0 ->
                if (p0 != null) {
                    when(p0.itemId){
                        R.id.ubahProfile -> {
                            editProfile(data)
                        }
                        R.id.keluar -> {
                            JefreeApp.sp.logOut()
                            JefreeApp.sp.login = false
                            val intent = Intent(context, Home::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(intent)
                        }
                    }
                }
                false
            }
        }

    }

/*    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu2, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }*/

    override fun showUser(data: Data) {
        view?.run {
            //inisialisasi data
            this@ProfileFragment.data = data
            namaUser.text = data.profile?.name
            tv_bio_profile.text = data.profile?.bio
            bisnistype.text = data.profile?.bussinessType
            phone.text = data.profile?.phone
            address.text = data.profile?.address
            email.text = data.profile?.email
            //transaksi
            kilogram.text = data.order?.totalOil
            transaksi.text = data.order?.totalOrder.toString()

            Glide.with(ccview2.context).load(data.profile?.avatar?.url).into(ccview2)

            if (data.profile?.avatar?.url != "" && data.profile?.name != "" && data.profile?.bio != "" && data.profile?.bussinessType != "" && data.profile?.phone != "" && data.profile?.address != "" && data.profile?.email != "") {
                pesanDataKosong.visibility = View.GONE
                return
            } else {
                pesanDataKosong.visibility = View.VISIBLE
            }

        }
    }

    override fun message(message: String) {
        activity?.toast(message)
    }


}