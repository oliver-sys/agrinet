package com.example.agrinet

import android.content.Intent

import android.app.AlertDialog
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ProductActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var databaseRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product)

        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        if (currentUser == null) {

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
            return
        }

        val userId = currentUser.uid
        databaseRef = FirebaseDatabase.getInstance().reference.child("users").child(userId).child("cart")

        val cartButton = findViewById<ImageView>(R.id.cartButton)
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
                        val productPrice =
                            productSnapshot.child("pricePerKilo").getValue(Double::class.java)

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

        // Set up onClickListener for each product button
        val productButtons = listOf(
            productButton1, productButton2, productButton3, productButton4, productButton5,
            productButton6, productButton7, productButton8, productButton9, productButton10
        )

        for ((index, button) in productButtons.withIndex()) {
            button.setOnClickListener {
                // Fetch the corresponding product information based on the index
                val productName = when (index) {
                    0 -> product1.text.toString()
                    1 -> product2.text.toString()
                    2 -> product3.text.toString()
                    3 -> product4.text.toString()
                    4 -> product5.text.toString()
                    5 -> product6.text.toString()
                    6 -> product7.text.toString()
                    7 -> product8.text.toString()
                    8 -> product9.text.toString()
                    9 -> product10.text.toString()
                    else -> "" // Handle other cases if needed
                }

                val productPrice = when (index) {
                    0 -> productPrice1.text.toString()
                    1 -> productPrice2.text.toString()
                    2 -> productPrice3.text.toString()
                    3 -> productPrice4.text.toString()
                    4 -> productPrice5.text.toString()
                    5 -> productPrice6.text.toString()
                    6 -> productPrice7.text.toString()
                    7 -> productPrice8.text.toString()
                    8 -> productPrice9.text.toString()
                    9 -> productPrice10.text.toString()
                    else -> "" // Handle other cases if needed
                }

                // Build the alert dialog
                val alertDialogBuilder = AlertDialog.Builder(this)
                alertDialogBuilder.setTitle("Add to Cart")
                alertDialogBuilder.setMessage("Add $productName to your cart for P$productPrice?")

                // Set up the layout for quantity selection
                val layout = LinearLayout(this)
                layout.orientation = LinearLayout.VERTICAL
                layout.setPadding(16, 16, 16, 16)

                val quantityTextView = TextView(this)
                quantityTextView.text = "Quantity: 1"
                quantityTextView.textSize = 18f
                layout.addView(quantityTextView)

                // Set up plus and minus buttons
                val minusButton = TextView(this)
                minusButton.text = "-"
                minusButton.textSize = 24f
                minusButton.setTextColor(resources.getColor(android.R.color.black))
                minusButton.background = resources.getDrawable(android.R.drawable.btn_default)
                layout.addView(minusButton)

                val plusButton = TextView(this)
                plusButton.text = "+"
                plusButton.textSize = 24f
                plusButton.setTextColor(resources.getColor(android.R.color.black))
                plusButton.background = resources.getDrawable(android.R.drawable.btn_default)
                layout.addView(plusButton)

                // Set up the click listeners for plus and minus buttons
                var quantity = 1 // Initial quantity
                minusButton.setOnClickListener {
                    if (quantity > 1) {
                        quantity--
                        quantityTextView.text = "Quantity: $quantity"
                    }
                }

                plusButton.setOnClickListener {
                    quantity++
                    quantityTextView.text = "Quantity: $quantity"
                }

                alertDialogBuilder.setView(layout)

                // Set positive button (OK button) click listener
                alertDialogBuilder.setPositiveButton("Add") { _, _ ->
                    // Handle the "Add" button click
                    // Here, you can add the product to the user's cart with the selected quantity
                    // For now, let's show a Toast
                    addToCart(userId, productName, productPrice.toDouble(), quantity)
                }

                // Set negative button (Cancel button) click listener
                alertDialogBuilder.setNegativeButton("Cancel") { dialog, _ ->
                    // Handle the "Cancel" button click
                    dialog.dismiss()
                }

                // Create and show the alert dialog
                val alertDialog = alertDialogBuilder.create()
                alertDialog.show()
            }
        }

        backButton.setOnClickListener {
            // Navigate back to the ShopActivity
            finish()
        }

        cartButton.setOnClickListener {
            val intent = Intent(this, Cart2Activity::class.java)
            startActivity(intent)
        }
    }

    private fun addToCart(userId: String, productName: String, productPrice: Double, quantity: Int) {
        // Add the selected product to the user's cart in the Firebase Realtime Database
        val cartItemRef = databaseRef.child(productName)

        cartItemRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // If the product is already in the cart, update the quantity
                if (snapshot.exists()) {
                    val existingQuantity = snapshot.child("quantity").getValue(Int::class.java) ?: 0
                    cartItemRef.child("quantity").setValue(existingQuantity + quantity)
                } else {
                    // If the product is not in the cart, add it with the selected quantity
                    cartItemRef.child("name").setValue(productName)
                    cartItemRef.child("price").setValue(productPrice)
                    cartItemRef.child("quantity").setValue(quantity)
                }

                Toast.makeText(
                    this@ProductActivity,
                    "$productName added to cart with quantity $quantity",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
                Toast.makeText(
                    this@ProductActivity,
                    "Failed to add $productName to cart",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}
