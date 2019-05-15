package com.jefree.app.ui.home.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.jefree.app.R
import com.jefree.app.common.toast
import com.jefree.app.model.riwayatpenjualan.RiwayatPenjualan
import com.jefree.app.ui.home.adapter.RiwayatAdapter
import com.jefree.app.ui.home.presenter.RiwayatPresenter
import com.jefree.app.ui.home.view.RiwayatView
import kotlinx.android.synthetic.main.fragment_riwayat.*

class RiwayatFragment : Fragment(), RiwayatView {

    companion object {
        fun newInstance() = RiwayatFragment()
    }

    private val riwayatPenjualanList = mutableListOf<RiwayatPenjualan>()
    private val riwayatAdapter = RiwayatAdapter(riwayatPenjualanList, this::onClick)

    private fun onClick(it: RiwayatPenjualan, ln1: LinearLayout, ln2: LinearLayout) {
        if (ln1.visibility == View.VISIBLE) {
            ln1.visibility = View.GONE
            ln2.visibility = View.VISIBLE

        } else {
            ln1.visibility = View.VISIBLE
            ln2.visibility = View.GONE
        }
    }

    val presenter = RiwayatPresenter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_riwayat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        presenter.getDataUser()

        presenter.showRiwayat()

        if (activity is AppCompatActivity) {
            (activity as AppCompatActivity).setSupportActionBar(toolbar)
        }

        (activity as AppCompatActivity).supportActionBar?.title = "Riwayat"

        rvRiwayat.run {
            layoutManager = LinearLayoutManager(context)
            adapter = riwayatAdapter
        }

        swipeRefresh.setOnRefreshListener {
            presenter.showRiwayat()
            swipeRefresh.isRefreshing = false
        }
    }

    override fun onError(message: String) {
        context!!.toast(message)
    }

    override fun onSuccess(data: List<RiwayatPenjualan>) {
        riwayatPenjualanList.clear()
        riwayatPenjualanList.addAll(data.sortedByDescending { it.id })
        riwayatAdapter.notifyDataSetChanged()
    }

    override fun showProgrees(show: Boolean) {
        if (show) {
            progressBar?.visibility = View.VISIBLE
        } else
            progressBar?.visibility = View.GONE
    }
}
