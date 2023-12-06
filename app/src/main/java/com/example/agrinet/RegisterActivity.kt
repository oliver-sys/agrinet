package com.example.agrinet

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import android.content.Intent
import android.widget.EditText
import android.widget.TextView

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase


class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var editEmail: EditText
    private lateinit var editPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)

        // Initialize Firebase Authentication
        auth = FirebaseAuth.getInstance()
        val databaseUrl = "https://agrinet-29fd3-default-rtdb.asia-southeast1.firebasedatabase.app"
        FirebaseDatabase.getInstance(databaseUrl).setPersistenceEnabled(true)

        // Initialize EditTexts
        editEmail = findViewById(R.id.editEmail)
        editPassword = findViewById(R.id.editPassword)

        val loginButton = findViewById<TextView>(R.id.loginButton)
        loginButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }



        // Initialize Button
        val registerButton = findViewById<ConstraintLayout>(R.id.registerButton)
        registerButton.setOnClickListener {
            val email = editEmail.text?.toString()
            val password = editPassword.text?.toString()

            if (!email.isNullOrBlank() && !password.isNullOrBlank()) {
                // Call function to register user with email and password
                registerUser(email, password)
            } else {
                // Handle empty email or password
                Toast.makeText(this, "Email and password cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }

        // Rest of your onCreate logic...
    }

    private fun registerUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Registration successful
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // Registration failed
                    Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            // User is signed in
            // Redirect to the main activity, home screen, or perform any other action
            addUserToDatabase(user.email)
            // Example: Start a new activity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            // Finish the current activity to prevent going back to the registration screen
            finish()
        } else {
            // User registration failed or user is not signed in
            // You can update the UI to display an error message or take appropriate actions
            // Example: Display an error message in a TextView
            Toast.makeText(this, "User registration failed. Please try again.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun addUserToDatabase(email: String?) {
        // Check if the email is not null
        if (email != null) {
            // Get a reference to the database
            val database = FirebaseDatabase.getInstance().reference

            // Get the UID of the currently authenticated user
            val userId = FirebaseAuth.getInstance().currentUser?.uid ?: ""

            // Set the user's email under the "users" node with the generated user ID
            database.child("users").child(userId).child("email").setValue(email)
        }
    }


}
