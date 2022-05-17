package com.example.movieapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.movieapps.onboarding.OnBoardingOneActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        var handler = Handler()

        /*
        Menahan activity selama 5 detik, setelah 5 detik
        jalankan intent perpindahan ke halaman on boarding
         */
        handler.postDelayed({
            var intent = Intent(this@SplashScreenActivity, OnBoardingOneActivity::class.java)
            startActivity(intent)

            finish();
        }, 4000)
    }
}