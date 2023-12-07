package com.example.agrinet

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.*

class ProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product)

        val backButton = findViewById<ImageView>(R.id.backButton)
        val product1 = findViewById<TextView>(R.id.t1)
        val product2 = findViewById<TextView>(R.id.t2)
        val product3 = findViewById<TextView>(R.id.t3)
        val product4 = findViewById<TextView>(R.id.t4)
        val product5 = findViewById<TextView>(R.id.t5)
        val product6 = findViewById<TextView>(R.id.t6)
        val product7 = findViewById<TextView>(R.id.t7)
        val product8 = findViewById<TextView>(R.id.t8)
        val product9 = findViewById<TextView>(R.id.t9)
        val product10 = findViewById<TextView>(R.id.t10)

        val productPrice1 = findViewById<TextView>(R.id.p1)
        val productPrice2 = findViewById<TextView>(R.id.p2)
        val productPrice3 = findViewById<TextView>(R.id.p3)
        val productPrice4 = findViewById<TextView>(R.id.p4)
        val productPrice5 = findViewById<TextView>(R.id.p5)
        val productPrice6 = findViewById<TextView>(R.id.p6)
        val productPrice7 = findViewById<TextView>(R.id.p7)
        val productPrice8 = findViewById<TextView>(R.id.p8)
        val productPrice9 = findViewById<TextView>(R.id.p9)
        val productPrice10 = findViewById<TextView>(R.id.p10)

        val productButton1 = findViewById<ConstraintLayout>(R.id.pButton1)
        val productButton2 = findViewById<ConstraintLayout>(R.id.pButton2)
        val productButton3 = findViewById<ConstraintLayout>(R.id.pButton3)
        val productButton4 = findViewById<ConstraintLayout>(R.id.pButton4)
        val productButton5 = findViewById<ConstraintLayout>(R.id.pButton5)
        val productButton6 = findViewById<ConstraintLayout>(R.id.pButton6)
        val productButton7 = findViewById<ConstraintLayout>(R.id.pButton7)
        val productButton8 = findViewById<ConstraintLayout>(R.id.pButton8)
        val productButton9 = findViewById<ConstraintLayout>(R.id.pButton9)
        val productButton10 = findViewById<ConstraintLayout>(R.id.pButton10)

        // Initialize Firebase
        val database = FirebaseDatabase.getInstance()
        val databaseRef = database.reference.child("sellers")

        // Attach a ValueEventListener to fetch data from the sellers node
        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (sellerSnapshot in dataSnapshot.children) {
                    // Assuming the structure is sellers -> seller1 -> products -> product1, product2, ...
                    for (productSnapshot in sellerSnapshot.child("products").children) {
                        // Fetch product name and price
                        val productName = productSnapshot.child("name").getValue(String::class.java)
                        val productPrice = productSnapshot.child("pricePerKilo").getValue(Double::class.java)

                        // Determine which TextViews to update based on product position
                        // Assuming the products are sorted in the same order as your TextViews
                        when (productSnapshot.key) {
                            "product1" -> {
                                product1.text = productName
                                productPrice1.text = "$productPrice"
                            }
                            "product2" -> {
                                product2.text = productName
                                productPrice2.text = "$productPrice"
                            }
                            // Continue this pattern for the other products
                            "product3" -> {
                                product3.text = productName
                                productPrice3.text = "$productPrice"
                            }
                            "product4" -> {
                                product4.text = productName
                                productPrice4.text = "$productPrice"
                            }
                            "product5" -> {
                                product5.text = productName
                                productPrice5.text = "$productPrice"
                            }
                            "product6" -> {
                                product6.text = productName
                                productPrice6.text = "$productPrice"
                            }
                            "product7" -> {
                                product7.text = productName
                                productPrice7.text = "$productPrice"
                            }
                            "product8" -> {
                                product8.text = productName
                                productPrice8.text = "$productPrice"
                            }
                            "product9" -> {
                                product9.text = productName
                                productPrice9.text = "$productPrice"
                            }
                            "product10" -> {
                                product10.text = productName
                                productPrice10.text = "$productPrice"
                            }

                        }
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle error
            }
        }

        // Add the ValueEventListener to the database reference
        databaseRef.addValueEventListener(valueEventListener)

        backButton.setOnClickListener {
            // Navigate back to the ShopActivity
            finish()
        }
    }
}
