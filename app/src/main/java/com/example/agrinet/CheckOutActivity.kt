package com.example.agrinet

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlin.properties.Delegates


class CheckOutActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var databaseRef: DatabaseReference
    private var subTotal: TextView by Delegates.notNull()
    private var Total: TextView by Delegates.notNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.check_out)
        val checkoutButton = findViewById<ConstraintLayout>(R.id.checkoutButton)
        val backButton = findViewById<ImageView>(R.id.backButton)
        subTotal = findViewById<TextView>(R.id.subTotal)
        Total = findViewById<TextView>(R.id.Total)

        val productNames = arrayOf(
            findViewById<TextView>(R.id.pName1),
            findViewById<TextView>(R.id.pName2),
            findViewById<TextView>(R.id.pName3),
            findViewById<TextView>(R.id.pName4),
            findViewById<TextView>(R.id.pName5),
            findViewById<TextView>(R.id.pName6),
            findViewById<TextView>(R.id.pName7)
        )

        val quantities = arrayOf(
            findViewById<TextView>(R.id.qText1),
            findViewById<TextView>(R.id.qText2),
            findViewById<TextView>(R.id.qText3),
            findViewById<TextView>(R.id.qText4),
            findViewById<TextView>(R.id.qText5),
            findViewById<TextView>(R.id.qText6),
            findViewById<TextView>(R.id.qText7)
        )

        val prices = arrayOf(
            findViewById<TextView>(R.id.pText1),
            findViewById<TextView>(R.id.pText2),
            findViewById<TextView>(R.id.pText3),
            findViewById<TextView>(R.id.pText4),
            findViewById<TextView>(R.id.pText5),
            findViewById<TextView>(R.id.pText6),
            findViewById<TextView>(R.id.pText7)
        )

        auth = FirebaseAuth.getInstance()
        // Assuming your cart data is stored under the user's ID in the database
        val currentUser = auth.currentUser
        if (currentUser == null) {

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
            return
        }

        val userId = currentUser.uid
        databaseRef = FirebaseDatabase.getInstance().reference.child("users").child(userId).child("cart")

        // Fetch cart items from the database
        databaseRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var index = 0
                var subtotal = 0.0  // Initialize subtotal variable

                for (cartSnapshot in dataSnapshot.children) {
                    if (index < productNames.size) {
                        // Assuming your cart item structure in the database
                        val productName = cartSnapshot.child("name").getValue(String::class.java)
                        val quantity = cartSnapshot.child("quantity").getValue(Int::class.java)
                        val pricePerItem = cartSnapshot.child("price").getValue(Double::class.java)

                        // Calculate total price
                        val totalPrice = quantity?.times(pricePerItem ?: 0.0) ?: 0.0
                        subtotal += totalPrice  // Accumulate total price to calculate subtotal

                        // Update the corresponding views
                        productNames[index].text = productName
                        quantities[index].text = "$quantity kg"
                        prices[index].text = "P${totalPrice}"

                        index++
                    } else {
                        // Break the loop if we have processed all available views
                        break
                    }
                }

                // Update subtotal TextView after processing all cart items
                subTotal.text = "P${subtotal}"
                Total.text = "P${subtotal + 200}"
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle error
            }
        })


        backButton.setOnClickListener{
            finish()
        }



        checkoutButton.setOnClickListener {
            // Build the alert dialog
            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder.setTitle("Order Placed!")
            alertDialogBuilder.setMessage("Thank you for your order.")

            // Set positive button (OK button) click listener
            alertDialogBuilder.setPositiveButton("OK") { _, _ ->
                // Handle the "OK" button click
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

                // Reset the user's cart in the database
                resetUserCart()

                finish()
            }

            // Create and show the alert dialog
            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }


    }
    private fun resetUserCart() {
        val currentUser = auth.currentUser
        if (currentUser == null) {

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
            return
        }

        val userId = currentUser.uid
        databaseRef = FirebaseDatabase.getInstance().reference.child("users").child(userId).child("cart")
        // Remove all cart items for the user
        databaseRef.removeValue()
    }
}