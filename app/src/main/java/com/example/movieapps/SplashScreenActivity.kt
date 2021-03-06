package com.example.movieapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.movieapps.onboarding.OnBoardingOneActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        var handler = Handler(Looper.getMainLooper())

        handler.postDelayed({
            var intent = Intent(this@SplashScreenActivity, OnBoardingOneActivity::class.java)
            startActivity(intent)

            finish();
        }, 3000)
    }
}