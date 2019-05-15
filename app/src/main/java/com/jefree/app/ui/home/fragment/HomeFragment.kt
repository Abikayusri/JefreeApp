package com.jefree.app.ui.home.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PopupMenu
import android.util.Log
import android.view.*
import com.jefree.app.JefreeApp
import com.jefree.app.R
import com.jefree.app.common.Constant
import com.jefree.app.common.TimeUtills
import com.jefree.app.common.toast
import com.jefree.app.config.GeneralKey
import com.jefree.app.model.order.Order
import com.jefree.app.ui.home.view.HomeView


//import com.example.oilink.model.User
import com.jefree.app.ui.aboutUs.list_more.GeneralActivity


import com.jefree.app.ui.home.presenter.HomePresenter
//import com.example.oilink.ui.home.HomeView
import com.jefree.app.ui.home.activity.LihatProfilPenggunaLain
import com.jefree.app.ui.home.adapter.OrderAdapter
import com.jefree.app.ui.jual.Jual
import com.jefree.app.ui.login.Login
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*
import android.widget.Toast




class HomeFragment : Fragment(), HomeView {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val presenter = HomePresenter(this)
    private val orderList = mutableListOf<Any>()
    private val userAdapter = OrderAdapter(orderList, this::onCLick)

    private fun onCLick(order: Order) {
        if(JefreeApp.sp.login){
            val intent = Intent(activity, LihatProfilPenggunaLain::class.java)
            intent.putExtra(Constant.ORDER, order)
            startActivity(intent)
        }else{
            startActivity(Intent(context, Login::class.java))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        refresh.isRefreshing = true
    }

    private fun setupView() {
        if (activity is AppCompatActivity) {
            (activity as AppCompatActivity).setSupportActionBar(toolbar)
        }

        (activity as AppCompatActivity).supportActionBar?.title = "Home"

        setHasOptionsMenu(true)

        presenter.getOrder()

        rv_user.run {
            layoutManager = LinearLayoutManager(context)
            adapter = userAdapter
        }


        refresh.setOnRefreshListener {
            presenter.getOrder()
            //untuk stop refresh
            refresh.isRefreshing = false
        }
        floating.setOnClickListener {
            if (JefreeApp.sp.login) {
                startActivity(Intent(context, Jual::class.java))
            } else {
                startActivity(Intent(context, Login::class.java))
                /* activity?.supportFragmentManager?.beginTransaction()
                     ?.replace(R.id.container, ProfileFragment.newInstance())
                     ?.commit()*/
            }
        }

        ivMenu.setOnClickListener { view ->
            val popup = PopupMenu(context!!, view)
            val inflater: MenuInflater = popup.menuInflater
            inflater.inflate(R.menu.menu, popup.menu)
            popup.show()

            popup.setOnMenuItemClickListener { p0 ->
                if (p0 != null) {
                    when(p0.itemId){
                        R.id.menu_tentang_kami -> {
                            startActivity(
                                    Intent(activity, GeneralActivity::class.java).putExtra(
                                            GeneralKey.key, GeneralKey.tentang_kami
                                    )
                            )
                        }
                        R.id.menu_tanya_jawab -> {
                            startActivity(
                                    Intent(activity, GeneralActivity::class.java).putExtra(
                                            GeneralKey.key,
                                            GeneralKey.tanya_jawab
                                    )
                            )
                        }
                        R.id.menu_syarat_dan_ketentuan -> {
                            startActivity(
                                    Intent(activity, GeneralActivity::class.java).putExtra(
                                            GeneralKey.key,
                                            GeneralKey.syarat_dan_ketentuan
                                    )
                            )
                        }
                        R.id.menu_kontak_kami -> {
                            startActivity(
                                    Intent(activity, GeneralActivity::class.java).putExtra(
                                            GeneralKey.key,
                                            GeneralKey.kontak_kami
                                    )
                            )
                        }
                        R.id.menu_ulas_kami -> {
                            ulasKami()
                        }
                    }
                }
                false
            }
        }


    }



    override fun showOrder(results: List<Order>?) {
        refresh?.isRefreshing = false
        orderList.clear()
        val nResults = results!!.map {
            it.date = TimeUtills.toDate(it.createdAt)
            return@map it
        }.sortedByDescending {
            it.date
        }
        nResults.forEachIndexed { index, order ->
            if (index != 0) {
                val orderBefore = nResults[index.minus(1)]
                val obDate = TimeUtills.convert(orderBefore.createdAt, "dd MMM yyyy")
                val oDate = TimeUtills.convert(order.createdAt, "dd MMM yyyy")
                if (obDate == oDate) {
                    orderList.add(order)
                } else {
                    orderList.add(oDate)
                    orderList.add(order)
                }
            } else {
                val date = TimeUtills.convert(order.createdAt, "dd MMM yyyy")
                val now = TimeUtills.convert(Date(), "dd MMM yyyy")
                val header = if (date == now) "Hari ini" else date
                orderList.add(header)
                orderList.add(order)
            }


        }

        userAdapter.notifyDataSetChanged()

        /*results.map {
            it.date = TimeUtills.toDate(it.createdAt)
            return@map it
        }
                .sortedBy { it.date }
                .also {
                    Log.d("TES", "asc : $it")
                    orderList.addAll(it)
                }*/
    }

    override fun onError(message: String) {
        refresh.isRefreshing = false
        activity?.toast(message)
    }

    override fun showProgrees(show: Boolean) {
        if (show) {
            progressBar?.visibility = View.VISIBLE
        } else
            progressBar?.visibility = View.GONE
    }

    /*    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
                inflater?.inflate(R.menu.menu, menu)

                ivMenu.setOnClickListener {  }

                super.onCreateOptionsMenu(menu, inflater)
            }*/

//    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
//        when (item?.itemId) {
//            R.id.menu_tentang_kami -> {
//                startActivity(
//                    Intent(activity, GeneralActivity::class.java).putExtra(
//                        GeneralKey.key, GeneralKey.tentang_kami
//                    )
//                )
//            }
//            R.id.menu_tanya_jawab -> {
//                startActivity(
//                    Intent(activity, GeneralActivity::class.java).putExtra(
//                        GeneralKey.key,
//                        GeneralKey.tanya_jawab
//                    )
//                )
//            }
//            R.id.menu_syarat_dan_ketentuan -> {
//                startActivity(
//                    Intent(activity, GeneralActivity::class.java).putExtra(
//                        GeneralKey.key,
//                        GeneralKey.syarat_dan_ketentuan
//                    )
//                )
//            }
//            R.id.menu_kontak_kami -> {
//                startActivity(
//                    Intent(activity, GeneralActivity::class.java).putExtra(
//                        GeneralKey.key,
//                        GeneralKey.kontak_kami
//                    )
//                )
//            }
//            R.id.menu_ulas_kami -> {
//                ulasKami()
//            }
//        }
//
//        return super.onOptionsItemSelected(item)
//    }

    fun ulasKami() {
        val appPackge = activity?.packageName
        Log.e("APPNAME",appPackge)
        Log.i("APPNAME",appPackge)
        Log.d("APPNAME",appPackge)

        try {
            activity?.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+appPackge)))
        }
        catch(ex : Exception){
            activity?.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/datails?id"+appPackge)))
            ex.printStackTrace()
        }




    }

}