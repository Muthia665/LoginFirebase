package com.muzaaz.loginfirebase

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textregis.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener {
            val email = loginEmail.text.toString()
            val password = loginpassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this,"Please Insert Email and Password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (email == "muthia09876@gmail.com" && password == "muthia") {
                Toast.makeText(this,"Login Succes", Toast.LENGTH_SHORT).show()
            }
            val progressDialog = ProgressDialog(this, R.style.Theme_MaterialComponents_Light_Dialog)
            progressDialog.isIndeterminate = true
            progressDialog.setMessage("Login . . .")
            progressDialog.show()

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener {
                progressDialog.hide()
                loginEmail.setText("")
                loginpassword.setText("")

                if (!it.isSuccessful) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }else
                    Toast.makeText(this,"Successfully Login", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, Dashboard::class.java)
                    startActivity(intent)
                }
                .addOnCompleteListener {
                    Toast.makeText(this,"Email or Password Incorect", Toast.LENGTH_SHORT).show()
                }

        }
    }
}
