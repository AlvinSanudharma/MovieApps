package com.example.movieapps.sign.signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.movieapps.home.HomeActivity
import com.example.movieapps.R
import com.example.movieapps.sign.signup.SignUpActivity
import com.example.movieapps.utils.Preferences
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_sign_in.*


class SignInActivity : AppCompatActivity() {
    lateinit var iUsername: String
    lateinit var iPassword: String

    lateinit var mDatabase: DatabaseReference
    lateinit var preference: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        mDatabase = FirebaseDatabase.getInstance().getReference("User")
        preference = Preferences(this)

        preference.setValues("onboarding", "1")

        if (preference.getValues("status").equals("1")) {
            finishAffinity()

            var goHome = Intent(this@SignInActivity, HomeActivity::class.java)
            startActivity(goHome)
        }

        btn_home.setOnClickListener {
            iUsername = et_username.text.toString()
            iPassword = et_password.text.toString()

            if(iUsername.equals("")) {
                et_username.error = "Silahkan tulis username Anda!"
                et_username.requestFocus()
            } else if(iPassword.equals("")) {
                et_password.error = "Silahkan tulis password Anda!"
                et_password.requestFocus()
            } else {
                pushLogin(iUsername, iPassword)
            }
        }

        btn_daftar.setOnClickListener {
            var intent = Intent(this@SignInActivity, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun pushLogin(iUsername: String, iPassword: String) {
        mDatabase.child(iUsername).addValueEventListener(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var user = dataSnapshot.getValue(User::class.java)

                if (user == null) {
                    Toast.makeText(this@SignInActivity, "Username tidak ditemukan",
                        Toast.LENGTH_LONG).show()
                } else {
                    if (user.password.equals(iPassword)) {
                        preference.setValues("nama", user.nama.toString())
                        preference.setValues("username", user.username.toString())
                        preference.setValues("url", user.url.toString())
                        preference.setValues("email", user.email.toString())
                        preference.setValues("saldo", user.saldo.toString())
                        preference.setValues("status", "1")

                        val intent = Intent(this@SignInActivity, HomeActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@SignInActivity, "Password yang anda masukan salah",
                            Toast.LENGTH_LONG).show()
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@SignInActivity, databaseError.message,
                    Toast.LENGTH_LONG).show()
            }
        })
    }
}