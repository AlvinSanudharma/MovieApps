package com.example.movieapps.sign

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movieapps.R
import kotlinx.android.synthetic.main.activity_sign_in.*
import com.google.firebase.database.DatabaseReference

import com.google.firebase.database.FirebaseDatabase




class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        button2.setOnClickListener {
            // Write a message to the database
            // Write a message to the database
            val database = FirebaseDatabase.getInstance("https://movie-apps-23990-default-rtdb.firebaseio.com/")
            val myRef = database.getReference("message")

            myRef.setValue("Hello, World!")
        }
    }
}