package com.example.movieapps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.movieapps.model.Film
import kotlinx.android.synthetic.main.activity_detail.iv_poster
import kotlinx.android.synthetic.main.activity_detail.tv_desc
import kotlinx.android.synthetic.main.activity_detail.tv_genre
import kotlinx.android.synthetic.main.activity_detail.tv_kursi
import kotlinx.android.synthetic.main.activity_detail.tv_rate

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val data = intent.getParcelableExtra<Film>("data")

        tv_kursi.text = data!!.judul
        tv_genre.text = data!!.genre
        tv_desc.text = data!!.desc
        tv_rate.text = data!!.rating

        Glide.with(this)
            .load(data.poster)
            .into(iv_poster)
    }
}