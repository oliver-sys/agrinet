package com.example.agrinet

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class CheckOutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.check_out)

        val backButton = findViewById<ImageView>(R.id.backButton)
        backButton.setOnClickListener{
            val intent = Intent( this, CartActivity::class.java)
            startActivity(intent)
        }

    }
}