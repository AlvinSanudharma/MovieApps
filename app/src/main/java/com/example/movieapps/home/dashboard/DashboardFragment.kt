package com.example.movieapps.home.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.movieapps.DetailActivity
import com.example.movieapps.R
import com.example.movieapps.model.Film
import com.example.movieapps.utils.Preferences
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_dashboard.iv_profile
import kotlinx.android.synthetic.main.fragment_dashboard.rv_coming_soon
import kotlinx.android.synthetic.main.fragment_dashboard.rv_now_playing
import kotlinx.android.synthetic.main.fragment_dashboard.tv_nama
import kotlinx.android.synthetic.main.fragment_dashboard.tv_saldo
import java.text.NumberFormat
import java.util.Locale

/**
 * A simple [Fragment] subclass.
 */
class DashboardFragment : Fragment() {
    private lateinit var preferences: Preferences;
    private lateinit var  mDatabase: DatabaseReference;

    private var dataList = ArrayList<Film>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        preferences = Preferences(requireActivity().applicationContext)
        mDatabase = FirebaseDatabase.getInstance().getReference("Film")

        tv_nama.setText(preferences.getValues("nama"))

        if (!preferences.getValues("saldo").equals("")) {
             currency(preferences.getValues("saldo")!!.toDouble(), tv_saldo)
        }

        Glide.with(this)
            .load(preferences.getValues("url"))
            .apply(RequestOptions.circleCropTransform())
            .into(iv_profile)

        rv_now_playing.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rv_coming_soon.layoutManager = LinearLayoutManager(context)

        getData()
    }

    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataList.clear()

                for (getDataSnapshot in dataSnapshot.children) {
                    var film = getDataSnapshot.getValue(Film::class.java)
                    dataList.add(film!!)
                }

                rv_now_playing.adapter = NowPlayingAdapter(dataList) {
                    var intent = Intent(context, DetailActivity::class.java).putExtra("data", it)
                    startActivity(intent)
                }

                rv_coming_soon.adapter = CoomingSoonAdapter(dataList) {
                    var intent = Intent(context, DetailActivity::class.java).putExtra("data", it)
                    startActivity(intent)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(context, "" + databaseError.message, Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun currency(harga: Double, textView: TextView) {
        val localID = Locale("in", "ID")
        val format = NumberFormat.getCurrencyInstance(localID)

        textView.setText(format.format(harga))
    }
}