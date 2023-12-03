package com.example.agrinet

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class ShopActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shop)

        val homeButton = findViewById<ImageView>(R.id.homeButton)
        val productButton = findViewById<ConstraintLayout>(R.id.shopButton2)

        homeButton.setOnClickListener{
            val intent = Intent( this, MainActivity::class.java)
            startActivity(intent)
        }

        productButton.setOnClickListener{
            val intent = Intent ( this, ProductActivity::class.java)
            startActivity(intent)
        }

    }
}
