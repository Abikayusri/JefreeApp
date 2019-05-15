package com.jefree.app.ui.home.adapter

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.jefree.app.JefreeApp
import com.jefree.app.R
import com.jefree.app.model.riwayatpenjualan.RiwayatPenjualan
import kotlinx.android.synthetic.main.item_riwayat.view.*
import java.text.SimpleDateFormat
import java.util.*
import java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME
import android.R.string
import com.jefree.app.common.currencyFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class RiwayatAdapter(
    private val riwayatPenjualanList: List<RiwayatPenjualan>,
    private val onClick: (riwayatPenjualan: RiwayatPenjualan, ln: LinearLayout, ln2: LinearLayout) -> Unit
) : RecyclerView.Adapter<RiwayatAdapter.RiwayatPenjualanHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RiwayatPenjualanHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        return RiwayatPenjualanHolder(inflater.inflate(R.layout.item_riwayat, viewGroup, false))
    }

    override fun onBindViewHolder(holder: RiwayatPenjualanHolder, p1: Int) {
        val riwayatPenjualan: RiwayatPenjualan = riwayatPenjualanList[p1]
        holder.bind(riwayatPenjualan).run {
            setOnClickListener{
                onClick(riwayatPenjualan, this.lnItemParent, this.lnItemChild)
            }
        }
    }

    override fun getItemCount(): Int = riwayatPenjualanList.size
    class RiwayatPenjualanHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(riwayatPenjualan: RiwayatPenjualan) = itemView.apply {
            when {
                riwayatPenjualan.status == "Menuggu" -> {
                    tvStatusParent.setBackgroundDrawable(context.resources.getDrawable(R.drawable.status_menunggu))
                    tvStatusChild.setBackgroundDrawable(context.resources.getDrawable(R.drawable.status_menunggu))

                    tvStatusParent.setTextColor(context.resources.getColor(R.color.colorTextStatusMenuggu))
                    tvStatusChild.setTextColor(context.resources.getColor(R.color.colorTextStatusMenuggu))

                }
                riwayatPenjualan.status == "Diproses" -> {
                    tvStatusParent.setBackgroundDrawable(context.resources.getDrawable(R.drawable.status_diproses))
                    tvStatusChild.setBackgroundDrawable(context.resources.getDrawable(R.drawable.status_diproses))

                    tvStatusParent.setTextColor(context.resources.getColor(R.color.colorTextStatusDiproses))
                    tvStatusChild.setTextColor(context.resources.getColor(R.color.colorTextStatusDiproses))
                }
                riwayatPenjualan.status == "Dibatalkan" -> {
                    tvStatusParent.setBackgroundDrawable(context.resources.getDrawable(R.drawable.status_dibatalkan))
                    tvStatusChild.setBackgroundDrawable(context.resources.getDrawable(R.drawable.status_dibatalkan))

                    tvStatusParent.setTextColor(context.resources.getColor(R.color.colorTextStatusDibatalkan))
                    tvStatusChild.setTextColor(context.resources.getColor(R.color.colorTextStatusDibatalkan))
                }
                else -> {
                    tvStatusParent.setBackgroundDrawable(context.resources.getDrawable(R.drawable.status_berhasil))
                    tvStatusChild.setBackgroundDrawable(context.resources.getDrawable(R.drawable.status_berhasil))

                    tvStatusParent.setTextColor(context.resources.getColor(R.color.colorTextStatusBerhasil))
                    tvStatusChild.setTextColor(context.resources.getColor(R.color.colorTextStatusBerhasil))
                }
            }

            tvStatusParent.text = riwayatPenjualan.status
            tvStatusChild.text = riwayatPenjualan.status
            tvUserChild.text = JefreeApp.sp.name
            tvParentKg.text = riwayatPenjualan.quantity+" Kg"
            tvParentPrice.text = currencyFormat(riwayatPenjualan.price!!.toDouble())
            tvChildKg.text = riwayatPenjualan.quantity+" Kg"
            tvChildPrice.text = currencyFormat(riwayatPenjualan.price.toDouble())
            tvChildAddress.text = riwayatPenjualan.address
//            tvParentDate.text = riwayatPenjualan.created_at!!.replace("-","/")
//            println("tanggal:" + getDelivery(riwayatPenjualan.created_at))
            if(riwayatPenjualan.note != null){
                tvChildNote.text = riwayatPenjualan.note
            }
        }

//        @SuppressLint("SimpleDateFormat")
//        private fun getDelivery(created_at: String?): Any? {
//            val date = Date()
//            val df = SimpleDateFormat("dd/MM/yyyy")

//            val date = LocalDate.parse(created_at, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
//            val df = SimpleDateFormat("MM/dd/yyyy hh:mm:ss.SSS")

//            return df.format(created_at)
//            return date
//        }


//        fun main() {
//            val text = "2011-03-10T11:54:30.207Z"
//            val parser = ISODateTimeFormat.dateTime()
//            val dt = parser.parseDateTime(text)
//
//            val formatter = DateTimeFormat.mediumDateTime()
//            println("tanggal baru : "+formatter.print(dt))
//        }
    }
}