package com.example.movieapps.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movieapps.R
import com.example.movieapps.sign.signin.SignInActivity
import kotlinx.android.synthetic.main.activity_on_boarding_three.*

class OnBoardingThreeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding_three)

        btn_home.setOnClickListener {
            finishAffinity()

            var intent = Intent(this@OnBoardingThreeActivity, SignInActivity::class.java)
            startActivity(intent)
        }
    }
}