package com.example.agrinet

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)

        val cartButton = findViewById<ImageView>(R.id.cartButton)
        val homeButton = findViewById<ImageView>(R.id.homeButton)
        val logoutButton = findViewById<ConstraintLayout>(R.id.logoutButton)

        cartButton.setOnClickListener{
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }

        homeButton.setOnClickListener{
            val intent = Intent( this, MainActivity::class.java)
            startActivity(intent)
        }

        logoutButton.setOnClickListener{
            val intent = Intent( this, LoginActivity::class.java)
            startActivity(intent)
        }

    }
}