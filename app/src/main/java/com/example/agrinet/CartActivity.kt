package com.example.agrinet

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class CartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cart)

        val homeButton = findViewById<ImageView>(R.id.homeButton)
        val profileButton = findViewById<ImageView>(R.id.profileButton)
        val checkoutButton = findViewById<ConstraintLayout>(R.id.checkoutButton)

        profileButton.setOnClickListener{
            val intent = Intent( this, ProfileActivity::class.java)
            startActivity(intent)
        }

        homeButton.setOnClickListener{
            val intent = Intent( this, MainActivity::class.java)
            startActivity(intent)
        }

        checkoutButton.setOnClickListener{
            val intent = Intent( this, CheckOutActivity::class.java)
            startActivity(intent)
        }

    }
}