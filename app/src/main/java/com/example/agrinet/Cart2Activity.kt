package com.example.agrinet

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class Cart2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cart2)

        val checkoutButton = findViewById<ConstraintLayout>(R.id.checkoutButton)


        checkoutButton.setOnClickListener{
            val intent = Intent( this, CheckOutActivity::class.java)
            startActivity(intent)
        }

    }
}