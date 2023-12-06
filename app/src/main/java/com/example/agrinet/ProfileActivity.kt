package com.example.agrinet

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.firebase.auth.FirebaseAuth

class ProfileActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)

        // Initialize Firebase Authentication
        auth = FirebaseAuth.getInstance()

        val cartButton = findViewById<ImageView>(R.id.cartButton)
        val homeButton = findViewById<ImageView>(R.id.homeButton)
        val logoutButton = findViewById<ConstraintLayout>(R.id.logoutButton)
        val userNameTextView = findViewById<TextView>(R.id.userName)

        // Set the email of the currently logged-in user to the userName TextView
        val currentUser = auth.currentUser
        currentUser?.let {
            val userEmail = currentUser.email
            userNameTextView.text = userEmail
        }

        cartButton.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }

        homeButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        logoutButton.setOnClickListener {
            // Sign out the user
            auth.signOut()

            // Redirect to the login page after logging out
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            // Finish the current activity to prevent going back to the profile screen
            finish()
        }
    }
}
