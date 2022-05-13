package com.example.movieapps.sign.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.movieapps.R
import com.example.movieapps.sign.signin.User
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    lateinit var sUsername: String
    lateinit var sPassword: String
    lateinit var sName: String
    lateinit var sEmail: String

    lateinit var mDatabaseReference: DatabaseReference
    lateinit var mDatabaseInstance: FirebaseDatabase
    lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        mDatabaseInstance = FirebaseDatabase.getInstance()
        mDatabase = FirebaseDatabase.getInstance().getReference()
        mDatabaseReference = mDatabaseInstance.getReference("User")

        btn_lanjutkan.setOnClickListener {
            sUsername = et_username.text.toString()
            sPassword = et_password.text.toString()
            sName = et_name.text.toString()
            sEmail = et_email.text.toString()

            if (sUsername.equals("")) {
                et_username.error = "Silahkan isi username!"
                et_username.requestFocus()
            } else if (sPassword.equals("")) {
                et_password.error = "Silahkan isi password!"
                et_password.requestFocus()
            } else if (sName.equals("")) {
                et_name.error = "Silahkan isi nama"
                et_name.requestFocus()
            } else if (sEmail.equals("")) {
                et_email.error = "Silahkan isi email"
                et_email.requestFocus()
            } else {
                saveUsername(sUsername, sPassword, sName, sEmail)
            }
        }
    }

    private fun saveUsername(sUsername: String, sPassword: String, sName: String, sEmail: String) {
        var user = User()

        user.nama = sName
        user.password = sPassword
        user.email = sEmail
        user.username = sUsername

        if (sUsername !== null) {
            checkingUsername(sUsername, user)
        }
    }

    private fun checkingUsername(sUsername: String, data: User) {
        mDatabaseReference.child(sUsername).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshop: DataSnapshot) {
                var user = dataSnapshop.getValue(User::class.java)

                if (user == null) {
                    mDatabaseReference.child(sUsername).setValue(data)

                    var goSignUpPhotoScreen = Intent(this@SignUpActivity,
                        SignUpPhotoActivity::class.java).putExtra("nama", data.nama)

                    startActivity(goSignUpPhotoScreen)
                } else {
                    Toast.makeText(this@SignUpActivity, "User telah terdaftar",
                        Toast.LENGTH_LONG).show()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@SignUpActivity, ""+databaseError.message, Toast.LENGTH_LONG).show()
            }

        })
    }

}