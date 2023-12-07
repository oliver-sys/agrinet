package com.example.agrinet

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

import com.google.firebase.database.FirebaseDatabase

class ShopActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shop)

        val homeButton = findViewById<ImageView>(R.id.homeButton)
        val sellerProduct1 = findViewById<ConstraintLayout>(R.id.sellerProduct1)
        val sellerProduct2 = findViewById<ConstraintLayout>(R.id.sellerProduct2)
        val sellerProduct3 = findViewById<ConstraintLayout>(R.id.sellerProduct3)
        val sellerProduct4 = findViewById<ConstraintLayout>(R.id.sellerProduct4)
        val sellerProduct5 = findViewById<ConstraintLayout>(R.id.sellerProduct5)

        val sellerName1 = findViewById<TextView>(R.id.sellerName1)
        val sellerName2 = findViewById<TextView>(R.id.sellerName2)
        val sellerName3 = findViewById<TextView>(R.id.sellerName3)
        val sellerName4 = findViewById<TextView>(R.id.sellerName4)
        val sellerName5 = findViewById<TextView>(R.id.sellerName5)
        val address1 = findViewById<TextView>(R.id.address1)
        val address2 = findViewById<TextView>(R.id.address2)
        val address3 = findViewById<TextView>(R.id.address3)
        val address4 = findViewById<TextView>(R.id.address4)
        val address5 = findViewById<TextView>(R.id.address5)

        // Initialize Firebase
        val database = FirebaseDatabase.getInstance()
        val databaseRef = database.reference

        // Assuming your sellers' data is stored in a node named "sellers"
        val sellersRef = databaseRef.child("sellers")

        // Fetch data for each seller
        for (i in 1..5) {
            val currentSellerRef = sellersRef.child("seller$i")

            currentSellerRef.get().addOnSuccessListener { snapshot ->
                if (snapshot.exists()) {
                    val sellerName = snapshot.child("name").getValue(String::class.java)
                    val sellerAddress = snapshot.child("address").getValue(String::class.java)

                    // Assign data to TextViews
                    when (i) {
                        1 -> {
                            sellerName1.text = sellerName
                            address1.text = sellerAddress
                        }
                        2 -> {
                            sellerName2.text = sellerName
                            address2.text = sellerAddress
                        }
                        3 -> {
                            sellerName3.text = sellerName
                            address3.text = sellerAddress
                        }
                        4 -> {
                            sellerName4.text = sellerName
                            address4.text = sellerAddress
                        }
                        5 -> {
                            sellerName5.text = sellerName
                            address5.text = sellerAddress
                        }
                    }
                } else {
                    //
                }
            }
        }

        homeButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        sellerProduct1.setOnClickListener {
            val intent = Intent(this, ProductActivity::class.java)
            startActivity(intent)

        }
        sellerProduct2.setOnClickListener {
            val intent = Intent(this, ProductActivity::class.java)
            startActivity(intent)

        }
        sellerProduct3.setOnClickListener {
            val intent = Intent(this, ProductActivity::class.java)
            startActivity(intent)

        }
        sellerProduct4.setOnClickListener {
            val intent = Intent(this, ProductActivity::class.java)
            startActivity(intent)

        }
        sellerProduct5.setOnClickListener {
            val intent = Intent(this, ProductActivity::class.java)
            startActivity(intent)

        }

    }
}
