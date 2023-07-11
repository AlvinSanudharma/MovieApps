package com.example.movieapps.home.tiket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.movieapps.R
import com.example.movieapps.model.Checkout
import com.example.movieapps.model.Film
import kotlinx.android.synthetic.main.activity_detail.iv_poster
import kotlinx.android.synthetic.main.activity_tiket.iv_poster_image
import kotlinx.android.synthetic.main.activity_tiket.rv_checkout
import kotlinx.android.synthetic.main.activity_tiket.tv_genre
import kotlinx.android.synthetic.main.activity_tiket.tv_rate
import kotlinx.android.synthetic.main.activity_tiket.tv_title

class TiketActivity : AppCompatActivity() {
    private var dataList = ArrayList<Checkout>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tiket)

        var data = intent.getParcelableExtra<Film>("data")

        tv_title.text = data!!.judul
        tv_genre.text = data!!.genre
        tv_rate.text = data!!.rating

        Glide.with(this)
            .load(data!!.poster)
            .into(iv_poster_image)

        rv_checkout.layoutManager = LinearLayoutManager(this)

        dataList.add(Checkout("C1", ""))
        dataList.add(Checkout("C2", ""))

        rv_checkout.adapter = TiketAdapter(dataList) {

        }
    }
}