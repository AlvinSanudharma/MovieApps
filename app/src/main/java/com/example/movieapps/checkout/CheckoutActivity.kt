package com.example.movieapps.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapps.R
import com.example.movieapps.model.Checkout
import com.example.movieapps.model.Film
import com.example.movieapps.utils.Preferences
import kotlinx.android.synthetic.main.activity_checkout.btn_tiket
import kotlinx.android.synthetic.main.activity_checkout.rc_checkout
import kotlinx.android.synthetic.main.activity_pilih_bangku.a3
import kotlinx.android.synthetic.main.activity_pilih_bangku.a4
import kotlinx.android.synthetic.main.activity_pilih_bangku.btn_home
import kotlinx.android.synthetic.main.activity_pilih_bangku.tv_kursi

class CheckoutActivity : AppCompatActivity() {
    private var dataList = ArrayList<Checkout>()
    private var total: Int = 0
    private  lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        preferences = Preferences(this)
        dataList = intent.getSerializableExtra("data") as ArrayList<Checkout>

        for (a in dataList.indices) {
            total += dataList[a].harga!!.toInt()
        }

        dataList.add(Checkout("Total harus dibayar", total.toString()))

        rc_checkout.layoutManager = LinearLayoutManager(this)
        rc_checkout.adapter = CheckoutAdapter(dataList) {

        }

        btn_tiket.setOnClickListener{
            var intent = Intent(this, CheckoutSuccessActivity::class.java)

            startActivity(intent)
        }
     }
}