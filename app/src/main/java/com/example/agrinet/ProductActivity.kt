package com.example.agrinet

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class ProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product)

        val backButton = findViewById<ImageView>(R.id.backButton)

        backButton.setOnClickListener{
            val intent = Intent( this, ShopActivity::class.java)
            startActivity(intent)

        }
    }
}
